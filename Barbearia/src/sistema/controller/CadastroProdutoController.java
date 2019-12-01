/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class CadastroProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TextField tfValorCusto;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfValorVenda;

    @FXML
    private CheckBox cbStatus;

    @FXML
    private TextField tfTipoProduto;

    @FXML
    private TextField tfCategoria;

    @FXML
    private Button btBuscarTipoProduto;

    @FXML
    private Button btBuscarCategoria;

    @FXML
    private Button btNovo;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfQuantidade;

    @FXML
    void novo(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void buscarCategoria(ActionEvent event) {

    }

    @FXML
    void buscarTipoProduto(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
