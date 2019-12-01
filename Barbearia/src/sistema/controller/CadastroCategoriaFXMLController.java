
package sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class CadastroCategoriaFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfNomeCategoria;

    @FXML
    private CheckBox cbStatus;

    @FXML
    private Button btNovo;

    @FXML
    private Button btCancelar;

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
