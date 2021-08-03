package SpyGlass.Services;

import SpyGlass.Exceptions.GoalExceptions.GoalAlreadyExistsException;
import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.GoalExceptions.NoGoalsFoundException;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountAlreadyExists;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountDoesNotExists;
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
import java.util.ArrayList;
import java.util.List;
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
     * Function attempts to remove a goal from the database.
     * @param goalUID: Goal UID to be removed from DB
     * @return Boolean True when the goal is removed from db, exceptions are thrown otherwise.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when the connection to database is interrupted.
     * @throws GoalDoesNotExistException Thrown when attempting to remove a goal which does not exist.
     */
    public Boolean deleteGoal(String goalUID) throws ExecutionException, InterruptedException, GoalDoesNotExistException {
        logger.info("Attempting to remove Goal: " + goalUID);
        //Get the goal document from the database.
        var goalDocument = db.collection(GOAL_COLLECTION).document(goalUID);
        //Check if the document exists in the database.
        if(goalDocument.get().get().exists()) {
            logger.info("Deleting Goal: " + goalUID);
            //If does delete the document from db
            goalDocument.delete().get();
            //Return true for success
            return true;
        } else {
            //Otherwise goal does not exists, throw exception
            throw new GoalDoesNotExistException(goalUID);
        }
    }

    /**
     *Function attempts to update a goal.
     * @param goalUID Goal UID to be updated.
     * @param updatedGoal Goal with the updated data.
     * @return Boolean true when operation is successful, otherwise exceptions are thrown.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when the connection to database is interrupted.
     * @throws GoalDoesNotExistException Thrown when attempting to update a goal which does not exist.
     */
    public Boolean updateGoal(String goalUID, Goal updatedGoal) throws ExecutionException, InterruptedException, GoalDoesNotExistException {
        logger.info("Attempting to update Goal: " + goalUID + " with " + updatedGoal);
        //Get the Goal document from the database.
        var goalDocument = db.collection(GOAL_COLLECTION).document(goalUID);
        //Check if the document exits.
        if(goalDocument.get().get().exists()) {
            logger.info("Updating goal " + goalUID);
            //If it exists, update the document with the updated goal.
            goalDocument.set(updatedGoal).get();
            //Return true for success.
            return true;
        } else {
            //Otherwise the document does not exists, throw exception.
            throw new GoalDoesNotExistException(goalUID);
        }
    }

    /**
     * Function attempts to add a new goal to the database.
     * @param newGoal: Goal to be added to database
     * @return Boolean tru if the operation was successful.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when the connection to database is interrupted.
     * @throws GoalAlreadyExistsException Thrown when attempting to add a goal which already exists.
     */
    public Boolean addNewGoal(Goal newGoal) throws ExecutionException, InterruptedException, GoalAlreadyExistsException {
        logger.info("Attempting to add new goal: " + newGoal.getUid());
        //Get the Goal document from database.
        var goalDocument = db.collection(GOAL_COLLECTION).document(newGoal.getUid());
        //Check if the goal already exists in the database
        if(goalDocument.get().get().exists()) {
            //If it does throw exception
            throw new GoalAlreadyExistsException(newGoal.getUid());
        } else {
            logger.info("Adding new Goal: " + newGoal.getUid());
            //Otherwise add the goal to the document.
            goalDocument.set(newGoal).get();
            //Return true for success.
            return true;
        }
    }

    /**
     * Function will attempt to get goal from the database using the goals uid.
     * @param goalUID: Goal UID to retrieve from database
     * @return Goal with provided UID, otherwise exceptions are thrown.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when the connection to database is interrupted.
     * @throws GoalDoesNotExistException Thrown when no goal is found in db with matching UID.
     */
    public Goal getGoal(String goalUID) throws ExecutionException, InterruptedException, GoalDoesNotExistException {
        logger.info("Attempting to get Goal: " + goalUID);
        //Get the document with a goal UID
        var goalDocument = db.collection(GOAL_COLLECTION).document(goalUID);
        //Check if the document exists in the database.
        if(goalDocument.get().get().exists()) {
            //If document exists, return the java object from it.
            return goalDocument.get().get().toObject(Goal.class);
        } else {
            //Thrown no goal with UID was found on the database.
            throw new GoalDoesNotExistException(goalUID);
        }
    }

    /**
     * Function attempts to get all the goals which belong to provided UID.
     * @param userUID: User to get the goals for.
     * @return List<Goal> when goals are found.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when the connection to database is interrupted.
     * @throws NoGoalsFoundException Thrown when no goals matching criteria are found.
     */
    public List<Goal> getGoals(String userUID) throws ExecutionException, InterruptedException, NoGoalsFoundException {
        logger.info("Obtaining goals for user: " + userUID);
        //Get the goal documents where the goal userUID matches the provided UID.
        var goals = db.collection(GOAL_COLLECTION).whereEqualTo("userUID", userUID).get().get();
        //Check if there are goals for the provided user.
        if(goals.isEmpty()) {
            //If the goals are empty, throw exception to let the user know they have no goals.
            throw new NoGoalsFoundException(userUID);
        } else {
            logger.info("Converting goals to java objects");
            //Convert the list of goals and return them .
            return goals.toObjects(Goal.class);
        }
    }

    /**
     * Function attempts to get get the list of investments for the provided users.
     * @param userUID User to get investment accounts for.
     * @return List of Investment Accounts found for the user.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when no goals matching criteria are found.
     */
    public List<InvestmentAccount> getInvestmentAccounts(String userUID) throws ExecutionException, InterruptedException {
        logger.info("Attempting to get locations for " + userUID);
        //Get the investment documents where the owner UID matches the passed UID.
        var investmentAccounts = db.collection(INVESTMENT_COLLECTION).whereEqualTo("userUID", userUID).get().get();
        // Check if there are no documents.
        if(investmentAccounts.isEmpty()) {
            //If there are no documents, return an empty list.
            return new ArrayList<>();
        } else {
            logger.info("Converting to Investment Objects");
            //Otherwise create Investment account objects from documents return the list.
            return investmentAccounts.toObjects(InvestmentAccount.class);
        }
    }


    /**
     * Function attempts to add new investment account to the database.
     * @param newInvestmentAccount: Investment Account to be added.
     * @return Boolean true on success, otherwise exceptions are thrown.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when no goals matching criteria are found.
     * @throws InvestmentAccountAlreadyExists Thrown when attempting to add an Investment Account which already exists.
     */
    public Boolean addInvestmentAccount(InvestmentAccount newInvestmentAccount) throws ExecutionException, InterruptedException, InvestmentAccountAlreadyExists {
        logger.info("Attempting to add new investmentAccount for user " + newInvestmentAccount.getUserUID());
        //Get document reference for the new Investment Account
        var investmentDocument = db.collection(INVESTMENT_COLLECTION).document(newInvestmentAccount.getUid());
        //Check if the document already exists.
        if(investmentDocument.get().get().exists()) {
            //If it does, the investment account already exists, throw exception.
            throw new InvestmentAccountAlreadyExists(newInvestmentAccount.getUid());
        } else {
            //Otherwise add the new account to the database.
            investmentDocument.set(newInvestmentAccount);
            //Return true for success.
            return true;
        }
    }

    /**
     * Function will attempt to get Investment Account matching the provided investment account uid.
     * @param investmentUID: Investment account uid to search for.
     * @return Investment Account if found, otherwise exceptions are thrown.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when no goals matching criteria are found.
     * @throws InvestmentAccountDoesNotExists Thrown when no Investment Account was found with the provided UID.
     */
    public InvestmentAccount getInvestment(String investmentUID) throws ExecutionException, InterruptedException, InvestmentAccountDoesNotExists {
        logger.info("Attempting to get Investment Account with UID: " + investmentUID);
        //Get the document reference from the database
        var investmentDocument = db.collection(INVESTMENT_COLLECTION).document(investmentUID);
        //Check if the document exists in the database
        if(investmentDocument.get().get().exists()) {
            logger.info("Obtained Investment Account UID: " + investmentUID);
            //Return the the java object version.
            return investmentDocument.get().get().toObject(InvestmentAccount.class);
        } else {
            //Otherwise throw exception that the investment account does not exist.
            throw new InvestmentAccountDoesNotExists(investmentUID);
        }
    }

    /**
     * Function to retrieve a user from the database.
     * @param userUID: User to be retrieved from database.
     * @return User object if successful.
     * @throws ExecutionException Thrown when the Connection to Database Fails.
     * @throws InterruptedException Thrown when no goals matching criteria are found.
     * @throws UserDoesNotExists Thrown when the user could not be located on the database
     */
    public User getUser(String userUID) throws ExecutionException, InterruptedException, UserDoesNotExists {
        logger.info("Getting user with UID: " + userUID);

        var userDocument = db.collection(USER_COLLECTION).document(userUID).get().get();
        if(userDocument.exists()) {
            return userDocument.toObject(User.class);
        } else throw new UserDoesNotExists(userUID);
    }


}
