package SpyGlass.Models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Class Goal
 */
public class Goal {

  //
  // Fields
  //

  private UUID uid;
  private double amount;
  private String name;
  private String description;
  private LocalDate startDate;
  private LocalDate projectedEndDate;
  private boolean isInvested;
  private double amountPerInterval;
  private IncrementFrequency savingInterval;
  private boolean onTrack;
  private UUID investmentAccountUID;
  private UUID userUID;
  
  //
  // Constructors
  //
  public Goal () {
    this.uid = UUID.randomUUID();
    this.startDate = LocalDate.now();
  };

  public Goal(String name, String description, LocalDate projectedEndDate, boolean isInvested, IncrementFrequency savingInterval, UUID investmentAccountUID, UUID userUID, double amount) {
    this();
    this.amount = amount;
    this.name = name;
    this.description = description;
    this.projectedEndDate = projectedEndDate;
    this.isInvested = isInvested;
    this.savingInterval = savingInterval;
    this.investmentAccountUID = investmentAccountUID;
    this.userUID = userUID;
    computeAmountPerInterval();
  }

  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of uid
   * @param newVar the new value of uid
   */
  public void setUid (UUID newVar) {
    uid = newVar;
  }

  /**
   * Get the value of uid
   * @return the value of uid
   */
  public UUID getUid () {
    return uid;
  }

  /**
   * Set the value of name
   * @param newVar the new value of name
   */
  public void setName (String newVar) {
    name = newVar;
  }

  /**
   * Get the value of name
   * @return the value of name
   */
  public String getName () {
    return name;
  }

  /**
   * Set the value of description
   * @param newVar the new value of description
   */
  public void setDescription (String newVar) {
    description = newVar;
  }

  /**
   * Get the value of description
   * @return the value of description
   */
  public String getDescription () {
    return description;
  }

  /**
   * Set the value of startDate
   * @param newVar the new value of startDate
   */
  public void setStartDate (LocalDate newVar) {
    startDate = newVar;
  }

  /**
   * Get the value of startDate
   * @return the value of startDate
   */
  public LocalDate getStartDate () {
    return startDate;
  }

  /**
   * Set the value of projectedEndDate
   * @param newVar the new value of projectedEndDate
   */
  public void setProjectedEndDate (LocalDate newVar) {
    projectedEndDate = newVar;
  }

  /**
   * Get the value of projectedEndDate
   * @return the value of projectedEndDate
   */
  public LocalDate getProjectedEndDate () {
    return projectedEndDate;
  }

  /**
   * Set the value of isInvested
   * @param newVar the new value of isInvested
   */
  public void setIsInvested (boolean newVar) {
    isInvested = newVar;
  }

  /**
   * Get the value of isInvested
   * @return the value of isInvested
   */
  public boolean getIsInvested () {
    return isInvested;
  }

  /**
   * Set the value of amountPerInterval
   * @param newVar the new value of amountPerInterval
   */
  public void setAmountPerInterval (double newVar) {
    amountPerInterval = newVar;
  }

  /**
   * Get the value of amountPerInterval
   * @return the value of amountPerInterval
   */
  public double getAmountPerInterval () {
    return amountPerInterval;
  }

  /**
   * Set the value of savingInterval
   * @param newVar the new value of savingInterval
   */
  public void setSavingInterval (IncrementFrequency newVar) {
    savingInterval = newVar;
  }

  /**
   * Get the value of savingInterval
   * @return the value of savingInterval
   */
  public IncrementFrequency getSavingInterval () {
    return savingInterval;
  }

  /**
   * Set the value of onTrack
   * @param newVar the new value of onTrack
   */
  public void setOnTrack (boolean newVar) {
    onTrack = newVar;
  }

  /**
   * Get the value of onTrack
   * @return the value of onTrack
   */
  public boolean getOnTrack () {
    return onTrack;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public boolean isInvested() {
    return isInvested;
  }

  public void setInvested(boolean invested) {
    isInvested = invested;
  }

  public boolean isOnTrack() {
    return onTrack;
  }

  /**
   * Set the value of investmentAccountUID
   * @param newVar the new value of investmentAccountUID
   */
  public void setInvestmentAccountUID (UUID newVar) {
    investmentAccountUID = newVar;
  }

  /**
   * Get the value of investmentAccountUID
   * @return the value of investmentAccountUID
   */
  public UUID getInvestmentAccountUID () {
    return investmentAccountUID;
  }

  /**
   * Set the value of userUID
   * @param newVar the new value of userUID
   */
  public void setUserUID (UUID newVar) {
    userUID = newVar;
  }

  /**
   * Get the value of userUID
   * @return the value of userUID
   */
  public UUID getUserUID () {
    return userUID;
  }

  private void computeAmountPerInterval() {
    if(!isInvested) {
      var month = startDate.until(projectedEndDate).getMonths();
      this.amountPerInterval = this.savingInterval != IncrementFrequency.Monthly?
              (this.amount / (month * this.savingInterval.sections)) : (this.amount / month);
     }
  }

  public void updateAmountPerInterval() {
    if(!isInvested) {
      var month = LocalDate.now().until(projectedEndDate).getMonths();
      this.amountPerInterval = this.savingInterval != IncrementFrequency.Monthly ?
              (this.amount / (month * this.savingInterval.sections)) : (this.amount / month);
    }
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Goal goal = (Goal) o;
    return isInvested == goal.isInvested && Double.compare(goal.amountPerInterval, amountPerInterval) == 0 && onTrack == goal.onTrack && Objects.equals(uid, goal.uid) && Objects.equals(name, goal.name) && Objects.equals(description, goal.description) && Objects.equals(startDate, goal.startDate) && Objects.equals(projectedEndDate, goal.projectedEndDate) && savingInterval == goal.savingInterval && Objects.equals(investmentAccountUID, goal.investmentAccountUID) && Objects.equals(userUID, goal.userUID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uid, name, description, startDate, projectedEndDate, isInvested, amountPerInterval, savingInterval, onTrack, investmentAccountUID, userUID);
  }

  @Override
  public String toString() {
    return "Goal{" +
            "uid=" + uid +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", startDate=" + startDate +
            ", projectedEndDate=" + projectedEndDate +
            ", isInvested=" + isInvested +
            ", amountPerInterval=" + amountPerInterval +
            ", savingInterval=" + savingInterval +
            ", onTrack=" + onTrack +
            ", investmentAccountUID=" + investmentAccountUID +
            ", userUID=" + userUID +
            '}';
  }
}
