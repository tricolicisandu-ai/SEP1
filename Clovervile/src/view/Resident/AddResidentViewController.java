package view.Resident;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.CloverVilleModelManager;
import view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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

  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;

  }

  public void initialize()
  {
    firstName.setText("");
    lastName.setText("");
    points.setText("");
  }

 // public void setResidentList(ResidentList residentList)
  //{
   // this.residentList = residentList;
  //}

  public void reset()
  {
    if (modelManager != null)
    {
      firstName.clear();
      lastName.clear();
      points.clear();
    }
  }

  @FXML private void AddResident(ActionEvent actionEvent)
  {
    String firstName = this.firstName.getText();
    String lastName = this.lastName.getText();
    String points = this.points.getText();

    if (firstName.isEmpty() || lastName.isEmpty() || points.isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "All fields must be completed");
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
    }

    try
    {
      int point = Integer.parseInt(points);
      Resident resident = new Resident(firstName, lastName, point);

   //   if (residentList != null)
      //{
      ResidentList list = modelManager.getAllResidents();
      list.addResident(resident);
      modelManager.saveResidents(list);


    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Invalid input. Please enter a whole number (0 or greater).");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
    reset();

  }
}

