package view.Resident;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class MainResidentViewController
{

  @FXML private AddResidentViewController addResidentViewController;
  @FXML private ManageResidentsViewController manageResidentsViewController;
  @FXML private PersonalPointsViewController personalPointsViewController;

  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;
  @FXML private Button backButton;
  @FXML private Tab addResidentTab;
  @FXML private Tab manageResidentsTab;
  @FXML private Tab personalPointsTab;

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

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == exitMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Are you sure you want to exit the program?", ButtonType.YES,
          ButtonType.NO);
      alert.setTitle("Exit");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        System.exit(0);
      }
    }
    else if (e.getSource() == aboutMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText(null);
      alert.setTitle("About");
      alert.setContentText(
          "Add, edit, or remove residents. Give personal points or reset them. Residents must be added before using trades or points");
      alert.showAndWait();
    }
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
  }

  public void tabChanged(Event event)
  {
    if (modelManager != null)
    {
      if (addResidentTab.isSelected())
      {
        if (addResidentViewController != null)
        {
          addResidentViewController.reset();
        }
      }
      else if (manageResidentsTab.isSelected())
      {
        if (manageResidentsTab != null)
        {
          manageResidentsViewController.reset();
        }
      }
      else if (personalPointsTab.isSelected())
      {
        if (personalPointsViewController != null)
        {
          personalPointsViewController.reset();
        }
      }
    }
  }

}
