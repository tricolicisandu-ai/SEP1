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


  /**
   * Initializes the controller with view, scene, and model references.
   */
  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.scene = scene;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
  }

  /**
   * Clears input fields when the view is loaded.
   */
  public void initialize()
  {
    greenTaskField.setText("");
    pointField.setText("");
  }

  /**
   * Resets the view to its default state.
   * This method refreshes the list of green actions
   * and clears all input fields if the model is available.
   */
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


  /**
   * Handles selection changes in the list.
   * When a green action is selected, its name and points
   * are shown in the input fields for viewing or editing.
   */
  public void handleList(ActionEvent e)
  {
    GreenAction selected = listBox.getSelectionModel().getSelectedItem();
    if (selected!= null)
    {
      greenTaskField.setText(selected.getName());
      pointField.setText(String.valueOf(selected.getGreenPoints()));
    }
  }


  /**
   * This method validates user input, ensures a green action
   * is selected, checks that the points value is valid,
   * and updates the selected green action in the mode
   */
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

  /**
   * This method ensures a green action is selected,
   * asks the user for confirmation, and then removes
   */
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


  /**
   * This method asks for user confirmation,
   * resets green actions in the model,
   * refreshes the list, and clears the input fields.
   */
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
  
  /**
   * This method retrieves the latest green actions
   * from the model and displays them in the UI list.
   */
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



