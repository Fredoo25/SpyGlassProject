package SpyGlass.Exceptions.GoalExceptions;

import java.util.UUID;

/**
 * Class GoalAlreadyExistsException
 */
public class GoalAlreadyExistsException extends Exception{


  public GoalAlreadyExistsException (String goalUID) {
    super("Goal: " + goalUID + " already Exists");
  };


}
