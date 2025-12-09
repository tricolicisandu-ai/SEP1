package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import model.CloverVilleModelManager;
import model.GreenAction;
import model.GreenActionList;
import view.ViewHandler;

public class ManageGreenActionController
{
  private Scene scene;
  private ViewHandler viewHandler;
  private CloverVilleModelManager modelManager;

  @FXML private TextField greenTaskField;
  @FXML private TextField pointField;

  @FXML private ComboBox<GreenAction> listBox;

  @FXML private Button editButton;
  @FXML private Button removeButton;
  @FXML private Button resetButton;

  private GreenActionList greenActionList;
  private GreenAction selectedGreenAction;

  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.scene = scene;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
  }

  public void initialize()
  {

    greenTaskField.setText("");
    pointField.setText("");

    editButton.setDisable(true);
    removeButton.setDisable(true);
    resetButton.setDisable(true);
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == editButton)
    {
      String greenTask = greenTaskField.getText();
      int points = Integer.parseInt(pointField.getText());


      modelManager.editGreenAction(greenTask, points);
      updateListBox();
      greenTaskField.setText("");
      pointField.setText("");
    }

    else if (e.getSource() == listBox)
    {
      GreenAction temp = listBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        greenTaskField.setText(temp.getName());
        pointField.setPrefColumnCount(temp.getGreenPoints());

      }
    }

  }
    public void handleRemove(ActionEvent e)
    {

      if (e.getSource()== removeButton)
      {

        GreenAction greenTask = listBox.getSelectionModel().getSelectedItem();
        modelManager.removeGreenAction(greenTask);
        updateListBox();
      }




  }









  private void updateListBox()
  {
    int currentIndex = listBox.getSelectionModel().getSelectedIndex();
    listBox.getItems().clear();

    GreenActionList greenActions = modelManager.getAllGreenActions();

    for (int i = 0; i < greenActions.getNumberOfGreenActions(); i++)
    {
      listBox.getItems().add(greenActions.getIndex(i));
    }

    if (currentIndex == -1 && listBox.getItems().size() > 0)
    {
      listBox.getSelectionModel().select(0);
    }
    else
    {
      listBox.getSelectionModel().select(currentIndex);
    }
  }



}



