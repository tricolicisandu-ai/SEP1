package view.Resident;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.CloverVilleModelManager;
import view.ViewHandler;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Resident;
import model.ResidentList;


public class AddResidentViewController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField points;

  /**
   * Initializes the controller with references to the view handler,
   * scene, and model manager.
   */
  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;

  }

  /**
   * This method clears all input fields so the user
   * starts with empty values.
   */
  public void initialize()
  {
    firstName.setText("");
    lastName.setText("");
    points.setText("");
  }

  /**
   * This method clears all fields
   */
  public void reset()
  {
    if (modelManager != null)
    {
      firstName.clear();
      lastName.clear();
      points.clear();
    }
  }

  /**
   * This method reads user input, validates that all fields
   * are filled and that the points value is a non-negative number,
   * creates a new Resident object, saves it to the model,
   * and resets the input fields after a successful add.
   */
  @FXML private void AddResident(Event e)
  {
    String firstName = this.firstName.getText();
    String lastName = this.lastName.getText();
    String points = this.points.getText();

    // Empty field validation
    if (firstName.isEmpty() || lastName.isEmpty() || points.isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "All fields must be completed");
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }

    try
    {
      int point = Integer.parseInt(points);

      // Negative number validation
      if (point < 0)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Points must be a non-negative number.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return; // stop execution
      }

      Resident resident = new Resident(firstName, lastName, point);

      ResidentList list = modelManager.getAllResidents();
      list.addResident(resident);
      modelManager.saveResidents(list);

      reset(); // reset ONLY after successful add
    }
    catch (NumberFormatException ex)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Invalid input. Please enter a whole number (0 or greater).");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
  }
}

