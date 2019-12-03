/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author matsui
 */
public class PrincipalController implements Initializable {

  /**
   * Initializes the controller class.
   */
    @FXML
    private MenuItem miEfetuarVenda;

    @FXML
    private MenuItem miClientes;
    
    @FXML
    private MenuBar mbPrincipal;

    @FXML
    private MenuItem miAnivesariantes;

    @FXML
    private Menu mbRelatorios;

    @FXML
    private MenuItem miSaidaEstoque;

    @FXML
    private Menu mbAgenda;

    @FXML
    private Menu mbVendas;

    @FXML
    private MenuItem miProdutos;

    @FXML
    private MenuItem miAgendamentos;

    @FXML
    private MenuItem miFuncionarios;

    @FXML
    private MenuItem miRelatorioProdutos;

    @FXML
    private MenuItem miCategorias;

    @FXML
    private MenuItem miEntradaEstoque;

    @FXML
    private Menu mbEstoque;

    @FXML
    private MenuItem miRelatorioVendas;

    @FXML
    private Menu mbCadastros;

    @FXML
    void abrirCategorias(ActionEvent event) {
        
        try {
            Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/ListaCategorias.fxml"));
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void abrirClientes(ActionEvent event) {
                try {
            Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/ListaClientes.fxml"));
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void abrirFuncionarios(ActionEvent event) {
                try {
            Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/ListaFuncionarios.fxml"));
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void abrirProdutos(ActionEvent event) {
                try {
            Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/ListaProdutos.fxml"));
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void abrirAgendamentos(ActionEvent event) {

    }

    @FXML
    void abrirVendas(ActionEvent event) {

    }

    @FXML
    void AbrirEntradaEstoque(ActionEvent event) {

    }

    @FXML
    void abrirSaidaEstoque(ActionEvent event) {

    }

    @FXML
    void abrirAniversariantes(ActionEvent event) {

    }

    @FXML
    void abrirRelatorioProdutos(ActionEvent event) {

    }

    @FXML
    void abrirRelatorioVendas(ActionEvent event) {

    }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  
  
}
