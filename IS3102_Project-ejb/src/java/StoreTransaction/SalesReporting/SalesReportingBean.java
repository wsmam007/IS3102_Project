package StoreTransaction.SalesReporting;

import StoreTransaction.RetailInventory.RetailInventoryBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

@Stateless
public class SalesReportingBean implements SalesReportingBeanLocal {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/SalesRecordingWebService/SalesRecordingWebService.wsdl")
    private StoreTransaction.SalesRecordingWebService_Service service;

    @EJB
    RetailInventoryBeanLocal inventoryBean;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Boolean submitSalesRecord(String email, String password, Long storeID, String posName, List<String> itemsPurchasedSKU, List<Integer> itemsPurchasedQty, Double amountDue, Double amountPaid, Double amountPaidUsingPoints,Integer loyaltyPointsDeducted, String memberEmail) {
        System.out.println("submitSalesRecord() called;");
        return createSalesRecord(memberEmail, password, storeID, posName, itemsPurchasedSKU, itemsPurchasedQty, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, memberEmail);
        //return true;
    }

    private Boolean createSalesRecord(java.lang.String staffEmail, java.lang.String password, java.lang.Long storeID, java.lang.String posName, java.util.List<java.lang.String> itemsPurchasedSKU, java.util.List<java.lang.Integer> itemsPurchasedQty, java.lang.Double amountDue, java.lang.Double amountPaid, java.lang.Double amountPaidUsingPoints, java.lang.Integer loyaltyPointsDeducted, java.lang.String memberEmail) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        StoreTransaction.SalesRecordingWebService port = service.getSalesRecordingWebServicePort();
        return port.createSalesRecord(staffEmail, password, storeID, posName, itemsPurchasedSKU, itemsPurchasedQty, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, memberEmail);
    }
    
    
}
