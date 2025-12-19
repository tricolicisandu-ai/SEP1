package model;

import parser.ParserException;
import parser.XmlJsonParser;
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
  // Main model manager class
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

    ResidentList allResidents = new ResidentList();
    // Create empty resident list

    try
    {
      allResidents = (ResidentList) MyFileHandler.readFromBinaryFile(residentsFile);
    // Read residents from file
    }
    catch (FileNotFoundException e)
    // If file is missing
    {
      System.out.println("File not found");
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
        // if class not found
    {
      System.out.println("Class Not Found");
    }
    return allResidents;
    // return resident list
  }

  /**
   * Uses MyFileHandler class to retrieve a GreenActionList object with all green actions
   * @return a GreenActionList object with all stored green actions
   */
  public GreenActionList getAllGreenActions()
  {
    GreenActionList allGreenActions = new GreenActionList();

    try
        // try to read data from file
    {
      allGreenActions = (GreenActionList) MyFileHandler.readFromBinaryFile(greenActionsFile);
      // read green actions from file
    }
    catch (FileNotFoundException e)
        // if file is not found
    {
      System.out.println("File Not Found");
    }
    catch (IOException e)
        // if error happens
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
    // try read data from file
    {
      allTradeOffers = (TradeOfferList) MyFileHandler.readFromBinaryFile(tradeOffersFile);
      // read trade offers from file
    }
    catch (FileNotFoundException e)
    // if file is not found
    {
      System.out.println("File Not Found");
    }
    catch (IOException e)
        //if error happens
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allTradeOffers;
  }



  /**
   * Edit the green action with the given old name and old green points
   * @param oldName the old name of the green action
   * @param oldGreenPoints the old green points of the green action
   * @param newName the new name of the green action
   * @param newGreenPoints the new green points of the green action
   */
  public void editGreenAction(String oldName, int oldGreenPoints, String newName, int newGreenPoints)
  {
    GreenActionList allGreenActions = getAllGreenActions();
    // Get all green actions from file

    for (int i = 0; i < allGreenActions.getNumberOfGreenActions(); i++)
    // loop through all green actions
    {
      GreenAction greenAction = allGreenActions.getIndex(i);
      // get green action at index i

      if (greenAction.getName().equals(oldName) && greenAction.getGreenPoints() == oldGreenPoints)
      // check if points match ald points
      {
        greenAction.setName(newName);
        // set new name
        greenAction.setGreenPoints(newGreenPoints);
        // set new green points
      }
    }
    saveGreenActions(allGreenActions);
    // save updated actions to file
    saveGreenActionsAsJson(allGreenActions);
    // save updated to JSON
  }



  /**
   * Edit the resident with the given old first name, old last name and old personal points
   * @param oldFirstName the old first name of the resident
   * @param oldLastName the old last name of the resident
   * @param oldPersonalPoints the old personal points of the resident
   * @param newFirstName the new first name of the resident
   * @param newLastName the new last name of the resident
   * @param newPersonalPoints the new personal points of the resident
   */
  public void editResident(String oldFirstName, String oldLastName,
      int oldPersonalPoints, String newFirstName, String newLastName,
      int newPersonalPoints)
  {
    ResidentList newResidents = getAllResidents();
    // get all residents from file

    for (int i = 0; i < newResidents.getNumberOfResidents(); i++)
    // loop through all residents
    {
      Resident resident = newResidents.getResident(i);
      // get resident at index i

      if (resident.getFirstName().equals(oldFirstName) &&
          resident.getLastName().equals(oldLastName) &&
          resident.getPersonalPoints() == oldPersonalPoints)
      // check old first name, old last name, old pers.points
      {
        resident.setFirstName(newFirstName);
        resident.setLastName(newLastName);
        resident.setPersonalPoints(newPersonalPoints);
      } // set new first name, new last name, new pers.points
    }

    try
    // try to save residents to file
    {
      MyFileHandler.writeToBinaryFile(residentsFile, newResidents);
      // write residents to file
    }
    catch (FileNotFoundException e)
        // if file is not found
    {
      System.out.println("File not found");
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error writing to the file");
    }
  }

