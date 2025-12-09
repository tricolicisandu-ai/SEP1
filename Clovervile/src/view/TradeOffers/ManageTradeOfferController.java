package view.TradeOffers;

import javafx.scene.Scene;
import model.CloverVilleModelManager;
import view.ViewHandler;

public class ManageTradeOfferController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }
}
