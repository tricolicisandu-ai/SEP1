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


  /*
   Initializes this controller with references to the view handler,
   scene, and model manager.
   */
  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
    updateTradeBox();
    updateResidentBox();
  }

  /*
   This method refreshes both the trade offer list and the resident list
   */
  public void reset()
  {
    if (modelManager != null)
    {
      updateTradeBox();
      updateResidentBox();
    }

  }

  /*
   This method validates that both a trade offer and a buyer are selected,
   prevents the seller from buying their own offer,
   and then attempts to execute the trade through the model.
   */
  public void handleConfirm(ActionEvent e)
  {
    TradeOffer tradeOffer = tradeBox.getSelectionModel().getSelectedItem();
    // Get selected trade offer from the list
    Resident buyer = residentBox.getSelectionModel().getSelectedItem();
    // Get selected buyer from UI

    if (tradeOffer == null || buyer == null)
    // Check if trade offer or buyer is not selected
    {
      Alert error = new Alert(Alert.AlertType.ERROR,
          "You must select both a trade offer and a buyer.");
      // Create error message
      error.setTitle("Error");
      // Set alert window title
      error.setHeaderText(null);
      // Remove header text
      error.showAndWait();
      // Show alert and wait for user
      return;
      // Stop method execution
    }


    if (tradeOffer.getSeller().equals(buyer))
    // Check if seller and buyer are the same person
    {
      Alert error = new Alert(Alert.AlertType.ERROR,
          "The seller cannot be the buyer of their own trade offer.");
      error.setTitle("Invalid trade");
      error.setHeaderText(null);
      error.showAndWait();
      return;
    }


    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to confirm?",
        // Create confirmation alert
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Confirm trade");
    alert.setHeaderText(null);
    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    // Check if user clicked YES
    {
      boolean success = modelManager.executeTrade(tradeOffer, buyer);
      // Try to execute the trade
      if (success)
      {
        updateTradeBox();
        // Refresh trade list
        updateResidentBox();
        // Refresh resident list
      }
      else  // If trade failed
      {
        Alert alert2 = new Alert(Alert.AlertType.ERROR,
            "The buyer does not have enough points ");
        // Create error message
        alert2.setTitle("Error");
        alert2.setHeaderText(null);
        alert2.showAndWait();
      }
    }
  }


  /*
   This method reloads all trade offers from the model and displays
   only those that do not yet have a buyer.
   */
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

  /*
   This method reloads all residents from the model so the user
   can select any resident as a potential buyer.
   */
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
