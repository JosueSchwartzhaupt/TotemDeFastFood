package com.josuesch.model.item;

public class Lanche extends Item {

    private String descricao;

    public Lanche(String nome, double preco, String descricao) {
        super(nome, preco);
        this.descricao = descricao;
    }
    
}
