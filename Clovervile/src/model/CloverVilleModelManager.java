package model;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A model manager providing a single access point to the model
 * @author Christina Jacob
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

  public void editGreenAction(String editedName, int editedGreenPoints)
  {

    GreenActionList newGreenActions = getAllGreenActions();

    try
    {
      MyFileHandler.writeToBinaryFile(greenActionsFile, newGreenActions);
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

  public void editResident(String firstName, int personalPoints)
  {
    ResidentList newResidents = getAllResidents();

    try
    {
      MyFileHandler.writeToBinaryFile(residentsFile, newResidents);
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


}
