package view.GreenActions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;

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
  }



}



