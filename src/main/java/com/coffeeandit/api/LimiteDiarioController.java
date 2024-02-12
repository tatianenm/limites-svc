package com.coffeeandit.api;

import com.coffeeandit.entity.LimiteDiario;
import com.coffeeandit.service.LimiteDiarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@RestController
public class LimiteDiarioController {

    LimiteDiarioService limiteDiarioService;

    public LimiteDiarioController(LimiteDiarioService limiteDiarioService) {
        this.limiteDiarioService = limiteDiarioService;
    }

    @GetMapping(value = "/limite-diario/{id}")
    public LimiteDiario findById(@PathVariable("id") Long id) {

        Optional<LimiteDiario> limiteDiarioOptional = limiteDiarioService.findById(id);

        if (limiteDiarioOptional.isPresent()) {
            return limiteDiarioOptional.get();
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
    }
}
