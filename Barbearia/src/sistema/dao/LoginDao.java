package sistema.dao;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Util;
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
import sistema.utils.Utils;

public class LoginDao extends Conexao implements IAutenticacao{

  private PreparedStatement ps = null;

  @Override
  public Boolean autenticar(String usuario, String senha) {
    senha = Utils.geraSenha(senha);
    String sql = "SELECT * FROM funcionario WHERE funcionario_email = ? AND funcionario_senha = ? AND funcionario_tipo = ? OR funcionario_tipo = ?";
    try {
      String s = new String(Utils.geraSenha(senha));
      ps = getPreparedStatement(sql);
      ps.setString(1, usuario);
      ps.setString(2, senha);
      ps.setString(3, "Administrador");
      ps.setString(4, "Atendente");
      ResultSet rs = ps.executeQuery();
      if(rs.next()){
        return true;
      }else{
        return false;
      }
      
    } catch (ClassNotFoundException | SQLException ex) {
      Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;    
  }
}
