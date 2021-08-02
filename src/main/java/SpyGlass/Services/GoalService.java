package SpyGlass.Services;

import SpyGlass.Models.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Class GoalService
 */
@Service
public class GoalService {

  //
  // Fields
  //

  private StorageService storageService;
  
  //
  // Constructors
  //
  @Autowired
  public GoalService (StorageService storageService) {
    this.storageService = storageService;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of storageService
   * @param newVar the new value of storageService
   */
  public void setStorageService (StorageService newVar) {
    storageService = newVar;
  }

  /**
   * Get the value of storageService
   * @return the value of storageService
   */
  public StorageService getStorageService () {
    return storageService;
  }

  //
  // Other methods
  //

  /**
   * @return       boolean
   * @param        goalUID
   */
  public boolean deleteGoal(UUID goalUID)
  {
  }


  /**
   * @return       boolean
   * @param        goalUID
   * @param        newGoal
   */
  public boolean updateGoal(UUID goalUID, Goal newGoal)
  {
  }


  /**
   * @return       boolean
   * @param        userUID
   * @param        newGoal
   */
  public boolean addNewGoal(UUID userUID, Goal newGoal)
  {
  }


  /**
   * @param        userUID
   */
  public List<Goal> getGoals(UUID userUID)
  {
  }


}
