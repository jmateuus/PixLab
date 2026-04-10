package PixLab.application.usecases;


import PixLab.application.dto.CreateTransferRequest;
import PixLab.application.dto.PixTransferDto;
import PixLab.application.ports.PixKeyRepositoryPort;
import PixLab.application.ports.PixTransferRepositoryPort;
import PixLab.domain.PixKey;
import PixLab.domain.PixTransfer;
import PixLab.domain.PixTransferStatus;
import java.time.Instant;

public class CreateTransferUseCase {
    private final PixTransferRepositoryPort pixTransferRepository;
    private final PixKeyRepositoryPort pixKeyRepository;

    public CreateTransferUseCase(
            PixTransferRepositoryPort pixTransferRepository,
            PixKeyRepositoryPort pixKeyRepository
    ) {
        this.pixTransferRepository = pixTransferRepository;
        this.pixKeyRepository = pixKeyRepository;
    }

    public PixTransferDto execute(CreateTransferRequest request) {
        PixKey origem = pixKeyRepository.findById(request.chaveOrigemId())
                .orElseThrow(() -> new IllegalArgumentException("chaveOrigem não encontrada"));
        PixKey destino = pixKeyRepository.findById(request.chaveDestinoId())
                .orElseThrow(() -> new IllegalArgumentException("chaveDestino não encontrada"));

        PixTransfer transfer = new PixTransfer(
                request.id(),
                origem,
                destino,
                request.valor(),
                PixTransferStatus.CREATED,
                Instant.now()
        );

        PixTransfer saved = pixTransferRepository.save(transfer);
        return PixMapper.toDto(saved);
    }
}
