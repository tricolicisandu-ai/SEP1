package view.MainView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

  public void init(ViewHandler viewHandler, CloverVilleModelManager ClovervilleModelManager, Scene scene)
  {
    this.viewHandler = viewHandler;
    this.ClovervilleModelManager = ClovervilleModelManager;
    this.scene = scene;
  }

  public void handleActions(ActionEvent e)
  {
    if (viewHandler == null)
    {
      System.err.println("ViewHandler is not initialized!");
      return;
    }
    if (e.getSource() == communityProgressButton)
    {
      viewHandler.openCommunityProgress();
    }
    else if (e.getSource() == residentsButton)
    {
      viewHandler.openResidents();
    }
    else if (e.getSource() == tradeOfferButton)
    {
      viewHandler.openTradeOffers();
    }
    else if (e.getSource() == greenActionButton)
    {
      viewHandler.openGreenAction();
    }
    else if (e.getSource() == exitMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to exit the program?", ButtonType.YES,
          ButtonType.NO);
      alert.setTitle("Exit");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        System.exit(0);
      }
    }
    else if (e.getSource() == aboutMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText(null);
      alert.setTitle("About");
      alert.setContentText(
          "This is the app for Cloverville. Here you can choose where you want to make changes or simply check stuff.");
      alert.showAndWait();
    }
  }
}
