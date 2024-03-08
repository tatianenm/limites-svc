package com.coffeeandit.events;

import com.coffeeandit.entity.TransactionDto;
import com.coffeeandit.service.LimiteDiarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class LimiteConsumer {

    private LimiteDiarioService limiteDiarioService;
    private ObjectMapper objectMapper;

    public LimiteConsumer(LimiteDiarioService limiteDiarioService, ObjectMapper objectMapper) {
        this.limiteDiarioService = limiteDiarioService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${app.topic}")
    public void onConsume(final String message) {
        try {
            var transactionDto = getTransaction(message);
            limiteDiarioService.validarLimiteDiario(transactionDto);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private TransactionDto getTransaction(String message) throws IOException {
    var transactionDto = objectMapper.readValue(message, TransactionDto.class);
        transactionDto.setData(LocalDateTime.now());
        return transactionDto;
}


}
