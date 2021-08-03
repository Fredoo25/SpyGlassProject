package SpyGlass.Controllers;

import SpyGlass.Exceptions.UserExceptions.UserAlreadyExistsException;
import SpyGlass.Exceptions.UserExceptions.UserDoesNotExists;
import SpyGlass.Models.User;
import SpyGlass.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

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
  //  POST request that adds new user via userService
  @PostMapping
  public Boolean newUser(@RequestBody User newUser) throws UserAlreadyExistsException, ExecutionException, InterruptedException {
    return userService.addUser(newUser);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        updatedUser
   */
  //  PUT request to update specified user's information via userService
  @PutMapping
  public Boolean updateUser(@RequestBody User updatedUser) throws UserDoesNotExists, ExecutionException, InterruptedException {
    return userService.updateUser(updatedUser);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        userUID
   */
  //  DELETE request to remove a specified user via userService
  @DeleteMapping("/{userUID}")
  public Boolean deleteUser(String userUID) throws UserDoesNotExists, ExecutionException, InterruptedException {
    return userService.deleteUser(userUID);
  }


}
