package PixLab.application.usecases;

import PixLab.application.dto.PixTransferDto;
import PixLab.application.ports.PixTransferRepositoryPort;
import PixLab.domain.PixTransfer;
import PixLab.domain.PixTransferStatus;

public class ConfirmTransferUseCase {
    private final PixTransferRepositoryPort pixTransferRepository;

    public ConfirmTransferUseCase(PixTransferRepositoryPort pixTransferRepository) {
        this.pixTransferRepository = pixTransferRepository;
    }

    public PixTransferDto execute(String transferId){
        PixTransfer transfer = pixTransferRepository.findById(transferId)
                .orElseThrow(() -> new IllegalArgumentException("Transferêcia não encontrada"));

        PixTransfer confirmed = transfer.withStatus(PixTransferStatus.CONFIRMED);
        PixTransfer saved = pixTransferRepository.save(confirmed);

        return PixMapper.toDto(saved);
    }
}
