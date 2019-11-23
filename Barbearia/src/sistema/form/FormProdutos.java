/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.form;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import sistema.dao.ProdutoDao;
import sistema.model.Produto;

/**
 *
 * @author marcos
 */
public class FormProdutos extends javax.swing.JFrame {

  DefaultTableModel dtmTabela = new DefaultTableModel(null, new String[]{"ID", "Nome", "Categoria"});

  private List<Produto> produtos;
  private ListSelectionModel lsmProdutos;
  private Produto produtoSelecionado = new Produto();
  private FormVenda formVenda;

  /**
   * Creates new form FormConsultaCliente
   */
  public FormProdutos() {
    initComponents();
    consultarProduto();
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
    jScrollPane1 = new javax.swing.JScrollPane();
    tabela = new javax.swing.JTable();
    jLabel1 = new javax.swing.JLabel();
    tfConsulta = new javax.swing.JTextField();
    btConsultar = new javax.swing.JButton();
    btIncluir = new javax.swing.JButton();
    btEditar = new javax.swing.JButton();
    btExcluir = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos / Serviços"));

    tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    lsmProdutos = tabela.getSelectionModel();
    lsmProdutos.addListSelectionListener(new ListSelectionListener(){
      public void valueChanged(ListSelectionEvent e){
        if(!e.getValueIsAdjusting()){
          linhaTabela(tabela);
        }
      }
    });
    tabela.setModel(dtmTabela);
    jScrollPane1.setViewportView(tabela);

    jLabel1.setText("Localizar");

    btConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search-32.png"))); // NOI18N
    btConsultar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btConsultarActionPerformed(evt);
      }
    });

    btIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/New document.png"))); // NOI18N
    btIncluir.setText("Incluir");
    btIncluir.setToolTipText("");
    btIncluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btIncluir.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    btIncluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    btIncluir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btIncluirActionPerformed(evt);
      }
    });

    btEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Modify.png"))); // NOI18N
    btEditar.setText("Editar");
    btEditar.setToolTipText("");
    btEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btEditar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    btEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    btEditar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btEditarActionPerformed(evt);
      }
    });

    btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Trash.png"))); // NOI18N
    btExcluir.setText("Deletar");
    btExcluir.setToolTipText("");
    btExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btExcluir.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    btExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    btExcluir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btExcluirActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel1)
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tfConsulta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(38, 38, 38)
            .addComponent(btIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tfConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(btExcluir)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addComponent(btConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(btEditar)
          .addComponent(btIncluir))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );

    setSize(new java.awt.Dimension(758, 454));
    setLocationRelativeTo(null);
  }// </editor-fold>//GEN-END:initComponents

  private void btConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultarActionPerformed
    consultarProduto();
  }//GEN-LAST:event_btConsultarActionPerformed

  private void btIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirActionPerformed
    FormCadProduto formCadProduto = new FormCadProduto();
    formCadProduto.setFormProduto(this);
    formCadProduto.setVisible(true);
  }//GEN-LAST:event_btIncluirActionPerformed

  private void btEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarActionPerformed
    if (tabela.getSelectedRow() != -1) {
      FormCadProduto formEditProduto = new FormCadProduto();
      formEditProduto.setId(produtoSelecionado.getId());
      formEditProduto.preencheFormulario(produtoSelecionado);
      formEditProduto.setEdit(true);
      formEditProduto.setFormProduto(this);
      formEditProduto.setVisible(true);
    } else {
      JOptionPane.showMessageDialog(this, "Um registro deve ser selecionado para alteração", "Alerta", JOptionPane.WARNING_MESSAGE);
    }
  }//GEN-LAST:event_btEditarActionPerformed

  private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
    if (tabela.getSelectedRow() != -1) {
      int res = JOptionPane.showConfirmDialog(null, "Deseja Excluir o registro Selecionado?", "Excluir", JOptionPane.YES_NO_OPTION);
      if (res == JOptionPane.YES_OPTION) {
        ProdutoDao produtoDao = new ProdutoDao();
        if (produtoDao.excluir(produtoSelecionado.getId())) {
          JOptionPane.showMessageDialog(this, "Registro deletado com Sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
          consultarProduto();
        }
      }
    } else {
      JOptionPane.showMessageDialog(this, "Um registro deve ser selecionado para exclusão", "Alerta", JOptionPane.WARNING_MESSAGE);
    }
  }//GEN-LAST:event_btExcluirActionPerformed

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
      java.util.logging.Logger.getLogger(FormProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(FormProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(FormProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(FormProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new FormProdutos().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btConsultar;
  private javax.swing.JButton btEditar;
  private javax.swing.JButton btExcluir;
  private javax.swing.JButton btIncluir;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tabela;
  private javax.swing.JTextField tfConsulta;
  // End of variables declaration//GEN-END:variables

  public void setForm(FormVenda formVenda) {
    this.formVenda = formVenda;
  }

  public void consultarProduto(){
    ProdutoDao produtoDao = new ProdutoDao();
    produtos = produtoDao.listar("%" + tfConsulta.getText() + "%");
    preencherTabela(produtos);
  }

  private void preencherTabela(List<Produto> produto) {
    while (dtmTabela.getRowCount() > 0) {
      dtmTabela.setRowCount(0);
    }
    if (produtos.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Registro não localizado.");
    } else {
      String[] linha = new String[]{null, null};
      for (int i = 0; i < produtos.size(); i++) {
        dtmTabela.addRow(linha);
        dtmTabela.setValueAt(produtos.get(i).getId(), i, 0);
        dtmTabela.setValueAt(produtos.get(i).getNome(), i, 1);
        dtmTabela.setValueAt(produtos.get(i).getCategoria().getCategoriaNome(), i, 2);
      }
    }
  }

  public void linhaTabela(JTable tabela) {
    if (tabela.getSelectedRow() != -1) {
      produtoSelecionado.setId(produtos.get(tabela.getSelectedRow()).getId());
      produtoSelecionado.setNome(produtos.get(tabela.getSelectedRow()).getNome());
      produtoSelecionado.setCategoria(produtos.get(tabela.getSelectedRow()).getCategoria());
      produtoSelecionado.setDescricao(produtos.get(tabela.getSelectedRow()).getDescricao());
      produtoSelecionado.setValorCusto(produtos.get(tabela.getSelectedRow()).getValorCusto());
      produtoSelecionado.setValorVenda(produtos.get(tabela.getSelectedRow()).getValorVenda());
      produtoSelecionado.setQuantidade(produtos.get(tabela.getSelectedRow()).getQuantidade());
      produtoSelecionado.setTipo(produtos.get(tabela.getSelectedRow()).getTipo());
      produtoSelecionado.setStatus(produtos.get(tabela.getSelectedRow()).getStatus());
    } else {
      produtoSelecionado.setId(0);
      produtoSelecionado.setNome("");
    }
  }

}
