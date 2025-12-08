package view.CommunityProgress;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.CloverVilleModelManager;
import view.ViewHandler;

import java.awt.*;

public class SetThresholdController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField communityPointsField;
  @FXML private TextField thresholdGoalField;
  @FXML private TextField pointsField;
  @FXML private Button addButton;

}
