package view.GreenActions;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class MainGreenActionController
{

  @FXML private AddGreenActionController addGreenActionController;
  @FXML private ManageGreenActionController manageGreenActionController;

  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;

    if (addGreenActionController != null)
    {
      addGreenActionController.init(viewHandler, scene, modelManager);
    }

    if (manageGreenActionController != null)
    {
      manageGreenActionController.init(viewHandler, scene, modelManager);
    }
  }
}
