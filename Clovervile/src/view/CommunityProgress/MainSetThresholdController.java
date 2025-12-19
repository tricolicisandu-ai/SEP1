package view.CommunityProgress;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class MainSetThresholdController
{
  @FXML private SetThresholdController setThresholdController;

  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;
  @FXML private Button backButton;
  @FXML private Tab setThresholdTab;

  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  /*
   Initializes this controller.
   This method receives references to the ViewHandler, Scene, and ModelManager,
   stores them in the controller, and passes them further to the
   setThresholdController if it exists.
    It ensures that all needed dependencies are connected before the view is used.
   */

  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;

    if (setThresholdController != null)
    {
      setThresholdController.init(viewHandler, scene, modelManager);
    }
    else
    {
      System.out.println("It is null :(");
    }
  }
  /*
   Handles all user actions from menu items and buttons.
   This method checks which UI element triggered the event
   and performs the correct action:
   exiting the program, showing the "About" dialog,
   or navigating back to the main view.
   */

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
      alert.setContentText("---------About---------.");
      alert.showAndWait();
    }
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
  }

  /*
    Handles tab change events.
    This method checks if the model is initialized and
    if the "Set Threshold" tab is selected.
    When the tab becomes active, it resets the
   SetThresholdController to ensure fresh data is shown.
   */
  public void tabChanged(Event event)
  {
    if (modelManager != null)
    {
      if (setThresholdTab.isSelected())
      {
        if (setThresholdController != null)
        {
          setThresholdController.reset();
        }
      }
    }
  }
}

