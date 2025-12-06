package view.Resident;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import javax.swing.*;

public class PersonalPointsViewController
{
  public TextField addPoints;
  public void resetPoints(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to reset?");
  }

  public void confirm(ActionEvent actionEvent)
  {
    JOptionPane.showMessageDialog(null, "Do you want to confirm?");
  }
}
