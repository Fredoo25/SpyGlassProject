package SpyGlass.Models;

import org.apache.tomcat.util.bcel.Const;

import java.rmi.server.UID;
import java.util.Objects;
import java.util.UUID;

/**
 * Class InvestmentAccount
 */
public class InvestmentAccount {

  //
  // Fields
  //
  private double percentageYield;
  private IncrementFrequency incrementInterval;
  private double currentTotal;
  private double projectedTotal;
  private UUID goalUID;
  private UUID userUID;
  private UUID uid;
  
  //
  // Constructors
  //
  public InvestmentAccount () {
    this.uid = UUID.randomUUID();
  };

  public InvestmentAccount(double percentageYield, IncrementFrequency incrementInterval, double currentTotal,
                           double projectedTotal,String goalUID, String userUID) {
    this();
    this.percentageYield = percentageYield;
    this.incrementInterval = incrementInterval;
    this.currentTotal = currentTotal;
    this.projectedTotal = projectedTotal;
    this.goalUID = UUID.fromString(goalUID);
    this.userUID = UUID.fromString(userUID);
  }

  public InvestmentAccount(double percentageYield, IncrementFrequency incrementInterval, double currentTotal,
                           double projectedTotal, String goalUID, String userUID,String uid) {
    this.percentageYield = percentageYield;
    this.incrementInterval = incrementInterval;
    this.currentTotal = currentTotal;
    this.projectedTotal = projectedTotal;
    this.goalUID = UUID.fromString(goalUID);
    this.userUID = UUID.fromString(userUID);
    this.uid = UUID.fromString(uid);
  }

  //
  // Methods
  //


  //
  // Accessor methods
  //


  public String getUid() {
    return uid.toString();
  }

  public void setUid(String uid) {
    this.uid = UUID.fromString(uid);
  }

  /**
   * Set the value of percentageYield
   * @param newVar the new value of percentageYield
   */
  public void setPercentageYield (double newVar) {
    percentageYield = newVar;
  }

  /**
   * Get the value of percentageYield
   * @return the value of percentageYield
   */
  public double getPercentageYield () {
    return percentageYield;
  }

  /**
   * Set the value of incrementInterval
   * @param newVar the new value of incrementInterval
   */
  public void setIncrementInterval(IncrementFrequency newVar) {
    incrementInterval = newVar;
  }

  /**
   * Get the value of incrementInterval
   * @return the value of incrementInterval
   */
  public IncrementFrequency getIncrementInterval() {
    return incrementInterval;
  }

  /**
   * Set the value of currentTotal
   * @param newVar the new value of currentTotal
   */
  public void setCurrentTotal (double newVar) {
    currentTotal = newVar;
  }

  /**
   * Get the value of currentTotal
   * @return the value of currentTotal
   */
  public double getCurrentTotal () {
    return currentTotal;
  }

  /**
   * Set the value of projectedTotal
   * @param newVar the new value of projectedTotal
   */
  public void setProjectedTotal (double newVar) {
    projectedTotal = newVar;
  }

  /**
   * Get the value of projectedTotal
   * @return the value of projectedTotal
   */
  public double getProjectedTotal () {
    return projectedTotal;
  }

  /**
   * Set the value of goalUID
   * @param newVar the new value of goalUID
   */
  public void setGoalUID (String newVar) {
    goalUID = UUID.fromString(newVar);
  }

  /**
   * Get the value of goalUID
   * @return the value of goalUID
   */
  public String getGoalUID () {
    return goalUID.toString();
  }

  /**
   * Set the value of userUID
   * @param newVar the new value of userUID
   */
  public void setUserUID (String newVar) {
    userUID = UUID.fromString(newVar);
  }

  /**
   * Get the value of userUID
   * @return the value of userUID
   */
  public String getUserUID () {
    return userUID.toString();
  }

  //
  // Other methods
  //

  /**
   * Adds the accrued interest amount to the account
   * Applies compounded Interest Formula:
   * C.I = Principal * ( 1 + rate) ^ time - Principal
   */
  public void applyPercentage()
  {
    double compounded = currentTotal * Math.pow((1 + percentageYield), this.incrementInterval.sections) - currentTotal;
    this.currentTotal += compounded;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InvestmentAccount that = (InvestmentAccount) o;
    return Double.compare(that.percentageYield, percentageYield) == 0 && Double.compare(that.currentTotal, currentTotal) == 0 && Double.compare(that.projectedTotal, projectedTotal) == 0 && incrementInterval == that.incrementInterval && Objects.equals(goalUID, that.goalUID) && Objects.equals(userUID, that.userUID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(percentageYield, incrementInterval, currentTotal, projectedTotal, goalUID, userUID);
  }

  @Override
  public String toString() {
    return "InvestmentAccount{" +
            "percentageYield=" + percentageYield +
            ", incrementInterval=" + incrementInterval +
            ", currentTotal=" + currentTotal +
            ", projectedTotal=" + projectedTotal +
            ", goalUID=" + getGoalUID() +
            ", userUID=" + getUserUID() +
            '}';
  }
}
