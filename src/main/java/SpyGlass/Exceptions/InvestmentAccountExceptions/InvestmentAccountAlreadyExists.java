package SpyGlass.Exceptions.InvestmentAccountExceptions;

import java.util.UUID;

/**
 * Class InvestmentAccountAlreadyExists
 */
public class InvestmentAccountAlreadyExists extends Exception{


  public InvestmentAccountAlreadyExists (String investmentUID) {
    super("Investment Account: " + investmentUID.toString() + " already exists");

  };


}
