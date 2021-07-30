package SpyGlass.Services;

import SpyGlass.Models.Goal;
import SpyGlass.Models.InvestmentAccount;
import SpyGlass.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StorageService {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public StorageService () { };
  
  /**
   * @return       Boolean
   * @param        newUser
   */
  public Boolean addUser(User newUser)
  {
  }


  /**
   * @return       Boolean
   * @param        userToUpdate
   */
  public Boolean updateUser(User userToUpdate)
  {
  }


  /**
   * @return       Boolean
   * @param        userUID
   */
  public Boolean deleteUser(UUID userUID)
  {
  }


  /**
   * @return       Boolean
   * @param        goalUID
   */
  public Boolean deleteGoal(UUID goalUID)
  {
  }


  /**
   * @param        goalUID
   * @param        newGoal
   */
  public void updateGoal(UUID goalUID, Goal newGoal)
  {
  }


  /**
   * @return       Boolean
   * @param        userUID
   * @param        newGoal
   */
  public Boolean addNewGoal(UUID userUID, Goal newGoal)
  {
  }


  /**
   * @return       Goal
   * @param        goalUID
   */
  public Goal getGoal(UUID goalUID)
  {
  }


  /**
   * @return       List<Goal>
   * @param        userUID
   */
  public List<Goal> getGoals(UUID userUID)
  {
  }


  /**
   * @return       List<InvestmentAccount>
   * @param        userUID
   */
  public List<InvestmentAccount> getInvestmentAccounts(UUID userUID)
  {
  }


  /**
   * @return       Boolean
   * @param        userUID
   * @param        newInvestmentAccount
   */
  public Boolean addInvestmentAccount(UUID userUID, InvestmentAccount newInvestmentAccount)
  {
  }


  /**
   * @return       InvestmentAccount
   * @param        investmentUID
   */
  public InvestmentAccount getInvestment(UUID investmentUID)
  {
  }


}
