package com.josuesch.model.item;

import com.josuesch.model.enums.Sabor;

import java.util.Objects;

public class Sobremesa extends Item {

    private Sabor sabor;

    public Sobremesa(String nome, double preco, Sabor sabor) {
        super(nome, preco);
        this.sabor = sabor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sobremesa sobremesa = (Sobremesa) o;
        return sabor == sobremesa.sabor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sabor);
    }

    @Override
    public String toString() {
        return String.format("%s | Sabor: %s",
                super.toString(),
                sabor);
    }

}
