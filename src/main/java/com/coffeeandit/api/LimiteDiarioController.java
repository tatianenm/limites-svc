package com.coffeeandit.api;

import com.coffeeandit.entity.LimiteDiario;
import com.coffeeandit.service.LimiteDiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/limite-diario")
public class LimiteDiarioController {

    LimiteDiarioService limiteDiarioService;

    public LimiteDiarioController(LimiteDiarioService limiteDiarioService) {
        this.limiteDiarioService = limiteDiarioService;
    }

    @GetMapping(value = "/{id}")
    public LimiteDiario findById(@PathVariable("id") Long id) {

        Optional<LimiteDiario> limiteDiarioOptional = limiteDiarioService.findById(id);

        if (limiteDiarioOptional.isPresent()) {
            return limiteDiarioOptional.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }

    @GetMapping(value = "/{agencia}/{conta}")
    public LimiteDiario buscarLimiteDiario(@PathVariable("agencia") final Long agencia, @PathVariable("conta") final Long conta) {
        var limiteDiario = limiteDiarioService.buscarLimiteDiario(agencia, conta);
        if (limiteDiario.isPresent()) {
            return limiteDiario.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
}
