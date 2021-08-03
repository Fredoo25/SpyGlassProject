package SpyGlass.Exceptions;

import SpyGlass.Exceptions.GoalExceptions.GoalAlreadyExistsException;
import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.GoalExceptions.NoGoalsFoundException;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountAlreadyExists;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountDoesNotExists;
import SpyGlass.Exceptions.UserExceptions.UserAlreadyExistsException;
import SpyGlass.Exceptions.UserExceptions.UserDoesNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.ExecutionException;

@ControllerAdvice
public class ExceptionManager {


  /**
   * @return       ResponseEntity<Object>: Returns String containing error message, Status code NOT_FOUND
   * @param        exception: Exception to be caught by the exception handler.
   */
  @ExceptionHandler({
          GoalDoesNotExistException.class,
          InvestmentAccountDoesNotExists.class,
          UserDoesNotExists.class
  })
  public ResponseEntity<Object> manageDoesNotExistExceptions(Exception exception)
  {
    return new ResponseEntity<>(
            exception.getMessage(), HttpStatus.NOT_FOUND
          );
  }


  /**
   * @return       ResponseEntity<Object>: Returns String containing error message, Status Code CONFLICT
   * @param        exception: Exception to be caught by the exception handler.
   */
  @ExceptionHandler({
          GoalAlreadyExistsException.class,
          InvestmentAccountAlreadyExists.class,
          UserAlreadyExistsException.class
  })
  public ResponseEntity<Object> manageAlreadyExistExceptions(Exception exception)
  {
    return new ResponseEntity<>(
            exception.getMessage(), HttpStatus.CONFLICT
    );
  }

  /**
   * Manages exceptions when the user has no goals stored.
   * @param ex: Exception to be managed
   * @return ResponseEntity informing front end there are no goals for that user.
   */
  @ExceptionHandler({NoGoalsFoundException.class})
  public ResponseEntity<Object> manageNoGoalsForUser(Exception ex) {
    return new ResponseEntity<>(
            ex.getMessage(), HttpStatus.NO_CONTENT
    );
  }

  /**
   * Function manages internal exceptions, these are the ones thrown when there are issues connecting with DB.
   * @param ex Exception to be managed by method
   * @return Response Entity notifying front end what's going on. HTTPStatus Internal Server Error.
   */
  @ExceptionHandler({
          ExecutionException.class,
          InterruptedException.class
  })
  public ResponseEntity<Object> manageFirebaseExceptions(Exception ex) {
    return new ResponseEntity<>(
            "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR
    );
  }


}
