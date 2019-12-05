package sistema.dao;

import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistema.bd.Conexao;
import sistema.model.Produto;

public class ProdutoDao extends Conexao implements ICrud<Produto> {

  private PreparedStatement ps = null;

  private void preencherLista(Produto p, ResultSet rs) throws SQLException {
    CategoriaDao categoriaDao = new CategoriaDao();
    p.setId(rs.getInt("produto_id"));
    p.setCategoria(categoriaDao.listarId(rs.getInt("categoria_id")));
    p.setNome(rs.getString("produto_nome"));
    p.setDescricao(rs.getString("produto_descricao"));
    p.setValorCusto(rs.getDouble("produto_valor_custo"));
    p.setValorVenda(rs.getDouble("produto_valor_venda"));
    p.setQuantidade(rs.getInt("produto_quantidade"));
    p.setTipo(rs.getInt("produto_tipo"));
    p.setStatus(rs.getInt("produto_status"));
  }

  @Override
  public Produto salvar(Produto item) {
    String sql = "INSERT INTO produto (categoria_id, produto_nome, produto_descricao, produto_valor_custo, produto_valor_venda, produto_quantidade, produto_tipo, produto_status) VALUES (?,?,?,?,?,?,?,?)";
    try {
      ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setInt(1, item.getCategoria().getCategoriaId());
      ps.setString(2, item.getNome());
      ps.setString(3, item.getDescricao());
      ps.setDouble(4, item.getValorCusto());
      ps.setDouble(5, item.getValorVenda());
      ps.setInt(6, item.getQuantidade());
      ps.setString(7, item.getTipo().toString());
      ps.setString(8, item.getStatus().toString());
      ps.execute();
      ResultSet rs = ps.getGeneratedKeys();
      Produto idProduto = new Produto();
      if (rs.next()) {
        idProduto.setId(rs.getInt(1));
      }
      rs.close();
      ps.close();
      return idProduto;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Produto alterar(Produto item) {
    String sql = "UPDATE produto SET categoria_id = ?, produto_nome = ?, produto_descricao = ?, produto_valor_custo = ?, produto_valor_venda = ?, "
            + "produto_tipo = ?, produto_status = ? WHERE produto_id = ?";
    try {
      ps = getConnection().prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
      ps.setInt(1, item.getCategoria().getCategoriaId());
      ps.setString(2, item.getNome());
      ps.setString(3, item.getDescricao());
      ps.setDouble(4, item.getValorCusto());
      ps.setDouble(5, item.getValorVenda());
      ps.setString(6, item.getTipo().toString());
      ps.setString(7, item.getStatus().toString());
      ps.setInt(8, item.getId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      Produto idProduto = new Produto();
      if (rs.next()) {
        idProduto.setId(rs.getInt(1));
      }
      rs.close();
      ps.close();
      return idProduto;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Boolean excluir(Integer id) {
     String sql = "SELECT COUNT(produto_id) c1 FROM item_venda WHERE produto_id = ?";
    try {
      ps = getConnection().prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      rs.next();
      if (rs.getInt("c1") > 0) {
        JOptionPane.showMessageDialog(null, "Registro não pode ser deletado\n existem registros relacionados", "Alerta", JOptionPane.WARNING_MESSAGE);
      } else {
        sql = "DELETE FROM produto WHERE produto_id = ?";
        ps = getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        int contador = ps.executeUpdate();
        return contador > 0;
      }
      rs.close();
      ps.close();
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public List<Produto> listar(String nome) {
    List<Produto> lista = new LinkedList<>();
    String sql = "SELECT * FROM produto WHERE produto_nome LIKE ?";
    try {
      ps = getPreparedStatement(sql);
      ps.setString(1, nome);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Produto p = new Produto();
        preencherLista(p, rs);
        lista.add(p);
      }
      return lista;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Produto listarId(Integer id) {
    return null;
  }
}
