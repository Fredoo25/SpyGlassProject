package SpyGlassTest;

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
import SpyGlass.Services.GoalService;
import SpyGlass.Services.InvestmentService;
import SpyGlass.Services.StorageService;
import SpyGlass.Services.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;


public class ServiceTesting {

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private GoalService goalService;

    @InjectMocks
    private InvestmentService investmentService;

    @Mock
    private StorageService storageService;

    private User mockUser;
    private Goal mockGoal;
    private InvestmentAccount mockInvestment;

    @BeforeEach
    void setup() throws UserAlreadyExistsException, ExecutionException, InterruptedException, GoalAlreadyExistsException, NoGoalsFoundException, GoalDoesNotExistException, InvestmentAccountAlreadyExists, InvestmentAccountDoesNotExists {
        MockitoAnnotations.initMocks(this);
        mockUser = new User();
        mockGoal = new Goal(UUID.randomUUID().toString(), 0.0, 0.0, "", "", "", (long) 398983, (long)9393990, true, 0.0, IncrementFrequency.Daily, true, UUID.randomUUID().toString(), mockUser.getUid());
        mockInvestment = new InvestmentAccount(0.0, IncrementFrequency.Daily, 0.0, 0.0, UUID.randomUUID().toString(), mockUser.getUid());


        Mockito.when(storageService.addUser(mockUser)).thenReturn(true);
        Mockito.when(storageService.addUser(null)).thenThrow(NullPointerException.class);

        List<Goal> ls = new ArrayList();
        ls.add(mockGoal);
        Mockito.when(storageService.addNewGoal(mockGoal)).thenReturn(true);
        Mockito.when(storageService.getGoals(mockUser.getUid())).thenReturn(ls);
        Mockito.when(storageService.updateGoal(mockGoal.getUid(), mockGoal)).thenReturn(true);
        Mockito.when(storageService.deleteGoal(mockGoal.getUid())).thenReturn(true);

        List<InvestmentAccount> investList = new ArrayList();
        investList.add(mockInvestment);
        Mockito.when(storageService.addInvestmentAccount(mockInvestment)).thenReturn(true);
        Mockito.when(storageService.getInvestment(mockInvestment.getUid())).thenReturn(mockInvestment);
        Mockito.when(storageService.getInvestmentAccounts(mockUser.getUid())).thenReturn(investList);

    }




    // User Service Testing

    @Test
    public void addNullUserTest() throws UserAlreadyExistsException, ExecutionException, InterruptedException {
        Assertions.assertThrows(NullPointerException.class, () -> {
            userService.addUser(null);
        });
    }

    @Test
    public void addUserTest() throws UserAlreadyExistsException, ExecutionException, InterruptedException {
        boolean actual = userService.addUser(mockUser);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    synchronized public void updateUserTest() throws UserDoesNotExists, ExecutionException, InterruptedException {
            List<String> goals = new ArrayList<>();
            goals.add(UUID.randomUUID().toString());
            mockUser.setGoals(goals);
            Boolean actual = userService.updateUser(mockUser);
            assertThat(actual).isEqualTo(true);
    }

    @Test
    synchronized public void deleteUserTest() throws UserDoesNotExists, ExecutionException, InterruptedException {
            Boolean actual = userService.deleteUser(mockUser.getUid());
            assertThat(actual).isEqualTo(true);
    }

    // Goal Service Testing

    @Test
    public void addNewGoalTest() throws GoalAlreadyExistsException, ExecutionException, InterruptedException {
            boolean actual = goalService.addNewGoal(mockUser.getUid(), mockGoal);
            assertThat(actual).isEqualTo(true);
    }

    @Test
    public void getGoalsTest() throws NoGoalsFoundException, ExecutionException, InterruptedException, GoalAlreadyExistsException {

        List<Goal> Actual = goalService.getGoals(mockUser.getUid());
        List<Goal> Expected = new ArrayList<>();
        Expected.add(mockGoal);

        assertThat(Actual).isEqualTo(Expected);
    }

    @Test
    public void updateGoalTest(){
        try {
            mockGoal.setName("test");
            Boolean actual = goalService.updateGoal(mockGoal.getUid(), mockGoal);
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void deleteGoalTest(){
        try {
            Boolean actual = goalService.deleteGoal(mockGoal.getUid());
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    // Investment Service Testing

    @Test
    public void addInvestmentTest(){
        try {
            boolean actual = investmentService.addInvestment(mockInvestment, mockUser.getUid());
            assertThat(actual).isEqualTo(true);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void getInvestmentTest(){
        try {
            InvestmentAccount Actual = investmentService.getInvestment(mockInvestment.getUid());
            assertThat(Actual).isEqualTo(mockInvestment);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    public void getInvestmentsTest(){
        try {
            List<InvestmentAccount> Actual = investmentService.getInvestmentAccounts(mockUser.getUid());
            List<InvestmentAccount> Expected = new ArrayList<>();
            Expected.add(mockInvestment);

            assertThat(Actual).isEqualTo(Expected);
        }catch(Exception ex) {
            Assertions.fail();
        }
    }

}
