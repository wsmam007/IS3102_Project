package SCM.RetailProductsAndRawMaterialsPurchasing;

import EntityManager.LineItemEntity;
import EntityManager.PurchaseOrderEntity;
import EntityManager.SupplierEntity;
import EntityManager.WarehouseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class RetailProductsAndRawMaterialsPurchasingBean implements RetailProductsAndRawMaterialsPurchasingBeanLocal {
@PersistenceContext
private EntityManager em;

public RetailProductsAndRawMaterialsPurchasingBean() {
}
  
@Override
public PurchaseOrderEntity createPurchaseOrder(SupplierEntity supplier, WarehouseEntity receivedWarehouse, Date expectedReceivedDate) {     
     try {
         PurchaseOrderEntity purchaseOrder = new PurchaseOrderEntity(supplier, receivedWarehouse, expectedReceivedDate);
         em.persist(purchaseOrder);
         
         System.out.println("PurchaseOrder with id: "+ purchaseOrder.getId() + " is created successfully");
         return purchaseOrder;
     }
     catch(EntityExistsException ex){
         ex.printStackTrace();
         return null;
     }
}

  @Override
    public Boolean addLineItemToPurchaseOrder(Long id, LineItemEntity lineItem) {
        try{
            Query query = em.createQuery("select p from PurchaseOrderEntity p where p.id = ?1").setParameter(1, id);
            PurchaseOrderEntity purchaseOrder = (PurchaseOrderEntity) query.getSingleResult();
            purchaseOrder.getLineItems().add(lineItem);
            em.merge(purchaseOrder);
            em.flush();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public PurchaseOrderEntity getPurchaseOrderById(Long id) {
        try{
            return em.find(PurchaseOrderEntity.class, id);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }        
    }
    @Override
    public Boolean updatePurchaseOrder(Long id, String status) {
        try{
            PurchaseOrderEntity purchaseOrder = em.find(PurchaseOrderEntity.class, id);
            purchaseOrder.setStatus(status);     
            em.persist(purchaseOrder);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }                
    } 

    @Override
    public List<PurchaseOrderEntity> getPurchaseOrderListByStatus(String status) {
        try{
            Query q = em.createQuery("select p from PurchaseOrderEntity p where p.status = ?1").setParameter(1, status);
            return q.getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }        
    }
    
    @Override
    public List<PurchaseOrderEntity> getPurchaseOrderList() {
        try{
            Query q = em.createQuery("select p from PurchaseOrderEntity p");
            return q.getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }        
    }
    
    
}

         


