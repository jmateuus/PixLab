package PixLab.application.dto;

import java.math.BigDecimal;

public record CreateTransferRequest(
        String id,
        String chaveOrigemId,
        String chaveDestinoId,
        BigDecimal valor
) {
}
