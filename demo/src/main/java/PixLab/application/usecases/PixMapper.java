package PixLab.application.usecases;

import PixLab.application.dto.PixKeyDto;
import PixLab.application.dto.PixTransferDto;
import PixLab.domain.PixKey;
import PixLab.domain.PixTransfer;

final class PixMapper {
    private PixMapper() {

    }

    static PixKeyDto toDto(PixKey pixKey) {
        return new PixKeyDto(
                pixKey.getId(),
                pixKey.getTipo(),
                pixKey.getValor(),
                pixKey.getTitular()
        );
    }

    static PixTransferDto toDto(PixTransfer transfer) {
        return new PixTransferDto(
                transfer.getId(),
                toDto(transfer.getChaveOrigem()),
                toDto(transfer.getChaveDestino()),
                transfer.getValor(),
                transfer.getStatus(),
                transfer.getDataCriacao()
        );
    }
}
