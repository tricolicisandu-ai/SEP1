package view.TradeOffers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import model.*;
import view.ViewHandler;
import javafx.event.ActionEvent;
import  model.TradeOfferList;
import model.TradeOffer;



public class RemoveTradeOfferController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private ComboBox<TradeOffer> tradeOfferComboBox;
  @FXML private Button removeButton;
}

public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
{
  this.scene = scene;
  this.viewHandler = viewHandler;
  this.modelManager = modelManager;
}

public void handleRemove(ActionEvent e)
{
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
      "Do you really want to remove?", ButtonType.YES, ButtonType.NO);
  alert.setTitle("Exit");
  alert.setHeaderText(null);

  alert.showAndWait();

  if (alert.getResult() == ButtonType.YES)
  {
    TradeOffer tradeOffer = tradeOfferComboBox.getSelectionModel().getSelectedItem();
    modelManager.removeTradeOffer(tradeOffer);
    updateTradeOfferComboBox();
  }

  else if (e.getSource() == tradeOfferComboBox)
  {
    TradeOffer temp = tradeOfferComboBox.getSelectionModel().getSelectedItem();

  }
}