package PixLab.domain.pixkey;

import PixLab.domain.pixTransfer.PixTransfer;

public class PixKey {
    private final String id;
    private final PixKeyType tipo;
    private final String valor;
    private final String titular;

    public PixKey(String id, PixKeyType tipo, String valor, String titular){
        this.id = requireText(id,"id");
        this.tipo = tipo;
        this.valor = valor;
        this.titular = titular;
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

    public static String requireText(String value, String field){
        if (value == null || value.isBlank()){
            throw  new IllegalArgumentException(field + "Não pode ser vazio");
        }
        return value;
    }
}
