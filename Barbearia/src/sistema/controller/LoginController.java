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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LoginController implements Initializable {

    private AnchorPane parent;

    private double xOffSet = 0;
    private double yOffSet = 0;
    @FXML
    private TextField tfUsuario;
  @FXML
  private PasswordField tfSenha;
  @FXML
  private Button btLogin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDragable();
    }

    private void makeStageDragable() {
        parent.setOnMousePressed((event) -> {
            xOffSet = event.getSceneX();
            yOffSet = event.getSceneY();
        });
        parent.setOnMouseDragged((event) -> {
//            Launch.stage.setX(event.getScreenX() - xOffSet);
//            Launch.stage.setY(event.getScreenY() - yOffSet);
//            Launch.stage.setOpacity(0.8f);
        });
        parent.setOnDragDone((event) -> {
//            Launch.stage.setOpacity(1.0f);
        });
        parent.setOnMouseReleased((event) -> {
//            Launch.stage.setOpacity(1.0f);
        });
    }

    private void handle_login(ActionEvent event) throws IOException {
        Parent menu = FXMLLoader.load(getClass().getResource("sistema/view/Principal.fxml"));
        parent.getChildren().removeAll();
        parent.getChildren().setAll(menu);
    }


    private void close_app(MouseEvent event) {
        System.exit(0);
    }

  @FXML
  private void logar(ActionEvent event) {
  }
}
