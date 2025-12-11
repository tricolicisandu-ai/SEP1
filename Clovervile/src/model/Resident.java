package model;
import java.io.Serializable;
/**
 * Represents a resident with a first name, last name, and a personal points score.
 * The class provides methods for accessing and modifying resident data.
 * @author Yuliia Iliienko
 * @version 1.0
 */
public class Resident implements Serializable
{
  private String firstName;
  private String lastName;
  private int personalPoints;

  /**
   * Constructs a Resident with the given first name, last name, and personal points.
   * @param firstName      the resident's first name
   * @param lastName       the resident's last name
   * @param personalPoints the number of personal points assigned to the resident
   */
  public Resident(String firstName, String lastName, int personalPoints)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.personalPoints = personalPoints;
  }

  /**
   * Returns the resident's first name.
   * @return the first name
   */

  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Returns the resident's last name.
   * @return the last name
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Returns the resident's personal points.
   * @return the personal points
   */
  public int getPersonalPoints()
  {
    return personalPoints;
  }

  /**
   * Updates the resident's first name.
   * @param firstName the new first name
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Updates the resident's last name.
   * @param lastName the new last name
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Updates the resident's personal points.
   * @param personalPoints the new amount of personal points
   */
  public void setPersonalPoints(int personalPoints)
  {
    this.personalPoints = personalPoints;
  }




  /**
   * Returns a string representation of the Resident.
   * @return a string representation of the Resident in the format: firstName, lastName, personalPoints
   */
  public String toString()
  {
    return "The first name: " + firstName + " ,the last name: " + lastName + " ,the personal points: " +personalPoints;
  }

  /**
   * Compares this resident to another object for equality.
   * Two residents are considered equal if their first name,
   * last name, and personal points are the same.
   * @param obj the object to compare with
   * @return true if the objects represent the same resident, false otherwise
   */
  public boolean equals(Object obj)
  {
    if (obj == null || getClass() != obj.getClass())
    {
      return false;
    }
    Resident resid=(Resident) obj;
    return this.firstName.equals(resid.firstName) &&
        this.lastName.equals(resid.lastName) &&
        this.personalPoints==(resid.personalPoints);
  }


}
