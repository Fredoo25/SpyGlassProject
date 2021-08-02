package SpyGlass.Services;

import SpyGlass.Exceptions.UserExceptions.UserAlreadyExistsException;
import SpyGlass.Exceptions.UserExceptions.UserDoesNotExists;
import SpyGlass.Models.Goal;
import SpyGlass.Models.InvestmentAccount;
import SpyGlass.Models.User;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class StorageService {

  private static final String PATH_TO_SERVICE_ACCOUNT_FILE = "src/main/resources/service-account.json";
  private static final String USER_COLLECTION = "Users";
  private static final String GOAL_COLLECTION = "Goals";
  private static final String INVESTMENT_COLLECTION = "Investments";

  private final Logger logger = LoggerFactory.getLogger(StorageService.class);
  private Firestore db = null;

  public StorageService () {
    try{
      //Load the service account file from filesystem.
      InputStream serviceAccount = new FileInputStream(PATH_TO_SERVICE_ACCOUNT_FILE);
      //Build credential object from the file.
      GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
      //Create a firebase object with the proper credentials.
      FirebaseOptions firebaseOptions = FirebaseOptions.builder().setCredentials(credentials).build();
      //Initialize the Firebase Connection with the passed options(credentials).
      FirebaseApp.initializeApp(firebaseOptions);
      //Get the firestore db from the Firestore Connection.
      this.db = FirestoreClient.getFirestore();
      logger.info("Storage Service initialized successfully!");
    } catch (FileNotFoundException e) {
        //If the service account file could not be loaded, print error message, exit application)
        logger.error("Service Account File could not be found, terminating application!!!");
        System.exit(1);
    } catch (IOException e) {
      //If the service account file could not be opened, print error message, exit application)
      logger.error("Error opening the the service account file, terminating application!!!");
        System.exit(1);
    }
  };

  /**
   * Function will add a new user to the persistent database.
   * @param newUser User to be added.
   * @return Boolean True if the user was successfully added to the database.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   * @throws UserAlreadyExistsException Thrown when attempting to add a user which already exists.
   */
  public Boolean addUser(User newUser) throws ExecutionException, InterruptedException, UserAlreadyExistsException {
    logger.info("Saving user into db");
    //Get document reference for the user's uid.
    var userDocument = db.collection(USER_COLLECTION).document(newUser.getUid());
    //Check if the document exists.
    if(userDocument.get().get().exists()) {
      //If it does, then the user already exists, throw exception
      throw new UserAlreadyExistsException(newUser.getUid());
    } else {
      //Otherwise add the new user.
      userDocument.set(newUser);
      //Return True for success.
      return true;
    }
  }


  /**
   * Function attempts to update an existing user in the Database.
   * @param userToUpdate: User to be updated, contains updated field.
   * @return Boolean True when the operation succeeds, exceptions are thrown otherwise.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   * @throws UserDoesNotExists Thrown when attempting to update a user which does not exist on the database.
   */
  public Boolean updateUser(User userToUpdate) throws ExecutionException, InterruptedException, UserDoesNotExists {
    logger.info("Updating User: " + userToUpdate.getUid());
    //Get document reference for passed user's UID
    var userDocument = db.collection(USER_COLLECTION).document(userToUpdate.getUid());
    //Get the document from DB and check if the document exists.
    if(!userDocument.get().get().exists()) {
      //if document does not exist, we are attempting to update a user which does not exist, throw exception.
      logger.info("User " + userToUpdate.getUid() + " does not exist");
      throw new UserDoesNotExists(userToUpdate.getUid());
    } else {
      //If document exists, then update the document with the new user data.
      userDocument.set(userToUpdate);
      //Return true for success.
      return true;
    }
  }


  /**
   * Function attempts to remove a  user from the database.
   * @param userUID: UID of user to be removed.
   * @return Boolean True when operation is successful, exceptions are thrown otherwise.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when the connection to database is interrupted.
   * @throws UserDoesNotExists Thrown when attempting to delete a user which does not exist on the database.
   */
  public Boolean deleteUser(String userUID) throws ExecutionException, InterruptedException, UserDoesNotExists {
    logger.info("Deleting User: " + userUID);
    //Get reference to user document using their UID
    var userDocument = db.collection(USER_COLLECTION).document(userUID);
    //Get the object from the database and check if the document exists.
    if(!userDocument.get().get().exists()) {
      //If the document does not exists, then we are attempting to remove a user which does not exist, throw exception.
      logger.info("User " + userUID + " does not exist");
      throw new UserDoesNotExists(userUID);
    } else {
      //Otherwise, user does exist, issue delete command to remove the user document.
      userDocument.delete().get();
      //Return true for success.
      return true;
    }
  }


  /**
   * @return       Boolean
   * @param        goalUID
   */
  public Boolean deleteGoal(UUID goalUID)
  {
  }


  /**
   * @param        goalUID
   * @param        newGoal
   */
  public void updateGoal(UUID goalUID, Goal newGoal)
  {
  }


  /**
   * @return       Boolean
   * @param        userUID
   * @param        newGoal
   */
  public Boolean addNewGoal(UUID userUID, Goal newGoal)
  {
  }


  /**
   * @return       Goal
   * @param        goalUID
   */
  public Goal getGoal(UUID goalUID)
  {
  }


  /**
   * @return       List<Goal>
   * @param        userUID
   */
  public List<Goal> getGoals(UUID userUID)
  {
  }


  /**
   * @return       List<InvestmentAccount>
   * @param        userUID
   */
  public List<InvestmentAccount> getInvestmentAccounts(UUID userUID)
  {
  }


  /**
   * @return       Boolean
   * @param        userUID
   * @param        newInvestmentAccount
   */
  public Boolean addInvestmentAccount(UUID userUID, InvestmentAccount newInvestmentAccount)
  {
  }


  /**
   * @return       InvestmentAccount
   * @param        investmentUID
   */
  public InvestmentAccount getInvestment(UUID investmentUID)
  {
  }


}
