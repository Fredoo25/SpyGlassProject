package SpyGlass.Controllers;

import SpyGlass.Models.InvestmentAccount;
import SpyGlass.Services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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
  public ResponseEntity<Boolean> addInvestment(UUID userUID, InvestmentAccount newInvestment)
  {
    return new ResponseEntity<>(investmentService.addInvestment(newInvestment, userUID), HttpStatus.CREATED);
  }


  /**
   * @return       ResponseEntity<List<InvestmentAccounts>>
   * @param        userUID
   */
  public ResponseEntity<List<InvestmentAccount>> getInvestments(UUID userUID)
  {
    return new ResponseEntity<>(investmentService.getInvestmentAccounts(userUID), HttpStatus.OK);
  }


  /**
   * @return       ResponseEntity<InvestmentAccount>
   * @param        investmentUID
   */
  public ResponseEntity<InvestmentAccount> getInvestment(UUID investmentUID)
  {
  }


}
