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
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    private Funcionario funcionario = null;
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
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ListaClientesController.class.getResource("/sistema/view/CadastroFuncionario.fxml"));
            VBox page = (VBox) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulário Cadastro Funcionário");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa no controller.
            CadastroFuncionarioController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void editar(ActionEvent event) {
        if (verificaFuncionario()) {
            try {
                // Carrega o arquivo fxml e cria um novo stage para a janela popup.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ListaClientesController.class.getResource("/sistema/view/CadastroFuncionario.fxml"));
                VBox page = (VBox) loader.load();

                // Cria o palco dialogStage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Formulário alteração Funcionário");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Define a pessoa no controller.
                CadastroFuncionarioController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.preencheForm(funcionario);
                controller.setTitulo("Editar Funcionário");

                // Mostra a janela e espera até o usuário fechar.
                dialogStage.showAndWait();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    @FXML
    void excluir(ActionEvent event) {
        excluirFuncionario();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaFuncionarios();
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

        if (verificaFuncionario()) {
            int res = JOptionPane.showConfirmDialog(null, "Deseja Excluir o registro Selecionado?", "Excluir", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                FuncionarioDao funcionarioDao = new FuncionarioDao();
                funcionarioDao.excluir(funcionario.getId());
                listaFuncionarios();
            }
        }

    }

    private boolean verificaFuncionario() {
        if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Selecione um Funcionario");
            return false;
        } else {
            return true;
        }
    }

}
