package SpyGlass.Controllers;

import SpyGlass.Models.Goal;
import SpyGlass.Services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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
  public ResponseEntity<List<Goal>> getGoals(UUID userUID)
  {
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        userUID
   * @param        newGoal
   */
  public ResponseEntity<Boolean> addNewGoal(UUID userUID, Goal newGoal)
  {
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        goalUID
   * @param        newGoal
   */
  public ResponseEntity<Boolean> updateGoal(UUID goalUID, Goal newGoal)
  {
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        goalUID
   */
  public ResponseEntity<Boolean> deleteGoal(UUID goalUID)
  {
  }


}
