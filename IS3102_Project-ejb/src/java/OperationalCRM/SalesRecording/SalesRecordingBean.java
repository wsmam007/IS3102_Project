package OperationalCRM.SalesRecording;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import EntityManager.SalesRecordEntity;
import EntityManager.StaffEntity;
import EntityManager.StoreEntity;
import HelperClasses.ReturnHelper;
import InventoryManagement.StoreInventoryManagement.StoreInventoryManagementBeanLocal;
import OperationalCRM.LoyaltyAndRewards.LoyaltyAndRewardsBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SalesRecordingBean implements SalesRecordingBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    AccountManagementBeanLocal accountManagementBean;
    
    @EJB
    LoyaltyAndRewardsBeanLocal loyaltyAndRewardsBean;

    @EJB
    StoreInventoryManagementBeanLocal storeInventoryManagementBean;
    
    @Override
    public ReturnHelper createSalesRecord(String staffEmail, String staffPassword, Long storeID, String posName, List<LineItemEntity> itemsPurchased, Double amountDue, Double amountPaid, Double amountPaidUsingPoints, Integer loyaltyPointsDeducted, String memberEmail) {
        System.out.println("createSalesRecord() called;");
        ReturnHelper rh = new ReturnHelper(false,"System Error");
        StoreEntity storeEntity = null;
        MemberEntity memberEntity = null;
        String currency = "";
        //Retrieve member
        if (memberEmail != null && memberEmail.length() >= 0) {
            try {
                Query q = em.createQuery("SELECT t FROM MemberEntity t WHERE t.email=:email");
                q.setParameter("email", memberEmail);
                memberEntity = (MemberEntity) q.getSingleResult();
                //Update the member loyalty points
                try {
                    rh = loyaltyAndRewardsBean.updateMemberLoyaltyPointsAndTier(memberEmail, amountPaid, storeID);
                } catch (Exception ex) {
                    System.out.println("Error in updating loyalty points");
                    return new ReturnHelper(false, "System error in updating loyalty points, transaction has been cancelled. Please contact customer service.");
                }
            } catch (NoResultException ex) {
                System.out.println("createSalesRecord(): Member does not exist:");
                return new ReturnHelper(false, "Member details could not be retrieved based on the card provided. Contact customer service.");
            } catch (Exception ex) {
                System.out.println("createSalesRecord(): Failed to retrieve member based on email:");
                ex.printStackTrace();
                return new ReturnHelper(false, "System error in retriving membership information.");
            }
        }
        //Retrieve country for currency & exchange rate to be stored in sales record
        try {
            storeEntity = em.getReference(StoreEntity.class, storeID);
            currency = storeEntity.getCountry().getCurrency();
        } catch (Exception ex) {
            System.out.println("createSalesRecord(): Error in retriving country");
            return new ReturnHelper(false, "System error in retriving country information.");
        }
        //Actual creating of sales record
        try {
            StaffEntity staffEntity = accountManagementBean.getStaffByEmail(staffEmail);
            SalesRecordEntity salesRecordEntity = new SalesRecordEntity(memberEntity, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, currency, posName, staffEntity.getName(), itemsPurchased);
            em.persist(salesRecordEntity);
            storeEntity.getSalesRecords().add(salesRecordEntity);
            em.merge(storeEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ReturnHelper(false, "System error in creating sales record.");
        }
        //Update inventory amount
        try {
            //TODO remove from retail outlet or furniture marketplace
            //storeInventoryManagementBean.
            //if kitchen just no updating of amount
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO log that inventory cannot be updated, continue to let customer checkout
        }
        return rh;
    }
}
