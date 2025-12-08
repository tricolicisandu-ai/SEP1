package view.GreenActions;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import model.CloverVilleModelManager;
import model.GreenAction;
import model.GreenActionList;
import view.ViewHandler;

import java.awt.*;

public class GreenActionsController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;


  @FXML private TableView<GreenAction> greenActionTableView;
  @FXML private TableColumn<GreenAction, String> greenActionColumn;
  @FXML private TableColumn<GreenAction, String> pointColumn;


}
