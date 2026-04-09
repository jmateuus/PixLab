package PixLab.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class PixTransfer {
    private final String id;
    private final PixKey chaveOrigem;
    private final PixKey chaveDestino;
    private final BigDecimal valor;
    private final PixTransferStatus status;
    private final Instant dataCriacao;

    public PixTransfer(
            String id,
            PixKey chaveOrigem,
            PixKey chaveDestino,
            BigDecimal valor,
            PixTransferStatus status,
            Instant dataCriacao
    ) {
        this.id = requireText(id, "id");
        this.chaveOrigem = Objects.requireNonNull(chaveOrigem, "chaveOrigem não pode ser nula");
        this.chaveDestino = Objects.requireNonNull(chaveDestino, "chaveDestino não pode ser nula");
        this.valor = Objects.requireNonNull(valor, "valor não pode ser nulo");
        this.status = Objects.requireNonNull(status, "status não pode ser nulo");
        this.dataCriacao = Objects.requireNonNull(dataCriacao, "dataCriacao não pode ser nula");

        validateBusinessRules();
    }

    private void validateBusinessRules() {
        if (valor.signum() <= 0) {
            throw new IllegalArgumentException("valor deve ser maior que zero");
        }

        if (chaveOrigem.getId().equals(chaveDestino.getId())) {
            throw new IllegalArgumentException("chave de origem deve ser diferente da chave de destino");
        }
    }

    public String getId() {
        return id;
    }

    public PixKey getChaveOrigem() {
        return chaveOrigem;
    }

    public PixKey getChaveDestino() {
        return chaveDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public PixTransferStatus getStatus() {
        return status;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public PixTransfer withStatus(PixTransferStatus newStatus) {
        return new PixTransfer(
                id,
                chaveOrigem,
                chaveDestino,
                valor,
                Objects.requireNonNull(newStatus, "status não pode ser nulo"),
                dataCriacao
        );
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " não pode ser vazio");
        }
        return value;
    }
}
