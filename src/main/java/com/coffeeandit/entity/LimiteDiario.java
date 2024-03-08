package com.coffeeandit.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Table
@Entity
//@Data
@EqualsAndHashCode(of = "id")
public class LimiteDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long agencia;

    private Long conta;

    private BigDecimal valor;

    private LocalDateTime data;

}
