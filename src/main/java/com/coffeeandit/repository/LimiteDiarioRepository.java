package com.coffeeandit.repository;

import com.coffeeandit.entity.LimiteDiario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LimiteDiarioRepository extends CrudRepository<LimiteDiario, Long> {

    Optional<LimiteDiario>  findByAgenciaAndConta(final Long agencia, final Long conta);

    LimiteDiario  findByAgenciaAndContaAndData(final Long agencia, final Long conta, final LocalDateTime data);
}
