package view.TradeOffers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;

import model.*;
import view.ViewHandler;

public class TradeController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;


  @FXML private ComboBox<Resident>residentBox;
  @FXML private ComboBox<TradeOffer>tradeBox;

  @FXML private Button confirmButton;


  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
    updateTradeBox();
    updateResidentBox();
  }


  public void reset()
  {
    if (modelManager != null)
    {
      updateTradeBox();
      updateResidentBox();
    }

  }
  public void handleConfirm(ActionEvent e)
  {
    TradeOffer tradeOffer = tradeBox.getSelectionModel().getSelectedItem();
    Resident buyer = residentBox.getSelectionModel().getSelectedItem();

    // 1. Validate selections FIRST
    if (tradeOffer == null || buyer == null)
    {
      Alert error = new Alert(Alert.AlertType.ERROR,
          "You must select both a trade offer and a buyer.");
      error.setTitle("Error");
      error.setHeaderText(null);
      error.showAndWait();
      return;
    }

    // 2. Prevent seller = buyer
    if (tradeOffer.getSeller().equals(buyer))
    {
      Alert error = new Alert(Alert.AlertType.ERROR,
          "The seller cannot be the buyer of their own trade offer.");
      error.setTitle("Invalid trade");
      error.setHeaderText(null);
      error.showAndWait();
      return;
    }

    // 3. Confirmation dialog AFTER validation
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to confirm?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Confirm trade");
    alert.setHeaderText(null);
    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      boolean success = modelManager.executeTrade(tradeOffer, buyer);

      if (success)
      {
        updateTradeBox();
        updateResidentBox();
      }
      else
      {
        Alert alert2 = new Alert(Alert.AlertType.ERROR,
            "Buyer is fucking poor. Tell that mf to earn some points");
        alert2.setTitle("Error");
        alert2.setHeaderText(null);
        alert2.showAndWait();
      }
    }
  }


  public void updateTradeBox()
  {
    int currentIndex = tradeBox.getSelectionModel().getSelectedIndex();
    tradeBox.getItems().clear();

    TradeOfferList tradeOffer = modelManager.getAllTradeOffers();

    for (int i = 0; i < tradeOffer.getNumberOfTradeOffers(); i++)
    {
      if(tradeOffer.getTradeOffer(i).getBuyer()==null)
      {
        tradeBox.getItems().add(tradeOffer.getTradeOffer(i));
      }
    }

  }

  public void updateResidentBox()
  {
    int currentIndex = residentBox.getSelectionModel().getSelectedIndex();
    residentBox.getItems().clear();

    ResidentList residents = modelManager.getAllResidents();

    for (int i = 0; i < residents.getNumberOfResidents(); i++)
    {
      residentBox.getItems().add(residents.getResident(i));
    }

  }


}
