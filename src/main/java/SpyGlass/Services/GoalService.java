package SpyGlass.Services;

import SpyGlass.Exceptions.GoalExceptions.GoalAlreadyExistsException;
import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.GoalExceptions.NoGoalsFoundException;
import SpyGlass.Models.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
  public boolean deleteGoal(String goalUID) throws GoalDoesNotExistException, ExecutionException, InterruptedException {
    storageService.deleteGoal(goalUID);
    return true;
  }


  /**
   * @return       boolean
   * @param        goalUID
   * @param        newGoal
   */
  public boolean updateGoal(String goalUID, Goal newGoal) throws GoalDoesNotExistException, ExecutionException, InterruptedException {
    storageService.updateGoal(goalUID,newGoal);
    return true;
  }


  /**
   * @return       boolean
   * @param        userUID
   * @param        newGoal
   */
  public boolean addNewGoal(String userUID, Goal newGoal) throws GoalAlreadyExistsException, ExecutionException, InterruptedException {
    storageService.addNewGoal(newGoal);
    return true;
  }


  /**
   * @param        userUID
   */
  public void getGoals(String userUID) throws NoGoalsFoundException, ExecutionException, InterruptedException {
    storageService.getGoals(userUID);
  }


}
