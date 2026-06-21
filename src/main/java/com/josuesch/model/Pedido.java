package com.josuesch.model;

import com.josuesch.model.item.Item;

import java.util.*;
import java.util.stream.Collectors;

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

    public void addItem(Item item, int quantidade) {
    if (quantidade <= 0) {
        itens.remove(item);
    } else {
        itens.put(item, quantidade);
    }
    }

    public void removeItem(Item item) {
        itens.computeIfPresent(item, (i, qtd) -> qtd > 1 ? qtd - 1 : null);
    }

    public void zerarItem(Item item) {
        itens.remove(item);
    }

    public int getQuantidadeItem(Item item) {
        return itens.getOrDefault(item, 0);
    }

    public int getQuantidadeDeItens() {
        return itens.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
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

    public String getResumo() {
        return String.format(
                "Pedido #%03d | %2d %-5s | R$ %6.2f | %s",
                numero,
                getQuantidadeDeItens(),
                getQuantidadeDeItens() == 1 ? "item" : "itens",
                getTotal(),
                status
        );
    }

    public String getDetalhes() {
        return """
            ========================================
            PEDIDO #%03d
            ========================================
            Status: %s
            Quantidade de itens: %d
            Valor total: R$ %.2f

            ITENS:
            %s
            """.formatted(
                numero,
                status,
                getQuantidadeDeItens(),
                getTotal(),
                itens.entrySet().stream()
                        .map(item -> String.format(
                                "%3d x %s",
                                item.getValue(),
                                item.getKey()))
                        .collect(Collectors.joining("\n"))
        );
    }
}
