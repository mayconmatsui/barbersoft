/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.utils;

import application.controllers.SplashController;
import javafx.application.Preloader;
import javafx.application.Preloader.ProgressNotification;
import javafx.application.Preloader.StateChangeNotification;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Simple Preloader Using the ProgressBar Control
 *
 * @author matsui
 */
public class NewPreloader extends Preloader {

  private Stage preloaderStage;
  private Scene scene;

  @Override
  public void init() throws Exception {
    Parent root1 = FXMLLoader.load(getClass().getResource("/sistema/view/Splash.fxml"));
    scene = new Scene(root1);
    scene.setFill(null);
    scene.getRoot().setStyle("-fx-background-color: transparent;");
    //scene = new Scene(root1, 700, 400, Color.TRANSPARENT); 
    root1.setStyle("-fx-background-color: transparent;");

  }

  @Override
  public void start(Stage stage) throws Exception {
    this.preloaderStage = stage;

    // Set preloader scene and show stage.
    preloaderStage.setScene(scene);
    preloaderStage.initStyle(StageStyle.TRANSPARENT);
    preloaderStage.show();
//    this.stage = stage;
//    stage.setScene(createPreloaderScene());
//    stage.show();
  }

  @Override
  public void handleStateChangeNotification(StateChangeNotification scn) {
    if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
      System.out.println("BEFORE_START");
      preloaderStage.hide();
    }
  }

  @Override
  public void handleApplicationNotification(Preloader.PreloaderNotification info) {

    if (info instanceof ProgressNotification) {
      SplashController.progress.setText(((ProgressNotification) info).getProgress() * 10 + "%");
      System.out.println("Value@ :" + ((ProgressNotification) info).getProgress());
    }
  }
}
