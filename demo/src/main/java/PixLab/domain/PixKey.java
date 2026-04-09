package PixLab.domain;

import java.util.Objects;

public class PixKey {
    private final String id;
    private final PixKeyType tipo;
    private final String valor;
    private final String titular;

    public PixKey(String id, PixKeyType tipo, String valor, String titular) {
        this.id = requireText(id, "id");
        this.tipo = Objects.requireNonNull(tipo, "tipo não pode ser nulo");
        this.valor = requireText(valor, "valor");
        this.titular = requireText(titular, "titular");
    }

    public String getId() {
        return id;
    }

    public PixKeyType getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public String getTitular() {
        return titular;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " não pode ser vazio");
        }
        return value;
    }
}
