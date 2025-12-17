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
    // Handles the confirmation action triggered by the user
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        // Creates a confirmation alert asking the user to confirm the action
        "Do you really want to confirm?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Exit");
    // Sets the title of the confirmation dialog
    alert.setHeaderText(null);
    alert.showAndWait();
    // Displays the alert and waits for the user response

    if (alert.getResult() == ButtonType.YES)
    {
      TradeOffer tradeOffer = tradeBox.getSelectionModel().getSelectedItem();
      // Gets the selected trade offer from the trade selection box
      Resident buyer = residentBox.getSelectionModel().getSelectedItem();
      // Gets the selected resident (buyer) from the resident selection box

      if (tradeOffer == null || buyer == null)
      // Checks if either the trade offer or buyer was not selected
      {
        Alert error3 = new Alert(Alert.AlertType.ERROR,
            "You must select both a trade offer and a buyer.");
        error3.setHeaderText(null);
        // Removes the header text from the error dialog
        error3.showAndWait();
        // Displays the error message
        return;
      }

      if (tradeOffer.getSeller().equals(buyer))
      // Checks if the buyer is the same person as the seller
      {
        Alert error4 = new Alert(Alert.AlertType.ERROR,
            "The seller cannot be the buyer of their own trade offer, Change the buyer.");
        error4.setTitle("Invalid trade");
        error4.setHeaderText(null);
        error4.showAndWait();
        return;
      }

      boolean success = modelManager.executeTrade(tradeOffer, buyer);
      // Attempts to execute the trade using the model manager
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
