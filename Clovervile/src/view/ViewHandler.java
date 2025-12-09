package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CloverVilleModelManager;
import view.CommunityProgress.MainSetThresholdController;
import view.GreenActions.MainGreenActionController;
import view.MainView.MainViewController;
import view.Resident.MainResidentViewController;
import view.TradeOffers.MainTradeOfferController;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private MainViewController mainViewController;
  private MainResidentViewController residentsController;
  private MainGreenActionController greenActionsController;
  private MainTradeOfferController tradeOffersController;
  private MainSetThresholdController mainSetThresholdController;

  private CloverVilleModelManager modelManager;


  public ViewHandler(Stage stage, CloverVilleModelManager modelManager)
  {
    this.stage = stage;
    this.modelManager = modelManager;
  }

  public void start()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/view/MainView/MainView.fxml"));

      Parent root = loader.load();
      Scene scene = new Scene(root);

      MainViewController controller = loader.getController();
      controller.init(this, modelManager, scene);

      stage.setScene(scene);
      stage.setTitle("CloverVille App");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openView(String id)
  {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView/MainView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainViewController controller = loader.getController();
      controller.init(this, modelManager, scene);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (stage.getScene() == null)
    {
      System.err.println("Error: Scene is null. Cannot open view " + id);
    }
  }

  public void openCommunityProgress()
  {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CommunityProgress/MainSetThresholdView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainSetThresholdController controller = loader.getController();
      controller.init(this, scene, modelManager);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openGreenAction()
  {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GreenActions/MainGreenActionView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainGreenActionController controller = loader.getController();
      controller.init(this, scene, modelManager);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openResidents()
  {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Resident/MainResidentView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainResidentViewController controller = loader.getController();
      controller.init(this, scene, modelManager);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openTradeOffers()
  {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TradeOffers/MainTradeOfferView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainTradeOfferController controller = loader.getController();
      controller.init(this, scene, modelManager);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
