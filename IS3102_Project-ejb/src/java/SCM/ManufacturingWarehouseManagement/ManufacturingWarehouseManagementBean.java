package SCM.ManufacturingWarehouseManagement;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.StorageBinEntity;
import EntityManager.TransferOrderEntity;
import EntityManager.WarehouseEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ManufacturingWarehouseManagementBean implements ManufacturingWarehouseManagementBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    StorageBinEntity storageBin;
    WarehouseEntity warehouse;

    @Override
    public void createStorageBin(String type, Integer _length, Integer width, Integer height, Long warehouseId) {
        storageBin = new StorageBinEntity(type, _length, width, height);
        warehouse = em.find(WarehouseEntity.class, warehouseId);
        storageBin.setWarehouse(warehouse);
        em.persist(storageBin);
    }

    @Override
    public boolean updateStorageBin(StorageBinEntity storageBin) {
        if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {
            return false;
        }
        em.merge(storageBin);
        return true;
    }

    @Override
    public boolean deleteStorageBin(Long id) {
        if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {
            return false;
        }
        em.remove(storageBin);
        return true;
    }

    @Override
    public StorageBinEntity viewStorageBin(Long id) {
        if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {
            return null;
        }

        return em.getReference(StorageBinEntity.class, storageBin.getId());
    }

    @Override
    public List<StorageBinEntity> viewAllStorageBin() {
        Query q = em.createQuery("Select sb from StorageBinEntity sb");
        List<StorageBinEntity> storageBins = q.getResultList();
        return storageBins;
    }

    @Override
    public void createTransferOrder(List<LineItemEntity> lineItems, StorageBinEntity origin, StorageBinEntity target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateTransferOrder(TransferOrderEntity transferOrder) {
        if (em.getReference(TransferOrderEntity.class, transferOrder.getId()) == null) {
            return false;
        }
        em.merge(transferOrder);
        return true;
    }

    @Override
    public TransferOrderEntity viewTransferOrder(Long id) {
        if (em.getReference(TransferOrderEntity.class, id) == null) {
            return null;
        }
        return em.getReference(TransferOrderEntity.class, id);
    }

    @Override
    public boolean deleteTransferOrder(Long id) {
        if (em.getReference(TransferOrderEntity.class, id) == null) {
            return false;
        }
        em.remove(em.getReference(TransferOrderEntity.class, id));
        return true;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public LineItemEntity createLineItem(ItemEntity item, Integer quantity, String packType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
