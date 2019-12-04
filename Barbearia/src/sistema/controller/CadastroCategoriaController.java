package sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import sistema.dao.CategoriaDao;
import sistema.model.Categoria;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class CadastroCategoriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Categoria categoria = new Categoria();
    private Stage dialogStage;
    @FXML
    private Label lbTitulo;

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
        salvar();
    }

    @FXML
    void cancelar(ActionEvent event) {
        fecharInterface();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTitulo(String titulo) {
        lbTitulo.setText(titulo);
    }

    public void preencheForm(Categoria categoria) {
        this.categoria = categoria;
        tfNomeCategoria.setText(categoria.getCategoriaNome());
        if (categoria.getCategoriaStatus() != 0) {
            cbStatus.setSelected(true);
        } else {
            cbStatus.setSelected(false);
        }
    }

    private void salvar() {

        if (validarCampos()) {
            if (!cbStatus.isSelected()) {
                int res = JOptionPane.showConfirmDialog(null, "Deseja habilitar o Status da Categoria", "Status", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    cbStatus.setSelected(true);
                }
            }
            CategoriaDao categoriaDao = new CategoriaDao();
            carregarDadosCampos();
            if (categoria.getCategoriaId() == null) {
                if (categoriaDao.salvar(categoria) == null) {
                    JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Categoria", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Categoria cadastrada com Sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                    fecharInterface();
                }
            }else {
                if (categoriaDao.alterar(categoria) == null) {
                JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Categoria", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Categoria cadastrada com Sucesso!", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                fecharInterface();
            }
            }
        }

    }

    private void carregarDadosCampos() {
        categoria.setCategoriaNome(tfNomeCategoria.getText());
        //operador condicional Ternário
        int res = cbStatus.isSelected() ? 1 : 0;
        categoria.setCategoriaStatus(res);
    }

    private boolean validarCampos() {
        if (tfNomeCategoria.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "O campo nome deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            tfNomeCategoria.setText(null);
            tfNomeCategoria.requestFocus();
            return false;
        }
        return true;
    }

    private void fecharInterface() {
        Stage stage = (Stage) btNovo.getScene().getWindow();
        stage.close();
    }

}
