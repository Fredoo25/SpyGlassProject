package SpyGlass.Exceptions;

import SpyGlass.Exceptions.GoalExceptions.GoalAlreadyExistsException;
import SpyGlass.Exceptions.GoalExceptions.GoalDoesNotExistException;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountAlreadyExists;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountDoesNotExists;
import SpyGlass.Exceptions.UserExceptions.UserAlreadyExistsException;
import SpyGlass.Exceptions.UserExceptions.UserDoesNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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


}