/**Uses MyFileHandler class to save the given ResidentList object to file
   * @param residents the ResidentList object to be saved
   */
  public void saveResidents(ResidentList residents)
  {
    try

    {
      MyFileHandler.writeToBinaryFile(residentsFile, residents);
      // write residents to file
    }
    catch (FileNotFoundException e)
        // if file is not found
    {
      System.out.println("File not found ");
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error writing to file");
    }
  }

/** Uses MyFileHandler class to save the given GreenActionList object to file
   * @param greenActions the GreenActionList object to be saved
   */
  public void saveGreenActions(GreenActionList greenActions)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(greenActionsFile, greenActions);
      // write green action to file
    }
    catch (FileNotFoundException e)
        // if file is not found
    {
      System.out.println("File not found ");
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error writing to file ");
    }
  }

  /** Uses MyFileHandler class to save the given TradeOfferList object to file
   * @param tradeOffers the TradeOfferList object to be saved
   */
  public void saveTradeOffers(TradeOfferList tradeOffers)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(tradeOffersFile, tradeOffers);
      //write trade offer to file
    }
    catch (FileNotFoundException e)
        // if file is not found
    {
      System.out.println("File not found");
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error writing to file");
    }
  }

/** Uses MyFileHandler class to save the given CommunityPool object to file
   * @param communityPool the CommunityPool object to be saved
   */
  public void saveCommunityPool(CommunityPool communityPool)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(communityPoolFile, communityPool);
      // write commun.pool to file
    }
    catch (FileNotFoundException e)
        // if file is not found
    {
      System.out.println("File Not found");
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error writing to file");
    }
  }

  /**  *Uses MyFileHandler class to save the given Threshold object to file
   * @param threshold the Threshold object to be saved
   */
  public void saveThresholds(Threshold threshold)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(thresholdsFile, threshold);
      // write thresholds to file
    }
    catch (FileNotFoundException e)
        // if file is not found
    {
      System.out.println("File not found");
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error writing to file");
    }
  }

/**   * Adds the given points to each resident in the given ResidentList
   * @param residents the ResidentList object containing the residents to add points to
   * @param points the number of points to add to each resident
   */
  public void addPersonalPoints(ResidentList residents, int points){

    ResidentList all = getAllResidents();
    // get all residents from file

    for (int i = 0; i < residents.getNumberOfResidents() ; i++)
    // loop through given residents
    {
      for (int j = 0; j < all.getNumberOfResidents() ; j++)
      // loop through all residents
      {
        if(all.getResident(j).equals(residents.getResident(i)))
        // check if residents are the same
        {
          all.getResident(j).setPersonalPoints(all.getResident(j).getPersonalPoints()+points);
          // update pers. points and add points
          break;
          // stop inner loop when resident is found
        }
      }
    }
    saveResidents(all);
    // save updated residents to file
  }




