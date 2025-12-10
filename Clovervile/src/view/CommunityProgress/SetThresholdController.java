package view.CommunityProgress;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;

import model.CloverVilleModelManager;
import model.CommunityPool;
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

  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }

  public void initialize()
  {
    thresholdGoalField.setText("");
    pointsField.setText("");
  }


}
