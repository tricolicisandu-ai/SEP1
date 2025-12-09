package view.Resident;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import javafx.scene.Scene;
import view.ViewHandler;

public class PersonalPointsViewController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField addPoints;
  @FXML private ListView<Resident> residentList;

  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }

  public void initialize()
  {
    addPoints.setText("");
    residentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
  }
  public void setResidentList( ListView<Resident> residentList)
  {
    this.residentList = residentList;
  }

  @FXML private void confirm(ActionEvent actionEvent)
  {
    String addPoints = this.addPoints.getText().trim();

    if (addPoints.isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "The field must be filled out");
      alert.setTitle("Error");
      alert.setHeaderText(null);

      alert.showAndWait();
      return;

    }

    try
    {
      int points = Integer.parseInt(addPoints);

      for (int i = 0; i < residentList.; i++)
      {

      }
      {
        modelManager.addPersonalPoints(ResidentList );
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

  public void resetPoints(ActionEvent actionEvent)
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to reset?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Exit");
    alert.setHeaderText(null);

    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      int total = modelManager.getAllResidents().getAllPersonalPoints();

      modelManager.getAllResidents().resetAllPersonalPoints();

      CommunityPool pool = modelManager.getCommunityPool();
      pool.setTotalPoints(pool.getTotalPoints()+total);
      modelManager.saveCommunityPool(pool);

    }

  }

}
