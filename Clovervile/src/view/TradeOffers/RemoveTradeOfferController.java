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
    updateListBox();
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updateListBox();

    }
  }

  public void handleRemove(ActionEvent e)
  {
    TradeOffer tradeOffer = TradeComboBox.getSelectionModel().getSelectedItem();

    // 1. No selection - error
    if (tradeOffer == null)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Please select a trade offer to remove.");
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }

    // 2. Confirmation dialog
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to remove?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Confirm removal");
    alert.setHeaderText(null);

    alert.showAndWait();

    // 3. Remove if confirmed
    if (alert.getResult() == ButtonType.YES)
    {
      modelManager.removeTradeOffer(tradeOffer);
      updateListBox();
    }
  }



  private void updateListBox()
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
