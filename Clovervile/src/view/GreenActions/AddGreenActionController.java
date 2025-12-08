package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.GreenAction;


public class AddGreenActionController
{
  @FXML private TextField GreenTaskField;
  @FXML private  TextField PointField;

  private GreenAction newGreenAction;




   @FXML private void AddGreenAction (ActionEvent event)
  {
    try
    {
      String name = GreenTaskField.getText().trim();
      int point = Integer.parseInt(PointField.getText().trim());

      newGreenAction = new GreenAction(name, point);


    }
    catch (Exception e)
    {
      System.out.println("Error saving green action: " + e.getMessage());
    }
  }

  public GreenAction getNewCustomer()
  {
    return newGreenAction;
  }
}
