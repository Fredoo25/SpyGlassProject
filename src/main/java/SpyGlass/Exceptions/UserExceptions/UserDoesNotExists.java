package SpyGlass.Exceptions.UserExceptions;

import java.util.UUID;

/**
 * Class UserDoesNotExists
 */
public class UserDoesNotExists extends Exception{

  //
  // Constructors
  //
  public UserDoesNotExists (String userUUID) {
    super("User " + userUUID + " does not exist" );

  };
  


}
