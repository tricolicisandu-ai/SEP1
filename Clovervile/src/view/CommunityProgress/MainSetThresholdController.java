package view.CommunityProgress;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class MainSetThresholdController
{
  @FXML private SetThresholdController setThresholdController;

  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;


  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;

    if (setThresholdController != null)
    {
      setThresholdController.init(viewHandler, scene, modelManager);
    }
  }
}
