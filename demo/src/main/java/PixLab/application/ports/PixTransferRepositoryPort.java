package PixLab.application.ports;

import PixLab.domain.PixTransfer;
import java.util.Optional;

public interface PixTransferRepositoryPort {
    PixTransfer save(PixTransfer transfer);

    Optional<PixTransfer> findById(String id);
}
