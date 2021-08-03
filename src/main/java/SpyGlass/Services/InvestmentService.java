package SpyGlass.Services;

import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountAlreadyExists;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountDoesNotExists;
import SpyGlass.Models.InvestmentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Class InvestmentService
 */
@Service
public class InvestmentService {

  //
  // Fields
  //

  private StorageService storageService;

  //
  // Constructors
  //
  @Autowired
  public InvestmentService (StorageService storageService) {
    this.storageService = storageService;
  };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of storageService
   * @param newVar the new value of storageService
   */
  public void setStorageService (StorageService newVar) {
    storageService = newVar;
  }

  /**
   * Get the value of storageService
   * @return the value of storageService
   */
  public StorageService getStorageService () {
    return storageService;
  }

  //
  // Other methods
  //

  /**
   *
   * @param userUID
   * @return List of Investment Accounts found for the user.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when no goals matching criteria are found.
   */
  public List<InvestmentAccount> getInvestmentAccounts(String userUID) throws ExecutionException, InterruptedException {
    return storageService.getInvestmentAccounts(userUID);

  }


  /**
   *
   * @param newInvestment
   * @param userUID
   * @return Boolean true if investment was added
   * @throws InvestmentAccountAlreadyExists Thrown when attempting to add an Investment Account which already exists.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when no goals matching criteria are found.
   */
  public boolean addInvestment(InvestmentAccount newInvestment, String userUID) throws InvestmentAccountAlreadyExists, ExecutionException, InterruptedException {
    storageService.addInvestmentAccount(newInvestment);
    return true;

  }


  /**
   *
   * @param investmentUID
   * @return investmentUID
   * @throws InvestmentAccountDoesNotExists Thrown when Investment Account does not exists.
   * @throws ExecutionException Thrown when the Connection to Database Fails.
   * @throws InterruptedException Thrown when no goals are found.
   */
  public String getInvestment(String investmentUID) throws InvestmentAccountDoesNotExists, ExecutionException, InterruptedException {
    storageService.getInvestment(investmentUID);
    return investmentUID;
  }


}
