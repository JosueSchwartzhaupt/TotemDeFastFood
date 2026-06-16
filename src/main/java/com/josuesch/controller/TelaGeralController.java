package com.josuesch.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import com.josuesch.model.Cardapio;
import com.josuesch.model.Pedido;
import com.josuesch.model.Sistema;
import com.josuesch.model.enums.Tamanho;
import com.josuesch.model.item.Bebida;
import com.josuesch.model.item.Item;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static com.josuesch.model.Cardapio.REFRIGERANTES;
import static com.josuesch.model.Cardapio.SUCOS;
import static com.josuesch.model.Cardapio.X_BURGER;
import static com.josuesch.model.Cardapio.X_SALADA;

public class TelaGeralController {
    @FXML
    private TextField txQtdLanche1;
    @FXML
    private TextField txQtdLanche2;
    @FXML
    private TextField txQtdBebida1;
    @FXML
    private TextField txQtdBebida2;
    @FXML
    private ComboBox<Tamanho> cbTamanhoBebida1;
    @FXML
    private ComboBox<Tamanho> cbTamanhoBebida2;    
    @FXML
    private TextField txTotalPedido;


    @FXML
    private TextField txNumeroPedido;
    @FXML
    private TextArea txListaDePedidos;
    @FXML
    private TextArea txDadosPedidoSelecionado;

    private int pedidoAtual;

    private Pedido pedidoCliente;
    private Sistema sistema;

    private Tamanho tamanhoRefriAnterior = Tamanho.GRANDE;
    private Tamanho tamanhoSucoAnterior = Tamanho.GRANDE;

    public TelaGeralController(Sistema sistema) {
        this.sistema = sistema;
        pedidoCliente = new Pedido();
    }
    @FXML
    public void initialize() {
        txNumeroPedido.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));

        cbTamanhoBebida1.getItems().addAll(Tamanho.values());
        cbTamanhoBebida2.getItems().addAll(Tamanho.values());

        cbTamanhoBebida1.setValue(tamanhoRefriAnterior);
        cbTamanhoBebida2.setValue(tamanhoSucoAnterior);

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
    private void onActionBtAddBebida1() throws IOException {
        pedidoCliente.addItem(REFRIGERANTES.get(cbTamanhoBebida1.getValue()));
        updateUi();
    }
    @FXML
    private void onActionBtSubBebida1() throws IOException {
        pedidoCliente.removeItem(REFRIGERANTES.get(cbTamanhoBebida1.getValue()));
        updateUi();
    }

    @FXML
    private void onActionBtAddBebida2() throws IOException {
        pedidoCliente.addItem(SUCOS.get(cbTamanhoBebida2.getValue()));
        updateUi();
    }
    @FXML
    private void onActionBtSubBebida2() throws IOException {
        pedidoCliente.removeItem(SUCOS.get(cbTamanhoBebida2.getValue()));
        updateUi();
    }

    @FXML
    private void OnActionCbTamanhoBebida1() {
        Tamanho atual = cbTamanhoBebida1.getValue();

        trocarOpcao(REFRIGERANTES, tamanhoRefriAnterior, atual);

        tamanhoRefriAnterior = atual;
        updateUi();
    }

    @FXML
    private void OnActionCbTamanhoBebida2() {
        Tamanho atual = cbTamanhoBebida2.getValue();

        trocarOpcao(SUCOS, tamanhoSucoAnterior, atual);

        tamanhoSucoAnterior = atual;
        updateUi();
}

    private <K, V extends Item> void trocarOpcao(Map<K, V> itens, K anterior, K atual) {
        if (Objects.equals(atual, anterior)) return;

        V itemAnterior = itens.get(anterior);
        V itemAtual = itens.get(atual);

        pedidoCliente.addItem(
                itemAtual,
                pedidoCliente.getQuantidadeItem(itemAnterior)
        );

        pedidoCliente.zerarItem(itemAnterior);
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

    @FXML
    private void onActionBtPedidoAnterior() throws IOException {
        sistema.getPedidoAnterior(pedidoAtual)
                .ifPresent(anterior -> pedidoAtual = anterior);
        updateUi();
    }

    @FXML
    private void onActionBtProximoPedido() throws IOException {
        sistema.getProximoPedido(pedidoAtual)
                .ifPresent(proximo -> pedidoAtual = proximo);
        updateUi();
    }

    @FXML
    private void onActionTxNumeroPedido() throws IOException {
        String input = txNumeroPedido.getText();

        try {
            pedidoAtual = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            pedidoAtual = 0;
        }

        updateUi();
    }
    @FXML
    private void OnActionBtIniciarPreparo() throws IOException {
        sistema.iniciaPedido(pedidoAtual);
        updateUi();
    }

    @FXML
    private void OnActionBtConcluir() throws IOException {
        sistema.finalizaPedido(pedidoAtual);
        updateUi();
    }

    @FXML
    private void OnActionBtCancelar() throws IOException {
        pedidoAtual = 0;
        updateUi();
    }

    private void updateUi(){
        txQtdLanche1.setText(Integer.toString(pedidoCliente.getQuantidadeItem(X_BURGER)));
        txQtdLanche2.setText(Integer.toString(pedidoCliente.getQuantidadeItem(X_SALADA)));
        txQtdBebida1.setText(Integer.toString(pedidoCliente.getQuantidadeItem(REFRIGERANTES.get(cbTamanhoBebida1.getValue()))));
        txQtdBebida2.setText(Integer.toString(pedidoCliente.getQuantidadeItem(SUCOS.get(cbTamanhoBebida2.getValue()))));
        txListaDePedidos.setText(sistema.listarPedidos());

        txTotalPedido.setText(String.format("%.2f",pedidoCliente.getTotal()));

        txNumeroPedido.setText(pedidoAtual > 0? Integer.toString(pedidoAtual): "");
        var pedido = sistema.getPedido(pedidoAtual);
        txDadosPedidoSelecionado.setText(pedido.map(Pedido::getDetalhes)
                .orElse(pedidoAtual == 0? "" : "Pedido não encontrado"));
    }
}
