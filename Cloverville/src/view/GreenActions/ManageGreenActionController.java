package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;

import model.CloverVilleModelManager;
import model.GreenAction;
import model.GreenActionList;
import model.Resident;
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
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updateListBox();

      greenTaskField.clear();
      pointField.clear();
      updateListBox();
      greenTaskField.clear();
      pointField.clear();

    }
  }


  public void handleList(ActionEvent e)
  {
    GreenAction selected = listBox.getSelectionModel().getSelectedItem();
    if (selected!= null)
    {
      greenTaskField.setText(selected.getName());
      pointField.setText(String.valueOf(selected.getGreenPoints()));
    }
  }


  public void handleEdit(ActionEvent e)
  {
    if (e.getSource() == editButton)
    {

      GreenAction selectedGreenTask = listBox.getSelectionModel().getSelectedItem();

      String oldGreenTask =selectedGreenTask.getName();
      int oldPersonalPoints = selectedGreenTask.getGreenPoints();

      String newGreenTask = greenTaskField.getText();
      int newGreenPoints = Integer.parseInt(pointField.getText());


      modelManager.editGreenAction(oldGreenTask, oldPersonalPoints,
          newGreenTask, newGreenPoints);
      updateListBox();
      greenTaskField.setText("");
      pointField.setText("");

    }

    else if (e.getSource() == listBox)
    {
      GreenAction temp = listBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        greenTaskField.setPromptText(temp.getName());
        pointField.setPrefColumnCount(temp.getGreenPoints());

      }
    }

  }



    public void handleRemove(ActionEvent e)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to remove?",
          ButtonType.YES, ButtonType.NO);
      alert.setTitle("Exit");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        GreenAction greenTask = listBox.getSelectionModel().getSelectedItem();
        modelManager.removeGreenAction(greenTask);
        updateListBox();
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







  public void handleReset(ActionEvent e)
  {

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to reset?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Exit");
    alert.setHeaderText(null);

    alert.showAndWait();


    if (alert.getResult() == ButtonType.YES)
    {

        modelManager.resetGreenAction();
        updateListBox();
        reset();
    }

    if (e.getSource() == listBox)
    {
      GreenAction temp = listBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        greenTaskField.setText(temp.getName());
        pointField.setPrefColumnCount(temp.getGreenPoints());

      }
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



