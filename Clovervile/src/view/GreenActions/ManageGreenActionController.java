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
      GreenAction selectedGreenTask =
          listBox.getSelectionModel().getSelectedItem();

      // 1. Must select a green action first
      if (selectedGreenTask == null)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "You must select a green action to edit.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

      String newGreenTask = greenTaskField.getText().trim();
      String pointsText = pointField.getText().trim();

      // 2. Fields must be filled
      if (newGreenTask.isEmpty() || pointsText.isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "All fields must be filled out.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

      int newGreenPoints;

      // 3. Points must be an integer
      try
      {
        newGreenPoints = Integer.parseInt(pointsText);
      }
      catch (NumberFormatException ex)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Points must be a valid number.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

      // 4. No negative numbers
      if (newGreenPoints < 0)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Points must be a non-negative number.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

      // Old values
      String oldGreenTask = selectedGreenTask.getName();
      int oldPersonalPoints = selectedGreenTask.getGreenPoints();

      modelManager.editGreenAction(
          oldGreenTask,
          oldPersonalPoints,
          newGreenTask,
          newGreenPoints
      );

      updateListBox();
      reset();
    }

    else if (e.getSource() == listBox)
    {
      GreenAction temp = listBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        greenTaskField.setText(temp.getName());
        pointField.setText(String.valueOf(temp.getGreenPoints()));
      }
    }
  }




  public void handleRemove(ActionEvent e)
    {
      GreenAction greenTask = listBox.getSelectionModel().getSelectedItem();

      if (greenTask == null)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Please select a Green Action to remove.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to remove?",
          ButtonType.YES, ButtonType.NO);
      alert.setTitle("Exit");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        modelManager.removeGreenAction(greenTask);
        reset();
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
    listBox.getItems().clear();

    GreenActionList greenActions = modelManager.getAllGreenActions();

    for (int i = 0; i < greenActions.getNumberOfGreenActions(); i++)
    {
      listBox.getItems().add(greenActions.getIndex(i));
    }

    listBox.getSelectionModel().clearSelection();
  }
}



