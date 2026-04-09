package PixLab.application.usecases;

import PixLab.application.dto.CreatePixKeyRequest;
import PixLab.application.dto.PixKeyDto;
import PixLab.application.ports.PixKeyRepositoryPort;
import PixLab.domain.PixKey;

public class CreatePixKeyUseCase {
    private final PixKeyRepositoryPort pixKeyRepository;

    public CreatePixKeyUseCase(PixKeyRepositoryPort pixKeyRepository) {
        this.pixKeyRepository = pixKeyRepository;
    }

    public PixKeyDto execute(CreatePixKeyRequest request) {
        PixKey pixKey = new PixKey(
                request.id(),
                request.tipo(),
                request.valor(),
                request.titular()
        );

        PixKey saved = pixKeyRepository.save(pixKey);
        return PixMapper.toDto(saved);
    }
}