package application;

import java.io.IOException;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author matsui
 */
public class Loading extends Preloader{
  
  private Stage preStage;
  private Scene scene;

  public Loading() {
    
  }
  
  @Override
  public void init() throws IOException {
    
    Parent root = FXMLLoader.load(getClass().getResource("/application/views/splash.fxml"));
    scene = new Scene(root);
    
  }
  

  @Override
  public void start(Stage primaryStage) throws Exception {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
