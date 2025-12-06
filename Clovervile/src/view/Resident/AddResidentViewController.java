package view.Resident;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import javax.swing.*;

public class AddResidentViewController
{
  public TextField firstName;
  public TextField lastName;
  public TextField points;

  public void initialize()
  {

    firstName.setText("Anna");

  }
  public void AddResident(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to add?");
  }

  public void CancelResident(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to cancel?");
  }
}
