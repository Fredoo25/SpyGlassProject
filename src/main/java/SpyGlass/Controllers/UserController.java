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
  @PostMapping
  public ResponseEntity<Boolean> newUser(@RequestBody User newUser) throws UserAlreadyExistsException, ExecutionException, InterruptedException {
    return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.CREATED);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        updatedUser
   */
  @PutMapping
  public ResponseEntity<Boolean> updateUser(@RequestBody User updatedUser) throws UserDoesNotExists, ExecutionException, InterruptedException {
    return new ResponseEntity<>(userService.updateUser(updatedUser), HttpStatus.CREATED);
  }


  /**
   * @return       ResponseEntity<Boolean>
   * @param        userUID
   */
  @DeleteMapping("/{userUID}")
  public ResponseEntity<Boolean> deleteUser(String userUID) throws UserDoesNotExists, ExecutionException, InterruptedException {
    return new ResponseEntity<>(userService.deleteUser(userUID), HttpStatus.OK);
  }


}
