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
  //  POST request that adds investment via investmentService
  @PostMapping("/{userUID}")
  public Boolean addInvestment(@PathVariable String userUID, @RequestBody InvestmentAccount newInvestment) throws InvestmentAccountAlreadyExists, ExecutionException, InterruptedException {
    return investmentService.addInvestment(newInvestment, userUID);
  }


  /**
   * @return       ResponseEntity<List<InvestmentAccounts>>
   * @param        userUID
   */
  //  GET request that retrieves all investments via investmentService
  @GetMapping("/{userUID}")
  public List<InvestmentAccount> getInvestments(@PathVariable String userUID) throws ExecutionException, InterruptedException {
    return investmentService.getInvestmentAccounts(userUID);
  }


  /**
   * @return       ResponseEntity<InvestmentAccount>
   * @param        investmentUID
   */
  //  GET request that retrieves single specified investment via investmentService
  @GetMapping("/{investmentUID}")
  public InvestmentAccount getInvestment(@PathVariable String investmentUID) throws InvestmentAccountDoesNotExists, ExecutionException, InterruptedException {
    return investmentService.getInvestment(investmentUID);
  }


}
