package view.TradeOffers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import model.*;
import view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.ResidentList;
import  model.TradeOfferList;

public class ManageTradeOfferController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField tradeOfferName;
  @FXML private TextField pointValue;
  @FXML private TableView sellerBox;
  private TradeOfferList tradeOfferList;
  private ResidentList residentList;



  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }
  public void initialize()
  {
    tradeOfferName.setText("");
    pointValue.setText("");

  }

  public void setTradeOfferList(TradeOfferList tradeOfferList)
  {
    this.tradeOfferList = tradeOfferList;
  }

  @FXML private void handleAdd(ActionEvent actionEvent)
  {
    String tradeOfferName = this.tradeOfferName.getText();
    String pointValue = this.pointValue.getText();

    if (tradeOfferName.isEmpty() || pointValue.isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "All fields must be filled out");
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
    }

    try
    {
      int tradePoint = Integer.parseInt(pointValue);
      Resident seller = sellerBox.getSelectionModel().getSelectedItem(); //???
      TradeOffer tradeOffer = new TradeOffer(tradeOfferName, tradePoint,
          seller);

      if (tradeOfferList != null)
      {
        tradeOfferList.add(tradeOffer);
      }
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Points must be a non-negative number.");
      alert.setHeaderText(null);
      alert.showAndWait();
    }
  }
}
