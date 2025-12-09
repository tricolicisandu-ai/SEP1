package view.Resident;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.CloverVilleModelManager;
import model.GreenAction;
import view.ViewHandler;

import java.awt.*;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Resident;
import model.ResidentList;


public class AddResidentViewController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;


  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField points;
  private ResidentList residentList;
  private CloverVilleModelManager manager;

  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }
  public void initialize()
  {
    manager = new CloverVilleModelManager("greenActions.bin", "tradeOffers.bin",
        "residents.bin", "communityPool.bin", "thresholds.bin");
    firstName.setText("");
    lastName.setText("");
    points.setText("");
  }

  public void setResidentList(ResidentList residentList)
  {
    this.residentList = residentList;
  }

  @FXML private void AddResident(ActionEvent actionEvent)
  {
    String firstName = this.firstName.getText();
    String lastName = this.lastName.getText();
    String points = this.points.getText();

    if(firstName.isEmpty() || lastName.isEmpty() || points.isEmpty() )
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "All fields must be filled out");
      alert.setTitle("Error");
      alert.setHeaderText(null);

      alert.showAndWait();
    }
  }

  try
  {
    int Point = Integer.parseInt(points);
    new Resident = new Resident(firstName, lastName, points);

    if (ResidentList != null)
    {
      ResidentList.addResident(new Resident());
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

  @FXML private void CancelResident(ActionEvent actionEvent)
  {


  }
}
