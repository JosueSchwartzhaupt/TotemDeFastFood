package com.josuesch.model.enums;

public enum Sabor {
    CHOCOLATE,
    BAUNILHA,
    MISTO;

    @Override
    public String toString() {
        return switch (this) {
            case CHOCOLATE -> "Chocolate";
            case BAUNILHA -> "Baunilha";
            case MISTO -> "Misto";
        };
    }
}
