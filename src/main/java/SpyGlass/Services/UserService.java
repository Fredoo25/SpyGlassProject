package SpyGlass.Services;

import SpyGlass.Exceptions.UserExceptions.UserAlreadyExistsException;
import SpyGlass.Exceptions.UserExceptions.UserDoesNotExists;
import SpyGlass.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Class UserService
 */
@Service
public class UserService {

  //
  // Fields
  //

  private StorageService storageService;
  
  //
  // Constructors
  //
  @Autowired
  public UserService (StorageService storageService) {

    this.storageService = storageService;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of storageService
   * @param newVar the new value of storageService
   */
  public void setStorageService (StorageService newVar) {
    storageService = newVar;
  }

  /**
   * Get the value of storageService
   * @return the value of storageService
   */
  public StorageService getStorageService () {
    return storageService;
  }

  //
  // Other methods
  //

  /**
   * Function adds a new user to storageService
   * @param newUser User to be added.
   * @return Boolean True if the user was successfully added to the database.
   * @throws UserAlreadyExistsException Thrown when attempting to add a user which already exists.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   */
  public boolean addUser(User newUser) throws UserAlreadyExistsException, ExecutionException, InterruptedException {
    storageService.addUser(newUser);
    return true;
  }


  /**
   *
   * @param updatedUser updates user in storageService
   * @return Boolean true when the user is updated, exceptions are thrown otherwise
   * @throws UserDoesNotExists Thrown when attempting to update a user which does not exist on the database.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   */
  public Boolean updateUser(User updatedUser) throws UserDoesNotExists, ExecutionException, InterruptedException {
    storageService.updateUser(updatedUser);
    return true;
  }


  /**
   *
   * @param userUID
   * @return Boolean true if user is deleted from storageService
   * @throws UserDoesNotExists Thrown when attempting to delete a user which does not exist on the database.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   */
  public Boolean deleteUser(String userUID) throws UserDoesNotExists, ExecutionException, InterruptedException {
    storageService.deleteUser(userUID);
    return true;
  }


}
