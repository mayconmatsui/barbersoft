/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.controller;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import sistema.dao.ClienteDao;
import sistema.model.Cliente;
import sistema.relatorio.Relatorio;

/**
 * FXML Controller class
 *
 * @author ezequiel
 */
public class AniversarianteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private List<Cliente> clientes = new ArrayList<>();
    private ObservableList<Cliente> obsCliente;

    @FXML
    private Label lbTitulo;

    @FXML
    private ComboBox<String> cbMesAniversariante;

    @FXML
    private Button btBusca;

    @FXML
    private Button btGerarRelatorio;

    @FXML
    private Button btCancelar;

    @FXML
    private TableView<Cliente> tbAniversariante;

    @FXML
    private TableColumn<Cliente, String> tbColumnNomeCliente;

    @FXML
    private TableColumn<Cliente, String> tbColumnDataNascimento;

    @FXML
    void buscar(ActionEvent event) {
        buscarAniversariantes();
    }

    @FXML
    void cancelar(ActionEvent event) {
        fecharGUI();
    }

    @FXML
    void gerarRelatorio(ActionEvent event) {
        relatorio();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarMes();
    }
    private void carregarMes() {
        ObservableList<String> meses = FXCollections.observableArrayList("Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro","Novembro", "Dezembro");
        cbMesAniversariante.setItems(meses);
    }

    private void buscarAniversariantes() {
       if(!cbMesAniversariante.getSelectionModel().isEmpty()){
           
       
        ClienteDao clienteDao = new ClienteDao();
        clientes = clienteDao.aniversariantes(cbMesAniversariante.getSelectionModel().getSelectedIndex()+1);
        
        if(clientes.isEmpty()){
        preencherTabela(clientes);    
            JOptionPane.showMessageDialog(null, "Registro não localizado");
        }else{
        preencherTabela(clientes);
        }
       }else{
          JOptionPane.showMessageDialog(null, "Selecione  mês desejado!"); 
       }
       
    
    }
    private void preencherTabela(List<Cliente> clientes) {
        tbColumnNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tbColumnDataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        obsCliente = FXCollections.observableArrayList(clientes);
        tbAniversariante.setItems(obsCliente);
    }
    

    private void fecharGUI() {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();      
    }

    private void relatorio() {
        if(!clientes.isEmpty()){
            Relatorio relatorio = new Relatorio();
            try {
                relatorio.gerarRelatorioAniversariantesMes(clientes);
            } catch (JRException ex) {
                Logger.getLogger(AniversarianteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
            JOptionPane.showMessageDialog(null, "Cliente não localizado");
        }
    }

}
