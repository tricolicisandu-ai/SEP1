package view.CommunityProgress;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class MainSetThresholdController
{
  @FXML private SetThresholdController setThresholdController;

  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;
  @FXML private Tab setThresholdTab;
  @FXML private TabPane tabPane;
  @FXML private Button backButton;


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
