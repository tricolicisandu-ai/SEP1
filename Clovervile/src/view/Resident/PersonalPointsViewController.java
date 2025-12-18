package view.Resident;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import javafx.scene.Scene;
import view.ViewHandler;

public class PersonalPointsViewController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField addPoints;
  @FXML private ListView<Resident> residentList;
  @FXML private Button confirm;

  /**
   * Initializes this controller with the view handler, scene, and model manager.
   */
  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;

    displayResidents();
  }

  /**
   * Clears the points input field and sets the ListView selection mode
   * to MULTIPLE so the user can select more than one resident.
   */
  public void initialize()
  {
    addPoints.setText("");
    residentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
  }

  /**
   * Clears the points field and reloads the residents list
   */
  public void reset()
  {
    if (modelManager != null)
    {
      addPoints.clear();
      displayResidents();
    }
  }


  /**
   * Loads residents from the model and displays them in the ListView.
   */
  private void displayResidents()
  {
    residentList.getItems().clear();
    ResidentList residentTab = modelManager.getAllResidents();
    for(int i = 0; i< residentTab.getNumberOfResidents(); i++)
    {
      residentList.getItems().add(residentTab.getResident(i));
    }
  }

  /**
   * Sets the ListView reference from outside (another controller).
   */
  public void setResidentList( ListView<Resident> residentList)
  {
    this.residentList = residentList;
    this.residentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //хочу передати вже готовий ListView  іншого вікна
  }

  /**
   * This method validates input (not empty, numeric, non-negative),
   * checks that at least one resident is selected, builds a temporary ResidentList
   * from selected items, and then calls the model method to add points.
   * And  refreshes the list and clears the input field
   */
  public void confirm(ActionEvent actionEvent)
  {
    if( addPoints.getText().isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "The field must be filled out");
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();

    }
    else
    {
      int newPoints = 0;
      try
      {
        newPoints = Integer.parseInt(addPoints.getText());
      }
      catch (NumberFormatException e)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Enter a valid number ");
        alert.showAndWait();
        return;
      }

      if (newPoints < 0)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText("Points must be a non-negative number.");
        alert.showAndWait();
        return;
      }

      ObservableList<Resident> selectedResidents = residentList.getSelectionModel()
          .getSelectedItems(); // обрані резиденти
      if (selectedResidents == null || selectedResidents.isEmpty())
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Please select at least one resident from the list");
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();

      }
      else
      {
        ResidentList selectList = new ResidentList(); // тимчасовий листрезидентів з обраних резидентів
        for (int i = 0; i < selectedResidents.size(); i++)
        {
          Resident resident = selectedResidents.get(i);
          selectList.addResident(resident);
        }
        modelManager.addPersonalPoints(selectList,
            newPoints);//чи вірно викликаний метод і чи вірно в дужках
        displayResidents();
        addPoints.clear();
      }
    }
  }

  public void resetPoints(ActionEvent actionEvent)
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to reset?",
        ButtonType.YES, ButtonType.NO);
    // create a confirmation dialog with YES and NO buttons
    alert.setTitle("Exit");
    // set the title of the alert window
    alert.setHeaderText(null);
    // remove the header text from the alert
    alert.showAndWait();
    // show the alert and wait for the user response

    if (alert.getResult() == ButtonType.YES)
    {
      // check if the user clicked YES
      int total = modelManager.getAllResidents().getAllPersonalPoints();
      // get total personal points from all residents

      ResidentList allResidents = modelManager.getAllResidents();
      // get the list of all residents
      allResidents.resetAllPersonalPoints();
      // reset personal points of all residents to zero

      CommunityPool pool = modelManager.getCommunityPool();
      // get the community pool
      pool.setTotalPoints(pool.getTotalPoints()+total);
      // add collected points to the community pool
      modelManager.saveCommunityPool(pool);
      // save the updated community pool
      modelManager.saveCommunityPoolAsJson(pool);
      // save the community pool as a JSON file
      modelManager.saveResidents(allResidents);
      // save the updated residents list
      displayResidents();
      // refresh the residents list


    }

  }
}