package view.Resident;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class MainResidentViewController
{
  @FXML private AddResidentViewController addResidentViewController;
  @FXML private ManageResidentsViewController manageResidentsViewController;
  @FXML private PersonalPointsViewController personalPointsViewController;

  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;

    if (addResidentViewController != null)
    {
      addResidentViewController.init(viewHandler, scene, modelManager);
    }

    if (manageResidentsViewController != null)
    {
      manageResidentsViewController.init(viewHandler, scene, modelManager);
    }

    if (personalPointsViewController != null)
    {
      personalPointsViewController.init(viewHandler, scene, modelManager);
    }
  }

}
