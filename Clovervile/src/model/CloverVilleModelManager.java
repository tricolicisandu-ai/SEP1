package model;

import javafx.scene.control.Alert;
import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * A model manager providing a single access point to the model
 * @author Christina Jacob
 * @author Sandu Tricolici
 * @author Yuliia Iliienko
 * @ version 1.0
 */

public class CloverVilleModelManager
{
  private String residentsFile;
  private String greenActionsFile;
  private String tradeOffersFile;
  private String communityPoolFile;
  private String thresholdsFile;


  /**
   * 3-argument constructor setting the file name
   * @param greenActionsFile the name and path of the file where green actions will be saved and retrieved
   * @param tradeOffersFile the name and path of the file where trade offers will be saved and retrieved
   * @param residentsFile the name and path of the file where residents will be saved and retrieved
   */


  public CloverVilleModelManager(String greenActionsFile, String tradeOffersFile, String residentsFile,String communityPoolFile, String thresholdsFile)
  {
    this.greenActionsFile = greenActionsFile;
    this.tradeOffersFile = tradeOffersFile;
    this.residentsFile = residentsFile;
    this.communityPoolFile = communityPoolFile;
    this.thresholdsFile = thresholdsFile;
  }

  /**
   * Uses the MyFileHandler class to retrieve a ResidentList object with all Residents.
   * @return a ResidentList object with all stored students
   */

