package SpyGlass.Controllers;

import SpyGlass.Models.User;
import SpyGlass.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Class UserController
 */
@RestController
@RequestMapping("/users")
public class UserController {

  //
  // Fields
  //

  private UserService userService;
  
  //
  // Constructors
  //
  @Autowired
  public UserController (UserService userService) {
    this.userService = userService;
  };

  //
  // Other methods
  //

  /**
   * @return       ResponseEntity<Boolean>
   * @param        newUser
   */
  public ResponseEntity<Boolean> newUser(User newUser)
  {
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        updatedUser
   */
  public ResponseEntity<Boolean> updateUser(User updatedUser)
  {
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        userToDelete
   */
  public ResponseEntity<Boolean> deleteUser(UUID userToDelete)
  {
  }


}
