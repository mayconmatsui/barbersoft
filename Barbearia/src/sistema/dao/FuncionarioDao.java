package sistema.dao;

import com.mysql.jdbc.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistema.bd.Conexao;
import sistema.model.Funcionario;

public class FuncionarioDao extends Conexao implements ICrud<Funcionario> {

  private PreparedStatement ps = null;

  @Override
  public List<Funcionario> listar(String nome) {
    try {
      List<Funcionario> lista = new LinkedList<>();
      String sql = "SELECT * FROM funcionario WHERE funcionario_nome LIKE ?";
      ps = getPreparedStatement(sql);
      ps.setString(1, nome);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        Funcionario f = new Funcionario();
        preencherLista(f, rs);
        lista.add(f);
      }
      return lista;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  @Override
  public Funcionario salvar(Funcionario item) {
    String sql = "INSERT INTO funcionario (funcionario_nome, funcionario_tipo, funcionario_cpf, funcionario_rg,"
            + " funcionario_data_nascimento, funcionario_endereco, funcionario_telefone, funcionario_email, funcionario_senha) VALUES (?,?,?,?,?,?,?,?,?)";
    try {
      ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, item.getNome());
      ps.setString(2, item.getTipo());
      ps.setString(3, item.getCPF());
      ps.setString(4, item.getRG());
      ps.setDate(5, (Date) item.getDataNascimento());
      ps.setString(6, item.getEndereco());
      ps.setString(7, item.getTelefone());
      ps.setString(8, item.getEmail());
      ps.setString(9, item.getSenha());
      ps.execute();
      ResultSet rs = ps.getGeneratedKeys();
      Funcionario idFuncionario = new Funcionario();
      if (rs.next()) {
        idFuncionario.setId(rs.getInt(1));
      }
      rs.close();
      ps.close();
      return idFuncionario;
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);

    }
    return null;
  }

  @Override
  public Funcionario alterar(Funcionario item) {
    if (item.getSenha().equals("N")) {
      String sql = "UPDATE funcionario SET funcionario_nome = ?, funcionario_tipo = ?, funcionario_cpf = ?, funcionario_rg = ?,"
              + " funcionario_data_nascimento = ?, funcionario_endereco = ?, funcionario_telefone = ?, funcionario_email = ? WHERE funcionario_id = ?";
      try {
        ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, item.getNome());
        ps.setString(2, item.getTipo());
        ps.setString(3, item.getCPF());
        ps.setString(4, item.getRG());
        ps.setDate(5, (Date) item.getDataNascimento());
        ps.setString(6, item.getEndereco());
        ps.setString(7, item.getTelefone());
        ps.setString(8, item.getEmail());
        ps.setInt(9, item.getId());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        Funcionario idFuncionario = new Funcionario();
        if (rs.next()) {
          idFuncionario.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
        return idFuncionario;
      } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);

      }
    } else {
      String sql = "UPDATE funcionario SET funcionario_nome = ?, funcionario_tipo = ?, funcionario_cpf = ?, funcionario_rg = ?,"
              + " funcionario_data_nascimento = ?, funcionario_endereco = ?, funcionario_telefone = ?, funcionario_email = ?, funcionario_senha = ? WHERE funcionario_id = ?";
      try {
        ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, item.getNome());
        ps.setString(2, item.getTipo());
        ps.setString(3, item.getCPF());
        ps.setString(4, item.getRG());
        ps.setDate(5, (Date) item.getDataNascimento());
        ps.setString(6, item.getEndereco());
        ps.setString(7, item.getTelefone());
        ps.setString(8, item.getEmail());
        ps.setString(9, item.getSenha());
        ps.setInt(10, item.getId());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        Funcionario idFuncionario = new Funcionario();
        if (rs.next()) {
          idFuncionario.setId(rs.getInt(1));
        }
        rs.close();
        ps.close();
        return idFuncionario;
      } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);

      }
    }

    return null;
  }

  @Override
  public Boolean excluir(Integer id) {
    String sql = "SELECT (SELECT COUNT(agenda_id) FROM agenda WHERE agenda_funcionario = ? OR funcionario_id = ?) as c1, "
            + "(SELECT COUNT(venda_id) FROM venda WHERE funcionario_id = ?) as c2";
    try {
        ps = getConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.setInt(2, id);
        ps.setInt(3, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        if(rs.getInt("c1") > 0 || rs.getInt("c2") > 0){
          JOptionPane.showMessageDialog(null, "Registro não pode ser deletado\n existem registros relacionados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }else{
          sql = "DELETE FROM funcionario WHERE funcionario_id = ?";
          ps = getConnection().prepareStatement(sql);
          ps.setInt(1, id);
          int contador = ps.executeUpdate();
          return contador > 0;
          
        }
        rs.close();
        ps.close();
      } catch (ClassNotFoundException | SQLException ex) {
        Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
      }
    return false;
  }

  private void preencherLista(Funcionario f, ResultSet rs) throws SQLException {
    f.setId(rs.getInt("funcionario_id"));
    f.setNome(rs.getString("funcionario_nome"));
    f.setTipo(rs.getString("funcionario_tipo"));
    f.setCPF(rs.getString("funcionario_cpf"));
    f.setRG(rs.getString("funcionario_rg"));
    f.setDataNascimento(rs.getDate("funcionario_data_nascimento"));
    f.setTelefone(rs.getString("funcionario_telefone"));
    f.setEmail(rs.getString("funcionario_email"));
    f.setSenha(rs.getString("funcionario_senha"));
    f.setEndereco(rs.getString("funcionario_endereco"));
  }

  public Boolean checkCPFCadastro(String cpf) {
    try {
      String sql = "SELECT COUNT(funcionario_id) contador FROM funcionario WHERE funcionario_cpf = ? ";
      ps = getPreparedStatement(sql);
      ps.setString(1, cpf);
      ResultSet rs = ps.executeQuery();
      rs.next();
      return Integer.parseInt(rs.getString("contador")) >= 1;

    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
    }

    return true;
  }

  public Boolean checkCPFUpdate(String cpf, Integer id) {
    try {
      String sql = "SELECT COUNT(funcionario_id) contador FROM funcionario WHERE funcionario_cpf = ? AND funcionario_id <> ?";
      ps = getPreparedStatement(sql);
      ps.setString(1, cpf);
      ps.setInt(2, id);
      ResultSet rs = ps.executeQuery();
      rs.next();
      return Integer.parseInt(rs.getString("contador")) >= 1;

    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return true;
  }

  @Override
  public Funcionario listarId(Integer id) {
    return null;
  }

}
