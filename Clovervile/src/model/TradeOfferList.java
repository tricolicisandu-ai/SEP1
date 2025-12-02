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
  private ArrayList<TradeOffer> tradeOffers;

  /**
   * No-argument constructor initializing the TradeOfferList.
   */
  public TradeOfferList()
  {
    tradeOffers = new ArrayList<TradeOffer>();
  }

  /**
   * Adds a TradeOffer to the list.
   * @param tradeOffer the tradeOffer to add to the list
   */
  public void add(TradeOffer tradeOffer)
  {
    tradeOffers.add(tradeOffer);
  }
}
