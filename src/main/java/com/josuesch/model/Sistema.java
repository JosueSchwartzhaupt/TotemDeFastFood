package com.josuesch.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.josuesch.model.Pedido.PRONTO;

public class Sistema {
    //Armazena os pedidos organizados pelo seu id
    private final Map<Integer, Pedido> pedidos = new HashMap<>();
    private int proximoNumero = 1;


    public Optional<Pedido> getPedido(int numero){
        return Optional.ofNullable(pedidos.get(numero));
    }

    public int addPedido(Pedido pedido){
        pedido.setNumero(proximoNumero++);
        pedidos.put(pedido.getNumero(), pedido);
        return pedido.getNumero();
    }

    // Metódos para alterar o status de um pedido
    public boolean iniciaPedido(int numero){
        return alteraStatus(numero, Pedido.PREPARANDO);
    }

    public boolean finalizaPedido(int numero){
        return alteraStatus(numero, PRONTO);
    }

    private boolean alteraStatus(int numero, String status){
        return getPedido(numero)
                .map(pedido -> {
                    pedido.setStatus(status);
                    return true;
                })
                .orElse(false);
    }

    public void iniciarNovoDia(){
        pedidos.clear();
        proximoNumero = 1;
    }

    // Verifica se todos os pedidos estão finalizados
    public boolean isPedidosFinalizados(){
        return pedidos.values().stream()
                .map(Pedido::getStatus)
                .allMatch(status -> status.equals(PRONTO));
    }

    public String listarPedidos(){
        return pedidos.values().stream()
                .sorted()
                .map(Pedido::getResumo)
                .collect(Collectors.joining("\n"));
    }

    // Métodos para navegar entre os pedidos
    public Optional<Integer> getProximoPedido(int atual) {
        return pedidos.keySet().stream()
                .filter(id -> id > atual)
                .min(Integer::compareTo);
    }

    public Optional<Integer>  getPedidoAnterior(int atual) {
        return pedidos.keySet().stream()
                .filter(id -> id < atual)
                .max(Integer::compareTo);
    }

}
