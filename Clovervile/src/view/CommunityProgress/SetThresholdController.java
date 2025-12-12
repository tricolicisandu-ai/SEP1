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


  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
    handleCommunity();
  }

  public void initialize()
  {
    thresholdGoalField.setText("");
    pointsField.setText("");

  }

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

  @FXML private void handleCommunity()
  {
    CommunityPool communityPool = modelManager.getCommunityPool();
    communityPointsField.setText(communityPool.getTotalPoints()+"");
  }
}
