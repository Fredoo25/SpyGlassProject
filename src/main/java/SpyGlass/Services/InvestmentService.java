package SpyGlass.Services;

import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountAlreadyExists;
import SpyGlass.Exceptions.InvestmentAccountExceptions.InvestmentAccountDoesNotExists;
import SpyGlass.Models.InvestmentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
   * @param        userUID
   * @return
   */
  public ArrayList<Object> getInvestmentAccounts(String userUID) throws ExecutionException, InterruptedException {
    storageService.getInvestmentAccounts(userUID);
    return new ArrayList<>();
  }


  /**
   * @param        newInvestment
   * @param        userUID
   * @return
   */
  public boolean addInvestment(InvestmentAccount newInvestment, UUID userUID) throws InvestmentAccountAlreadyExists, ExecutionException, InterruptedException {
    storageService.addInvestmentAccount(newInvestment);
    return true;

  }


  /**
   * @return       InvestmentAccount
   * @param        investmentUID
   */
  public String getInvestment(String investmentUID) throws InvestmentAccountDoesNotExists, ExecutionException, InterruptedException {
    storageService.getInvestment(investmentUID);
    return investmentUID;
  }


}
