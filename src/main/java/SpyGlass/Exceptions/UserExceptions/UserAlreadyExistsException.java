package SpyGlass.Exceptions.UserExceptions;

import SpyGlass.Models.User;

import java.util.UUID;

/**
 * Class UserAlreadyExistsException
 */
public class UserAlreadyExistsException extends Exception{
  // Constructors
  //
  public UserAlreadyExistsException (String userUID) {
    super("User: " + userUID + " already Exists");
  };
  


}
