package com.coffeeandit.service;

import com.coffeeandit.entity.LimiteDiario;
import com.coffeeandit.repository.LimiteDiarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class LimiteDiarioService {

    LimiteDiarioRepository limiteDiarioRepository;
    @Value("${limite.valor:0}")
    private BigDecimal valorDiario;

    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository) {
        this.limiteDiarioRepository = limiteDiarioRepository;
    }

    public Optional<LimiteDiario> buscarLimiteDiario(final Long agencia, final Long conta) {
        var limiteDiario = limiteDiarioRepository.findByAgenciaAndConta(agencia, conta);
        if (limiteDiario.isEmpty()) {
            var limite = new LimiteDiario();
            limite.setValor(valorDiario);
            limite.setConta(conta);
            limite.setAgencia(agencia);
            return Optional.of(limiteDiarioRepository.save(limite));
        }
        return limiteDiario;
    }

    public Optional<LimiteDiario> findById(Long id) {
        return limiteDiarioRepository.findById(id);
    }
}
