
package sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class CadastroFuncionarioController implements Initializable {

    /**
     * Initializes the controller class.
     */
        @FXML
    private TextField tfRG;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfCPF;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfEndereco;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfUsuario;

    @FXML
    private Button btNovo;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfNome;

    @FXML
    void novo(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
