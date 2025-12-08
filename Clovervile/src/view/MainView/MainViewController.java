package view.MainView;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import model.CloverVilleModelManager;
import view.ViewHandler;


public class MainViewController
{
  private Scene scene;
  private CloverVilleModelManager ClovervilleModelManager;
  private ViewHandler viewHandler;

  @FXML Button residentsButton;
  @FXML Button tradeOfferButton;
  @FXML Button communityProgressButton;
  @FXML Button greenActionButton;
  @FXML MenuItem exitMenuItem;
  @FXML MenuItem aboutMenuItem;

  public void initialize(ViewHandler viewHandler, CloverVilleModelManager ClovervilleModelManager, Scene scene)
  {
    this.viewHandler = viewHandler;
    this.ClovervilleModelManager = ClovervilleModelManager;
    this.scene = scene;
  }


}
