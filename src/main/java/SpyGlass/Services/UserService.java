package SpyGlass.Services;

import SpyGlass.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
   * @return       Boolean
   * @param        newUser
   */
  public Boolean addUser(User newUser) {

    storage;
  }


  /**
   * @return       Boolean
   * @param        updatedUser
   */
  public Boolean updateUser(User updatedUser)
  {
    return null;
  }


  /**
   * @return       Boolean
   * @param        userUID
   */
  public Boolean deleteUser(UUID userUID)
  {
  }


}
