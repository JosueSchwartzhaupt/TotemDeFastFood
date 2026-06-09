package com.josuesch;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PrimaryController {
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

    private int qtdLanche1;
    
    @FXML
    private void onActionBtAddLanche1() throws IOException {
        qtdLanche1++;
        updateUi();
    }
    @FXML
    private void onActionBtSubLanche1() throws IOException {
        qtdLanche1--;
        updateUi();
    }
    private void updateUi(){
        txQtdLanche1.setText(Integer.toString(qtdLanche1));
        System.out.println("Tela mudada, " + qtdLanche1);
    }
}
