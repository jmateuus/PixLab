package PixLab.application.dto;

import PixLab.domain.PixKeyType;

public record PixKeyDto(
        String id,
        PixKeyType tipo,
        String valor,
        String titular
) {
}
