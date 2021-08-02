package SpyGlass.Exceptions.InvestmentAccountExceptions;

import java.util.UUID;

/**
 * Class InvestmentAccountDoesNotExists
 */
public class InvestmentAccountDoesNotExists extends Exception {


  public InvestmentAccountDoesNotExists (String investmentUID) {
    super("Investment Account: " + investmentUID + " does not exits");
  };
  


}
