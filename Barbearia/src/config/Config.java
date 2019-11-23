package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Config {
  private String driver = null;
  private String url = null;
  private String user = null;
  private String pass = null;
  private File config = new File("./src/config/config.cfg");

  public Config() {
    try {
      getConfig();
    } catch (IOException ex) {
      Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
    }
  } 
  
  private void getConfig() throws IOException {
    FileReader fr = new FileReader(config);
    BufferedReader br = new BufferedReader(fr);

    String linha;

    while ((linha = br.readLine()) != null) {
      byte[] decoded = Base64.getDecoder().decode(linha);
      linha = new String(decoded);
      String[] infos = linha.split("=");
      
      if(infos[0].equals("driver")){
        driver = infos[1];
      }else if(infos[0].equals("url")){
        url = infos[1];
      }else if(infos[0].equals("user")){
        user = infos[1];
      }else if(infos[0].equals("pass")){
        pass = infos[1];
      }
    }
  }

  public String getDriver() {
    return driver;
  }

  public String getUrl() {
    return url;
  }

  public String getUser() {
    return user;
  }

  public String getPass() {
    return pass;
  }
  
  
}
