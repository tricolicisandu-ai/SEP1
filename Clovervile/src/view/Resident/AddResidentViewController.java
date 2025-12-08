package view.Resident;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.ResidentList;

import javax.swing.*;

public class AddResidentViewController
{
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField points;
  private ResidentList resdentList;


  public void initialize()
  {

    firstName.setText("Anna");
    lastName.setText("Olsen");
    points.setText("0");

  }

  public void setResdentList(ResidentList resdentList)
  {
    this.resdentList = resdentList;
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

  public void CancelResident(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to cancel?");
  }
}
