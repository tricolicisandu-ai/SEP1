package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.CloverVilleModelManager;
import model.GreenAction;
import model.GreenActionList;
import javafx.scene.Scene;
import view.ViewHandler;


public class AddGreenActionController
{
  @FXML private TextField GreenTaskField;
  @FXML private  TextField PointField;
  private GreenActionList greenActionList;
  private GreenAction newGreenAction;
  private CloverVilleModelManager modelManager;
  private Scene scene;
  private ViewHandler viewHandler;



  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }


  public void initialize()
  {
    GreenTaskField.setText("");
    PointField.setText("");

  }

  public void setGreenActionList(GreenActionList greenActionList)
  {
    this.greenActionList = greenActionList;
  }



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
      int Point = Integer.parseInt(PointField);
      newGreenAction = new GreenAction(GreenTaskField, Point);

      if (greenActionList != null)
      {
        greenActionList.addGreenAction(newGreenAction);
      }
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
}
