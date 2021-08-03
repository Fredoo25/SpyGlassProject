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
import org.junit.Rule;
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

    @Test
    public void addUserTest() throws UserAlreadyExistsException, ExecutionException, InterruptedException {
        User user = new User();
        Boolean actual = storageService.addUser(user);
        assertThat(actual).isEqualTo(true);

    }

    @Test
    public void updateUserTest() throws UserDoesNotExists, ExecutionException, InterruptedException, UserAlreadyExistsException {
        User user = new User();
        storageService.addUser(user);
        List<UUID> goals = new ArrayList<>();
        UUID id = new UUID(8, 88);
        goals.add(id);
        user.setGoals(goals);
        Boolean actual = storageService.updateUser(user);
        assertThat(actual).isEqualTo(true);

    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void deleteUserTest() throws ExecutionException, InterruptedException, UserAlreadyExistsException, UserDoesNotExists {
        exceptionRule.expect(UserDoesNotExists.class);
        User user = new User();
        storageService.addUser(user);
        storageService.deleteUser(user.getUid());
        storageService.getUser(user.getUid());

    }

    @Test
    public void addGoalTest() throws GoalAlreadyExistsException, ExecutionException, InterruptedException {
        Goal goal = new Goal();
        Boolean actual = storageService.addNewGoal(goal);
        assertThat(actual).isEqualTo(true);

    }

    @Test
    public void getGoalTest() throws GoalAlreadyExistsException, ExecutionException, InterruptedException, GoalDoesNotExistException {
        Goal goal = new Goal();
        storageService.addNewGoal(goal);
        Goal Actual = storageService.getGoal(goal.getUid());
        Goal Expected = goal;
        assertThat(Actual).isEqualTo(Expected);
    }

    @Test
    public void updateGoalTest() throws ExecutionException, InterruptedException, GoalDoesNotExistException, GoalAlreadyExistsException {
        Goal goal = new Goal();
        storageService.addNewGoal(goal);
        goal.setName("test");
        Boolean actual = storageService.updateGoal(goal.getUid(), goal);
        assertThat(actual).isEqualTo(true);

    }

    @Test
    public void deleteGoalTest() throws ExecutionException, InterruptedException, GoalAlreadyExistsException, GoalDoesNotExistException {
        exceptionRule.expect(GoalDoesNotExistException.class);
        Goal goal = new Goal();
        storageService.addNewGoal(goal);
        storageService.deleteGoal(goal.getUid());
        storageService.getGoal(goal.getUid());

    }

    @Test
    public void getGoalsTest() throws GoalAlreadyExistsException, ExecutionException, InterruptedException, NoGoalsFoundException {
        User user = new User();
        Goal goal = new Goal();
        Goal goal2 = new Goal();
        Goal goal3 = new Goal();
        storageService.addNewGoal(goal);
        storageService.addNewGoal(goal2);
        storageService.addNewGoal(goal3);
        List<Goal> Actual = storageService.getGoals(user.getUid());
        List<Goal> Expected = new ArrayList<>();
        Expected.add(goal);
        Expected.add(goal2);
        Expected.add(goal3);


        assertThat(Actual).isEqualTo(Expected);
    }

    @Test
    public void addInvestmentTest() throws ExecutionException, InterruptedException, InvestmentAccountAlreadyExists {
        InvestmentAccount investmentAccount = new InvestmentAccount();
        //InvestmentAccount investmentAccount = new InvestmentAccount(0.05, IncrementFrequency.Daily, 5.0, 100.0, );
        Boolean actual = storageService.addInvestmentAccount(investmentAccount);
        assertThat(actual).isEqualTo(true);

    }

    @Test
    public void getInvestmentTest() throws ExecutionException, InterruptedException, InvestmentAccountAlreadyExists, InvestmentAccountDoesNotExists {
        InvestmentAccount investmentAccount = new InvestmentAccount();
        storageService.addInvestmentAccount(investmentAccount);
        InvestmentAccount Actual = storageService.getInvestment(investmentAccount.getUid());
        InvestmentAccount Expected = investmentAccount;
        assertThat(Actual).isEqualTo(Expected);
    }

    @Test
    public void getInvestmentsTest() throws ExecutionException, InterruptedException, NoGoalsFoundException, InvestmentAccountAlreadyExists {
        User user = new User();
        InvestmentAccount investmentAccount = new InvestmentAccount();
        InvestmentAccount investmentAccount2 = new InvestmentAccount();
        InvestmentAccount investmentAccount3 = new InvestmentAccount();
        storageService.addInvestmentAccount(investmentAccount);
        storageService.addInvestmentAccount(investmentAccount2);
        storageService.addInvestmentAccount(investmentAccount3);
        List<InvestmentAccount> Actual = storageService.getInvestmentAccounts(user.getUid());
        List<InvestmentAccount> Expected = new ArrayList<>();
        Expected.add(investmentAccount);
        Expected.add(investmentAccount2);
        Expected.add(investmentAccount3);


        assertThat(Actual).isEqualTo(Expected);
    }
}
