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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.FuncionarioDao;
import sistema.model.Funcionario;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class ListaFuncionariosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private List<Funcionario> funcionarios = new ArrayList<>();
    private static Funcionario funcionario = null;
    private ObservableList<Funcionario> obsFuncionario;
  
     @FXML
    private Button btBusca;

    @FXML
    private TableColumn<Funcionario, String> tbColumnCPF;

    @FXML
    private TableColumn<Funcionario, String> tbColumnNomeFuncionario;

    @FXML
    private TableColumn<Funcionario, String> tbColumnEndereco;

    @FXML
    private Button btExcluir;

    @FXML
    private TextField tfBusca;

    @FXML
    private TableView<Funcionario> tbFuncionario;

    @FXML
    private TableColumn<Funcionario, String> tbColumnTelefone;

    @FXML
    private Button btEditar;

    @FXML
    private TableColumn<Funcionario, String> tbColumnDataNascimento;

    @FXML
    private Button btNovo;

    @FXML
    void buscar(ActionEvent event) {
        listaFuncionarios();

    }

    @FXML
    void novo(ActionEvent event) {
        try {
            Parent layout =FXMLLoader.load(getClass().getResource("/sistema/view/CadastroFuncionario.fxml"));
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void editar(ActionEvent event) {
        if(verificaFuncionario()){
        try {
            CadastroFuncionarioController cadastro = new CadastroFuncionarioController();
            System.out.println(funcionario.getId());
            System.out.println(funcionario.getNome());
            System.out.println(funcionario.getDataNascimento());
            System.out.println(funcionario.getEmail());
            System.out.println(funcionario.getEndereco());
            System.out.println(funcionario.getTelefone());
            System.out.println(funcionario.getTipo());
            System.out.println(funcionario.getSenha());
            cadastro.preencheForm(funcionario);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sistema/view/CadastroFuncionario.fxml"));
            VBox root = (VBox)loader.load();
            loader.setController(cadastro);
            Stage stage = new Stage();
            Scene scena = new Scene(root);
            stage.setScene(scena);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ListaFuncionariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    }

    @FXML
    void excluir(ActionEvent event) {
        excluirFuncionario();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        funcionarioSelecionado();
    }   
    private void listaFuncionarios() {
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        funcionarios = funcionarioDao.listar("%" + tfBusca.getText() + "%");
        if (funcionarios.isEmpty()) {
            preencherTabela(funcionarios);
            JOptionPane.showMessageDialog(null, "Registro não localizado");
        } else {
            preencherTabela(funcionarios);
        }
    }
    //Metodo Prencher Tabela
    private void preencherTabela(List<Funcionario> funcionarios) {
        tbColumnNomeFuncionario.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbColumnDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        tbColumnCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        tbColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tbColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        obsFuncionario = FXCollections.observableArrayList(funcionarios);
        tbFuncionario.setItems(obsFuncionario);
    }
    //Metodo coletar Informação Cliente Selecionado 
    private void funcionarioSelecionado() {
        tbFuncionario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> valor(newValue));
    }

    private void valor(Funcionario newValue) {
        this.funcionario = newValue;
    }

    private void excluirFuncionario() {
        
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Selecione um Funcionario");

        } else {
            int res = JOptionPane.showConfirmDialog(null, "Deseja Excluir o registro Selecionado?", "Excluir", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                FuncionarioDao funcionarioDao = new FuncionarioDao();
               // System.out.println("excluir id :"+ funcionario.getId());
                funcionarioDao.excluir(funcionario.getId());
                listaFuncionarios();
            }
        }

    }
    private boolean verificaFuncionario(){
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Selecione um Funcionario");
            return false;
        } else {
            return true;
        }
    }
    
}
