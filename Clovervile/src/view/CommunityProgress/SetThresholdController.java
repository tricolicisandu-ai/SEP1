package view.CommunityProgress;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;

import model.CloverVilleModelManager;
import model.CommunityPool;
import model.Threshold;
import view.ViewHandler;


public class SetThresholdController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField communityPointsField;
  @FXML private TextField thresholdGoalField;
  @FXML private TextField pointsField;
  @FXML private Button addButton;

  private Threshold newThreshold;

  /*
    Initializes this controller with references to the ViewHandler, Scene,
   and ModelManager.
    This method stores these references and immediately updates
    the community points shown in the view
   */

  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
    handleCommunity();
  }

  /*
   Initializes fields when the view is loaded.
   This method clears the threshold goal and points input fields
   to ensure the user starts with empty inputs.
   */
  public void initialize()
  {
    thresholdGoalField.setText("");
    pointsField.setText("");
  }


  /*
   Handles the action of adding or updating a threshold.
   This method reads user input, validates that all fields are filled,
   converts the point value to an integer, creates a new Threshold object,
   and saves it using the model manager.
   If the input is invalid, an error message is shown.
   */
  @FXML private void handleAdd (ActionEvent event)
  {
    String threshold = this.thresholdGoalField.getText().trim();
    String pointCost = this.pointsField.getText().trim();

    if (threshold.isEmpty() || pointCost.isEmpty())
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
      int Point = Integer.parseInt(pointCost);
      newThreshold= new Threshold(threshold, Point);
      modelManager.setThreshold(newThreshold);
      modelManager.saveThresholdAsJson(newThreshold);
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Invalid Input");
      alert.setContentText("Enter a valid number ");

      alert.showAndWait();
    }
  }

  /*
   Updates the community points shown in the view.
   This method retrieves the current CommunityPool from the model
   and displays the total points
   */
  @FXML private void handleCommunity()
  {
    CommunityPool communityPool = modelManager.getCommunityPool();
    communityPointsField.setText(communityPool.getTotalPoints()+"");
  }

  /*
   Resets the view to its default state.
   This method clears all input fields and refreshes
   the displayed community points when the model is available.
    */
  public void reset()
  {
    if (modelManager != null)
    {
      thresholdGoalField.clear();
      pointsField.clear();
      handleCommunity();
    }
  }
}
