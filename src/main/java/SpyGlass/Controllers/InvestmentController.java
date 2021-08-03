package SpyGlass.Controllers;

import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountAlreadyExists;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountDoesNotExists;
import SpyGlass.Models.InvestmentAccount;
import SpyGlass.Services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Class InvestmentController
 */
@RestController
@RequestMapping("/investments")
public class InvestmentController {

  //
  // Fields
  //

  private final InvestmentService investmentService;
  
  //
  // Constructors
  //
  @Autowired
  public InvestmentController (InvestmentService investmentService) {
    this.investmentService = investmentService;
  };
  
  /**
   * @return       ResponseEntity<Boolean>
   * @param        userUID
   * @param        newInvestment
   */
  @PostMapping("/{userUID}")
  public ResponseEntity<Boolean> addInvestment(@PathVariable String userUID, @RequestBody InvestmentAccount newInvestment) throws InvestmentAccountAlreadyExists, ExecutionException, InterruptedException {
    return new ResponseEntity<>(investmentService.addInvestment(newInvestment, userUID), HttpStatus.CREATED);
  }


  /**
   * @return       ResponseEntity<List<InvestmentAccounts>>
   * @param        userUID
   */
  @GetMapping("/{userUID}")
  public ResponseEntity<ArrayList<Object>> getInvestments(@PathVariable String userUID) throws ExecutionException, InterruptedException {
    return new ResponseEntity<>(investmentService.getInvestmentAccounts(userUID), HttpStatus.OK);
  }


  /**
   * @return       ResponseEntity<InvestmentAccount>
   * @param        investmentUID
   */
  @GetMapping("/{investmentUID}")
  public ResponseEntity<String> getInvestment(@PathVariable String investmentUID) throws InvestmentAccountDoesNotExists, ExecutionException, InterruptedException {
    return new ResponseEntity<>(investmentService.getInvestment(investmentUID), HttpStatus.OK);
  }


}
