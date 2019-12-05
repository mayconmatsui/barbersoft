/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import sistema.dao.ProdutoDao;
import sistema.model.Produto;
import sistema.relatorio.Relatorio;

/**
 * FXML Controller class
 *
 * @author matsui
 */
public class PrincipalController implements Initializable {

    @FXML
    private MenuBar mbPrincipal;

    @FXML
    private Menu mbCadastros;

    @FXML
    private MenuItem miCategorias;

    @FXML
    private MenuItem miClientes;

    @FXML
    private MenuItem miFuncionarios;

    @FXML
    private MenuItem miProdutos;

    @FXML
    private Menu mbAgenda;

    @FXML
    private MenuItem miAgendamentos;

    @FXML
    private Menu mbVendas;

    @FXML
    private MenuItem miEfetuarVenda;

    @FXML
    private Menu mbEstoque;

    @FXML
    private MenuItem miEntradaEstoque;

    @FXML
    private MenuItem miSaidaEstoque;

    @FXML
    private Menu mbRelatorios;

    @FXML
    private MenuItem miAnivesariantes;

    @FXML
    private MenuItem miRelatorioProdutos;

    @FXML
    private MenuItem miRelatorioClientes;



    


    @FXML
    void abrirCategorias(ActionEvent event) {

        try {
            Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/ListaCategorias.fxml"));
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.setTitle("Listagem de Categorias");
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
            stage.setTitle("Listagem de Clientes");
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
            stage.setTitle("Listagem de Funcionários");
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
            stage.setTitle("Listagem de Produtos");
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
        try {
            Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/Aniversariante.fxml"));
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.setTitle("Relatório Aniversariantes");
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void abrirRelatorioProdutos(ActionEvent event) {
        List<Produto> produto = new ArrayList<>();
        ProdutoDao produtoDao = new ProdutoDao();
        produto = produtoDao.listar("% %");
        if(!produto.isEmpty()){
            Relatorio relatorio = new Relatorio();
            try {
                relatorio.gerarRelatorioProdutos(produto);
                
            } catch (JRException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Não Foi Encontrado Produto!");
        }
    }

    @FXML
    void abrirRelatorioClientes(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
