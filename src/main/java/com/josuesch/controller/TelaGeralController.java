package com.josuesch.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import com.josuesch.model.Pedido;
import com.josuesch.model.Sistema;
import com.josuesch.model.enums.Sabor;
import com.josuesch.model.enums.Tamanho;
import com.josuesch.model.item.Item;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static com.josuesch.model.Cardapio.*;

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
    private TextField txQtdSobremesa1;
    @FXML
    private TextField txQtdSobremesa2;
    @FXML
    private ComboBox<Tamanho> cbTamanhoBebida1;
    @FXML
    private ComboBox<Tamanho> cbTamanhoBebida2;
    @FXML
    private ComboBox<Sabor> cbSaborSobremesa1;
    @FXML
    private ComboBox<Sabor> cbSaborSobremesa2;
    @FXML
    private TextField txTotalPedido;


    @FXML
    private TextField txNumeroPedido;
    @FXML
    private TextArea txListaDePedidos;
    @FXML
    private TextArea txDadosPedidoSelecionado;

    private final Sistema sistema;

    private int pedidoAtual;
    private Pedido pedidoCliente;

    private Tamanho tamanhoRefriAnterior = Tamanho.GRANDE;
    private Tamanho tamanhoSucoAnterior = Tamanho.GRANDE;

    private Sabor saborCasquinhaAnterior = Sabor.MISTO;
    private Sabor saborSundaeAnterior = Sabor.MISTO;

    public TelaGeralController(Sistema sistema) {
        this.sistema = sistema;
        pedidoCliente = new Pedido();
    }

    // Inicialização de alguns componentes da tela
    @FXML
    public void initialize() {
        // Permitir apenas a digitação de números na caixa de texto
        txNumeroPedido.setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("\\d*") ? change : null
        ));

        // Inicialização dinâmica das comboboxs
        cbTamanhoBebida1.getItems().addAll(Tamanho.values());
        cbTamanhoBebida2.getItems().addAll(Tamanho.values());

        cbTamanhoBebida1.setValue(tamanhoRefriAnterior);
        cbTamanhoBebida2.setValue(tamanhoSucoAnterior);

        cbSaborSobremesa1.getItems().addAll(Sabor.values());
        cbSaborSobremesa2.getItems().addAll(Sabor.values());

        cbSaborSobremesa1.setValue(saborCasquinhaAnterior);
        cbSaborSobremesa2.setValue(saborSundaeAnterior);

        updateUi();
    }

    // Tratamento de adição e subtração dos Lanches
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

    // Tratamento de adição e subtração das Bebidas
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

    // Tratamento de adição e subtração das Sobremesas
    @FXML
    private void onActionBtAddSobremesa1() throws IOException {
        pedidoCliente.addItem(CASQUINHAS.get(cbSaborSobremesa1.getValue()));
        updateUi();
    }

    @FXML
    private void onActionBtSubSobremesa1() throws IOException {
        pedidoCliente.removeItem(CASQUINHAS.get(cbSaborSobremesa1.getValue()));
        updateUi();
    }

    @FXML
    private void onActionBtAddSobremesa2() throws IOException {
        pedidoCliente.addItem(SUNDAES.get(cbSaborSobremesa2.getValue()));
        updateUi();
    }
    @FXML
    private void onActionBtSubSobremesa2() throws IOException {
        pedidoCliente.removeItem(SUNDAES.get(cbSaborSobremesa2.getValue()));
        updateUi();
    }

    @FXML
    private void OnActionCbSaborSobremesa1() {
        Sabor atual = cbSaborSobremesa1.getValue();

        trocarOpcao(CASQUINHAS, saborCasquinhaAnterior, atual);

        saborCasquinhaAnterior = atual;
        updateUi();
    }

    @FXML
    private void OnActionCbSaborSobremesa2() {
        Sabor atual = cbSaborSobremesa2.getValue();

        trocarOpcao(SUNDAES, saborSundaeAnterior, atual);

        saborSundaeAnterior = atual;
        updateUi();
    }

    // Metódo Auxiliar para trocar o tamanho dos itens no pedidoAtual
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

    // Tratamento da finalização do pedido
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

    @FXML
    private void OnActionBtIniciarNovoDia() throws IOException {
        if(sistema.isPedidosFinalizados()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Iniciar Novo Dia");
            alert.setHeaderText("Tem certeza?");
            alert.setContentText(
                    "O registros de pedidos será perdido."
            );

            alert.showAndWait()
                    .filter(ButtonType.OK::equals)
                    .ifPresent(btn -> {
                        sistema.iniciarNovoDia();
                        updateUi();
                    });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dia não finalizado");
            alert.setHeaderText("Ainda há pedidos não finalizados no dia de hoje!");
            alert.setContentText(
                    "Por favor entregar todos antes de finalizar o dia."
            );

            alert.showAndWait();
        }
    }

    // Atualiza os campos visualizados pelo usuário
    private void updateUi(){
        txQtdLanche1.setText(Integer.toString(pedidoCliente.getQuantidadeItem(X_BURGER)));
        txQtdLanche2.setText(Integer.toString(pedidoCliente.getQuantidadeItem(X_SALADA)));
        txQtdBebida1.setText(Integer.toString(pedidoCliente.getQuantidadeItem(REFRIGERANTES.get(cbTamanhoBebida1.getValue()))));
        txQtdBebida2.setText(Integer.toString(pedidoCliente.getQuantidadeItem(SUCOS.get(cbTamanhoBebida2.getValue()))));
        txQtdSobremesa1.setText(Integer.toString(pedidoCliente.getQuantidadeItem(CASQUINHAS.get(cbSaborSobremesa1.getValue()))));
        txQtdSobremesa2.setText(Integer.toString(pedidoCliente.getQuantidadeItem(SUNDAES.get(cbSaborSobremesa2.getValue()))));
        txListaDePedidos.setText(sistema.listarPedidos());

        txTotalPedido.setText(String.format("%.2f",pedidoCliente.getTotal()));

        txNumeroPedido.setText(pedidoAtual > 0? Integer.toString(pedidoAtual): "");
        var pedido = sistema.getPedido(pedidoAtual);
        txDadosPedidoSelecionado.setText(pedido.map(Pedido::getDetalhes)
                .orElse(pedidoAtual == 0? "" : "Pedido não encontrado"));
    }
}
