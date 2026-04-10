package PixLab.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class PixTransferTest {

    @Test
    void shouldCreateTransferWithValidData() {
        PixKey origem = new PixKey("key-1", PixKeyType.CPF, "12345678900", "Alice");
        PixKey destino = new PixKey("key-2", PixKeyType.EMAIL, "bob@email.com", "Bob");

        PixTransfer transfer = new PixTransfer(
                "trx-1",
                origem,
                destino,
                BigDecimal.valueOf(100),
                PixTransferStatus.CREATED,
                Instant.parse("2026-01-01T10:00:00Z")
        );

        assertEquals("trx-1", transfer.getId());
        assertEquals(PixTransferStatus.CREATED, transfer.getStatus());
        assertEquals(BigDecimal.valueOf(100), transfer.getValor());
    }

    @Test
    void shouldRejectTransferWhenValueIsNotGreaterThanZero() {
        PixKey origem = new PixKey("key-1", PixKeyType.CPF, "12345678900", "Alice");
        PixKey destino = new PixKey("key-2", PixKeyType.EMAIL, "bob@email.com", "Bob");

        assertThrows(
                IllegalArgumentException.class,
                () -> new PixTransfer(
                        "trx-1",
                        origem,
                        destino,
                        BigDecimal.ZERO,
                        PixTransferStatus.CREATED,
                        Instant.now()
                )
        );
    }

    @Test
    void shouldRejectTransferWhenOriginAndDestinationAreTheSame() {
        PixKey origem = new PixKey("key-1", PixKeyType.CPF, "12345678900", "Alice");
        PixKey destino = new PixKey("key-1", PixKeyType.PHONE, "+5511999999999", "Alice");

        assertThrows(
                IllegalArgumentException.class,
                () -> new PixTransfer(
                        "trx-1",
                        origem,
                        destino,
                        BigDecimal.ONE,
                        PixTransferStatus.CREATED,
                        Instant.now()
                )
        );
    }
}
