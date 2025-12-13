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

  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;

    displayResidents(); //для завантаження списку резидентів у таблицю
  }

  public void initialize()
  {
    addPoints.setText("");
    residentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
  }

  public void reset()
  {
    if (modelManager != null)
    {
      addPoints.clear();
      displayResidents();
      residentList.refresh();
    }

  }


  // для відображення резидентів у таблиці
  private void displayResidents()
  {
    residentList.getItems().clear();
    ResidentList residentTab = modelManager.getAllResidents();
    for(int i = 0; i< residentTab.getNumberOfResidents(); i++)
    {
      residentList.getItems().add(residentTab.getResident(i));
    }
  }



  public void setResidentList( ListView<Resident> residentList)
  {
    this.residentList = residentList;
    this.residentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //хочу передати вже готовий ListView  іншого вікна
  }

  public void confirm(ActionEvent actionEvent)
  {
    //if (e.getSource() == confirm)

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
    alert.setTitle("Exit");
    alert.setHeaderText(null);
    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      int total = modelManager.getAllResidents().getAllPersonalPoints();

      ResidentList allResidents = modelManager.getAllResidents();
      allResidents.resetAllPersonalPoints();

      CommunityPool pool = modelManager.getCommunityPool();
      pool.setTotalPoints(pool.getTotalPoints()+total);
      modelManager.saveCommunityPool(pool);
      modelManager.saveResidents(allResidents);
      //для оновлення списка резидентів
      displayResidents();

    }

  }
}