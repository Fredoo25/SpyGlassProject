package SpyGlassTest;

import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.UserExceptions.UserDoesNotExists;
import SpyGlass.Models.Goal;
import SpyGlass.Models.IncrementFrequency;
import SpyGlass.Models.InvestmentAccount;
import SpyGlass.Models.User;
import SpyGlass.Services.StorageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

public class StorageTesting {

    final static StorageService storageService = new StorageService();

    @Test
    synchronized public void doAllUserTests() {
        final User testUser = new User();

        try{
            addUserTest(testUser);
            getUserTest(testUser);
            updateUserTest(testUser);
            deleteUserTest(testUser);
            Assertions.assertTrue(true);
        }catch (Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    synchronized public void doAllGoalTests() {
        final User testUser2 = new User();

        final Goal testGoal = new Goal(UUID.randomUUID().toString(), 0.0, 0.0, "", "", "", (long) 398983, (long)9393990, true, 0.0, IncrementFrequency.Daily, true, UUID.randomUUID().toString(), testUser2.getUid());

        final Goal testGoal2 = new Goal(UUID.randomUUID().toString(), 0.0, 0.0, "", "", "", (long) 398983, (long)9393990, true, 0.0, IncrementFrequency.Daily, true, UUID.randomUUID().toString(), testUser2.getUid());

        try{
            addGoalTest(testGoal);
            getGoalTest(testGoal);
            updateGoalTest(testGoal);
            getGoalsTest(testUser2, testGoal, testGoal2);
            deleteGoalTest(testGoal);
            deleteGoalTest(testGoal2);
            Assertions.assertTrue(true);
        }catch (Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    synchronized public void doAllInvestmentTest(){
        final User testUser3 = new User();
        final InvestmentAccount testInvestment = new InvestmentAccount(0.0, IncrementFrequency.Daily, 0.0, 0.0, UUID.randomUUID().toString(), testUser3.getUid());
        final InvestmentAccount testInvestment2 = new InvestmentAccount(0.0, IncrementFrequency.Daily, 0.0, 0.0, UUID.randomUUID().toString(), testUser3.getUid());

        try{
            addInvestmentTest(testInvestment);
            getInvestmentTest(testInvestment);
            getInvestmentsTest(testUser3, testInvestment, testInvestment2);
            Assertions.assertTrue(true);
        }catch (Exception ex) {
            Assertions.fail();
        }
    }


    // User Testing

    synchronized public void addUserTest(User testUser) {
        try {
            Boolean actual = storageService.addUser(testUser);
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }

    }

    synchronized public void getUserTest(User testUser){
        try{
            User actual = storageService.getUser(testUser.getUid());
            assertThat(actual).isEqualTo(testUser);
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
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }

    }

    synchronized public void deleteUserTest(User testUser) throws ExecutionException, InterruptedException,UserDoesNotExists {
        storageService.deleteUser(testUser.getUid());
        try {
            storageService.getUser(testUser.getUid());
        }catch(UserDoesNotExists ex) {
            Assertions.assertTrue(true);
        }
    }


    // Goal Testing

    synchronized public void addGoalTest(Goal testGoal) {
        try {
            Boolean actual = storageService.addNewGoal(testGoal);
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    synchronized public void getGoalTest(Goal testGoal) {
        try {
            Goal Actual = storageService.getGoal(testGoal.getUid());
            assertThat(Actual).isEqualTo(testGoal);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    synchronized public void updateGoalTest(Goal testGoal) {
        try {
            testGoal.setName("test");
            Boolean actual = storageService.updateGoal(testGoal.getUid(), testGoal);
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    synchronized public void deleteGoalTest(Goal testGoal) throws ExecutionException, InterruptedException, GoalDoesNotExistException {
        storageService.deleteGoal(testGoal.getUid());
        try {
            storageService.getGoal(testGoal.getUid());
        }catch(GoalDoesNotExistException ex) {
            Assertions.assertTrue(true);
        }
    }

    synchronized public void getGoalsTest(User testUser, Goal testGoal, Goal testGoal2) {
        try {
            storageService.addNewGoal(testGoal2);

            List<Goal> Actual = storageService.getGoals(testUser.getUid());
            List<Goal> Expected = new ArrayList<>();
            Expected.add(testGoal);
            Expected.add(testGoal2);

            assertThat(Actual).isEqualTo(Expected);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }


    // Investment Testing

    synchronized public void addInvestmentTest(InvestmentAccount testInvestment) {
        try {
            Boolean actual = storageService.addInvestmentAccount(testInvestment);
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    synchronized public void getInvestmentTest(InvestmentAccount testInvestment) {
        try {
            InvestmentAccount Actual = storageService.getInvestment(testInvestment.getUid());
            assertThat(Actual).isEqualTo(testInvestment);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    synchronized public void getInvestmentsTest(User testUser, InvestmentAccount testInvestment, InvestmentAccount testInvestment2) {
        try {
            storageService.addInvestmentAccount(testInvestment2);

            List<InvestmentAccount> Actual = storageService.getInvestmentAccounts(testUser.getUid());
            List<InvestmentAccount> Expected = new ArrayList<>();
            Expected.add(testInvestment);
            Expected.add(testInvestment2);

            assertThat(Actual).isEqualTo(Expected);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }
}
