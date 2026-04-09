package PixLab.application.usecases;

import PixLab.application.dto.PixTransferDto;
import PixLab.application.ports.PixTransferRepositoryPort;

public class GetTransferUseCase {
    private final PixTransferRepositoryPort pixTransferRepository;

    public GetTransferUseCase(PixTransferRepositoryPort pixTransferRepository) {
        this.pixTransferRepository = pixTransferRepository;
    }

    public PixTransferDto execute(String transferId) {
        return pixTransferRepository.findById(transferId)
                .map(PixMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("transferência não encontrada"));
    }
}
