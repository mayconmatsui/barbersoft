package sistema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.LoginDao;

public class LoginController implements Initializable {

    private AnchorPane parent;
    @FXML
    private TextField tfUsuario;

    @FXML
    private PasswordField tfSenha;

    @FXML
    private Button btLogin;

    @FXML
    void logar(ActionEvent event) {
        login();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void login() {
         if (tfUsuario.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Preencher o campo Usuário", "Alerta", JOptionPane.WARNING_MESSAGE);

    } else if (tfSenha.getText().isEmpty()) {
      JOptionPane.showMessageDialog(null, "Preencher o campo Senha", "Alerta", JOptionPane.INFORMATION_MESSAGE);
    }
    LoginDao login = new LoginDao();
    String senha = tfSenha.getText();
    if (login.autenticar(tfUsuario.getText(), senha)) {
      JOptionPane.showMessageDialog(null, "Login Efetuado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      abrirPrincipal();
      fecharLogin();
    } else {
      JOptionPane.showMessageDialog(null, "Erro ao efetuar Login\nUsuário ou senha inválido", "Alert", JOptionPane.WARNING_MESSAGE);

    }
    }

    private void fecharLogin() {
        Stage stage = (Stage)btLogin.getScene().getWindow();
        stage.close();
    }

    private void abrirPrincipal() {
        try {
            Parent layout;
        
            layout = FXMLLoader.load(getClass().getResource("/sistema/view/Principal.fxml"));
        
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.setMaximized(true);
            stage.setTitle("BarberSoft - Gerenciamento");         
            stage.show();
            } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         

    }

}
