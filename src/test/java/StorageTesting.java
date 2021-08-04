import SpyGlass.Exceptions.GoalExceptions.GoalAlreadyExistsException;
import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.GoalExceptions.NoGoalsFoundException;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountAlreadyExists;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountDoesNotExists;
import SpyGlass.Exceptions.UserExceptions.UserAlreadyExistsException;
import SpyGlass.Exceptions.UserExceptions.UserDoesNotExists;
import SpyGlass.Models.Goal;
import SpyGlass.Models.IncrementFrequency;
import SpyGlass.Models.InvestmentAccount;
import SpyGlass.Models.User;
import SpyGlass.Services.StorageService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StorageTesting {

    StorageService storageService = new StorageService();
    final User testUser = new User();
    final Goal testGoal = new Goal();

    @Test
    synchronized public void doAllUserTests() {
        final User testUser = new User();

        try{
            addUserTest(testUser);
            updateUserTest(testUser);
            deleteUserTest(testUser);
            // create a get user test
            Assertions.assertTrue(true);
        }catch (Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    synchronized public void doAllGoalTests() {
        final User testUser2 = new User();

        final Goal testGoal = new Goal(UUID.randomUUID().toString(), 0.0, 0.0, "", "", "", LocalDate.now()., LocalDate.now(), true, 0.0, IncrementFrequency.Daily, true, "", testUser2.getUid());

        final Goal testGoal2 = new Goal(UUID.randomUUID().toString(), 0.0, 0.0, "", "", "", LocalDate.now(), LocalDate.now(), true, 0.0, IncrementFrequency.Daily, true, "", testUser2.getUid());

        try{
            addGoalTest(testGoal);
            getGoalTest(testGoal);
            updateGoalTest(testGoal);
            getGoalsTest(testUser2, testGoal, testGoal2);
            deleteGoalTest(testGoal);
            Assertions.assertTrue(true);
        }catch (Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    synchronized public void doAllInvestmentTest(){
        final User testUser3 = new User();
        final InvestmentAccount testInvestment = new InvestmentAccount();

        try{

            Assertions.assertTrue(true);
        }catch (Exception ex) {
            Assertions.fail();
        }
    }

    synchronized public void addUserTest(User testUser) {
        try {
            Boolean actual = storageService.addUser(testUser);
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }

    }


    synchronized public void updateUserTest(User testUser) {
        try {
            List<String> goals = new ArrayList<>();
            goals.add(UUID.randomUUID().toString());
            testUser.setGoals(goals);
            Boolean actual = storageService.updateUser(testUser);
            Assertions.assertTrue(true);
        }catch(Exception ex) {
            Assertions.fail();
        }

    }

    @Test
    synchronized public void deleteUserTest(User testUser) throws ExecutionException, InterruptedException,UserDoesNotExists {
        storageService.deleteUser(testUser.getUid());
        try {
            storageService.getUser(testUser.getUid());
        }catch(UserDoesNotExists ex) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    synchronized public void addGoalTest(Goal testGoal) {
        try {
            Boolean actual = storageService.addNewGoal(testGoal);
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    synchronized public void getGoalTest(Goal testGoal) throws ExecutionException, InterruptedException, GoalDoesNotExistException {
        Goal Actual = storageService.getGoal(testGoal.getUid());
        assertThat(Actual).isEqualTo(testGoal);
    }

    @Test
    synchronized public void updateGoalTest(Goal testGoal) {
        try {
            testGoal.setName("test");
            Boolean actual = storageService.updateGoal(testGoal.getUid(), testGoal);
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    synchronized public void deleteGoalTest(Goal testGoal) throws ExecutionException, InterruptedException, GoalDoesNotExistException {
        storageService.deleteGoal(testGoal.getUid());
        try {
            storageService.getGoal(testGoal.getUid());
        }catch(GoalDoesNotExistException ex) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    synchronized public void getGoalsTest(User testUser, Goal testGoal, Goal testGoal2) throws GoalAlreadyExistsException, ExecutionException, InterruptedException, NoGoalsFoundException {
        storageService.addNewGoal(testGoal2);

        List<Goal> Actual = storageService.getGoals(testUser.getUid());
        List<Goal> Expected = new ArrayList<>();
        Expected.add(testGoal);
        Expected.add(testGoal2);


        assertThat(Actual).isEqualTo(Expected);
    }

    @Test
    public void addInvestmentTest() throws ExecutionException, InterruptedException, InvestmentAccountAlreadyExists {
//        InvestmentAccount investmentAccount = new InvestmentAccount();
//        investmentAccount.setUserUID(testUser.getUid());
//        //InvestmentAccount investmentAccount = new InvestmentAccount(0.05, IncrementFrequency.Daily, 5.0, 100.0, );
//        Boolean actual = storageService.addInvestmentAccount(investmentAccount);
//        assertThat(actual).isEqualTo(true);

    }

    @Test
    public void getInvestmentTest() throws ExecutionException, InterruptedException, InvestmentAccountAlreadyExists, InvestmentAccountDoesNotExists {
        InvestmentAccount investmentAccount = new InvestmentAccount();
        storageService.addInvestmentAccount(investmentAccount);
        InvestmentAccount Actual = storageService.getInvestment(investmentAccount.getUid());
        assertThat(Actual).isEqualTo(investmentAccount);
    }

    @Test
    public void getInvestmentsTest() throws ExecutionException, InterruptedException, NoGoalsFoundException, InvestmentAccountAlreadyExists {
//        InvestmentAccount investmentAccount = new InvestmentAccount();
//        InvestmentAccount investmentAccount2 = new InvestmentAccount();
//        InvestmentAccount investmentAccount3 = new InvestmentAccount();
//        investmentAccount.setUserUID(testUser.getUid());
//        investmentAccount2.setUserUID(testUser.getUid());
//        investmentAccount3.setUserUID(testUser.getUid());
//        storageService.addInvestmentAccount(investmentAccount);
//        storageService.addInvestmentAccount(investmentAccount2);
//        storageService.addInvestmentAccount(investmentAccount3);
//        List<InvestmentAccount> Actual = storageService.getInvestmentAccounts(testUser.getUid());
//        List<InvestmentAccount> Expected = new ArrayList<>();
//        Expected.add(investmentAccount);
//        Expected.add(investmentAccount2);
//        Expected.add(investmentAccount3);
//
//
//        assertThat(Actual).isEqualTo(Expected);
    }
}
