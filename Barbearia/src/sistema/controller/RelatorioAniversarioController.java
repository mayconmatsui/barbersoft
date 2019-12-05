package sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author matsui
 */
public class RelatorioAniversarioController implements Initializable {

  @FXML
  private Button btNovo;
  @FXML
  private Button btCancelar;
  @FXML
  private TextField dpInicial;
  @FXML
  private TextField dpFinal;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }  

  @FXML
  private void novo(ActionEvent event) {
  }

  @FXML
  private void cancelar(ActionEvent event) {
  }
  
}
