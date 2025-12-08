package view.Resident;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.ResidentList;

import javax.swing.*;

public class AddResidentViewController
{
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField points;
  private RedentList resdentList;
  private Object showError;

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
    String firstName = firstName.getText();
    String lastName = lastName.getText();
    String points = points.getText();

    if(firstName.isEmpty() || lastName.isEmpty() || points.isEmpty() )
    {
      showError("All fields must be filled out")
      return;
    }

  }

  public void CancelResident(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to cancel?");
  }
}
