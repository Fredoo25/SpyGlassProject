package SpyGlass.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public User() {
        this.uid = UUID.randomUUID();
    }

    public User(String uid, List<UUID> goals, List<UUID> investmentAccounts) {
        this.uid = UUID.fromString(uid);
        this.goals = goals;
        this.investmentAccounts = investmentAccounts;
    }

    public User(List<UUID> goals, List<UUID> investmentAccounts) {
        this();
        this.goals = goals;
        this.investmentAccounts = investmentAccounts;
    }

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
  public void setUid (String newVar) {
    uid = UUID.fromString(newVar);
  }

  /**
   * Get the value of uid
   * User Uinique Identifier
   * @return the value of uid
   */
  public String getUid () {
    return uid.toString();
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
  public List<String> getGoals () {
    return goals.stream().map(UUID::toString).collect(Collectors.toList());
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
  public List<String> getInvestmentAccounts () {
    return investmentAccounts.stream().map(UUID::toString).collect(Collectors.toList());
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
