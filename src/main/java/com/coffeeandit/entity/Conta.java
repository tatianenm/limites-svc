package com.coffeeandit.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class Conta implements Serializable {

    @Serial
    private static final long serialVersionUID = -6394829157326400210L;
    private Long codigoAgencia;
    private Long codigoConta;

}
