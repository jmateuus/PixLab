package PixLab.application.ports;

import PixLab.domain.PixTransfer;

import java.util.Optional;

public interface PixTransferRepositoryPort {
    PixTransfer save(PixTransfer pixTransfer);

    Optional<PixTransfer> findById(String id);
}
