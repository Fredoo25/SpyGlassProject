package SpyGlass.Services;

import SpyGlass.Exceptions.GoalExceptions.GoalAlreadyExistsException;
import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.GoalExceptions.NoGoalsFoundException;
import SpyGlass.Models.Goal;
import SpyGlass.Models.InvestmentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
   * delete a goal
   * @param goalUID
   * @return Boolean true if goal was deleted
   * @throws GoalDoesNotExistException Thrown when attempting to remove a goal which does not exist.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   */
  public boolean deleteGoal(String goalUID) throws GoalDoesNotExistException, ExecutionException, InterruptedException {
    return storageService.deleteGoal(goalUID);

  }


  /**
   * Update a goal
   * @param goalUID
   * @param newGoal
   * @return Boolean true if goal was updated
   * @throws GoalDoesNotExistException Thrown when attempting to update a goal which does not exist.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   */
  public boolean updateGoal(String goalUID, Goal newGoal) throws GoalDoesNotExistException, ExecutionException, InterruptedException {
    return storageService.updateGoal(goalUID,newGoal);

  }


  /**
   * Add a new goal to userUID
   * @param userUID
   * @param newGoal
   * @return Boolean true if a new goal is added
   * @throws GoalAlreadyExistsException Thrown when attempting to add a goal which already exists.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   */
  public boolean addNewGoal(String userUID, Goal newGoal) throws GoalAlreadyExistsException, ExecutionException, InterruptedException {
    return storageService.addNewGoal(newGoal);
  }


  /**
   *
   * @param userUID
   * @throws NoGoalsFoundException Thrown when no goals matching criteria are found.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   */
  public List<Goal> getGoals(String userUID) throws NoGoalsFoundException, ExecutionException, InterruptedException {
    return storageService.getGoals(userUID);
  }


}
