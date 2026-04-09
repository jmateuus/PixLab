package PixLab.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import PixLab.application.dto.CreatePixKeyRequest;
import PixLab.application.dto.CreateTransferRequest;
import PixLab.application.dto.PixKeyDto;
import PixLab.application.dto.PixTransferDto;
import PixLab.application.ports.PixKeyRepositoryPort;
import PixLab.application.ports.PixTransferRepositoryPort;
import PixLab.application.usecases.ConfirmTransferUseCase;
import PixLab.application.usecases.CreatePixKeyUseCase;
import PixLab.application.usecases.CreateTransferUseCase;
import PixLab.application.usecases.GetTransferUseCase;
import PixLab.domain.PixKey;
import PixLab.domain.PixKeyType;
import PixLab.domain.PixTransfer;
import PixLab.domain.PixTransferStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class UseCasesTest {

    @Test
    void shouldCreatePixKeyAndReturnDto() {
        InMemoryPixKeyRepository keyRepository = new InMemoryPixKeyRepository();
        CreatePixKeyUseCase useCase = new CreatePixKeyUseCase(keyRepository);

        PixKeyDto output = useCase.execute(new CreatePixKeyRequest(
                "key-1",
                PixKeyType.EMAIL,
                "ana@email.com",
                "Ana"
        ));

        assertEquals("key-1", output.id());
        assertEquals(PixKeyType.EMAIL, output.tipo());
        assertEquals("Ana", output.titular());
    }

    @Test
    void shouldCreateTransferUsingPortsAndReturnDto() {
        InMemoryPixKeyRepository keyRepository = new InMemoryPixKeyRepository();
        InMemoryPixTransferRepository transferRepository = new InMemoryPixTransferRepository();

        keyRepository.save(new PixKey("origem", PixKeyType.CPF, "12345678900", "Alice"));
        keyRepository.save(new PixKey("destino", PixKeyType.EMAIL, "bob@email.com", "Bob"));

        CreateTransferUseCase useCase = new CreateTransferUseCase(transferRepository, keyRepository);

        PixTransferDto output = useCase.execute(new CreateTransferRequest(
                "trx-1",
                "origem",
                "destino",
                BigDecimal.TEN
        ));

        assertEquals("trx-1", output.id());
        assertEquals(PixTransferStatus.CREATED, output.status());
        assertEquals(BigDecimal.TEN, output.valor());
    }

    @Test
    void shouldConfirmAndGetTransfer() {
        InMemoryPixTransferRepository transferRepository = new InMemoryPixTransferRepository();

        PixKey origem = new PixKey("origem", PixKeyType.CPF, "12345678900", "Alice");
        PixKey destino = new PixKey("destino", PixKeyType.EMAIL, "bob@email.com", "Bob");
        transferRepository.save(new PixTransfer(
                "trx-1",
                origem,
                destino,
                BigDecimal.ONE,
                PixTransferStatus.CREATED,
                Instant.now()
        ));

        ConfirmTransferUseCase confirmTransferUseCase = new ConfirmTransferUseCase(transferRepository);
        GetTransferUseCase getTransferUseCase = new GetTransferUseCase(transferRepository);

        PixTransferDto confirmed = confirmTransferUseCase.execute("trx-1");
        PixTransferDto found = getTransferUseCase.execute("trx-1");

        assertEquals(PixTransferStatus.CONFIRMED, confirmed.status());
        assertEquals(PixTransferStatus.CONFIRMED, found.status());
    }

    @Test
    void shouldFailWhenTransferNotFound() {
        GetTransferUseCase getTransferUseCase = new GetTransferUseCase(new InMemoryPixTransferRepository());

        assertThrows(IllegalArgumentException.class, () -> getTransferUseCase.execute("inexistente"));
    }

    private static class InMemoryPixKeyRepository implements PixKeyRepositoryPort {
        private final Map<String, PixKey> data = new HashMap<>();

        @Override
        public PixKey save(PixKey pixKey) {
            data.put(pixKey.getId(), pixKey);
            return pixKey;
        }

        @Override
        public Optional<PixKey> findById(String id) {
            return Optional.ofNullable(data.get(id));
        }
    }

    private static class InMemoryPixTransferRepository implements PixTransferRepositoryPort {
        private final Map<String, PixTransfer> data = new HashMap<>();

        @Override
        public PixTransfer save(PixTransfer transfer) {
            data.put(transfer.getId(), transfer);
            return transfer;
        }

        @Override
        public Optional<PixTransfer> findById(String id) {
            return Optional.ofNullable(data.get(id));
        }
    }
}
