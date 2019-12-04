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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.ClienteDao;
import sistema.model.Cliente;

/**
 * FXML Controller class
 *
 * @author matsui
 */
public class ListaClientesController implements Initializable {

  /**
   * Initializes the controller class.
   */
  private List<Cliente> clientes = new ArrayList<>();
  private static Cliente cliente = null;
  private ObservableList<Cliente> obsCliente;
  private CadastroClienteController cadastroCliente = new CadastroClienteController();
  @FXML
  private Button btBusca;

  @FXML
  private TableColumn<Cliente, String> tbColumnCPF;

  @FXML
  private TableColumn<Cliente, String> tbColumnEndereco;

  @FXML
  private Button btExcluir;

  @FXML
  private TableView<Cliente> tbCliente;

  @FXML
  private TextField tfBusca;

  @FXML
  private TableColumn<Cliente, String> tbColumnTelefone;

  @FXML
  private TableColumn<Cliente, String> tbColumnNomeCliente;

  @FXML
  private Button btEditar;

  @FXML
  private TableColumn<Cliente, String> tbColumnDataNascimento;

  @FXML
  private Button btNovo;

  @FXML
  void buscar(ActionEvent event) {
    listaClientes();
  }

  @FXML
  void novo(ActionEvent event) {
    try {
      Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/CadastroCliente.fxml"));
      Scene cena = new Scene(layout);
      Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
      stage.setScene(cena);
      stage.show();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @FXML
  void editar(ActionEvent event) {
    try {
      // Carrega o arquivo fxml e cria um novo stage para a janela popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ListaClientesController.class.getResource("/sistema/view/CadastroCliente.fxml"));
        VBox page = (VBox) loader.load();

        // Cria o palco dialogStage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Formulário alteração Cliente");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Define a pessoa no controller.
        CadastroClienteController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.preencheForm(cliente);
        controller.setTitulo("Editar Cliente");

        // Mostra a janela e espera até o usuário fechar.
        dialogStage.showAndWait();

//        return controller.isOkClicked();
//      FXMLLoader loader = new FXMLLoader();
//      loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
//      AnchorPane page = (AnchorPane) loader.load();
//      Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/CadastroCliente.fxml"));
//      Scene cena = new Scene(layout);
//      Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//      stage.setScene(cena);
//      stage.show();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  @FXML
  void excluir(ActionEvent event) {
    excluirCliente();

  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    clienteSelecionado();
  }

  private void listaClientes() {
    ClienteDao clienteDao = new ClienteDao();
    clientes = clienteDao.listar("%" + tfBusca.getText() + "%");
    if (clientes.isEmpty()) {
      preencherTabela(clientes);
      JOptionPane.showMessageDialog(null, "Registro não localizado");
    } else {
      preencherTabela(clientes);
    }
  }
  //Metodo Prencher Tabela

  private void preencherTabela(List<Cliente> clientes) {
    tbColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
    tbColumnDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
    tbColumnCPF.setCellValueFactory(new PropertyValueFactory<>("CPF"));
    tbColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    tbColumnEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
    obsCliente = FXCollections.observableArrayList(clientes);
    tbCliente.setItems(obsCliente);
  }

  //Metodo coletar Informação Cliente Selecionado 
  private void clienteSelecionado() {
    tbCliente.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> valor(newValue));
  }

  private void valor(Cliente newValue) {
    this.cliente = newValue;

  }

  private void excluirCliente() {

    if (cliente == null) {
      JOptionPane.showMessageDialog(null, "Selecione um cliente");

    } else {
      int res = JOptionPane.showConfirmDialog(null, "Deseja Excluir o registro Selecionado?", "Excluir", JOptionPane.YES_NO_OPTION);
      if (res == JOptionPane.YES_OPTION) {
        ClienteDao clienteDao = new ClienteDao();
        System.out.println("excluir id :" + cliente.getId());
        //clienteDao.excluir(cliente.getId());
        listaClientes();
      }
    }

  }
}
