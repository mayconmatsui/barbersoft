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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MenuController implements Initializable {

  @FXML
  private AnchorPane content;
  @FXML
  private HBox top;

  @Override
  public void initialize(URL url, ResourceBundle rb) {
  }

  @FXML
  private void handle_logout(ActionEvent event) throws IOException {
    Parent menu = FXMLLoader.load(getClass().getResource("/application/view/Login.fxml"));
    content.getChildren().removeAll();
    content.getChildren().setAll(menu);
  }

//  @FXML
//  private void minimize_stage(MouseEvent event) {
//    LaunchController.stage.setIconified(true);
//  }

  @FXML
  private void close_app(MouseEvent event) {
    System.exit(0);
  }
}
