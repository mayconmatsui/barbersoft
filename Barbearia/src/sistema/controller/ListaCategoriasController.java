
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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.CategoriaDao;
import sistema.model.Categoria;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class ListaCategoriasController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Categoria> categorias = new ArrayList<>();
    private ObservableList<Categoria> obsCategoria;
   @FXML
    private TableView<Categoria> tbCategoria;

    @FXML
    private Button btBusca;

    @FXML
    private Button btExcluir;

    @FXML
    private TableColumn<Categoria, String> tbColumnStatus;

    @FXML
    private TextField tfBusca;

    @FXML
    private Button btEditar;

    @FXML
    private TableColumn<Categoria, String> tbColumnNomeCategoria;

    @FXML
    private Button btNovo;

    @FXML
    void buscar(ActionEvent event) {
  
        
    }
    @FXML
    void novo(ActionEvent event) {
        fecharInterface();
        
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
       tbCategoria.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> categoriaSelecionada(newValue));
    }    

    private void listaCategorias() {
        CategoriaDao categoriaDao = new CategoriaDao();
        categorias = categoriaDao.listar("%"+tfBusca.getText()+"%");
        if(categorias.isEmpty()){
            preencherTabela(categorias);
            JOptionPane.showMessageDialog(null, "Registro não localizado");
        }else{
        preencherTabela(categorias);
        }
    }

    private void preencherTabela(List<Categoria> categorias) {
        tbColumnNomeCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaNome"));
        tbColumnStatus.setCellValueFactory(new PropertyValueFactory<>("categoriaStatus"));
        obsCategoria = FXCollections.observableArrayList(categorias);
        tbCategoria.setItems(obsCategoria);
    }

    private Categoria categoriaSelecionada(Categoria categoria) {
        return categoria;  
    }
    private void fecharInterface() {
        Stage stage = (Stage) btNovo.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }
    
}
