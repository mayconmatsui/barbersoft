package sistema.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.FuncionarioDao;
import sistema.model.Funcionario;
import sistema.utils.Utils;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class CadastroFuncionarioController implements Initializable {

  private Funcionario funcionario = new Funcionario();
  private Stage dialogStage;

  /**
   * Initializes the controller class.
   */
  @FXML
  private Label lbTitulo;

  @FXML
  private Button btNovo;

  @FXML
  private Button btCancelar;

  @FXML
  private TextField tfNome;

  @FXML
  private DatePicker dpDataNasc;

  @FXML
  private TextField tfRG;

  @FXML
  private TextField tfCPF;

  @FXML
  private TextField tfTelefone;

  @FXML
  private TextField tfEndereco;

  @FXML
  private TextField tfEmail;

  @FXML
  private ComboBox<String> cbTipoFuncionario;

  @FXML
  private HBox boxSenha;

  @FXML
  private PasswordField pfSenha;

  @FXML
  private PasswordField pfSenhaConfirma;

  public void setTitulo(String titulo) {
    this.lbTitulo.setText(titulo);
  }

  public void preencheForm(Funcionario funcionario) {
    this.funcionario = funcionario;
    tfNome.setText(funcionario.getNome());
    dpDataNasc.setValue(funcionario.getDataNascimento());
    tfRG.setText(funcionario.getRG());
    tfCPF.setText(funcionario.getCPF());
    tfTelefone.setText(funcionario.getTelefone());
    tfEndereco.setText(funcionario.getEndereco());
    tfEmail.setText(funcionario.getEmail());
    cbTipoFuncionario.setValue(funcionario.getTipo());
    if (funcionario.getTipo().equals("Administrador") || funcionario.getTipo().equals("Atendente")) {
      boxSenha.setVisible(false);
    }
  }

  public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
  }

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
    carregarComboBox();
  }

  /*  */
 /*  */
  private void salvar() {
    
    if (validarCampos()) {
      FuncionarioDao funcionarioDao = new FuncionarioDao();
      carregarDadosCampos();
      if (this.funcionario.getId() != null) {
        if (funcionarioDao.alterar(this.funcionario) == null) {
          JOptionPane.showMessageDialog(null, "Erro ao Alterar Funcionário", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null, "Funcionário alterado com Sucesso!", "Alteração", JOptionPane.INFORMATION_MESSAGE);
          fecharInterface();
        }
      } else {
        if (funcionarioDao.salvar(this.funcionario) == null) {
          JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Funcionário", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
          JOptionPane.showMessageDialog(null, "Funcionário cadastrado com Sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
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
      JOptionPane.showMessageDialog(null, "O Campo endereço deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
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
      tfEmail.setText(null);
      tfEmail.requestFocus();
      return false;
    } else if (cbTipoFuncionario.getSelectionModel().isEmpty()) {
      JOptionPane.showMessageDialog(null, "O tipo de funcionario deve ser Selecionado", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      cbTipoFuncionario.requestFocus();
      return false;
    } else if (!validaSenha()) {
      return false;
    }

    return true;
  }

  private Boolean validaSenha() {
    if (funcionario.getId() != null) {
      if ((funcionario.getTipo().equals("Barbeiro") && cbTipoFuncionario.getValue().equals("Administrador")) && pfSenha.getText().isEmpty() || (funcionario.getTipo().equals("Barbeiro") && cbTipoFuncionario.getValue().equals("Atendente")) && pfSenha.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Campo senha e verificação de senha deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        return false;
      } else if ((funcionario.getTipo().equals("Barbeiro") && cbTipoFuncionario.getValue().equals("Administrador")) && !pfSenha.getText().equals(pfSenhaConfirma.getText()) || (funcionario.getTipo().equals("Barbeiro") && cbTipoFuncionario.getValue().equals("Atendente")) && !pfSenha.getText().equals(pfSenhaConfirma.getText())) {
        JOptionPane.showMessageDialog(null, "Senhas não conferem", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        return false;
      }
      return true;
    } else {
      if ((cbTipoFuncionario.getValue().equals("Administrador") || cbTipoFuncionario.getValue().equals("Atendente")) && (pfSenha.getText().isEmpty() || pfSenhaConfirma.getText().isEmpty())) {
        JOptionPane.showMessageDialog(null, "Campo senha e verificação de senha deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        return false;
      } else if ((cbTipoFuncionario.getValue().equals("Administrador") || cbTipoFuncionario.getValue().equals("Atendente")) && !pfSenha.getText().equals(pfSenhaConfirma.getText())) {
        JOptionPane.showMessageDialog(null, "Senhas não conferem", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        return false;
      }
      return true;
    }
  }

  private void carregarDadosCampos() {
    funcionario.setNome(tfNome.getText());
    funcionario.setDataNascimento(dpDataNasc.getValue());
    funcionario.setRG(tfRG.getText());
    funcionario.setCPF(tfCPF.getText());
    funcionario.setTelefone(tfTelefone.getText());
    funcionario.setEndereco(tfEndereco.getText());
    funcionario.setEmail(tfEmail.getText());
    funcionario.setTipo(cbTipoFuncionario.getValue());

    if ((cbTipoFuncionario.getValue().equals("Administrador") || cbTipoFuncionario.getValue().equals("Atendente"))) {
      String senha = pfSenha.getText();
      senha = Utils.geraSenha(senha);
      funcionario.setSenha(senha);
    } else {
      funcionario.setSenha("");
    }
  }

  private void fecharInterface() {
    Stage stage = (Stage) btNovo.getScene().getWindow(); //Obtendo a janela atual
    stage.close();
  }

  public void carregarComboBox() {
    ObservableList<String> cargo = FXCollections.observableArrayList("Administrador", "Atendente", "Barbeiro");
    cbTipoFuncionario.setItems(cargo);
    cbTipoFuncionario.valueProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (funcionario.getId() != null) {
//          if (newValue.equals("Administrador") || newValue.equals("Atendente") || newValue.equals("Barbeiro")) {
//            boxSenha.setVisible(false);
//          } else {
//            boxSenha.setVisible(true);
//          }
        } else {
          if (newValue.equals("Administrador") || newValue.equals("Atendente")) {
            boxSenha.setVisible(true);
          } else {
            boxSenha.setVisible(false);
          }
        }
      }
    });
  }

}
