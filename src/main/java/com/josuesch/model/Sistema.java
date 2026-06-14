package com.josuesch.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Sistema {
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

    public boolean iniciaPedido(int numero){
        return alteraStatus(numero, Pedido.PREPARANDO);
    }

    public boolean finalizaPedido(int numero){
        return alteraStatus(numero, Pedido.PRONTO);
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



//    public boolean removePedido(Integer numeroDoPedido){
//        if (pedidos.containsKey(numeroDoPedido)) {
//            pedidos.remove(numeroDoPedido);
//            return true;
//        }
//        return false;
//    }


}
