package SpyGlass.Exceptions.UserExceptions;

import java.util.UUID;

/**
 * Class UserDoesNotExists
 */
public class UserDoesNotExists extends Exception{

  //
  // Constructors
  //
  public UserDoesNotExists (UUID userUUID) {
    super("User " + userUUID.toString() + " does not exist" );

  };
  


}
