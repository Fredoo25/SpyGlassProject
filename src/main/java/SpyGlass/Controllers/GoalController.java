package SpyGlass.Controllers;

import SpyGlass.Exceptions.GoalExceptions.GoalAlreadyExistsException;
import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.GoalExceptions.NoGoalsFoundException;
import SpyGlass.Models.Goal;
import SpyGlass.Services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Class GoalController
 */
@RestController
@RequestMapping("/goals")
public class GoalController {

  //
  // Fields
  //

  private final GoalService goalService;
  
  //
  // Constructors
  //
  @Autowired
  public GoalController (GoalService goalService) {
    this.goalService = goalService;
  };

  //
  // Other methods
  //

  /**
   * @return       ResponseEntity<List<Goal>>
   * @param        userUID
   */
  //  GET request that retrieves according goals list to specified user
  @GetMapping("/{userUID}")
  public List<Goal> getGoals(@PathVariable String userUID) throws NoGoalsFoundException, ExecutionException, InterruptedException {
    return goalService.getGoals(userUID);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        userUID
   * @param        newGoal
   */
  //  Adds new goal to specified user's list of goals
  @PostMapping("/{userUID}")
  public Boolean addNewGoal(@PathVariable String userUID, @RequestBody Goal newGoal) throws GoalAlreadyExistsException, ExecutionException, InterruptedException {
    return goalService.addNewGoal(userUID, newGoal);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        goalUID
   * @param        newGoal
   */
  //  Retrieves specified goal and updates specified goal's information
@PutMapping("/{goalUID}")
  public Boolean updateGoal(@PathVariable String goalUID, @RequestBody Goal newGoal) throws GoalDoesNotExistException, ExecutionException, InterruptedException {
    return goalService.updateGoal(goalUID, newGoal);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        goalUID
   */
  //  Removes the specified goal from user's goal list
  @DeleteMapping("/{goalUID}")
  public Boolean deleteGoal(@PathVariable String goalUID) throws GoalDoesNotExistException, ExecutionException, InterruptedException {
    return goalService.deleteGoal(goalUID);
  }


}
