package com.coffeeandit.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


@Table
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class LimiteDiario {

    @Id
    private Long id;

    private Long agencia;

    private Long conta;

    private BigDecimal valor;
}
