package view.Resident;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import javax.swing.*;

public class ManageResidentsViewController
{
  public TextField firstName;
  public TextField lastName;
  public TextField points;

  public void confirmEdit(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to confirm edit?");
  }

  public void removeResident(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to remove?");
  }
}
