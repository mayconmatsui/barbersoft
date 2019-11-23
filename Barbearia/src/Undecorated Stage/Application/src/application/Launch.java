package application;

import application.controllers.SplashController;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launch extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/application/views/Login.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.initStyle(StageStyle.UNDECORATED);
    stage.show();
  }

  @Override
  public void init() throws Exception {
    for (int i = 1; i <= 100; i++) {
      double progress = (double) i / 10;
      LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
      Thread.sleep(20);
    }
  }

  public static void main(String[] args) {
    LauncherImpl.launchApplication(Launch.class, NewPreloader.class, args);
  }

}