package SpyGlass.Exceptions.GoalExceptions;

import java.util.UUID;

/**
 * Class GoalDoesNotExistException
 */
public class GoalDoesNotExistException  extends Exception{

  public GoalDoesNotExistException (String goalUID) {
    super("Goal: " + goalUID + " does not exist");
  };

}
