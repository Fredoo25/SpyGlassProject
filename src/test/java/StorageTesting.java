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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StorageTesting {

    StorageService storageService = new StorageService();
    final Goal testGoal = new Goal();
    final User testUser = new User();

    @Test
    synchronized public void doAllTests() {
        final User testUser = new User();

        try{
            addUserTest(testUser);
            updateUserTest(testUser);
            deleteUserTest(testUser);
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
    synchronized public void deleteUserTest(User testUser) throws ExecutionException, InterruptedException, UserAlreadyExistsException, UserDoesNotExists {
        storageService.deleteUser(testUser.getUid());
        try {
            storageService.getUser(testUser.getUid());
        }catch(UserDoesNotExists ex) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    public void addGoalTest() throws GoalAlreadyExistsException, ExecutionException, InterruptedException {
        testGoal.setUserUID(testUser.getUid());
        Boolean actual = storageService.addNewGoal(testGoal);
        assertThat(actual).isEqualTo(true);

    }

    @Test
    public void getGoalTest() throws GoalAlreadyExistsException, ExecutionException, InterruptedException, GoalDoesNotExistException {
        Goal Actual = storageService.getGoal(testGoal.getUid());
        assertThat(Actual).isEqualTo(testGoal);
    }

    @Test
    public void updateGoalTest() throws ExecutionException, InterruptedException, GoalDoesNotExistException, GoalAlreadyExistsException {
        testGoal.setName("test");
        Boolean actual = storageService.updateGoal(testGoal.getUid(), testGoal);
        assertThat(actual).isEqualTo(true);

    }

    @Test
    public void deleteGoalTest() throws ExecutionException, InterruptedException, GoalAlreadyExistsException, GoalDoesNotExistException {
        storageService.deleteGoal(testGoal.getUid());
        storageService.getGoal(testGoal.getUid());

    }

    @Test
    public void getGoalsTest() throws GoalAlreadyExistsException, ExecutionException, InterruptedException, NoGoalsFoundException {
        Goal goal = new Goal();
        Goal goal2 = new Goal();
        Goal goal3 = new Goal();
        goal.setUserUID(testUser.getUid());
        goal2.setUserUID(testUser.getUid());
        goal3.setUserUID(testUser.getUid());
        storageService.addNewGoal(goal);
        storageService.addNewGoal(goal2);
        storageService.addNewGoal(goal3);
        List<Goal> Actual = storageService.getGoals(testUser.getUid());
        List<Goal> Expected = new ArrayList<>();
        Expected.add(goal);
        Expected.add(goal2);
        Expected.add(goal3);


        assertThat(Actual).isEqualTo(Expected);
    }

    @Test
    public void addInvestmentTest() throws ExecutionException, InterruptedException, InvestmentAccountAlreadyExists {
        InvestmentAccount investmentAccount = new InvestmentAccount();
        investmentAccount.setUserUID(testUser.getUid());
        //InvestmentAccount investmentAccount = new InvestmentAccount(0.05, IncrementFrequency.Daily, 5.0, 100.0, );
        Boolean actual = storageService.addInvestmentAccount(investmentAccount);
        assertThat(actual).isEqualTo(true);

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
        InvestmentAccount investmentAccount = new InvestmentAccount();
        InvestmentAccount investmentAccount2 = new InvestmentAccount();
        InvestmentAccount investmentAccount3 = new InvestmentAccount();
        investmentAccount.setUserUID(testUser.getUid());
        investmentAccount2.setUserUID(testUser.getUid());
        investmentAccount3.setUserUID(testUser.getUid());
        storageService.addInvestmentAccount(investmentAccount);
        storageService.addInvestmentAccount(investmentAccount2);
        storageService.addInvestmentAccount(investmentAccount3);
        List<InvestmentAccount> Actual = storageService.getInvestmentAccounts(testUser.getUid());
        List<InvestmentAccount> Expected = new ArrayList<>();
        Expected.add(investmentAccount);
        Expected.add(investmentAccount2);
        Expected.add(investmentAccount3);


        assertThat(Actual).isEqualTo(Expected);
    }
}
