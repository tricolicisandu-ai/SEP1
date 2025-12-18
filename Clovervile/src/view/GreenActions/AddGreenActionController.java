package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.CloverVilleModelManager;
import model.CommunityPool;
import model.GreenAction;
import model.GreenActionList;
import javafx.scene.Scene;
import view.ViewHandler;


public class AddGreenActionController
{
  @FXML private TextField GreenTaskField;
  @FXML private  TextField PointField;
  @FXML private Button addButton;
  private CloverVilleModelManager modelManager;
  private Scene scene;
  private ViewHandler viewHandler;


  /**
   * Initializes this controller.
   * This method receives and stores references to the ViewHandler,
   * Scene, and ModelManager so the controller can interact
   * with the application logic and navigation.
   */
  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.scene = scene;
    this.modelManager = modelManager;

  }

  /**
   * Initializes the view when it is loaded.
   * This method clears all input fields to ensure
   * the user starts with empty fields
   */
  public void initialize()
  {
    GreenTaskField.setText("");
    PointField.setText("");

  }

  /**
   * Resets the view to its default state.
   * This method clears all input fields if the model
   * manager is available.
   */
  public void reset()
  {
    if (modelManager != null)
    {
      GreenTaskField.clear();
      PointField.clear();
    }
  }

  /**
   * Handles the action of adding a new green action.
   * This method reads user input, validates that all fields are filled
   * and that the points value is non-negative, creates a new GreenAction,
   * updates the CommunityPool points, saves all changes to files and JSON.
   */
  @FXML private void handleAdd (ActionEvent event)
  {
    String GreenTaskField = this.GreenTaskField.getText().trim();
    String PointField = this.PointField.getText().trim();


    if (GreenTaskField.isEmpty() || PointField.isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "All fields must be filled out");
      alert.setTitle("Error");
      alert.setHeaderText(null);

      alert.showAndWait();
      return;
    }

    try
    {
        int  Point = Integer.parseInt(PointField);

      // NEW: reject negative values
      if (Point < 0)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Points must be a non-negative number.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

        GreenAction newGreenAction = new GreenAction(GreenTaskField, Point);

        GreenActionList list = modelManager.getAllGreenActions();
        list.addGreenAction(newGreenAction);

        CommunityPool pool = modelManager.getCommunityPool();
        pool.setTotalPoints( pool.getTotalPoints()+Point);
        modelManager.saveCommunityPool(pool);
        modelManager.saveCommunityPoolAsJson(pool);
        modelManager.saveGreenActions(list);
        modelManager.saveGreenActionsAsJson(list);
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Invalid Input");
      alert.setContentText("Enter a valid number ");

      alert.showAndWait();
    }
    reset();
  }
}
