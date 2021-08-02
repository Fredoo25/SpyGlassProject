package SpyGlass.Exceptions.GoalExceptions;

public class NoGoalsFoundException extends Exception{
    public NoGoalsFoundException(String uid) {
        super("No goals found for user " +  uid);
    }
}
