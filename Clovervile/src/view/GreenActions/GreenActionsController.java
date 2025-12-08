package view.GreenActions;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CloverVilleModelManager;
import model.GreenAction;
import model.GreenActionList;
import view.ViewHandler;

import java.awt.*;

public class GreenActionsController
{

  @FXML private TableView<GreenAction> greenActionTableView;
  @FXML private TableColumn<GreenAction, String> greenActionColumn;
  @FXML private TableColumn<GreenAction, Integer> pointColumn;

  private GreenActionList greenActionList;
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;


  public void init(CloverVilleModelManager modelManager, ViewHandler viewHandler, Scene scene)
  {
    this.scene = scene;
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.greenActionList = modelManager.getAllGreenActions();

  }

  @FXML public void initialize()
  {

  }


}
