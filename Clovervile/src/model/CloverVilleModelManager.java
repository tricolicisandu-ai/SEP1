package model;

import parser.ParserException;
import parser.XmlJsonParser;
import utils.MyFileHandler;

import java.io.File;
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

//  public void editGreenAction(String oldName, int oldGreenPoints, String newName, int newGreenPoints)
//  {
//
//    GreenActionList allGreenActions = getAllGreenActions();
//
//    for (int i = 0;  i <allGreenActions.getNumberOfGreenActions(); i++)
//    {
//      GreenAction greenAction = allGreenActions.getIndex(i);
//
//      if (greenAction.getName().equals(oldName) && greenAction.getGreenPoints()==oldGreenPoints)
//        greenAction.setName(newName);
//        greenAction.setGreenPoints(newGreenPoints);
//    }
//    try
//    {
//      MyFileHandler.writeToBinaryFile(greenActionsFile, allGreenActions);
//    }
//    catch (FileNotFoundException e)
//    {
//      System.out.println("File not found");
//    }
//    catch (IOException e)
//    {
//      System.out.println("IO Error writing to file");
//    }
//  }

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

    for (int i = 0; i < allGreenActions.getNumberOfGreenActions(); i++)
    {
      GreenAction greenAction = allGreenActions.getIndex(i);

      if (greenAction.getName().equals(oldName) && greenAction.getGreenPoints() == oldGreenPoints)
      {
        greenAction.setName(newName);
        greenAction.setGreenPoints(newGreenPoints);
      }
    }
    saveGreenActions(allGreenActions);
    saveGreenActionsAsJson(allGreenActions);
  }


  //  // Change the country of the model.Student with the given firstname and lastname
  //  public void changeCountry(String firstName, String lastName, String country)
  //  {
  //    StudentList allStudents = getAllStudents();
  //
  //    for (int i = 0; i < allStudents.size(); i++)
  //    {
  //      Student student = allStudents.get(i);
  //
  //      if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName))
  //      {
  //        student.setCountry(country);
  //      }
  //    }
  //
  //    saveStudents(allStudents);
  //  }

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

    for (int i = 0; i < newResidents.getNumberOfResidents(); i++)
    {
      Resident resident = newResidents.getResident(i);

      if (resident.getFirstName().equals(oldFirstName) &&
          resident.getLastName().equals(oldLastName) &&
          resident.getPersonalPoints() == oldPersonalPoints)
      {
        resident.setFirstName(newFirstName);
        resident.setLastName(newLastName);
        resident.setPersonalPoints(newPersonalPoints);
      }
    }

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

/**Uses MyFileHandler class to save the given ResidentList object to file
   * @param residents the ResidentList object to be saved
   */
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

/** Uses MyFileHandler class to save the given GreenActionList object to file
   * @param greenActions the GreenActionList object to be saved
   */
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

  /** Uses MyFileHandler class to save the given TradeOfferList object to file
   * @param tradeOffers the TradeOfferList object to be saved
   */
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

/** Uses MyFileHandler class to save the given CommunityPool object to file
   * @param communityPool the CommunityPool object to be saved
   */
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

  /** Uses MyFileHandler class to save the given Threshold object to file
   * @param threshold the Threshold object to be saved
   */
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

/**   * Adds the given points to each resident in the given ResidentList
   * @param residents the ResidentList object containing the residents to add points to
   * @param points the number of points to add to each resident
   */
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

/**   * Resets the personal points of all residents and adds the total to the community pool
   */
  public void resetPoints()// в дужках??  овторюю метод з Controller
  {
    ResidentList residents = getAllResidents();
    int total = residents.getAllPersonalPoints();
    residents.resetAllPersonalPoints();  // скидаю персональні бали
    saveResidents(residents); //чи потрібно зберігати оновлений список резидентів

    CommunityPool pool = getCommunityPool();
    pool.setTotalPoints(pool.getTotalPoints() + total);

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

  /**Retrieves the Threshold object from file
   * @return the Threshold object
   */
  public void removeGreenAction(GreenAction greenAction)
  {
    GreenActionList greenActionList = getAllGreenActions();
    greenActionList.removeGreenAction(greenAction);
    saveGreenActions(greenActionList);
    saveGreenActionsAsJson(greenActionList);
  }
  /**Removes the given resident from the ResidentList and saves the updated list to file
   * @param resident the Resident object to be removed
   */
  public void removeResident(Resident resident)
  {
    ResidentList residentList = getAllResidents();
    residentList.removeResident(resident);
    saveResidents(residentList);
  }

  /** Resets all green actions in the GreenActionList and saves the updated list to file
   */
  public void resetGreenAction()
  {
    GreenActionList greenActionList = getAllGreenActions();
    greenActionList.resetGreenAction();
    saveGreenActions(greenActionList);
    saveGreenActionsAsJson(greenActionList);
  }

  /**Removes the given trade offer from the TradeOfferList and saves the updated list to file
   * @param tradeOffer the TradeOffer object to be removed
   */
  public void removeTradeOffer(TradeOffer tradeOffer)
  {
    TradeOfferList tradeOfferList = getAllTradeOffers();
    tradeOfferList.remove(tradeOffer);
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
    }
    catch (IOException e)
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
    TradeOffer theOffer = null;
    for (int i = 0; i < offers.getNumberOfTradeOffers() ; i++)
    {
      if(offers.getTradeOffer(i).equals(tradeOffer))
      {
        theOffer = offers.getTradeOffer(i);
      }
    }

    if(theOffer!=null)
    {
      ResidentList residents = getAllResidents();
      Resident seller = tradeOffer.getSeller();

      Resident theBuyer = null;
      Resident theSeller = null;

      for (int i = 0; i < residents.getNumberOfResidents() ; i++)
      {
        if(residents.getResident(i).equals(buyer))
        {
          theBuyer = residents.getResident(i);
        }
        if(residents.getResident(i).equals(seller))
        {
          theSeller = residents.getResident(i);
        }
      }

      if(theBuyer!=null && theSeller!=null)
      {
        if (theBuyer.getPersonalPoints() >= theOffer.getPointCost())
        {
          theSeller.setPersonalPoints(theSeller.getPersonalPoints()+theOffer.getPointCost());
          theBuyer.setPersonalPoints(theBuyer.getPersonalPoints()-theOffer.getPointCost());
          theOffer.setBuyer(theBuyer);

          saveTradeOffers(offers);
          saveResidents(residents);

          return true;
        }
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
    try
    {
      greenActionParser.toJsonFile(list, "Clovervile/GreenActions.json");
    }
    catch (ParserException e)
    {
      System.out.println("Error");
      System.out.println(e.getMessage());
    }

  }

/** Saves the given TradeOfferList object as a JSON file
   * @param tradeOfferList the TradeOfferList object to be saved as JSON
   */
  public void saveTradeOfferListAsJson(TradeOfferList tradeOfferList)
  {

    XmlJsonParser tradeOffersParser = new XmlJsonParser();
    try
    {
      tradeOffersParser.toJsonFile(tradeOfferList, "Clovervile/TradeOffers.json");
    }
    catch (ParserException e)
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
    try
    {
      thresholdParser.toJsonFile(newThreshold, "Clovervile/Threshold.json");
    }
    catch (ParserException e)
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
    try
    {
      communityPoolParser.toJsonFile(communityPool, "Clovervile/CommunityPool.json");
    }
    catch (ParserException e)
    {
      System.out.println("Error saving CommunityPool as JSON");
      System.out.println(e.getMessage());
    }
  }
}
