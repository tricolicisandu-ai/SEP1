package view.TradeOffers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class MainTradeOfferController
{
  @FXML private ManageTradeOfferController manageTradeOfferController;
  @FXML private TradeController tradeController;

  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;

    if (manageTradeOfferController != null)
    {
      manageTradeOfferController.init(viewHandler, scene, modelManager);
    }

    if (tradeController != null)
    {
      tradeController.init(viewHandler, scene, modelManager);
    }
  }
}
