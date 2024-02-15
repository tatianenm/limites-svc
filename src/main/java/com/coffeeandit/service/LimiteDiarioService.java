package com.coffeeandit.service;

import com.coffeeandit.entity.LimiteDiario;
import com.coffeeandit.repository.LimiteDiarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LimiteDiarioService {

    LimiteDiarioRepository limiteDiarioRepository;
    @Value()

    public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository) {
        this.limiteDiarioRepository = limiteDiarioRepository;
    }

    public Optional<LimiteDiario> inserirLimiteDiario(final Long agencia, final Long conta){
      final Optional<LimiteDiario> limiteDiarioOptional = limiteDiarioRepository.findByAgenciaAndConta(agencia, conta);
      if(limiteDiarioOptional.isPresent()){

      }
      return limiteDiarioOptional;
    }
    public Optional<LimiteDiario> findById(Long id){
        return limiteDiarioRepository.findById(id);
    }
}
