package view.Resident;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.CloverVilleModelManager;
import model.CommunityPool;
import model.Resident;

public class PersonalPointsViewController
{
  @FXML private TextField addPoints;
  @FXML private ListView<Resident> residentList;

  private CloverVilleModelManager manager;

  public void initialize()
  {
residentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    manager = new CloverVilleModelManager("greenActions.bin",  "tradeOffers.bin", "residents.bin", "communityPool.bin", "thresholds.bin");
  }

  public void resetPoints(ActionEvent actionEvent)
  {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
        "Do you really want to reset?",
        ButtonType.YES, ButtonType.NO);
    alert.setTitle("Exit");
    alert.setHeaderText(null);

    alert.showAndWait();

    if (alert.getResult() == ButtonType.YES)
    {
      int total = manager.getAllResidents().getAllPersonalPoints();

      manager.getAllResidents().resetAllPersonalPoints();

      CommunityPool pool = manager.getCommunityPool();
      pool.setTotalPoints(pool.getTotalPoints()+total);
      manager.saveCommunityPool(pool);

    }




  }

  public void confirm(ActionEvent actionEvent)
  {

  }
}
