package model;

import java.io.Serializable;

/**
 * A class representing a Trade Offer with a offer name, point cost and the seller.
 * @author Sandu Tricolici
 * @version 1.0
 */
public class TradeOffer implements Serializable
{
  private String offerName;
  private int pointCost;
  private Resident seller;
  private Resident buyer;

  /**
   * Three-argument constructor.
   * @param offerName the tradeOffer's name
   * @param pointCost the tradeOffer's point
   * @param seller the seller of the Trade Offer
   */
  public TradeOffer(String offerName, int pointCost, Resident seller)
  {
    this.offerName = offerName;
    this.pointCost = pointCost;
    this.seller = seller;
  }

  /**
   * Gets the tradeOffer's name of offer
   * @return the offer name of the Trade Offer
   */
  public String getOfferName()
  {
    return offerName;
  }

  /**
   * Gets the tradeOffer's point Cost
   * @return the point value Cost of the Trade Offer
   */
  public int getPointCost()
  {
    return pointCost;
  }

  /**
   * Gets the TradeOffer's seller from Resident Class
   * @return the Seller information of the Trade Offer
   */
  public Resident getSeller()
  {
    return seller;
  }

  /**
   * Gets the TradeOffer's buyer from Resident Class
   * @return the Buyer information of the Trade Offer
   */
  public Resident getBuyer()
  {
    return buyer;
  }

  /**
   * Sets the tradeOffer's offer name.
   * @param offerName what the tradeOffer's offer name will be set to
   */
  public void setOfferName(String offerName)
  {
    this.offerName = offerName;
  }

  /**
   * Sets the tradeOffer's point cost.
   * @param pointCost what the tradeOffer's Point Cost will be set to
   */
  public void setPointCost(int pointCost)
  {
    this.pointCost = pointCost;
  }

  /**
   * Sets the tradeOffer's person who buys the trade offer.
   * @param buyer what the tradeOffer's buyer will be set to
   */
  public void setBuyer(Resident buyer)
  {
    this.buyer = buyer;
  }

  /**
   * Returns a string representation of the TradeOffer.
   * @return a string representation of the TradeOffer in the format: "OfferName: pointCost, The seller: SellerName"
   */
  public String toString()
  {
    return offerName + ": " + pointCost + "p, " + "The seller: " + seller.getFirstName() + " " + seller.getLastName();
  }

  /**
   * Compares offer name, point cost and seller of two TradeOffer.
   * @param obj the object to compare with
   * @return true if the given object is equal to this TradeOffer
   */
  public boolean equals(Object obj)
  {
    if(obj == null || getClass() != obj.getClass())
    {
      return false;
    }

    TradeOffer other = (TradeOffer) obj;

    return offerName.equals(other.offerName) && pointCost == other.pointCost && seller.equals(other.seller);
  }
}



