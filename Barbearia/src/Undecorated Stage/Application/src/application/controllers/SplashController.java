/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author matsui
 */
public class SplashController implements Initializable{
  
  @FXML
  private Label label;
  public static Label progress;

  public void setLabel(String text) {
    this.label.setText(text);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    progress = label;    
  }
  

}
