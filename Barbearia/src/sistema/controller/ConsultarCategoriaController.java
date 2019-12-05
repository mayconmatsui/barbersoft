package sistema.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class ConsultarCategoriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Categoria categoria = new Categoria();
    private List<Categoria> categorias = new ArrayList<>();
    private ObservableList<Categoria> obsCategoria;
    private Stage dialogStage;
    private CadastroProdutoController produtoController = new CadastroProdutoController();

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField tfBusca;

    @FXML
    private Button btBusca;

    @FXML
    private TableView<Categoria> tbCategoria;

    @FXML
    private TableColumn<Categoria, String> tbColumnNomeCategoria;

    @FXML
    private TableColumn<Categoria, String> tbColumnStatus;

    @FXML
    void buscar(ActionEvent event) {
        listaCategoria();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listaCategoria();
        tbCategoria.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> valor(newValue));

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    public void setTitulo(String titulo) {
        this.lbTitulo.setText(titulo);
    }

    private void listaCategoria() {
        CategoriaDao categoriaDao = new CategoriaDao();
        categorias = categoriaDao.listar("%" + tfBusca.getText() + "%");
        if (categorias.isEmpty()) {
            preencherTabela(categorias);
            JOptionPane.showMessageDialog(null, "Registro não localizado");
        } else {
            preencherTabela(categorias);
        }
    }

    //Metodo Prencher Tabela
    private void preencherTabela(List<Categoria> categorias) {
        tbColumnNomeCategoria.setCellValueFactory(new PropertyValueFactory<>("categoriaNome"));
        tbColumnStatus.setCellValueFactory(new PropertyValueFactory<>("categoriaStatus"));
        obsCategoria = FXCollections.observableArrayList(categorias);
        tbCategoria.setItems(obsCategoria);
    }

    private void valor(Categoria newValue) {
        this.categoria = newValue;
        if (this.categoria != null) { 
            this.produtoController.carregarCategoria(categoria);
            fecharInterface();
        }
    }

    private void fecharInterface() {
        dialogStage.close();
    }
    public void setProdutoController(CadastroProdutoController produtoController){
        this.produtoController = produtoController;       
    }

}
