package PixLab.application.ports;

import PixLab.domain.PixKey;

import java.util.Optional;

public interface PixKeyRepositoryPort {
    PixKey save(PixKey pixKey);

    Optional<PixKey> findById(String id);
}
