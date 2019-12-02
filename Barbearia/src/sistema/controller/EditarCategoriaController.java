package sistema.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
public class EditarCategoriaController implements Initializable {

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

    private void salvar() {
        if (validarCampos()) {
            if (!cbStatus.isSelected()) {
                int res = JOptionPane.showConfirmDialog(null, "Deseja habilitar o Status da Categoria", "Status", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    cbStatus.setSelected(true);
                }

            }
            Categoria categoria = new Categoria();
            CategoriaDao categoriaDao = new CategoriaDao();
            carregarDadosCampos(categoria);
            if (categoriaDao.alterar(categoria) == null) {
                JOptionPane.showMessageDialog(null, "Erro ao Editar Categoria", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Categoria Editada com Sucesso!", "Editar", JOptionPane.INFORMATION_MESSAGE);
                fecharInterface();
            }

        }
    }

    private void carregarDadosCampos(Categoria categoria) {
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

    public void adicionarValorCampos(Categoria c) {
        tfNomeCategoria.setText(c.getCategoriaNome());
        if (c.getCategoriaStatus() == 1) {
            cbStatus.setSelected(true);
        } else {
            cbStatus.setSelected(false);
        }
    }
}
