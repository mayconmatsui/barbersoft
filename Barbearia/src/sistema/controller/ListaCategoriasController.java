
package sistema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
    private Categoria categoria = null;
    
    
    
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
        listaCategorias();
  
        
    }
    @FXML
    void novo(ActionEvent event) {
         try {
      // Carrega o arquivo fxml e cria um novo stage para a janela popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ListaClientesController.class.getResource("/sistema/view/CadastroCategoria.fxml"));
        VBox page = (VBox) loader.load();

        // Cria o palco dialogStage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Formulário Cadastro Categoria");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Define a pessoa no controller.
        CadastroCategoriaController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
        listaCategorias();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
        
        
    }

    @FXML
    void editar(ActionEvent event) {
        if(categoria != null){
        try {
      // Carrega o arquivo fxml e cria um novo stage para a janela popup.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ListaClientesController.class.getResource("/sistema/view/CadastroCategoria.fxml"));
        VBox page = (VBox) loader.load();

        // Cria o palco dialogStage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Formulário alteração Categoria");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Define a pessoa no controller.
        CadastroCategoriaController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.preencheForm(categoria);
        controller.setTitulo("Editar Categoria");
        dialogStage.showAndWait();
        listaCategorias();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
        
    }else{
            JOptionPane.showMessageDialog(null, "Selecione uma Categoria");
        }
    }

    @FXML
    void excluir(ActionEvent event) {
        excluirCliente();
       

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listaCategorias();
        categoriaSelecionada();
    }    

    private void listaCategorias() {
        CategoriaDao categoriaDao = new CategoriaDao();
        categorias = categoriaDao.listar("%"+tfBusca.getText()+"%");
        
        if(categorias.isEmpty()){
            obsCategoria.clear();
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

    private void categoriaSelecionada() {
        tbCategoria.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> valor(newValue));
    }
    private void valor(Categoria newValue) {
    this.categoria = newValue;
  }
    private void fecharInterface() {
        Stage stage = (Stage) btNovo.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }
     private void excluirCliente() {

    if (categoria == null) {
      JOptionPane.showMessageDialog(null, "Selecione uma Categoria");

    } else {
      int res = JOptionPane.showConfirmDialog(null, "Deseja Excluir o registro Selecionado?", "Excluir", JOptionPane.YES_NO_OPTION);
      if (res == JOptionPane.YES_OPTION) {
        CategoriaDao categoriaDao = new CategoriaDao();
        categoriaDao.excluir(categoria.getCategoriaId());
        listaCategorias();
      }
    }

  }
}
