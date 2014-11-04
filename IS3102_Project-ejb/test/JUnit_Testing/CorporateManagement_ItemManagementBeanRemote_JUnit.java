package JUnit_Testing;

import CorporateManagement.ItemManagement.ItemManagementBeanRemote;
import EntityManager.BillOfMaterialEntity;
import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.ProductGroupLineItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.RetailProductEntity;
import EntityManager.Supplier_ItemEntity;
import HelperClasses.ReturnHelper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CorporateManagement_ItemManagementBeanRemote_JUnit {

    ItemManagementBeanRemote itemManagementBean = lookupItemManagementBeanRemote();

    public CorporateManagement_ItemManagementBeanRemote_JUnit() {
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
    public void test01GetItemBySKU() {
        System.out.println("testgetItemBySKU");
        String testdata_SKU = "F_TD_01";
        ItemEntity result = itemManagementBean.getItemBySKU(testdata_SKU);
        assertEquals(testdata_SKU, result.getSKU());
    }

    @Test
    public void test02CheckSKUExists() {
        System.out.println("testCheckSKUExists");
        String testdata_SKU = "F_TD_01";
        Boolean result = itemManagementBean.checkSKUExists(testdata_SKU);
        assertTrue(result);
    }

    @Test
    public void test03AddRawMaterial() {
        System.out.println("testAddRawMaterial");
        String testdata_SKU = "RM2";
        String testdata_name = "Metal bars";
        String testdata_category = "";
        String testdata_description = "Hard metal bars.";
        Integer testdata_length = 30;
        Integer testdata_width = 10;
        Integer testdata_height = 10;
        Boolean result = itemManagementBean.addRawMaterial(testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_length, testdata_width, testdata_height);
        assertTrue(result);
    }

    @Test
    public void test04EditRawMaterial() {
        System.out.println("testEditRawMaterial");
        String testdata_id = "66";
        String testdata_SKU = "RM2";
        String testdata_name = "Metal pole";
        String testdata_category = "";
        String testdata_description = "Hard metal pole.";
        Boolean result = itemManagementBean.editRawMaterial(testdata_id, testdata_SKU, testdata_name, testdata_category, testdata_description);
        assertFalse(result);
    }

    @Test
    public void test05RemoveRawMaterial() {
        System.out.println("testRemoveRawMaterial");
        String testdata_SKU = "RM10";
        Boolean result = itemManagementBean.removeRawMaterial(testdata_SKU);
        assertFalse(result);
    }

    @Test
    public void test06ViewRawMaterial() {
        System.out.println("testViewRawMaterial");
        String testdata_SKU = "RM1";
        RawMaterialEntity result = itemManagementBean.viewRawMaterial(testdata_SKU);
        assertEquals(result.getSKU(), testdata_SKU);
    }

    @Test
    public void test07ListAllRawMaterials() {
        System.out.println("testListAllRawMaterials");
        List result = itemManagementBean.listAllRawMaterials();
        assertTrue(!result.isEmpty());
    }

    @Test
    public void test08AddFurniture() {
        System.out.println("testAddFurniture");
        String testdata_SKU = "F5";
        String testdata_name = "Metal Table";
        String testdata_category = "Tables";
        String testdata_description = "Hard metal table.";
        String testdata_imageURL = "";
        Integer testdata_length = 30;
        Integer testdata_width = 20;
        Integer testdata_height = 10;
        Boolean result = itemManagementBean.addFurniture(testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_imageURL, testdata_length, testdata_width, testdata_height);
        assertTrue(result);
    }

    @Test
    public void test09EditFurniture() {
        System.out.println("testEditFurniture");
        String testdata_id = "300";
        String testdata_SKU = "F5";
        String testdata_name = "Wooden Table";
        String testdata_category = "Tables";
        String testdata_description = "Hard wooden table.";
        String testdata_imageURL = "";
        Boolean result = itemManagementBean.editFurniture(testdata_id, testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_imageURL);
        assertFalse(result);
    }

    @Test
    public void test10RemoveFurniture() {
        System.out.println("testRemoveFurniture");
        String testdata_SKU = "F10";
        Boolean result = itemManagementBean.removeFurniture(testdata_SKU);
        assertFalse(result);
    }

    @Test
    public void test11ViewFurniture() {
        System.out.println("testViewFurniture");
        String testdata_SKU = "F_TD_01";
        FurnitureEntity result = itemManagementBean.viewFurniture(testdata_SKU);
        assertEquals(result.getSKU(), "F_TD_01");
    }

    @Test
    public void test12ListAllFurniture() {
        System.out.println("testListAllFurniture");
        List result = itemManagementBean.listAllFurniture();
        assertTrue(!result.isEmpty());
    }

    @Test
    public void test13AddRetailProduct() {
        System.out.println("testAddRetailProduct");
        String testdata_SKU = "RT1";
        String testdata_name = "Famous Amus";
        String testdata_category = "Dried food";
        String testdata_description = "Very famous cookie.";
        String testdata_imageURL = "";
        Integer testdata_length = 5;
        Integer testdata_width = 5;
        Integer testdata_height = 5;
        Boolean result = itemManagementBean.addRetailProduct(testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_imageURL, testdata_length, testdata_width, testdata_height);
        assertTrue(result);
    }

    @Test
    public void test14EditRetailProduct() {
        System.out.println("testEditRetailProduct");
        String testdata_id = "333";
        String testdata_SKU = "RT1";
        String testdata_name = "Infamous Amus";
        String testdata_category = "Dried food";
        String testdata_description = "Very infamous cookie.";
        String testdata_imageURL = "";
        Boolean result = itemManagementBean.editRetailProduct(testdata_id, testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_imageURL);
        assertFalse(result);
    }
    @Test
    public void test15ViewRetailProduct() {
        System.out.println("testViewRetailProduct");
        String testdata_SKU = "RT1";
        RetailProductEntity result = itemManagementBean.viewRetailProduct(testdata_SKU);
        assertEquals(result.getSKU(), "RT1");
    }
    @Test
    public void test16RemoveRetailProduct() {
        System.out.println("testRemoveRetailProduct");
        String testdata_SKU = "RT5";
        Boolean result = itemManagementBean.removeRetailProduct(testdata_SKU);
        assertFalse(result);
    }

    @Test
    public void test17ListAllRetailProduct() {
        System.out.println("testListAllRetailProduct");
        List result = itemManagementBean.listAllRetailProduct();
        assertTrue(!result.isEmpty());
    }

    @Test
    public void test18CreateBOM() {
        System.out.println("testCreateBOM");
        String testdata_name = "BOM for Metal Table";
        String testdata_description = "This is the BOM for metal table.";
        // to test...................
        Boolean result = itemManagementBean.createBOM(testdata_name, testdata_description, 10);
        assertTrue(result);
    }

    @Test
    public void test19EditBOM() {
        System.out.println("testEditBOM");
        Long testdata_id = 12L;
        String testdata_name = "BOM for Round Table";
        String testdata_description = "This is the BOM for round table.";
        Boolean result = itemManagementBean.editBOM(testdata_id, testdata_name, testdata_description);
        assertFalse(result);
    }

    @Test
    public void test20DeleteBOM() {
        System.out.println("testDeleteBOM");
        Long testdata_id = 222L;
        Boolean result = itemManagementBean.deleteBOM(testdata_id);
        assertNotNull(result);
    }

    @Test
    public void test21ViewSingleBOM() {
        System.out.println("testViewSingleBOM");
        Long testdata_BOMId = 2L;
        BillOfMaterialEntity result = itemManagementBean.viewSingleBOM(testdata_BOMId);
        assertNull(result);
    }

    @Test
    public void test22ListAllBOM() {
        System.out.println("testListAllBOM");
        List result = itemManagementBean.listAllBOM();
        assertTrue(!result.isEmpty());
    }

    @Test
    public void test23AddLineItemToBOM() {
        System.out.println("testAddLineItemToBOM");
        String testdata_SKU = "F_TD_01";
        Integer testdata_qty = 2;
        Long testdata_BOMId = 1L;
        Boolean result = itemManagementBean.addLineItemToBOM(testdata_SKU, testdata_qty, testdata_BOMId);
        assertFalse(result);
    }

    @Test
    public void test24DeleteLineItemFromBOM() {
        System.out.println("testDeleteLineItemFromBOM");
        Long testdata_lineItemId = 1L;
        Long testdata_BOMId = 1L;
        Boolean result = itemManagementBean.deleteLineItemFromBOM(testdata_lineItemId, testdata_BOMId);
        assertFalse(result);
    }

    @Test
    public void test25LinkBOMAndFurniture() {
        System.out.println("testLinkBOMandFurniture");
        Long testdata_BOMId = 1L;
        Long testdata_FurnitureId = 1L;
        Boolean result = itemManagementBean.deleteLineItemFromBOM(testdata_BOMId, testdata_FurnitureId);
        assertNotNull(!result);
    }

    @Test
    public void test26ListAllFurnitureWithoutBOM() {
        System.out.println("testListAllFurnitureWithoutBOM");
        List result = itemManagementBean.listAllFurnitureWithoutBOM();
        assertTrue(!result.isEmpty());
    }

    @Test
    public void test27CreateProductGroup() {
        System.out.println("testCreateProductGroup");
        String testdata_name = "F001";
        Integer testdata_workhours = 100;
        Integer testdata_lotSize = 100;
        ProductGroupEntity result = itemManagementBean.createProductGroup(testdata_name, testdata_workhours, testdata_lotSize);
        assertNull(result);
    }

    @Test
    public void test28EditProductGroup() {
        System.out.println("testEditProductGroup");
        Long testdata_productGroupId = 1L;
        String testdata_name = "F001";
        Integer testdata_workhours = 100;
        Integer testdata_lotSize = 100;
        Boolean result = itemManagementBean.editProductGroup(testdata_productGroupId, testdata_name, testdata_workhours, testdata_lotSize);
        assertFalse(result);
    }

    @Test
    public void test29GetProductGroup() {
        System.out.println("testGetProductGroup");
        Long testdata_id = 1L;
        ProductGroupEntity result = itemManagementBean.getProductGroup(testdata_id);
        assertNull(result);
    }

    @Test
    public void test30GetAllProductGroup() {
        System.out.println("testGetAllProductGroup");
        List result = itemManagementBean.getAllProductGroup();
        assertFalse(result.isEmpty());
    }

    @Test
    public void test31CreateProductGroupLineItem() {
        System.out.println("testCreateProductGroupLineItem");
        String testdata_SKU = "F_TD_01";
        Double testdata_percent = 0.1;
        ProductGroupLineItemEntity result = itemManagementBean.createProductGroupLineItem(testdata_SKU, testdata_percent);
        assertNotNull(result);
    }

    @Test
    public void test32EditProductGroupLineItem() {
        System.out.println("testEditProductGroupLineItem");
        Long testdata_productGroupId = 1L;
        Long testdata_productGroupLineItemId = 1L;
        String testdata_SKU = "F_TD_01";
        Double testdata_percent = 0.1;
        Boolean result = itemManagementBean.editProductGroupLineItem(testdata_productGroupId, testdata_productGroupLineItemId, testdata_SKU, testdata_percent);
        assertFalse(result);
    }

    @Test
    public void test33AddLineItemToProductGroup() {
        System.out.println("testAddLineItemToProductGroup");
        Long testdata_productGroupId = 1L;
        Long testdata_lineItemId = 1L;
        Boolean result = itemManagementBean.addLineItemToProductGroup(testdata_productGroupId, testdata_lineItemId);
        assertFalse(result);
    }

    @Test
    public void test34RemoveLineItemFromProductGroup() {
        System.out.println("testRemoveLineItemToProductGroup");
        Long testdata_productGroupId = 1L;
        Long testdata_lineItemId = 1L;
        Boolean result = itemManagementBean.removeLineItemFromProductGroup(testdata_productGroupId, testdata_lineItemId);
        assertFalse(result);

    }

    @Test
    public void test35RemoveProductGroup() {
        System.out.println("testRemoveProductGroup");
        Long testdata_productGroupId = 1L;
        Boolean result = itemManagementBean.removeProductGroup(testdata_productGroupId);
        assertFalse(result);
    }

    @Test
    public void test36CheckIfSKUIsFurniture() {
        System.out.println("testCheckIfSKUIsFurniture");
        String testdata_SKU = "F_TD_01";
        Boolean result = itemManagementBean.checkIfSKUIsFurniture(testdata_SKU);
        assertTrue(result);
    }


    @Test
    public void test37GetCountryItemPricing() {
        System.out.println("testGetCountryItemPricing");
        Long testdata_countryItemId = 1L;
        Item_CountryEntity result = itemManagementBean.getCountryItemPricing(testdata_countryItemId);
        assertNull(result);
    }

    @Test
    public void test38ListAllCountryItemPricing() {
        System.out.println("testGetCountryItemPricing");
        List result = itemManagementBean.listAllCountryItemPricing();
        assertNotNull(result.isEmpty());
    }

    @Test
    public void test39ListAllCountry() {
        System.out.println("testListAllCountry");
        List result = itemManagementBean.listAllCountry();
        assertNotNull(result.isEmpty());
    }

    @Test
    public void test40ListAllItemsSKU() {
        System.out.println("testListAllItemsSKU");
        List result = itemManagementBean.listAllItemsSKU();
        assertNotNull(result.isEmpty());
    }

    @Test
    public void test41ListAllItemsSKUForSupplier() {
        System.out.println("testListAllItemsSKUorSupplier");
        List result = itemManagementBean.listAllItemsSKUForSupplier();
        assertNotNull(result);
    }

    @Test
    public void test42ListAllItemsOfCountry() {
        System.out.println("testListAllItemsOfCountry");
        Long testdata_countryId = 1L;
        List result = itemManagementBean.listAllItemsOfCountry(testdata_countryId);
        assertNotNull(result.isEmpty());
    }

    @Test
    public void test43GetItemPricing() {
        System.out.println("testGetItemPricing");
        Long testdata_itemId = 1L;
        String testdata_SKU = "F_TD_01";
        Item_CountryEntity result = itemManagementBean.getItemPricing(testdata_itemId, testdata_SKU);
        assertNull(result);
    }

    private ItemManagementBeanRemote lookupItemManagementBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ItemManagementBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/ItemManagementBean!CorporateManagement.ItemManagement.ItemManagementBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