  public ResidentList getAllResidents()
  {
    // Initialize empty resident list
    ResidentList allResidents = new ResidentList();

    try
    {
      allResidents = (ResidentList) MyFileHandler.readFromBinaryFile(residentsFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allResidents;
  }

  /**
   * Uses MyFileHandler class to retrieve a GreenActionList object with all green actions
   * @return a GreenActionList object with all stored green actions
   */
  public GreenActionList getAllGreenActions()
  {
    GreenActionList allGreenActions = new GreenActionList();

    try
    {
      allGreenActions = (GreenActionList) MyFileHandler.readFromBinaryFile(greenActionsFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File Not Found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allGreenActions;
  }

  /**
   * Uses MyFieHandler class to retrieve a TradeOfferList object with all trade offers
   * @return a TradeOfferList object with all stored trade offers
   */
  public TradeOfferList getAllTradeOffers()
  {
    TradeOfferList allTradeOffers = new TradeOfferList();

    try
  {
    allTradeOffers = (TradeOfferList) MyFileHandler.readFromBinaryFile(tradeOffersFile);
  }
    catch (FileNotFoundException e)
    {
      System.out.println("File Not Found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allTradeOffers;
  }

  public void editGreenAction(String oldName, int oldGreenPoints,
      String newName, int newGreenPoints)
  {

    GreenActionList allGreenActions = getAllGreenActions();

    for (int i = 0;  i <allGreenActions.getNumberOfGreenActions(); i++)
    {
      GreenAction greenAction = allGreenActions.getIndex(i);

      if (greenAction.getName().equals(oldName) && greenAction.getGreenPoints()==oldGreenPoints)
      {
        greenAction.setName(newName);
        greenAction.setGreenPoints(newGreenPoints);
      }
      saveGreenActions(allGreenActions);
    }
    try
    {
      MyFileHandler.writeToBinaryFile(greenActionsFile, allGreenActions);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }

  }



public void editResident(String oldFirstName, String oldLastName, int oldPersonalPoints,
    String newFirstName, String newLastName, int newPersonalPoints)
{
  ResidentList residents = getAllResidents();
  for (int i = 0; i < residents.getNumberOfResidents(); i++)
  {
    Resident resident = residents.getResident(i);

    // Find the resident by their current name
    if (resident.getFirstName().equals(oldFirstName) && resident.getLastName()
        .equals(oldLastName) && resident.getPersonalPoints() == oldPersonalPoints)
    {
      // Update to new values
      resident.setFirstName(newFirstName);
      resident.setLastName(newLastName);
      resident.setPersonalPoints(newPersonalPoints);

      // Save the updated list back
      saveResidents(residents); // You need this method
      return; // Exit after finding and editing
    }
  }

      try
      {
        MyFileHandler.writeToBinaryFile(residentsFile, residents);
      }
      catch (FileNotFoundException e)
      {
        System.out.println("File not found");
      }
      catch (IOException e)
      {
        System.out.println("IO Error writing to the file");
      }
    }




  public void saveResidents(ResidentList residents)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(residentsFile, residents);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  public void saveGreenActions(GreenActionList greenActions)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(greenActionsFile, greenActions);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found ");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file ");
    }
  }

  public void saveTradeOffers(TradeOfferList tradeOffers)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(tradeOffersFile, tradeOffers);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }


  public void saveCommunityPool(CommunityPool communityPool)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(communityPoolFile, communityPool);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File Not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  public void saveThresholds(Threshold threshold)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(thresholdsFile, threshold);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  public void addPersonalPoints(ResidentList residents, int points){

    ResidentList all = getAllResidents();

    for (int i = 0; i < residents.getNumberOfResidents() ; i++)
    {
      for (int j = 0; j < all.getNumberOfResidents() ; j++)
      {
        if(all.getResident(j).equals(residents.getResident(i)))
        {
          all.getResident(j).setPersonalPoints(all.getResident(j).getPersonalPoints()+points);
          break;
        }
      }
    }

    saveResidents(all);

  }

  public void resetPoints()// в дужках??  овторюю метод з Controller
  {
    ResidentList residents = getAllResidents();
    int total = residents.getAllPersonalPoints();
    residents.resetAllPersonalPoints();  // скидаю персональні бали
    saveResidents(residents); //чи потрібно зберігати оновлений список резидентів

    CommunityPool pool = getCommunityPool();
    pool.setTotalPoints(pool.getTotalPoints() + total);
    saveCommunityPool(pool);

  }


  public CommunityPool getCommunityPool()
  {
    // Initialize empty resident list
    CommunityPool pool = null;

    try
    {
      pool = (CommunityPool) MyFileHandler.readFromBinaryFile(communityPoolFile);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return pool;
  }

  public void removeGreenAction(GreenAction greenAction)
  {
    GreenActionList greenActionList = getAllGreenActions();
    greenActionList.removeGreenAction(greenAction);
    saveGreenActions(greenActionList);
  }

  public void removeResident(Resident resident)
  {
    ResidentList residentList = getAllResidents();
    residentList.removeResident(resident);
    saveResidents(residentList);


  }


  public void resetGreenAction()
  {
    GreenActionList greenActionList = getAllGreenActions();
    greenActionList.resetGreenAction();
    saveGreenActions(greenActionList);

  }

  public void removeTradeOffer(TradeOffer tradeOffer)
  {
     TradeOfferList tradeOfferList = getAllTradeOffers();
    tradeOfferList.remove(tradeOffer);
    saveTradeOffers(tradeOfferList);
  }


  public void setThreshold(Threshold threshold)
  {

    try
    {
      MyFileHandler.writeToBinaryFile("thresholds.bin", threshold);
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }

  }


  public boolean executeTrade(TradeOffer tradeOffer, Resident buyer)
  {
      TradeOfferList offers = getAllTradeOffers();
      TradeOffer theOffer = null;
      for (int i = 0; i < offers.getNumberOfTradeOffers(); i++)
      {
        if (offers.getTradeOffer(i).equals(tradeOffer))
        {
          theOffer = offers.getTradeOffer(i);
          break;
        }
      }

      if (theOffer != null)
      {
        System.out.println("1");
        ResidentList residents = getAllResidents();
        Resident seller = tradeOffer.getSeller();

        Resident theBuyer = null;
        Resident theSeller = null;

        for (int i = 0; i < residents.getNumberOfResidents(); i++)
        {
          if (residents.getResident(i).equals(buyer))
          {
            System.out.println("2");
            theBuyer = residents.getResident(i);
          }
          if (residents.getResident(i).equals(seller))
          {
            System.out.println("3");
            theSeller = residents.getResident(i);
          }
        }

        if (theBuyer != null && theSeller != null)
        {
          System.out.println("4");
          if (theBuyer.getPersonalPoints() >= theOffer.getPointCost())
          {
            theSeller.setPersonalPoints(theSeller.getPersonalPoints() + theOffer.getPointCost());
            theBuyer.setPersonalPoints(theBuyer.getPersonalPoints() - theOffer.getPointCost());
            theOffer.setBuyer(theBuyer);

//            removeTradeOffer(theOffer);
            saveResidents(residents);
            saveTradeOffers(offers);
            System.out.println("eguwrogwgwf");
            return true;
          }


        }

      }

    return false;
  }


//  public boolean executeTrade(TradeOffer tradeOffer, Resident buyer)
//  {
//    TradeOfferList offers = getAllTradeOffers();
//    TradeOffer theOffer = null;
//    for (int i = 0; i < offers.getNumberOfTradeOffers(); i++)
//    {
//      if (offers.getTradeOffer(i).equals(tradeOffer))
//      {
//        theOffer = offers.getTradeOffer(i);
//        break;
//      }
//    }
//
//    if (theOffer == null)
//    {
//      Alert alert = new Alert(Alert.AlertType.ERROR,
//          "Trade offer not found.");
//      alert.setTitle("Error");
//      alert.setHeaderText(null);
//      alert.showAndWait();
//      return false;
//    }
//
//    Resident seller = tradeOffer.getSeller();
//    ResidentList residents = getAllResidents();
//
//    Resident theBuyer = null;
//    Resident theSeller = null;
//
//    for (int i = 0; i < residents.getNumberOfResidents(); i++)
//    {
//      if (residents.getResident(i).equals(buyer))
//      {
//        theBuyer = residents.getResident(i);
//      }
//      if (residents.getResident(i).equals(seller))
//      {
//        theSeller = residents.getResident(i);
//      }
//    }
//
//
//    if (theBuyer.getPersonalPoints() < theOffer.getPointCost())
//    {
//      Alert alert = new Alert(Alert.AlertType.ERROR,
//          "Fckin poor.");
//      alert.setTitle("Error");
//      alert.setHeaderText(null);
//      alert.showAndWait();
//      return false;
//    }
//
//    theSeller.setPersonalPoints(theSeller.getPersonalPoints() + theOffer.getPointCost());
//    theBuyer.setPersonalPoints(theBuyer.getPersonalPoints() - theOffer.getPointCost());
//    theOffer.setBuyer(theBuyer);
//
//    removeTradeOffer(theOffer);
//    saveResidents(residents);
//    return true;
//  }




}
