/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.ProdutoDao;
import sistema.model.Categoria;
import sistema.model.Produto;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class CadastroProdutoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Produto produto = new Produto();
    private Categoria categoria = new Categoria();
    private Stage dialogStage;

    @FXML
    private Label lbTitulo;

    @FXML
    private ComboBox<String> cbTipoProduto;

    @FXML
    private TextField tfValorCusto;

    @FXML
    private TextArea taDescricao;

    @FXML
    private TextField tfValorVenda;

    @FXML
    private CheckBox cbStatus;

    @FXML
    private TextField tfTipoProduto;

    @FXML
    private TextField tfCategoria;

    @FXML
    private Button btBuscarTipoProduto;

    @FXML
    private Button btBuscarCategoria;

    @FXML
    private Button btNovo;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfQuantidade;

    @FXML
    void novo(ActionEvent event) {
        salvar();
        

    }

    @FXML
    void cancelar(ActionEvent event) {
        fecharInterface();

    }

    @FXML
    void buscarCategoria(ActionEvent event) {
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ListaClientesController.class.getResource("/sistema/view/ConsultarCategoria.fxml"));
            VBox page = (VBox) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Formulário Consulta Categoria");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner((Stage) ((Button) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            Stage stage = (Stage) btBuscarCategoria.getScene().getWindow();

            // Define a pessoa no controller.
            ConsultarCategoriaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProdutoController(this);
            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
            System.out.println(categoria.getCategoriaNome());
            System.out.println(categoria.getCategoriaId());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        carregarComboBox();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTitulo(String titulo) {
        this.lbTitulo.setText(titulo);
    }

    public void preencheForm(Produto produto) {
        this.produto = produto;
        this.categoria = produto.getCategoria();
        tfCategoria.setText(produto.getCategoria().getCategoriaNome());
        tfNome.setText(produto.getNome());
        tfValorCusto.setText(String.valueOf(produto.getValorCusto()));
        tfValorVenda.setText(String.valueOf(produto.getValorVenda()));
        tfQuantidade.setText(String.valueOf(produto.getQuantidade()));
        String tipo;
        if (produto.getTipo() == 0) {
            tipo = "Compra";
        } else {
            tipo = "Venda";
        }
        cbTipoProduto.setValue(tipo);
        if (produto.getStatus() != 0) {
            cbStatus.setSelected(true);
        } else {
            cbStatus.setSelected(false);
        }
        taDescricao.setText(produto.getDescricao());
    }

    private void salvar() {
        if (validarCampos()) {
            ProdutoDao funcionarioDao = new ProdutoDao();
            carregarDadosCampos();
            if (produto.getId() == null) {
                if (funcionarioDao.salvar(this.produto) == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Produto", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com Sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    fecharInterface();
                }
            } else {
                if (funcionarioDao.alterar(this.produto) == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao Editar Produto", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Produto editado com Sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    fecharInterface();
                }
            }
        }
    }

    private boolean validarCampos() {
        if (tfCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Categoria deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            tfCategoria.setText(null);
            tfCategoria.requestFocus();
            return false;
        } else if (tfNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            tfNome.setText(null);
            tfNome.requestFocus();
            return false;
        } else if (tfValorCusto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Valor Custo deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            tfValorCusto.setText(null);
            tfValorCusto.requestFocus();
            return false;
        } else if (tfValorVenda.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Valor Venda deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            tfValorVenda.setText(null);
            tfValorVenda.requestFocus();
            return false;
        } else if (tfQuantidade.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Quantidade deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            tfQuantidade.setText(null);
            tfQuantidade.requestFocus();
            return false;
        } else if (cbTipoProduto.getSelectionModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo Tipo de Produto deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            cbTipoProduto.requestFocus();
            return false;
        } else if (cbStatus.isSelected() == false) {
            int res = JOptionPane.showConfirmDialog(null, "Deseja Habilitar o Produto", "Atenção", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                cbStatus.requestFocus();
                return false;
            }
        }

        return true;
    }

    private void carregarDadosCampos() {
        produto.setCategoria(categoria);
        System.out.println(categoria.getCategoriaId());
        System.out.println(categoria.getCategoriaNome());
        System.out.println(categoria.getCategoriaStatus());
        produto.setNome(tfNome.getText());
        Double custo = Double.parseDouble(tfValorCusto.getText());
        produto.setValorCusto(custo);
        Double venda = Double.parseDouble(tfValorVenda.getText());
        produto.setValorVenda(venda);
        produto.setQuantidade(Integer.parseInt(tfQuantidade.getText()));
        produto.setTipo(cbTipoProduto.getSelectionModel().getSelectedIndex());
        if (!cbStatus.isSelected() == true) {
            produto.setStatus(0);
        } else {
            produto.setStatus(1);
        }
        produto.setDescricao(taDescricao.getText());

    }

    private void fecharInterface() {
        Stage stage = (Stage) btNovo.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

    public void carregarComboBox() {
        ObservableList<String> tipo = FXCollections.observableArrayList("Compra", "Venda");
        cbTipoProduto.setItems(tipo);
    }

    public void carregarCategoria(Categoria categoria) {
        this.categoria = categoria;
        tfCategoria.setText(categoria.getCategoriaNome());
    }
}
