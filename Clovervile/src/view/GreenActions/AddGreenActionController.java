package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GreenAction;



public class AddGreenActionController
{
  @FXML private TextField GreenTaskField;
  @FXML private  TextField PointField;
  private GreenAction newGreenAction;

  @FXML private void handleAdd (ActionEvent event)
  {

    try
    {
      String GreenTask = GreenTaskField.getText().trim();
      String Point = PointField.getText().trim();
    }
    catch (Exception e)
    {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }
}
