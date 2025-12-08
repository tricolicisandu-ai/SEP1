package view.Resident;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Resident;
import model.ResidentList;

import javax.swing.*;

public class AddResidentViewController
{
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField points;
  private ResidentList residentList;
  private Object showError;

  public void initialize()
  {
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
    String firstName = firstName.getText();
    String lastName = lastName.getText();
    String points = points.getText();

    if(firstName.isEmpty() || lastName.isEmpty() || points.isEmpty() )
    {
      showError("All fields must be filled out.")
      return;
    }

    int points;
    try
    {
      String pointsText;
      points = Integer.parseInt(pointsText);
    }
      catch (NumberFormatException e)
      {
        showError("Points must be an integer.");
        return;
      }
    Resident newResident = new Resident(firstName, lastName, points);
    ResidentList list = model.getAllResidents();
    list.addResident(newResident);
    model.saveResidents(list);

  }

  @FXML private void CancelResident(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to cancel?");
  }
}
