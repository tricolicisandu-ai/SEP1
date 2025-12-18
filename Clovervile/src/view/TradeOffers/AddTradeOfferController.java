package view.TradeOffers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import view.ViewHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.ResidentList;
import  model.TradeOfferList;

public class AddTradeOfferController
{
  private Scene scene;
  private CloverVilleModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField tradeOfferName;
  @FXML private TextField pointValue;
  @FXML private TableView<Resident> sellerBox;
  @FXML private TableColumn<Resident, String> firstNameColumn;
  @FXML private TableColumn<Resident, String> lastNameColumn;
  @FXML private TableColumn<Resident, Integer> pointsColumn;


  /**
   * Initializes this controller with the view handler, scene, and model manager.
   */
  public void init(ViewHandler viewHandler, Scene scene, CloverVilleModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
    updateTable();

  }

  /**
   * refreshes the seller list and clears input fields.
   */
  public void reset()
  {
    if (modelManager != null)
    {
      updateTable();
      tradeOfferName.clear();
      pointValue.clear();
    }

  }
  /**
   * Clears input fields and sets up table column mappings so the TableView
   * can display Resident data
   */
  public void initialize()
  {
    tradeOfferName.setText("");
    pointValue.setText("");

    firstNameColumn.setCellValueFactory(new PropertyValueFactory<Resident, String>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<Resident, String>("lastName"));
    pointsColumn.setCellValueFactory(new PropertyValueFactory<Resident, Integer>("personalPoints"));


  }

  /**
   Reads the trade name, point cost, and selected seller,checks the data
   * and creates a TradeOffer, saves it to the model (binary and JSON), and resets the form.
   */
  @FXML
  private void handleAdd(ActionEvent actionEvent)
  {
    String tradeOfferName = this.tradeOfferName.getText();
    String pointValue = this.pointValue.getText();
    Resident seller = sellerBox.getSelectionModel().getSelectedItem();

    // 1. Validate required inputs
    if (seller == null || tradeOfferName.isEmpty() || pointValue.isEmpty())
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Please select a seller and fill out all fields.");
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }

    // 2. Validate points
    int tradePoint;
    try
    {
      tradePoint = Integer.parseInt(pointValue);

      if (tradePoint < 0)
      {
        Alert alert = new Alert(Alert.AlertType.ERROR,
            "Points must be a non-negative number.");
        alert.setHeaderText(null);
        alert.showAndWait();
        return;
      }
    }
    catch (NumberFormatException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR,
          "Points must be a valid number.");
      alert.setHeaderText(null);
      alert.showAndWait();
      return;
    }

    // 3. Create and save trade offer
    TradeOffer tradeOffer = new TradeOffer(tradeOfferName, tradePoint, seller);
    TradeOfferList tradeOfferList = modelManager.getAllTradeOffers();
    tradeOfferList.add(tradeOffer);
    modelManager.saveTradeOffers(tradeOfferList);
    modelManager.saveTradeOfferListAsJson(tradeOfferList);

    reset();
  }

  /**
   * Refreshes the seller ComboBox.
   * Clears existing items and reloads all residents
   */
  private void updateTable()
  {


    sellerBox.getItems().clear();

    ResidentList residents = modelManager.getAllResidents();
    for (int i = 0; i < residents.getNumberOfResidents(); i++)
    {
      sellerBox.getItems().add(residents.getResident(i));
    }

  }

}
