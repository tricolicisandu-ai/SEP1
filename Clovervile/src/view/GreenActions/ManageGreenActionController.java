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

  @FXML private ComboBox<GreenAction>listBox;


  @FXML private Button editButton;
  @FXML private Button removeButton;
  @FXML private Button resetButton;



  private GreenActionList greenActionList;
  private GreenAction selectedGreenAction;



  public void init(ViewHandler viewHandler, CloverVilleModelManager modelManager, Scene scene)
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


    listBox.valueProperty().addListener(greenTaskField, pointField)
    {

    }
  }






}



