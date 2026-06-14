package com.josuesch.model;

import com.josuesch.model.item.Item;

import java.util.*;

public class Pedido implements Comparable<Pedido>{

    public static final String NOVO = "A Preparar";
    public static final String PREPARANDO = "Preparando";
    public static final String PRONTO = "Pronto";

    private final Map<Item, Integer> itens = new HashMap<>();
    private int numero;
    private String status;

    public Pedido() {
        this.numero = -1;
        this.status = NOVO;
    }

    @Override
    public int compareTo(Pedido outroPedido) {
        return Integer.compare(this.numero, outroPedido.numero);
    }

    public void addItem(Item item) {
        itens.merge(item, 1 , Integer::sum);
    }

    public void removeItem(Item item) {
        itens.computeIfPresent(item, (i, qtd) -> qtd > 1 ? qtd - 1 : null);
    }

    public int getQuantidadeItem(Item item) {
        return itens.getOrDefault(item, 0);
    }

    public double getTotal() {
        return itens.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPreco() * entry.getValue())
                .sum();
    }

    public int getNumero() {
        return numero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public boolean isEmpty() {
        return itens.isEmpty();
    }
}
