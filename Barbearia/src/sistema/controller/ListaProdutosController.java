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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.ProdutoDao;
import sistema.model.Produto;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class ListaProdutosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Produto> produtos = new ArrayList<>();
    private static Produto produto = null;
    private ObservableList<Produto> obsProduto;
    @FXML
    private TableView<Produto> tbProduto;

    @FXML
    private TableColumn<Produto, String> tbColumnQuantidade;

    @FXML
    private Button btBusca;

    @FXML
    private TableColumn<Produto, String> tbColumnValorVenda;

    @FXML
    private Button btExcluir;

    @FXML
    private TableColumn<Produto, String> tbColumnStatus;

    @FXML
    private TableColumn<Produto, String> tbColumnValorCusto;

    @FXML
    private TextField tfBusca;

    @FXML
    private TableColumn<Produto, String> tbColumnNomeProduto;

    @FXML
    private Button btEditar;

    @FXML
    private Button btNovo;

    @FXML
    void buscar(ActionEvent event) {
        listaProdutos();
    }

    @FXML
    void novo(ActionEvent event) {
        try {
            Parent layout = FXMLLoader.load(getClass().getResource("/sistema/view/CadastroProduto.fxml"));
            Scene cena = new Scene(layout);
            Stage stage = new Stage();
            stage.setScene(cena);
            stage.showAndWait();
            listaProdutos();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void editar(ActionEvent event) {
        if(verificaProduto()){
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ListaClientesController.class.getResource("/sistema/view/CadastroProduto.fxml"));
            VBox page = (VBox) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulário alteração Produto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa no controller.
            CadastroProdutoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.preencheForm(produto);
            controller.setTitulo("Editar Produto");

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
    }

    @FXML
    void excluir(ActionEvent event) {

        excluirProduto();
        listaProdutos();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        listaProdutos();
        produtoSelecionado();
    }

    private void listaProdutos() {
        ProdutoDao produtoDao = new ProdutoDao();
        produtos = produtoDao.listar("%" + tfBusca.getText() + "%");
        if (produtos.isEmpty()) {
            preencherTabela(produtos);
            JOptionPane.showMessageDialog(null, "Registro não localizado");
        } else {
            preencherTabela(produtos);
        }
    }

    //Metodo Prencher Tabela
    private void preencherTabela(List<Produto> produtos) {
        tbColumnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbColumnValorVenda.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        tbColumnValorCusto.setCellValueFactory(new PropertyValueFactory<>("valorCusto"));
        tbColumnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        tbColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        obsProduto = FXCollections.observableArrayList(produtos);
        tbProduto.setItems(obsProduto);
    }
    
    //Metodo coletar Informação Cliente Selecionado 
    private void produtoSelecionado() {
        tbProduto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> valor(newValue));
    }

    private void valor(Produto newValue) {
        this.produto = newValue;
    }

    private void excluirProduto() {

        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Selecione um Produto");

        } else {
            int res = JOptionPane.showConfirmDialog(null, "Deseja Excluir o registro Selecionado?", "Excluir", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                ProdutoDao produtoDao = new ProdutoDao();
                produtoDao.excluir(produto.getId());
                listaProdutos();
            }
        }

    }

    private boolean verificaProduto() {
        if (produto == null) {
            JOptionPane.showMessageDialog(null, "Selecione um Produto");
            return false;
        } else {
            return true;
        }
    }

}
