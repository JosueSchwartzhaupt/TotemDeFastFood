package com.josuesch;

import java.util.ArrayList;
import java.util.List;

public class Pedido implements Comparable<Pedido>{
    private final List<Item> itens = new ArrayList<>();
    private int numero;
    private String status;

    @Override
    public int compareTo(Pedido arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
