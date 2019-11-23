/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.form;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistema.dao.ClienteDao;
import sistema.dao.FuncionarioDao;
import sistema.model.Cliente;
import sistema.model.Funcionario;
import sistema.utils.Utils;

/**
 *
 * @author matsui
 */
public class FormCadClientes extends javax.swing.JFrame {

  private Boolean edit = false;
  private FormClientes formClientes;
  private Integer id = 0;

  /**
   * Creates new form FormClientes
   */
  public FormCadClientes() {
    setTitle("Sistema Barbearia - Clientes");
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    tfNome = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    tfRG = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    tfCPF = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    tfEmail = new javax.swing.JTextField();
    tfEndereco = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    tfTelefone = new javax.swing.JTextField();
    tfDataNascimento = new javax.swing.JFormattedTextField();
    btSalvar = new javax.swing.JButton();
    btCancelar = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes"));

    jLabel1.setText("Nome");

    jLabel2.setText("RG");

    jLabel3.setText("CPF");

    jLabel4.setText("Data Nascimento");

    jLabel5.setText("Email");

    jLabel6.setText("Endereço");

    jLabel7.setText("Telefone");

    try {
      tfDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
    } catch (java.text.ParseException ex) {
      ex.printStackTrace();
    }

    btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Yes.png"))); // NOI18N
    btSalvar.setText("Salvar");
    btSalvar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btSalvarActionPerformed(evt);
      }
    });

    btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Delete.png"))); // NOI18N
    btCancelar.setText("Cancelar");
    btCancelar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btCancelarActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel5)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(tfEndereco, javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCancelar))
              .addComponent(tfEmail, javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel1)
                  .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel4)
                  .addComponent(tfDataNascimento, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel2)
                  .addComponent(tfRG, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(tfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(tfTelefone)
                  .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jLabel7)
                    .addGap(0, 0, Short.MAX_VALUE)))))
            .addGap(15, 15, 15))))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel4)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGap(2, 2, 2)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(tfDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel2)
          .addComponent(jLabel7)
          .addComponent(jLabel3))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(tfTelefone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(tfRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addGap(18, 18, 18)
        .addComponent(jLabel6)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
        .addComponent(jLabel5)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(btSalvar)
          .addComponent(btCancelar))
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    pack();
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
    this.dispose();
  }//GEN-LAST:event_btCancelarActionPerformed

  private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
    if (edit) {
      try {
        editarCliente();
      } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FormCadClientes.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      try {
        cadastrarCliente();
      } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FormCadClientes.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }//GEN-LAST:event_btSalvarActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(FormCadClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(FormCadClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(FormCadClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(FormCadClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new FormCadClientes().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btCancelar;
  private javax.swing.JButton btSalvar;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JTextField tfCPF;
  private javax.swing.JFormattedTextField tfDataNascimento;
  private javax.swing.JTextField tfEmail;
  private javax.swing.JTextField tfEndereco;
  private javax.swing.JTextField tfNome;
  private javax.swing.JTextField tfRG;
  private javax.swing.JTextField tfTelefone;
  // End of variables declaration//GEN-END:variables

  public void preencheFormulario(Cliente cliente) {
    tfNome.setText(cliente.getNome());
    tfDataNascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDataNascimento()));
    tfRG.setText(cliente.getRG());
    tfCPF.setText(cliente.getCPF());
    tfTelefone.setText(cliente.getTelefone());
    tfEndereco.setText(cliente.getEndereco());
    tfEmail.setText(cliente.getEmail());
  }

  public void setEdit(Boolean edit) {
    this.edit = edit;
  }

  public void setFormClientes(FormClientes formClientes) {
    this.formClientes = formClientes;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  private void editarCliente() throws ClassNotFoundException, SQLException {
    if (id <= 0) {
      JOptionPane.showMessageDialog(this, "Nenhum Registro foi Informado", "Erro", JOptionPane.ERROR_MESSAGE);
    } else if (tfNome.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O campo nome deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfDataNascimento.getText().isEmpty() || tfDataNascimento.getText().equals("  /  /    ")) {
      JOptionPane.showMessageDialog(this, "O campo data de nascimento deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfCPF.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O campo CPF deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (!Utils.isCPF(tfCPF.getText())) {
      JOptionPane.showMessageDialog(this, "CPF inválido, informar somentes números", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfTelefone.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O campo telefone deve Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfEndereco.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O Campor endereço deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfEmail.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O campo email deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (!Utils.isEmail(tfEmail.getText())) {
      JOptionPane.showMessageDialog(this, "Email inválido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else {
      Cliente cliente = new Cliente();
      ClienteDao clienteDao = new ClienteDao();

      if (clienteDao.checkCPFUpdate(tfCPF.getText(), id)) {
        JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      } else {
        cliente.setId(id);
        cliente.setNome(tfNome.getText());
        cliente.setRG(tfRG.getText());
        cliente.setCPF(tfCPF.getText());
        cliente.setTelefone(tfTelefone.getText());
        cliente.setEndereco(tfEndereco.getText());
        cliente.setEmail(tfEmail.getText());

        try {
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
          java.util.Date dataJava = df.parse(tfDataNascimento.getText());
          java.sql.Date dataSql = new Date(dataJava.getTime());
          cliente.setDataNascimento(dataSql);
        } catch (ParseException ex) {
          Logger.getLogger(FormClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (clienteDao.alterar(cliente) == null) {
          System.out.println("Deu erro");
        } else {
          formClientes.consultarCliente();
          this.dispose();
        }

      }
    }
  }

  private void cadastrarCliente() throws ClassNotFoundException, SQLException {
    if (tfNome.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O campo nome deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfDataNascimento.getText().isEmpty() || tfDataNascimento.getText().equals("  /  /    ")) {
      JOptionPane.showMessageDialog(this, "O campo data de nascimento deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfCPF.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O campo CPF deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (!Utils.isCPF(tfCPF.getText())) {
      JOptionPane.showMessageDialog(this, "CPF inválido, informar somentes números", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfTelefone.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O campo telefone deve Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfEndereco.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O Campor endereço deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (tfEmail.getText().isEmpty()) {
      JOptionPane.showMessageDialog(this, "O campo email deve ser Preenchido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else if (!Utils.isEmail(tfEmail.getText())) {
      JOptionPane.showMessageDialog(this, "Email inválido", "Atenção", JOptionPane.INFORMATION_MESSAGE);
    } else {
      Cliente cliente = new Cliente();
      ClienteDao clienteDao = new ClienteDao();

      if (clienteDao.checkCPFCadastro(tfCPF.getText())) {
        JOptionPane.showMessageDialog(this, "CPF já cadastrado.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
      } else {
        cliente.setNome(tfNome.getText());
        cliente.setRG(tfRG.getText());
        cliente.setCPF(tfCPF.getText());
        cliente.setTelefone(tfTelefone.getText());
        cliente.setEndereco(tfEndereco.getText());
        cliente.setEmail(tfEmail.getText());
        try {
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
          java.util.Date dataJava = df.parse(tfDataNascimento.getText());
          java.sql.Date dataSql = new Date(dataJava.getTime());
          cliente.setDataNascimento(dataSql);
        } catch (ParseException ex) {
          Logger.getLogger(FormClientes.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (clienteDao.salvar(cliente) == null) {
          System.out.println("Deu erro");
        } else {
          formClientes.consultarCliente();
          this.dispose();
        }

      }
    }
  }

}