package JUnit_Testing;

import EntityManager.StorageBinEntity;
import EntityManager.WarehouseEntity;
import SCM.ManufacturingWarehouseManagement.ManufacturingWarehouseManagementBeanRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class SCM_ManufacturingWarehouseManagementBeanRemote_JUnit {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    
    ManufacturingWarehouseManagementBeanRemote manufacturingWarehouseManagementBean = lookupManufacturingWarehouseManagementBeanRemote();

    Long warehouseID = 1L;//Need to change to the warehouse created after the facility management JUnit test
    
    // Automatically retrieved variable
    Long storageBinID = null;

    public SCM_ManufacturingWarehouseManagementBeanRemote_JUnit() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test01CreateStorageBin() {
        System.out.println("test01CreateStorageBin");
        WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
        StorageBinEntity expResult = new StorageBinEntity(warehouseEntity, "Inbound", 1200, 1200, 1200);
        Boolean result = manufacturingWarehouseManagementBean.createStorageBin(warehouseID, "Pallet", 200, 200, 200);
        assertTrue(result);
    }

    @Test
    public void test02ViewAllStorageBin() {
        System.out.println("test02ViewAllStorageBin");
        List<StorageBinEntity> result = manufacturingWarehouseManagementBean.viewAllStorageBin(warehouseID);
        storageBinID = result.get(result.size()-1).getId();
        System.out.println(storageBinID+"!!!!!!!!!!!");
        assertNotNull(result);
    }
    
    @Test
    public void test03ViewStorageBin() {
        System.out.println("test03ViewStorageBin");
        StorageBinEntity result = manufacturingWarehouseManagementBean.viewStorageBin(storageBinID);
        assertNotNull(result);
    }
    
    @Test
    public void test04UpdateStorageBin() {
        System.out.println("testUpdateStorageBin");
        Boolean result = manufacturingWarehouseManagementBean.updateStorageBin(storageBinID, 300, 300, 300);
        assertTrue(result);
    }

    @Test
    public void testDeleteStorageBin() {
    }

    @Test
    public void testGetInboundStorageBin() {
    }

    @Test
    public void testGetOutboundStorageBin() {
    } //look for the inbound storagebin

    @Test
    public void testCreateTransferOrder() {
    }

    @Test
    public void testAddLineItemToTransferOrder() {
    }

    @Test
    public void testRemoveLineItemFromTransferOrder() {
    }

    @Test
    public void testMarkTransferOrderAsCompleted() {
    }

    @Test
    public void testCancelTransferOrder() {
    }

    @Test
    public void testViewTransferOrder() {
    }

    @Test
    public void testViewAllTransferOrderByWarehouseId() {
    }

    @Test
    public void testDeleteTransferOrder() {
    }

    @Test
    public void testMarkTransferOrderAsUnfulfilled() {
    }

    @Test
    public void testSearchItemBySKU() {
    }

    private ManufacturingWarehouseManagementBeanRemote lookupManufacturingWarehouseManagementBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ManufacturingWarehouseManagementBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/ManufacturingWarehouseManagementBean!SCM.ManufacturingWarehouseManagement.ManufacturingWarehouseManagementBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
