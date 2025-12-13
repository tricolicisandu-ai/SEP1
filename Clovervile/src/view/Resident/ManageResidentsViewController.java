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
      // Get the resident currently selected in combo box
      Resident selectedResident = residentsComboBox.getSelectionModel().getSelectedItem();


      String oldFirstName = selectedResident.getFirstName();
      String oldLastName = selectedResident.getLastName();
      int oldPersonalPoints = selectedResident.getPersonalPoints();
      String newFirstName = firstNameField.getText();
      String newLastName = lastNameField.getText();
      int newPoints = Integer.parseInt(pointsField.getText());

      // Use old values to find, new values to update
      modelManager.editResident(oldFirstName, oldLastName,oldPersonalPoints,
          newFirstName, newLastName, newPoints);
      modelManager.saveResidents(modelManager.getAllResidents());
      updateResidentsComboBox();
    }
    else if (e.getSource() == residentsComboBox)
    {
      Resident temp = residentsComboBox.getSelectionModel().getSelectedItem();

      if (temp != null) {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        pointsField.setText(String.valueOf(temp.getPersonalPoints()));
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
      Resident resident = residentsComboBox.getSelectionModel().getSelectedItem();
      modelManager.removeResident(resident);
      updateResidentsComboBox();
    }

    else if (e.getSource() == residentsComboBox)
    {
      Resident temp = residentsComboBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        pointsField.setPrefColumnCount(temp.getPersonalPoints());

      }
    }
  }


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
