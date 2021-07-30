package SpyGlass.Models;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Class User
 */
public class User {

  //
  // Fields
  //

  /**
   * User Uinique Identifier
   */
  private UUID uid;
  /**
   * List of UID For Goals.
   */
  private List<UUID> goals;
  /**
   * List of Investment Accounts. 
   */
  private List<UUID> investmentAccounts;
  
  //
  // Constructors
  //
  public User () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of uid
   * User Uinique Identifier
   * @param newVar the new value of uid
   */
  public void setUid (UUID newVar) {
    uid = newVar;
  }

  /**
   * Get the value of uid
   * User Uinique Identifier
   * @return the value of uid
   */
  public UUID getUid () {
    return uid;
  }

  /**
   * Set the value of goals
   * List of UID For Goals.
   * @param newVar the new value of goals
   */
  public void setGoals (List<UUID> newVar) {
    goals = newVar;
  }

  /**
   * Get the value of goals
   * List of UID For Goals.
   * @return the value of goals
   */
  public List<UUID> getGoals () {
    return goals;
  }

  /**
   * Set the value of investmentAccounts
   * List of Investment Accounts.
   * @param newVar the new value of investmentAccounts
   */
  public void setInvestmentAccounts (List<UUID> newVar) {
    investmentAccounts = newVar;
  }

  /**
   * Get the value of investmentAccounts
   * List of Investment Accounts.
   * @return the value of investmentAccounts
   */
  public List<UUID> getInvestmentAccounts () {
    return investmentAccounts;
  }

  //
  // Other methods
  //


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(uid, user.uid) && Objects.equals(goals, user.goals) && Objects.equals(investmentAccounts, user.investmentAccounts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, goals, investmentAccounts);
  }

  @Override
  public String toString() {
    return "User{" +
            "uid=" + uid +
            ", goals=" + goals +
            ", investmentAccounts=" + investmentAccounts +
            '}';
  }
}
