import SpyGlass.Exceptions.GoalExceptions.GoalAlreadyExistsException;
import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.UserExceptions.UserAlreadyExistsException;
import SpyGlass.Exceptions.UserExceptions.UserDoesNotExists;
import SpyGlass.Models.Goal;
import SpyGlass.Models.User;
import SpyGlass.Services.StorageService;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StorageTesting {

    StorageService storageService;

    @Test
    public void addUserTest() throws UserAlreadyExistsException, ExecutionException, InterruptedException {
        User user = new User();
        Boolean actual = storageService.addUser(user);
        assertThat(actual).isEqualTo(true);

    }

    @Test
    public void updateUserTest() throws UserDoesNotExists, ExecutionException, InterruptedException {
        User user = new User();
        System.out.println(user.getUid());
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
    public void updateGoalTest() throws ExecutionException, InterruptedException, GoalDoesNotExistException {
        Goal goal = new Goal();
        goal.setName("test");
        Boolean actual = storageService.updateGoal(goal.getUid(), goal);
        assertThat(actual).isEqualTo(true);

    }
}
