package PixLab.application.dto;

import PixLab.domain.PixTransferStatus;

import java.math.BigDecimal;
import java.time.Instant;

public record PixTransferDto(
        String id,
        PixKeyDto chaveOrigem,
        PixKeyDto chaveDestino,
        BigDecimal valor,
        PixTransferStatus status,
        Instant dataCriacao
) {
}
