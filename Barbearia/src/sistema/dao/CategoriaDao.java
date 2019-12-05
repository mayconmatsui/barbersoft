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
import sistema.model.Categoria;

public class CategoriaDao extends Conexao implements ICrud<Categoria> {

  private PreparedStatement ps = null;

  private void preencherLista(Categoria c, ResultSet rs) throws SQLException {
    c.setCategoriaId(rs.getInt("categoria_id"));
    c.setCategoriaNome(rs.getString("categoria_nome"));
    c.setCategoriaStatus(rs.getInt("categoria_status"));
  }

  @Override
  public Categoria salvar(Categoria item) {
    String sql = "INSERT INTO categoria (categoria_nome, categoria_status) VALUES (?,?)";
    try {
      ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, item.getCategoriaNome());
      ps.setString(2, String.valueOf(item.getCategoriaStatus()));
      ps.execute();
      ResultSet rs = ps.getGeneratedKeys();
      Categoria idCategoria = new Categoria();
      if (rs.next()) {
        idCategoria.setCategoriaId(rs.getInt(1));
      }
      rs.close();
      ps.close();
      return idCategoria;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Categoria alterar(Categoria item) {
    String sql = "UPDATE categoria SET categoria_nome = ?, categoria_status = ? WHERE categoria_id = ?";
    try {
      ps = getConnection().prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, item.getCategoriaNome());
      ps.setString(2, String.valueOf(item.getCategoriaStatus()));
      ps.setInt(3, item.getCategoriaId());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      Categoria idCategoria = new Categoria();
      if (rs.next()) {
        idCategoria.setCategoriaId(rs.getInt(1));
      }
      rs.close();
      ps.close();
      return idCategoria;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Boolean excluir(Integer id) {
     String sql = "SELECT COUNT(produto_id) c1 FROM produto WHERE categoria_id = ?";
    try {
      ps = getConnection().prepareStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      rs.next();
      if (rs.getInt("c1") > 0) {
        JOptionPane.showMessageDialog(null, "Registro não pode ser deletado\n existem registros relacionados", "Alerta", JOptionPane.WARNING_MESSAGE);
      } else {
        sql = "DELETE FROM categoria WHERE categoria_id = ?";
        ps = getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        int contador = ps.executeUpdate();
        return contador > 0;
      }
      rs.close();
      ps.close();
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public List<Categoria> listar(String nome) {
    List<Categoria> lista = new LinkedList<>();
    String sql = "SELECT * FROM categoria WHERE categoria_nome LIKE ?";
    try {
      ps = getPreparedStatement(sql);
      ps.setString(1, nome);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Categoria c = new Categoria();
        preencherLista(c, rs);
        lista.add(c);
      }
      return lista;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Categoria listarId(Integer id) {
    String sql = "SELECT * FROM categoria WHERE categoria_id = ?";
    try {
      ps = getPreparedStatement(sql);
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      rs.next();
      Categoria c = new Categoria();
      preencherLista(c, rs);
      return c;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}
