package view.Resident;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddResidentViewFXML extends Application
{
  public void start(Stage window) throws IOException
  {
    window.setTitle("AddResidentView GUI FXML");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("AddResidentView.fxml"));
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.show();
  }
}
