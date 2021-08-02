package SpyGlass.Services;

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

@Service
public class StorageService {

  private static final String PATH_TO_SERVICE_ACCOUNT_FILE = "src/main/resources/service-account.json";

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
   * @return       Boolean
   * @param        newUser
   */
  public Boolean addUser(User newUser)
  {
  }


  /**
   * @return       Boolean
   * @param        userToUpdate
   */
  public Boolean updateUser(User userToUpdate)
  {
  }


  /**
   * @return       Boolean
   * @param        userUID
   */
  public Boolean deleteUser(UUID userUID)
  {
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
