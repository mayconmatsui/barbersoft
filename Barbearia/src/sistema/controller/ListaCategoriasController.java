
package sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class ListaCategoriasController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    private TableView<?> tbCategoria;

    @FXML
    private Button btBusca;

    @FXML
    private Button btExcluir;

    @FXML
    private TableColumn<?, ?> tbColumnStatus;

    @FXML
    private TextField tfBusca;

    @FXML
    private Button btEditar;

    @FXML
    private TableColumn<?, ?> tbColumnClientes;

    @FXML
    private Button btNovo;

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void novo(ActionEvent event) {

    }

    @FXML
    void editar(ActionEvent event) {

    }

    @FXML
    void excluir(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
