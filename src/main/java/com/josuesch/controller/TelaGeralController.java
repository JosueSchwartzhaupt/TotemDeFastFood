package com.josuesch.controller;

import java.io.IOException;

import com.josuesch.model.Cardapio;
import com.josuesch.model.Pedido;
import com.josuesch.model.Sistema;
import com.josuesch.model.item.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static com.josuesch.model.Cardapio.X_BURGER;
import static com.josuesch.model.Cardapio.X_SALADA;

public class TelaGeralController {
    @FXML
    private TextField txQtdLanche1;
    @FXML
    private TextField txQtdLanche2;
    @FXML
    private TextField txTotalPedido;


    @FXML
    private TextField txNumeroPedido;
    @FXML
    private TextArea txListaDePedidos;
    @FXML
    private TextArea txDadosPedidoSelecionado;

    private Pedido pedidoCliente;
    private Sistema sistema;

    public TelaGeralController(Sistema sistema) {
        this.sistema = sistema;
        pedidoCliente = new Pedido();
    }
    @FXML
    public void initialize() {
        updateUi();
    }

    @FXML
    private void onActionBtAddLanche1() throws IOException {
        pedidoCliente.addItem(X_BURGER);
        updateUi();
    }
    @FXML
    private void onActionBtSubLanche1() throws IOException {
        pedidoCliente.removeItem(X_BURGER);
        updateUi();
    }

    @FXML
    private void onActionBtAddLanche2() throws IOException {
        pedidoCliente.addItem(X_SALADA);
        updateUi();
    }
    @FXML
    private void onActionBtSubLanche2() throws IOException {
        pedidoCliente.removeItem(X_SALADA);
        updateUi();
    }

    @FXML
    private void onActionBtConfirmarPedido() throws IOException {
        if (pedidoCliente.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pedido vazio");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Adicione pelo menos um item ao pedido."
            );

            alert.showAndWait();
            return;
        }

        int numero = sistema.addPedido(pedidoCliente);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pedido Confirmado");
        alert.setHeaderText("Pedido realizado com sucesso!");
        alert.setContentText(
                "Seu número de pedido é: " + numero
        );

        alert.showAndWait();

        pedidoCliente = new Pedido();
        updateUi();
    }

    @FXML
    private void onActionBtCancelarPedido() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancelar Pedido");
        alert.setHeaderText("Tem certeza?");
        alert.setContentText(
                "O pedido atual será perdido."
        );

        alert.showAndWait()
                .filter(ButtonType.OK::equals)
                .ifPresent(btn -> {
                    pedidoCliente = new Pedido();
                    updateUi();
                });
    }

    private void updateUi(){
        txQtdLanche1.setText(Integer.toString(pedidoCliente.getQuantidadeItem(X_BURGER)));
        txQtdLanche2.setText(Integer.toString(pedidoCliente.getQuantidadeItem(X_SALADA)));

        txTotalPedido.setText(String.format("%.2f",pedidoCliente.getTotal()));
    }
}
