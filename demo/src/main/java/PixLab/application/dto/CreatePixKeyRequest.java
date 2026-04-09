package PixLab.application.dto;

import PixLab.domain.PixKeyType;

public record CreatePixKeyRequest(
        String id,
        PixKeyType tipo,
        String valor,
        String titular
) {
}
