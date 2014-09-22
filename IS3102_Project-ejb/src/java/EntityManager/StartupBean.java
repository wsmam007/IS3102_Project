/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityManager;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
@Startup
public class StartupBean {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    private void startup() {
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity t");
            List<RoleEntity> roleEntities = q.getResultList();
            // Don't insert anything if database appears to be initiated.
            if (roleEntities != null && roleEntities.size() > 0) {
                System.out.println("Skipping init of database, already initated.");
                return;
            }
            System.out.println("Starting to init database.");
            try {
                //Create roles
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.create("Administrator", "System");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Regional Manager", "Region");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Warehouse Manager", "Facility");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Store Manager", "Facility");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Marketing Director", "Organization");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Product Development Engineer", "Organization");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Purchasing Manager", "Region");
                em.persist(roleEntity);
                System.out.println("RolesEntity init success.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of roles:\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create administrator account
                StaffEntity staffEntity = new StaffEntity();
                String passwordSalt = accountManagementBean.generatePasswordSalt();
                String passwordHash = accountManagementBean.generatePasswordHash(passwordSalt, "admin");
                staffEntity.create("S0000000A", "Administrator", "+65 65162727", "a@a", "Island Furniture", passwordSalt, passwordHash);
                List<RoleEntity> roles = new ArrayList();
                Query e = em.createQuery("SELECT t FROM RoleEntity t where t.name='Administrator'");
                RoleEntity roleEntity = (RoleEntity) e.getSingleResult();
                roles.add(roleEntity);
                staffEntity.setRoles(roles);
                em.persist(staffEntity);
                System.out.println("Created administrator with ID:a@a and PW:admin.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of administrator account:\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create countries
                CountryEntity country = new CountryEntity();
                country.setCountryCode(65);
                country.setCurrency("SGD");
                country.setExchangeRate(0.75);
                country.setName("Singapore");
                em.persist(country);
                country = new CountryEntity();
                country.setCountryCode(60);
                country.setCurrency("RM");
                country.setExchangeRate(3.0);
                country.setName("Malaysia");
                em.persist(country);
                country = new CountryEntity();
                country.setCountryCode(62);
                country.setCurrency("RUPIAH");
                country.setExchangeRate(100.0);
                country.setName("Indonesia");
                em.persist(country);
                country = new CountryEntity();
                country.setCountryCode(1);
                country.setCurrency("USD");
                country.setExchangeRate(1.0);
                country.setName("United States");
                em.persist(country);
                System.out.println("Created country entities.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of country entities:\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create warehouse
                WarehouseEntity warehouseEntity;
                warehouseEntity = new WarehouseEntity("Warehouse SG1", "3 Jurong Industrial Park", "67183645", "MFSG1@islandfurniture.com");
                em.persist(warehouseEntity);
                warehouseEntity = new WarehouseEntity("Warehouse SG2", "26 Toh Guan Road", "67183664", "MFSG2@islandfurniture.com");
                em.persist(warehouseEntity);
                System.out.println("Created warehouse entities.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of warehouse entities:\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create item
                FurnitureEntity furnitureEntity = new FurnitureEntity("F1", "LiINNMON/ADILS", "Tables & Desks","Pre-drilled holes for legs, for easy assembly. Adjustable feet make the table stand steady also on uneven floors", "imageURL", 1,1,1);
                em.persist(furnitureEntity);
                furnitureEntity = new FurnitureEntity("F2", "MOSHULT", "Beds & Mattresses","Get all-over support and comfort with a resilient foam mattress. This mattress is approved for children. It has a child-proof zipper without a pull mechanism, which eliminates small parts and prevents children from opening it. Easy to keep clean since you can remove the cover and wash it by machine. Easy to bring home since the mattress is roll packed.", "imageURL", 1,1,1);
                em.persist(furnitureEntity);
                RawMaterialEntity rawMaterialEntity = new RawMaterialEntity("RM1", "Steel", "Metal", "A piece of steel", 1,1,1);
                em.persist(rawMaterialEntity);
                System.out.println("Created item entities.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of item entities:\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create supplier
                SupplierEntity supplierEntity = new SupplierEntity("Supplier 1", "67911580", "supplier1@email.com", "231 Bukit Panjang Road");
                em.persist(supplierEntity);
                supplierEntity = new SupplierEntity("Supplier 2", "67911432", "supplier2@email.com", "3 Dover Road");
                em.persist(supplierEntity);
                System.out.println("Created supplierEntity.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of supplierEntity:\n" + ex);
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("Skipped init of database:\n" + ex);
            ex.printStackTrace();
        }
    }

    @PreDestroy
    private void shutdown() {
        System.out.println("Application is shutting down.");
    }
}
