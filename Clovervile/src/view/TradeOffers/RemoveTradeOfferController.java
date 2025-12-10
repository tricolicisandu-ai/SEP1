package view.TradeOffers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;

import model.*;
import view.ViewHandler;

public class RemoveTradeOfferController
{
  private Scene scene;
  private ViewHandler viewHandler;
  private CloverVilleModelManager modelManager;

  @FXML private ComboBox<TradeOffer> TradeComboBox;


  public void init(ViewHandler viewHandler, Scene scene,
      CloverVilleModelManager modelManager)
  {
    this.scene = scene;
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
  }


  public void handleRemove(ActionEvent e)
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to remove?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Exit");
    alert.setHeaderText(null);

    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      TradeOffer tradeOffer = TradeComboBox.getSelectionModel().getSelectedItem();
      modelManager.removeTradeOffer(tradeOffer);

    }
    updateTradeComboBox();


}

    private void updateTradeComboBox()
    {
      int currentIndex = TradeComboBox.getSelectionModel().getSelectedIndex();
      TradeComboBox.getItems().clear();

      TradeOfferList tradeOfferList = modelManager.getAllTradeOffers();

      for (int i = 0; i < tradeOfferList.getNumberOfTradeOffers(); i++)
      {
        TradeComboBox.getItems().add(tradeOfferList.getTradeOffer(i));
      }
    }
}

