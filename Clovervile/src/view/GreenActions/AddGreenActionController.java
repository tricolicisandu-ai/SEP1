package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
<<<<<<< Updated upstream
import model.GreenAction;


=======
import javafx.scene.control.Button;
import model.GreenAction;




>>>>>>> Stashed changes
public class AddGreenActionController
{
  @FXML private TextField GreenTaskField;
  @FXML private TextField PointField;
  @FXML private Button AddButton;

<<<<<<< Updated upstream
  private GreenAction newGreenAction;




   @FXML private void AddGreenAction (ActionEvent event)
  {
    try
    {
      String name = GreenTaskField.getText().trim();
      int point = Integer.parseInt(PointField.getText().trim());

      newGreenAction = new GreenAction(name, point);
=======
  public void handleAdd(ActionEvent event)
  {
    try
    {
      String GreenTask = GreenTaskField.getText().trim();
      String Point = PointField.getText().trim();
>>>>>>> Stashed changes


    }
    catch (Exception e)
    {
      System.out.println("Error saving green action: " + e.getMessage());
    }
  }

<<<<<<< Updated upstream
  public GreenAction getNewCustomer()
  {
    return newGreenAction;
  }
=======
>>>>>>> Stashed changes
}
