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
  public void handleConfirm(ActionEvent e )
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to confirm?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Exit");
    alert.setHeaderText(null);

    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      TradeOffer tradeOffer = tradeBox.getSelectionModel().getSelectedItem();
      Resident buyer = residentBox.getSelectionModel().getSelectedItem();
      modelManager.executeTrade(tradeOffer,buyer);
    }
    updateTradeBox();
    updateResidentBox();

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
