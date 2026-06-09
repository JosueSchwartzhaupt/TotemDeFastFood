package com.josuesch;

import javafx.scene.shape.QuadCurve;

public abstract class Item {
    private String nome;
    private double preco;
    private int quantidade;

    public Item(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
