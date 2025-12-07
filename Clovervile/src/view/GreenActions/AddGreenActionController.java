package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.CloverVilleModelManager;
import model.GreenAction;
import model.GreenActionList;





public class AddGreenActionController
{
  @FXML private TextField GreenTaskField;
  @FXML private  TextField PointField;

  private GreenAction newGreenAction;
  private CloverVilleModelManager model;



  public void set(CloverVilleModelManager model)
  {
    this.model = model;
  }
   @FXML private void AddGreenAction (ActionEvent event)
  {
    try
    {
      String name = GreenTaskField.getText().trim();
      int point = Integer.parseInt(PointField.getText().trim());

      newGreenAction = new GreenAction(name, point);

      GreenActionList greenActions = model.getAllGreenActions();

      greenActions.addGreenAction(newGreenAction);
      model.saveGreenActions(greenActions);

    }
    catch (Exception e)
    {
      System.out.println("Error saving green action: " + e.getMessage());
    }
  }
}
