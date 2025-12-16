package view.Resident;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import model.*;

import view.ViewHandler;

public class ManageResidentsViewController
{
  private Scene scene;
  private ViewHandler viewHandler;
  private CloverVilleModelManager modelManager;

  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField pointsField;


  @FXML private ComboBox<Resident> residentsComboBox;
  @FXML private Button editButton;



  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.scene = scene;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    updateResidentsComboBox();
  }
 public void initialize()
  {
    firstNameField.setText("");
    lastNameField.setText("");
    pointsField.setText("");

  }


  public void reset()
  {
    if (modelManager != null)
    {
      updateResidentsComboBox();
      firstNameField.clear();
      lastNameField.clear();
      pointsField.clear();
      updateResidentsComboBox();
    }

  }

  public void handleResidentList(ActionEvent e)
  {
    Resident selected = residentsComboBox.getSelectionModel().getSelectedItem();
    if (selected!= null)
    {
      firstNameField.setText(selected.getFirstName());
      lastNameField.setText(selected.getLastName());
      pointsField.setText(String.valueOf(selected.getPersonalPoints()));
    }
  }


//  public void handleEdit(ActionEvent e)
//  {
//
//    if (e.getSource() == editButton)
//    {
//      String firstName = firstNameField.getText();
//      String lastName = lastNameField.getText();
//      int points = Integer.parseInt(pointsField.getText());
//
//
//      modelManager.editResident(firstName,lastName, points);
//      updateResidentsComboBox();
//      firstNameField.setText("");
//      lastNameField.setText("");
//      pointsField.setText("");
//    }
//
//    else if (e.getSource() == residentsComboBox)
//    {
//      Resident temp = residentsComboBox.getSelectionModel().getSelectedItem();
//
//      if (temp != null)
//      {
//        firstNameField.setText(temp.getFirstName());
//        lastNameField.setText(temp.getLastName());
//        pointsField.setPrefColumnCount(temp.getPersonalPoints());
//
//      }
//    }
//  }

  public void handleEdit(ActionEvent e)
  {
    if (e.getSource() == editButton)
    {
      // 1. Check if a resident is selected
      Resident selectedResident = residentsComboBox.getSelectionModel().getSelectedItem();
      if (selectedResident == null)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Please select a resident to edit.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

      // 2. Check empty fields
      String newFirstName = firstNameField.getText();
      String newLastName = lastNameField.getText();
      String pointsText = pointsField.getText();

      if (newFirstName.isEmpty() || newLastName.isEmpty() || pointsText.isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "All fields must be filled out.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

      int newPoints;

      // 3. Check numeric input
      try
      {
        newPoints = Integer.parseInt(pointsText);
      }
      catch (NumberFormatException ex)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid input");
        alert.setContentText("Personal points must be a number.");
        alert.showAndWait();
        return;
      }

      // 4. Check negative values
      if (newPoints < 0)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Personal points cannot be negative.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }

      // Old values
      String oldFirstName = selectedResident.getFirstName();
      String oldLastName = selectedResident.getLastName();
      int oldPersonalPoints = selectedResident.getPersonalPoints();

      // 5. Update resident
      modelManager.editResident(
          oldFirstName, oldLastName, oldPersonalPoints,
          newFirstName, newLastName, newPoints);

      modelManager.saveResidents(modelManager.getAllResidents());
      updateResidentsComboBox();
      reset();
    }
    else if (e.getSource() == residentsComboBox)
    {
      Resident temp = residentsComboBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        pointsField.setText(String.valueOf(temp.getPersonalPoints()));
      }
    }
  }

  public void handleRemove(ActionEvent e)
  {
    // 1. Check if a resident is selected BEFORE showing confirmation
    Resident resident = residentsComboBox.getSelectionModel().getSelectedItem();

    if (resident == null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Please select a resident to remove.");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }

    // 2. Now show confirmation
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to remove?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Confirm removal");
    alert.setHeaderText(null);
    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      if (modelManager.isResidentInTradeOffer(resident))
      {
        Alert tradeAlert = new Alert(
            Alert.AlertType.CONFIRMATION,
            "This resident is part of a trade offer.\n\n" +
                "Do you want to remove the resident and the trade offer?",
            ButtonType.YES, ButtonType.NO);

        tradeAlert.setTitle("Resident in trade offer");
        tradeAlert.setHeaderText(null);
        tradeAlert.showAndWait();

        if (tradeAlert.getResult() == ButtonType.YES)
        {
          modelManager.removeTradeOffersBySeller(resident);
          modelManager.removeResident(resident);
          updateResidentsComboBox();
          reset();
        }
      }
      else
      {
        modelManager.removeResident(resident);
        updateResidentsComboBox();
        reset();
      }
    }
  }



  //  public void handleRemove(ActionEvent e)
//  {
//    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//        "Do you really want to remove?",
//        ButtonType.YES, ButtonType.NO);
//    alert.setTitle("Exit");
//    alert.setHeaderText(null);
//
//    alert.showAndWait();
//
//    if (alert.getResult() == ButtonType.YES)
//    {
//      Resident resident = residentsComboBox.getSelectionModel().getSelectedItem();
//      modelManager.removeResident(resident);
//      updateResidentsComboBox();
//    }
//
//    else if (e.getSource() == residentsComboBox)
//    {
//      Resident temp = residentsComboBox.getSelectionModel().getSelectedItem();
//
//      if (temp != null)
//      {
//        firstNameField.setText(temp.getFirstName());
//        lastNameField.setText(temp.getLastName());
//        pointsField.setPrefColumnCount(temp.getPersonalPoints());
//
//      }
//    }
//  }


  private void updateResidentsComboBox()
  {
    int currentIndex = residentsComboBox.getSelectionModel().getSelectedIndex();
    residentsComboBox.getItems().clear();

    ResidentList residents = modelManager.getAllResidents();
    System.out.println(residents.getNumberOfResidents());
    for (int i = 0; i < residents.getNumberOfResidents(); i++)
    {
      residentsComboBox.getItems().add(residents.getResident(i));
    }
  }
}
