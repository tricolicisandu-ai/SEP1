package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewHandler;
import model.CloverVilleModelManager;

public class StartApplication extends Application
{
  @Override
  public void start(Stage stage)
  {
    CloverVilleModelManager modelManager = new CloverVilleModelManager("Cloverville/greenActions.bin",  "Cloverville/tradeOffers.bin", "Cloverville/residents.bin", "Cloverville/communityPool.bin", "Cloverville/thresholds.bin");
    ViewHandler viewHandler = new ViewHandler(stage, modelManager);
    viewHandler.start();
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}

