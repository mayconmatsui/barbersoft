/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.bd;

import config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author marcos
 */
public abstract class Conexao {
  
  Config config = new Config();

  private Connection con = null;
  private String driver = config.getDriver();
  private String url    = config.getUrl();
  private String user   = config.getUser();
  private String pass   = config.getPass();
  
  public Connection getConnection() throws ClassNotFoundException, SQLException {
    if (con == null) {
      Class.forName(driver);
      con = DriverManager.getConnection(url, user, pass);
    }
    return con;
  }

  public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
    return getConnection().prepareStatement(sql);
  }

}
