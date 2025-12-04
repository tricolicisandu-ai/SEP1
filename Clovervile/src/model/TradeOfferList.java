package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of TradeOffer objects.
 * @author Sandu Tricolici
 * @version 1.0
 */
public class TradeOfferList implements Serializable
{
  private ArrayList<TradeOffer> offers;

  /**
   * No-argument constructor initializing the TradeOfferList.
   */
  public TradeOfferList()
  {
    offers = new ArrayList<TradeOffer>();
  }

  /**
   * Adds a TradeOffer to the list.
   * @param tradeOffer the tradeOffer to add to the list
   */
  public void add(TradeOffer tradeOffer)
  {
    offers.add(tradeOffer);
  }

  /**
   * Removes a TradeOffer from the list at the specified index.
   * @param index the index of the TradeOffer to remove
   */
  public void removeTradeOffer(int index)
  {
    if (index >= 0 && index < offers.size())
    {
      offers.remove(index);
    }
  }


/**
   * Removes a TradeOffer from the list.
   * @param offer the TradeOffer to remove
   * @return true if the TradeOffer was successfully removed, false otherwise
   */
  public boolean remove(TradeOffer offer)
  {
    return offers.remove(offer);
  }

  /**
   * Gets the TradeOffer stored at a specific index.
   * @param index the position of the TradeOffer
   * @return the TradeOffer at the given index
   */
  public TradeOffer getTradeOffer(int index)
  {
    return offers.get(index);
  }

  /**
   * Gets how many TradeOffers objects are in the list.
   * @return the number of TradeOffers objects in the list
   */
  public int getNumberOfTradeOffers()
  {
    return offers.size();
  }

  /**
   * Gets a String representation of the StudentList.
   * @return a String containing information about all Student objects in the list - each Student object followed by a new line character
   */
  public String toString()
  {
    String returnStr = "";

    for(int i = 0; i<offers.size(); i++)
    {
      TradeOffer temp = offers.get(i);

      returnStr += temp +"\n";
    }
    return returnStr;
  }


  /**
   * Compares this TradeOfferList with another object for equality.
   * @param obj The object to compare with this TradeOfferList
   * @return true if the objects are equal, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    TradeOfferList other = (TradeOfferList) obj;
    return offers.equals(other.offers);
  }
}