/**Retrieves the CommunityPool object from file
   * @return the CommunityPool object
   */
  public CommunityPool getCommunityPool()
  {
    // Initialize empty resident list
    CommunityPool pool = new CommunityPool();

    try
    {
      pool = (CommunityPool) MyFileHandler.readFromBinaryFile(communityPoolFile);
      // read commun. pool from file
    }
    catch (FileNotFoundException e)
        //if file is not found
    {
      System.out.println("File not found");
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
        // if class is not found
    {
      System.out.println("Class Not Found");
    }
    return pool;
  }

  /**Retrieves the Threshold object from file
   * @return the Threshold object
   */
  public void removeGreenAction(GreenAction greenAction)
  {
    GreenActionList greenActionList = getAllGreenActions();
    // get all green actions from file
    greenActionList.removeGreenAction(greenAction);
    // remove given green action from list
    saveGreenActions(greenActionList);
    // save updated green actions to file
    saveGreenActionsAsJson(greenActionList);
    // save updated to JSON
  }
  /**Removes the given resident from the ResidentList and saves the updated list to file
   * @param resident the Resident object to be removed
   */
  public void removeResident(Resident resident)
  {
    ResidentList residentList = getAllResidents();
    // get all residents from file
    residentList.removeResident(resident);
    // remove given resident from list
    saveResidents(residentList);
    // save updated residents to file
  }

  /** Resets all green actions in the GreenActionList and saves the updated list to file
   */
  public void resetGreenAction()
  {
    GreenActionList greenActionList = getAllGreenActions();
    //get all green actions from file
    greenActionList.resetGreenAction();
    // reset green action values
    saveGreenActions(greenActionList);
    // save updated green action to file
    saveGreenActionsAsJson(greenActionList);
  }

  /**Removes the given trade offer from the TradeOfferList and saves the updated list to file
   * @param tradeOffer the TradeOffer object to be removed
   */
  public void removeTradeOffer(TradeOffer tradeOffer)
  {
    TradeOfferList tradeOfferList = getAllTradeOffers();
    tradeOfferList.remove(tradeOffer);
    // remove given trade offer
    saveTradeOffers(tradeOfferList);
    saveTradeOfferListAsJson(tradeOfferList);
  }

/**Retrieves the Threshold object from file
   * @return the Threshold object
   */
  public void setThreshold(Threshold threshold)
  {

    try
    {
      MyFileHandler.writeToBinaryFile("thresholds.bin", threshold);
      // write threshold to binary file
    }
    catch (IOException e)
        // if error happens
    {
      System.out.println("IO Error reading file");
    }

  }

/** Executes the given trade offer for the given buyer resident
   * @param tradeOffer the TradeOffer object to be executed
   * @param buyer the Resident object representing the buyer
   * @return true if the trade offer was successfully executed, false otherwise
   */


public boolean executeTrade(TradeOffer tradeOffer, Resident buyer)
{
  TradeOfferList offers = getAllTradeOffers();
  // load all trade offers from file
  ResidentList residents = getAllResidents();
  // load all residents from file

  for (int i = 0; i < offers.getNumberOfTradeOffers(); i++)
  // loop through all trade offers
  {
    TradeOffer current = offers.getTradeOffer(i);
    // get trade offer at index i

    if (current.equals(tradeOffer))
    // check if this offer is the one we want to execute
    {
      Resident seller = current.getSeller();
      // get the seller from the trade offer

      Resident theBuyer = null;
      // will store the buyer object from the main residents list
      Resident theSeller = null;
      // will store the seller object from the main residents list

      for (int j = 0; j < residents.getNumberOfResidents(); j++)
      // loop through all residents
      {
        if (residents.getResident(j).equals(buyer))
          // if this resident matches the buyer
          theBuyer = residents.getResident(j);
        // save the real buyer reference from the list

        if (residents.getResident(j).equals(seller))
          // if this resident matches the seller
          theSeller = residents.getResident(j);
        // save the real seller reference from the list
      }

      if (theBuyer == null || theSeller == null)
        // if buyer or seller was not found in residents list
        return false;

      if (theBuyer.getPersonalPoints() < current.getPointCost())
        // check if buyer has enough points
        return false;


      theSeller.setPersonalPoints(theSeller.getPersonalPoints() + current.getPointCost());
      // add points to seller
      theBuyer.setPersonalPoints(theBuyer.getPersonalPoints() - current.getPointCost());
      // subtract points from buyer
      offers.remove(current);
      // remove the trade offer after it is completed


      saveTradeOffers(offers);
      saveResidents(residents);
      saveTradeOfferListAsJson(offers);

      return true;
    }
  }
  return false;
}


  /** Saves the given GreenActionList object as a JSON file
   * @param list the GreenActionList object to be saved as JSON
   */
  public void saveGreenActionsAsJson(GreenActionList list)
  {

    XmlJsonParser greenActionParser = new XmlJsonParser();
    // create JSON parser
    try
    // try to save green actions to JSON file
    {
      greenActionParser.toJsonFile(list, "Clovervile/GreenActions.json");
    // convert list to JSON file
    }
    catch (ParserException e)
    // if parsing error happens
    {
      System.out.println("Error");
      System.out.println(e.getMessage());
      // print detailed error message
    }

  }

/** Saves the given TradeOfferList object as a JSON file
   * @param tradeOfferList the TradeOfferList object to be saved as JSON
   */
  public void saveTradeOfferListAsJson(TradeOfferList tradeOfferList)
  {

    XmlJsonParser tradeOffersParser = new XmlJsonParser();
    // create JSON parser
    try
    // try to save trade offers to JSON file
    {
      tradeOffersParser.toJsonFile(tradeOfferList, "Clovervile/TradeOffers.json");
    // convert list to JSON file
    }
    catch (ParserException e)
        // if parsing error happens
    {
      System.out.println("Error");
      System.out.println(e.getMessage());
    }
  }

/** Saves the given Threshold object as a JSON file
   * @param newThreshold the Threshold object to be saved as JSON
   */
  public void saveThresholdAsJson(Threshold newThreshold)
  {

    XmlJsonParser thresholdParser = new XmlJsonParser();
    // create JSON parser
    try
    // try to save threshold to JSON file
    {
      thresholdParser.toJsonFile(newThreshold, "Clovervile/Threshold.json");
    // convert threshold to JSON file
    }
    catch (ParserException e)
        // if parsing error happens
    {
      System.out.println("Error");
      System.out.println(e.getMessage());
    }

  }

/** Saves the given CommunityPool object as a JSON file
   * @param communityPool the CommunityPool object to be saved as JSON
   */
  public void saveCommunityPoolAsJson(CommunityPool communityPool)
  {
    XmlJsonParser communityPoolParser = new XmlJsonParser();
    // create Json parser
    try
    {
      communityPoolParser.toJsonFile(communityPool, "Clovervile/CommunityPool.json");
      // convert community pool to JSON file
    }
    catch (ParserException e)
        // if parsing error happens
    {
      System.out.println("Error saving CommunityPool as JSON");
      System.out.println(e.getMessage());
    }
  }


/** Checks if the given resident is involved in any trade offers
   * @param resident the Resident object to check
   * @return true if the resident is involved in any trade offers, false otherwise
   */

  public boolean isResidentInTradeOffer(Resident resident)
  {
    TradeOfferList tradeOfferList = getAllTradeOffers();

    if (resident == null)
    {
      return false;
      // return false if resident does not exist
    }

    for (int i = 0; i < tradeOfferList.getNumberOfTradeOffers(); i++)
    //Loop through all trade offers
    {
      TradeOffer offer = tradeOfferList.getTradeOffer(i);
      // get trade offer at index i

      if (resident.equals(offer.getSeller()) ||
          resident.equals(offer.getBuyer()))
      // check if resident is the seller and if the resident is the buyer
      {
        return true;
        // resident is found in a trade offer
      }
    }

    return false;
    // resident is not in any trade offer
  }


/** Removes all trade offers associated with the given seller resident
   * @param seller the Resident object representing the seller
   */

  public void removeTradeOffersBySeller(Resident seller)
  {
    TradeOfferList tradeOfferList = getAllTradeOffers();

    for (int i = tradeOfferList.getNumberOfTradeOffers() - 1; i >= 0; i--)
    // loop backwards through trade offers
    {
      TradeOffer offer = tradeOfferList.getTradeOffer(i);
      // get trade offer at index i

      if (offer.getSeller().equals(seller))
      // check if seller matches
      {
        tradeOfferList.remove(offer);
        // remove trade offer
      }
    }

    saveTradeOffers(tradeOfferList);
    // save updated trade offers to file
    saveTradeOfferListAsJson(tradeOfferList);
    // save updated trade offers to JSON
  }




}
