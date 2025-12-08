package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CloverVilleModelManager;
import view.MainView.MainViewController;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private CloverVilleModelManager cloverVilleModelManager;

  public ViewHandler(Stage stage, CloverVilleModelManager cloverVilleModelManager)
  {
    this.stage = stage;
    this.cloverVilleModelManager = cloverVilleModelManager;
  }

  public void start()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(
          getClass().getResource("/view/MainView/MainView.fxml"));

      Parent root = loader.load();
      Scene scene = new Scene(root);

      MainViewController controller = loader.getController();
      controller.initialize(this, cloverVilleModelManager, scene);

      stage.setScene(scene);
      stage.setTitle("CloverVille App");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openView(String id)
  {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/view/MainView/MainView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainViewController controller = loader.getController();
      controller.initialize(this, cloverVilleModelManager, scene);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (stage.getScene() == null)
    {
      System.err.println("Error: Scene is null. Cannot open view " + id);
      return;
    }
  }

  public void openCommunityProgress(String id)
  {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/view/CommunityProgress/MainSetThresholdsView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainViewController controller = loader.getController();
      controller.initialize(this,  scene, cloverVilleModelManager);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (stage.getScene() == null)
    {
      System.err.println("Error: Scene is null. Cannot open view " + id);
      return;
    }
  }
}
