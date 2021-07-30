package SpyGlass.Exceptions.GoalExceptions;

import java.util.UUID;

/**
 * Class GoalAlreadyExistsException
 */
public class GoalAlreadyExistsException extends Exception{


  public GoalAlreadyExistsException (UUID goalUID) {
    super("Goal: " + goalUID.toString() + " already Exists");
  };


}
