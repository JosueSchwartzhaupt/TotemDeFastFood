package com.josuesch.model.item;

import com.josuesch.model.enums.Tamanho;

import java.util.Objects;

public class Bebida extends Item {

    private Tamanho tamanho;

    public Bebida(String nome, double preco, Tamanho tamanho) {
        super(nome, preco);
        this.tamanho = tamanho;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bebida bebida = (Bebida) o;
        return tamanho == bebida.tamanho;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tamanho);
    }

    @Override
    public String toString() {
        return super.toString() + " " + tamanho;
    }
}
