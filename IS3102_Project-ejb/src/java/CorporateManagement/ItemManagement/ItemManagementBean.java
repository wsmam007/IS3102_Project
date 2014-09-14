package CorporateManagement.ItemManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductionGroupEntity;
import EntityManager.RetailProductEntity;
import EntityManager.FurnitureEntity;
import EntityManager.BillOfMaterialEntity;
import javax.ejb.Remove;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ItemManagementBean implements ItemManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public boolean addRawMaterial(String SKU, String name, String category, String description, Integer _length, Integer width, Integer height) {
        System.out.println("addRawMaterial() called with SKU:" + SKU);

        Long id;
        try {
            ItemEntity item = new ItemEntity(SKU, _length, width, height);
            RawMaterialEntity rawMaterialEntity = new RawMaterialEntity(name, category, description, item);
            em.persist(rawMaterialEntity);
            System.out.println("Raw Material name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new raw material:\n" + ex);
            return false;
        }
    }

    public boolean editRawMaterial(String SKU, String name, String category, String description) {
        System.out.println("editRawMaterial() called with SKU:" + SKU);

        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");

            for (Object o : q.getResultList()) {
                RawMaterialEntity i = (RawMaterialEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    if (name != null) {
                        i.setName(name);
                    }
                    if (category != null) {
                        i.setCategory(category);
                    }
                    if (description != null) {
                        i.setDescription(description);
                    }
                    em.persist(i);
                    System.out.println("\nServer updated raw material:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the item
        } catch (Exception ex) {
            System.out.println("\nServer failed to update raw material:\n" + ex);
            return false;
        }
    }

    public boolean removeRawMaterial(String SKU) {
        System.out.println("removeRawMaterial() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");

            for (Object o : q.getResultList()) {
                RawMaterialEntity i = (RawMaterialEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed raw material:\n" + SKU);
                    return true;
                }
            }
            return false; //Could not find to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove raw material:\n" + ex);
            return false;
        }
    }

    public RawMaterialEntity viewRawMaterial(String SKU) {
        System.out.println("viewRawMaterial() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");

            for (Object o : q.getResultList()) {
                RawMaterialEntity i = (RawMaterialEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    System.out.println("\nServer returns raw material:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view raw material:\n" + ex);
            return null;
        }
    }

    public boolean addFurniture(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height) {
        System.out.println("addFurniture() called with SKU:" + SKU);

        Long id;
        try {
            ItemEntity item = new ItemEntity(SKU, _length, width, height);
            FurnitureEntity furnitureEntity = new FurnitureEntity(name, category, description, imageURL, item);
            em.persist(furnitureEntity);
            System.out.println("Furniture name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new furniture:\n" + ex);
            return false;
        }
    }

    public boolean editFurniture(String SKU, String name, String category, String description, String imageURL) {
        System.out.println("editFurniture() called with SKU:" + SKU);

        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");

            for (Object o : q.getResultList()) {
                FurnitureEntity i = (FurnitureEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    if (name != null) {
                        i.setName(name);
                    }
                    if (category != null) {
                        i.setCategory(category);
                    }
                    if (description != null) {
                        i.setDescription(description);
                    }
                    if (imageURL != null) {
                        i.setImageURL(imageURL);
                    }
                    em.persist(i);
                    System.out.println("\nServer updated furniture:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the item
        } catch (Exception ex) {
            System.out.println("\nServer failed to update furniture:\n" + ex);
            return false;
        }
    }

    public boolean removeFurniture(String SKU) {
        System.out.println("removeFurniture() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");

            for (Object o : q.getResultList()) {
                FurnitureEntity i = (FurnitureEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed furniture:\n" + SKU);
                    return true;
                }
            }
            return false; //Could not find to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove furniture:\n" + ex);
            return false;
        }
    }

    public FurnitureEntity viewFurniture(String SKU) {
        System.out.println("viewRawMaterial() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");

            for (Object o : q.getResultList()) {
                FurnitureEntity i = (FurnitureEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    System.out.println("\nServer returns furniture:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view furniture:\n" + ex);
            return null;
        }
    }

    public boolean addRetailProduct(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height) {
        System.out.println("addRetailProduct() called with SKU:" + SKU);

        Long id;
        try {
            ItemEntity item = new ItemEntity(SKU, _length, width, height);
            RetailProductEntity retailProductEntity = new RetailProductEntity(name, category, description, imageURL, item);
            em.persist(retailProductEntity);
            System.out.println("Retail product name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new furniture:\n" + ex);
            return false;
        }
    }

    public boolean editRetailProduct(String SKU, String name, String category, String description, String imageURL) {
        System.out.println("editRetailProduct() called with SKU:" + SKU);

        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");

            for (Object o : q.getResultList()) {
                RetailProductEntity i = (RetailProductEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    if (name != null) {
                        i.setName(name);
                    }
                    if (category != null) {
                        i.setCategory(category);
                    }
                    if (description != null) {
                        i.setDescription(description);
                    }
                    if (imageURL != null) {
                        i.setImageURL(imageURL);
                    }
                    em.persist(i);
                    System.out.println("\nServer updated retail product:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the item
        } catch (Exception ex) {
            System.out.println("\nServer failed to update retail product:\n" + ex);
            return false;
        }
    }

    public boolean removeRetailProduct(String SKU) {
        System.out.println("removeRetailProduct() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");

            for (Object o : q.getResultList()) {
                RetailProductEntity i = (RetailProductEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed retail product:\n" + SKU);
                    return true;
                }
            }
            return false; //Could not find to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove retail product:\n" + ex);
            return false;
        }
    }

    public RetailProductEntity viewRetailProduct(String SKU) {
        System.out.println("viewRetailProduct() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");

            for (Object o : q.getResultList()) {
                RetailProductEntity i = (RetailProductEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    System.out.println("\nServer returns retail product:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view retail product:\n" + ex);
            return null;
        }
    }

    public boolean createBillOfMaterial(String name) {
        System.out.println("createBillOfMaterial() called with name:" + name);

        Long id;
        try {
            BillOfMaterialEntity billOfMaterialEntity = new BillOfMaterialEntity();
            billOfMaterialEntity.create(name);
            em.persist(billOfMaterialEntity);
            id = billOfMaterialEntity.getId();
            System.out.println("Bill Of Material Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register bill of material:\n" + ex);
            return false;
        }
    }

    public boolean editBillOfMaterial(String name) {
        System.out.println("editBillOfMaterial() called with bill of material name:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM BillOfMaterialEntity t");

            for (Object o : q.getResultList()) {
                BillOfMaterialEntity i = (BillOfMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    i.setName(name);
                    em.flush();
                    System.out.println("\nServer updated bill of material:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update bill of material:\n" + ex);
            return false;
        }
    }

    public boolean deleteBillOfMaterial(String bomName) {
        System.out.println("deleteBillOfMaterial() called with bomName:" + bomName);
        try {
            Query q = em.createQuery("SELECT t FROM BillOfMaterialEntity t");

            for (Object o : q.getResultList()) {
                BillOfMaterialEntity i = (BillOfMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(bomName)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed bill of material:\n" + bomName);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove bill of material:\n" + ex);
            return false;
        }
    }

    public BillOfMaterialEntity viewBillOfMaterial(String name) {
        System.out.println("viewBillOfMaterial() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM BillOfMaterialEntity t");

            for (Object o : q.getResultList()) {
                BillOfMaterialEntity i = (BillOfMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("\nServer returns bill of material:\n" + name);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to view bill of material:\n" + ex);
            return null;
        }
    }

    public boolean createProductionGroup(String name) {
        System.out.println("createProductionGroup() called with name:" + name);

        Long id;
        try {
            ProductionGroupEntity productionGroupEntity = new ProductionGroupEntity();
            productionGroupEntity.create(name);
            em.persist(productionGroupEntity);
            id = productionGroupEntity.getId();
            System.out.println("Production Group Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register production group:\n" + ex);
            return false;
        }
    }

    public boolean editProductionGroup(String name) {
        System.out.println("editProductionGroup() called with production group name:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM ProductionGroupEntity t");

            for (Object o : q.getResultList()) {
                ProductionGroupEntity i = (ProductionGroupEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    i.setName(name);
                    em.flush();
                    System.out.println("\nServer updated production group:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update production group:\n" + ex);
            return false;
        }
    }

    public boolean deleteProductionGroup(String name) {
        System.out.println("deleteProductionGroup() called with bomName:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM ProductionGroupEntity t");

            for (Object o : q.getResultList()) {
                ProductionGroupEntity i = (ProductionGroupEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed production group:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove production group:\n" + ex);
            return false;
        }
    }

    public ProductionGroupEntity viewProductionGroup(String name) {
        System.out.println("viewProductionGroup() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM ProductionGroupEntity t");

            for (Object o : q.getResultList()) {
                ProductionGroupEntity i = (ProductionGroupEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("\nServer returns production group:\n" + name);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to view production group:\n" + ex);
            return null;
        }
    }

    @Override
    public ItemEntity getItemBySKU(String SKU) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Remove
    public void remove() {
        System.out.println("Item Management Bean is removed.");
    }

}
