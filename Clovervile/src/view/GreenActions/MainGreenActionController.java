package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class MainGreenActionController
{
  @FXML private AddGreenActionController addGreenActionController;
  @FXML private ManageGreenActionController manageGreenActionController;

  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;
  @FXML private Button backButton;
  @FXML private Tab addGreenActionTab;
  @FXML private Tab manageGreenActionTab;

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

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == exitMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to exit the program?", ButtonType.YES,
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
          "---------About---------.");
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
      if (addGreenActionTab.isSelected())
      {
        if (addGreenActionController != null)
        {
          addGreenActionController.reset();
        }
      }
      else if (manageGreenActionTab.isSelected())
      {
        if (manageGreenActionController != null)
        {
          manageGreenActionController.reset();
        }
      }
    }
  }
}
