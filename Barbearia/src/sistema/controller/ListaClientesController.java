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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author matsui
 */

public class ListaClientesController implements Initializable {

  /**
   * Initializes the controller class.
   */
    @FXML
    private Button btBusca;

    @FXML
    private Button btExcluir;

    @FXML
    private TextField tfBusca;

    @FXML
    private Button btEditar;

    @FXML
    private Button btNovo;

    @FXML
    private TableColumn<?, ?> tbClientes;

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void novo(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
