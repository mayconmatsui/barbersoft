package sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.ClienteDao;
import sistema.model.Cliente;
import sistema.utils.Utils;

/**
 * FXML Controller class
 *
 * @author matsui
 */
public class CadastroClienteController implements Initializable {

  /**
   * Initializes the controller class.
   */
  @FXML
  private Label lbTitulo;

  @FXML
  private TextField tfRG;

  @FXML
  private DatePicker dpDataNasc;

  @FXML
  private TextField tfTelefone;

  @FXML
  private TextField tfCPF;

  @FXML
  private TextField tfEndereco;

  @FXML
  private TextField tfEmail;

  @FXML
  private Button btNovo;

  @FXML
  private Button btCancelar;

  @FXML
  private TextField tfNome;

  private Stage dialogStage;
  private Cliente cliente = new Cliente();

  @FXML
  void novo(ActionEvent event) {
    salvar();
  }

  @FXML
  void cancelar(ActionEvent event) {
    fecharInterface();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  public void setTitulo(String titulo) {
    this.lbTitulo.setText(titulo);
  }

  public void preencheForm(Cliente cliente) {
    this.cliente = cliente;
    tfNome.setText(cliente.getNome());
    tfRG.setText(cliente.getRG());
    tfCPF.setText(cliente.getCPF());
    tfTelefone.setText(cliente.getTelefone());
    tfEndereco.setText(cliente.getEndereco());
    tfEmail.setText(cliente.getEmail());
    dpDataNasc.setValue(cliente.getDataNascimento());
  }

  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

  private void salvar() {
    if (validarCampos()) {
      ClienteDao clienteDao = new ClienteDao();
      carregarDadosCampos();
      if (this.cliente.getId() != null) {
        if (clienteDao.alterar(this.cliente) == null) {
          JOptionPane.showMessageDialog(null, "Erro ao Alterar Cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null, "Cliente alterado com Sucesso!", "Alteração", JOptionPane.INFORMATION_MESSAGE);
          fecharInterface();
        }
      } else {
        if (clienteDao.salvar(this.cliente) == null) {
          JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Cliente", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null, "Cliente cadastrado com Sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
          fecharInterface();
        }
      }
    }
  }

  private boolean validarCampos() {
    if (tfNome.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "O campo nome deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      tfNome.setText(null);
      tfNome.requestFocus();
      return false;
    } else if (dpDataNasc.getValue() == null) {
      JOptionPane.showMessageDialog(null, "O campo data de nascimento deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      dpDataNasc.setValue(null);
      dpDataNasc.requestFocus();
      return false;
    } else if (tfRG.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "O campo RG deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      tfRG.setText(null);
      tfRG.requestFocus();
      return false;
    } else if (tfCPF.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "O campo CPF deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      tfCPF.setText(null);
      tfCPF.requestFocus();
      return false;
    } else if (!Utils.isCPF(tfCPF.getText())) {
      JOptionPane.showMessageDialog(null, "CPF inválido, informar somentes números", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      tfCPF.setText(null);
      tfCPF.requestFocus();
      return false;
    } else if (tfTelefone.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "O campo telefone deve Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      tfTelefone.setText(null);
      tfTelefone.requestFocus();
      return false;
    } else if (tfEndereco.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "O Campor endereço deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      tfEndereco.setText(null);
      tfEndereco.requestFocus();
      return false;
    } else if (tfEmail.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "O campo email deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      tfEmail.setText(null);
      tfEmail.requestFocus();
      return false;
    } else if (!Utils.isEmail(tfEmail.getText())) {
      JOptionPane.showMessageDialog(null, "Email inválido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      return false;
    }
    return true;
  }

  private void carregarDadosCampos() {
    cliente.setNome(tfNome.getText());
    cliente.setDataNascimento(dpDataNasc.getValue());
    cliente.setRG(tfRG.getText());
    cliente.setCPF(tfCPF.getText());
    cliente.setTelefone(tfTelefone.getText());
    cliente.setEndereco(tfEndereco.getText());
    cliente.setEmail(tfEmail.getText());
  }

  private void fecharInterface() {
    Stage stage = (Stage) btNovo.getScene().getWindow(); //Obtendo a janela atual
    stage.close();
  }

}
