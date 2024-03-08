package com.coffeeandit.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@EqualsAndHashCode(of = "uuid")
@ToString(of = {"uuid", "situacaoEnum"})
public class TransactionDto {

    private UUID uuid;
    private BigDecimal valor;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime data;
    private Conta conta;
    private BeneficiarioDto beneficiarioDto;
    private TipoTransacao tipoTransacao;
    private SituacaoEnum situacaoEnum;

    public void suspeitaFraude(){
        situacaoEnum = SituacaoEnum.EM_SUSPEITA_FRAUDE;
    }

    public void analiseHumana(){
        situacaoEnum = SituacaoEnum.EM_ANALISE_HUMANA;
    }

    public void analisada(){
        situacaoEnum = SituacaoEnum.ANALISADA;
    }
}
