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
  @GetMapping("/{userUID}")
  public ResponseEntity<List<Goal>> getGoals(@PathVariable String userUID) throws NoGoalsFoundException, ExecutionException, InterruptedException {
    return new ResponseEntity<>(goalService.getGoals(userUID), HttpStatus.OK);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        userUID
   * @param        newGoal
   */
  @PostMapping("/{userUID}")
  public ResponseEntity<Boolean> addNewGoal(@PathVariable String userUID, @RequestBody Goal newGoal) throws GoalAlreadyExistsException, ExecutionException, InterruptedException {
    return new ResponseEntity<>(goalService.addNewGoal(userUID, newGoal), HttpStatus.CREATED);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        goalUID
   * @param        newGoal
   */
@PutMapping("/{goalUID}")
  public ResponseEntity<Boolean> updateGoal(@PathVariable String goalUID, @RequestBody Goal newGoal) throws GoalDoesNotExistException, ExecutionException, InterruptedException {
    return new ResponseEntity<>(goalService.updateGoal(goalUID, newGoal), HttpStatus.CREATED);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        goalUID
   */
  @DeleteMapping("/{goalUID}")
  public ResponseEntity<Boolean> deleteGoal(@PathVariable String goalUID) throws GoalDoesNotExistException, ExecutionException, InterruptedException {
    return new ResponseEntity<>( goalService.deleteGoal(goalUID), HttpStatus.OK);
  }


}
