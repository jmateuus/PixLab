package PixLab.domain.pixTransfer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

public class PixTransfer {
    private final String id;
    private final String chaveOrigem;
    private final String chaveDestino;
    private final PixTransferStatus status;
    private final BigDecimal valor;
    private final Instant dataCriacao;

    public PixTransfer(String id, String chaveOrigem, String chaveDestino, PixTransferStatus status, BigDecimal valor, Instant dataCriacao){
        this.id = requireText(id,"id");
        this.chaveOrigem = Objects.requireNonNull(chaveOrigem, "chaveOrigem não pode ser nula");
        this.chaveDestino = Objects.requireNonNull(chaveDestino, "chaveDestino não pode ser nula");
        this.valor = Objects.requireNonNull(valor, "valor não pode ser nulo");
        this.status = Objects.requireNonNull(status, "status não pode ser nulo");
        this.dataCriacao = Objects.requireNonNull(dataCriacao, "dataCriacao não pode ser nula");

        validateBusinessRules();
    }

    private void validateBusinessRules(){
        if (valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }
    }

    public String getId(){
        return id;
    }

    public String getChaveOrigem() {
        return chaveOrigem;
    }

    public String getChaveDestino() {
        return chaveDestino;
    }

    public PixTransferStatus getStatus() {
        return status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public static String requireText(String value, String field){
        if (value == null || value.isBlank()){
            throw new IllegalArgumentException(field+ "Não pode ser vazio");
        }
        return value;
    }
}
