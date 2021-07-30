package SpyGlass.Services;

import SpyGlass.Models.InvestmentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
   */
  public void getInvestmentAccounts(UUID userUID)
  {
  }


  /**
   * @param        newInvestment
   * @param        userUID
   */
  public void addInvestment(InvestmentAccount newInvestment, UUID userUID)
  {
  }


  /**
   * @return       InvestmentAccount
   * @param        investmentUID
   */
  public InvestmentAccount getInvestment(UUID investmentUID)
  {
  }


}
