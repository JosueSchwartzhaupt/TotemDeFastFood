package com.josuesch.model.enums;

public enum Tamanho {
    PEQUENO,
    MEDIO,
    GRANDE;

    @Override
    public String toString() {
        return switch (this) {
            case PEQUENO -> "Pequeno";
            case MEDIO -> "Médio";
            case GRANDE -> "Grande";
        };
    }
}
