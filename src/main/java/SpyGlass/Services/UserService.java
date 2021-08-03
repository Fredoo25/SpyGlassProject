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
   * @param        newUser
   * @return
   */
  public boolean addUser(User newUser) throws UserAlreadyExistsException, ExecutionException, InterruptedException {
    storageService.addUser(newUser);
    return true;
  }


  /**
   * @return       Boolean
   * @param        updatedUser
   */
  public Boolean updateUser(User updatedUser) throws UserDoesNotExists, ExecutionException, InterruptedException {
    storageService.updateUser(updatedUser);
    return true;
  }


  /**
   * @return       Boolean
   * @param        userUID
   */
  public Boolean deleteUser(String userUID) throws UserDoesNotExists, ExecutionException, InterruptedException {
    storageService.deleteUser(userUID);
    return true;
  }


}
