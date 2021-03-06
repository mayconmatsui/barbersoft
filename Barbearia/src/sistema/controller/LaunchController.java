package sistema.controller;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sistema.utils.NewPreloader;

public class LaunchController extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/sistema/view/Login.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    
//    stage.initStyle(StageStyle.UNDECORATED);
    stage.show();
  }

  @Override
  public void init() throws Exception {
    for (int i = 1; i <= 100; i++) {
      double progress = (double) i / 10;
      LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
      Thread.sleep(5);
    }
  }

  public static void main(String[] args) {
    LauncherImpl.launchApplication(LaunchController.class, NewPreloader.class, args);
  }

}
