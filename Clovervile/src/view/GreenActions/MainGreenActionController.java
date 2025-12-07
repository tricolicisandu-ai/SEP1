package view.GreenActions;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGreenActionController extends Application
{
  public void start(Stage window) throws IOException
  {
    window.setTitle("MainGreenActionView.FXML");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("AddGreenActionView.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.show();
  }
}

