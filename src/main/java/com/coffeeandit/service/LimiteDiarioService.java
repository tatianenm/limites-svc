package com.coffeeandit.service;

import com.coffeeandit.entity.LimiteDiario;
import com.coffeeandit.entity.TransactionDto;
import com.coffeeandit.events.LimiteProducer;
import com.coffeeandit.repository.LimiteDiarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class LimiteDiarioService {

    LimiteDiarioRepository limiteDiarioRepository;
    LimiteProducer limiteProducer;
    @Value("${limite.valor:0}")
    private BigDecimal valorDiario;

    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository, LimiteProducer limiteProducer) {
        this.limiteDiarioRepository = limiteDiarioRepository;
        this.limiteProducer = limiteProducer;
    }

    public Optional<LimiteDiario> buscarLimiteDiario(final Long agencia, final Long conta) {
        var limiteDiario = limiteDiarioRepository.findByAgenciaAndConta(agencia, conta);
        if (limiteDiario.isEmpty()) {
            var limite = new LimiteDiario();
            limite.setValor(valorDiario);
            limite.setConta(conta);
            limite.setAgencia(agencia);
            limite.setData(LocalDateTime.now());
            return Optional.of(limiteDiarioRepository.save(limite));
        }
        return limiteDiario;
    }

    public Optional<LimiteDiario> findById(Long id) {
        return limiteDiarioRepository.findById(id);
    }

    public void validarLimiteDiario(TransactionDto transactionDto){
        var limiteDiario = limiteDiarioRepository.findByAgenciaAndContaAndData(transactionDto.getConta().getCodigoAgencia(),
                transactionDto.getConta().getCodigoConta(), LocalDateTime.now());
        if(Objects.isNull(limiteDiario)) {
            limiteDiario = new LimiteDiario();
            salvaLimiteDiario(transactionDto, limiteDiario);
        }
        if(limiteDiario.getValor().compareTo(transactionDto.getValor()) < 0){
            transactionDto.suspeitaFraude();
            log.info("Transação excede valor diário:{}", transactionDto);
        } else if (limiteDiario.getValor().compareTo(BigDecimal.valueOf(100000L)) > 0) {
            transactionDto.analiseHumana();
            log.info("Transação está em análise humana: {}", transactionDto);
        }else {
            transactionDto.analisada();
            log.info("Transação está analisada");
            limiteDiario.setValor(limiteDiario.getValor().subtract(transactionDto.getValor()));
            limiteDiarioRepository.save(limiteDiario);
        }
        limiteProducer.enviar(transactionDto);

    }

    private void salvaLimiteDiario(TransactionDto transactionDto, LimiteDiario limiteDiario) {

            limiteDiario.setAgencia(transactionDto.getConta().getCodigoAgencia());
            limiteDiario.setConta(transactionDto.getConta().getCodigoConta());
            limiteDiario.setValor(valorDiario);
            limiteDiario.setData(transactionDto.getData());
            limiteDiario = limiteDiarioRepository.save(limiteDiario);

    }
}
