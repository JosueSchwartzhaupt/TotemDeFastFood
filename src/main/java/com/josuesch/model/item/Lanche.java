package com.josuesch.model.item;

public class Lanche extends Item {

    private String descricao;

    public Lanche(String nome, double preco, String descricao) {
        super(nome, preco);
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("%s | %s",
                super.toString(),
                descricao);
    }
    
}
