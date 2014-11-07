package AnalyticalCRM.ValueAnalysis;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.RetailProductEntity;
import EntityManager.SalesRecordEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import EntityManager.MenuItemEntity;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.apache.commons.math3.stat.regression.SimpleRegression;

@Stateful
public class CustomerValueAnalysisBean implements CustomerValueAnalysisBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @EJB
    AccountManagementBeanLocal accountManagementBean;

    @EJB
    ItemManagementBeanLocal itemManagementBean;

    public Date getItemLastPurchase(Long itemId) {
        System.out.println("getItemLastPurchase" + itemId);
        Date latest = null;
        try {
            Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = q.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased() != null && salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity item : salesRecord.getItemsPurchased()) {
                        if (item.getItem().getId().equals(itemId)) {
                            if (latest == null) {
                                System.out.println("latest is null");
                                latest = salesRecord.getCreatedDate();
                            } else if (salesRecord.getCreatedDate().getTime() > latest.getTime()) {
                                System.out.println("latest is not null, changing latest date");
                                latest = salesRecord.getCreatedDate();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return latest;
    }

    public LineItemEntity getSecondProductFromFirstMenuItem(String menuItem) {
        System.out.println("getSecondProductFromFirstMenuItem()" + menuItem);
        LineItemEntity secondProduct = new LineItemEntity();
        List<LineItemEntity> listOfSecondProducts = new ArrayList();
        try {
            Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = q.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased() != null && salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity item : salesRecord.getItemsPurchased()) {
                        if (item.getItem().getName().equalsIgnoreCase(menuItem)) {
                            for (LineItemEntity item2 : salesRecord.getItemsPurchased()) {
                                if (!item2.getItem().getName().equalsIgnoreCase(menuItem)) {
                                    secondProduct = item2;

                                    if (listOfSecondProducts.size() == 0) {
                                        secondProduct.setQuantity(1);
                                        listOfSecondProducts.add(secondProduct);
                                    } else {
                                        Boolean flag = false;
                                        for (int i = 0; i < listOfSecondProducts.size(); i++) {
                                            if (listOfSecondProducts.get(i).getItem().getId().equals(secondProduct.getItem().getId())) {
                                                listOfSecondProducts.get(i).setQuantity(listOfSecondProducts.get(i).getQuantity() + 1);
                                                flag = true;
                                                break;
                                            }
                                        }
                                        if (flag == false) {
                                            secondProduct.setQuantity(1);
                                            listOfSecondProducts.add(secondProduct);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < listOfSecondProducts.size(); i++) {
                System.out.println("Item " + i + " " + listOfSecondProducts.get(i).getItem().getName() + " quantity is " + listOfSecondProducts.get(i).getQuantity());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LineItemEntity highestGrade = null;
        if (listOfSecondProducts.size() != 0) {
            highestGrade = listOfSecondProducts.get(0);
            for (int i = 1; i < listOfSecondProducts.size(); i++) {
                if (listOfSecondProducts.get(i).getQuantity() > highestGrade.getQuantity()) {
                    highestGrade = listOfSecondProducts.get(i);
                }
            }
        }
        if (highestGrade != null) {
            return highestGrade;
        } else {
            return null;
        }
    }

    public LineItemEntity getSecondProductFromFirstRP(String retailProduct) {
        System.out.println("getSecondProductFromFirstRP()" + retailProduct);
        LineItemEntity secondProduct = new LineItemEntity();
        List<LineItemEntity> listOfSecondProducts = new ArrayList();
        try {
            Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = q.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased() != null && salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity item : salesRecord.getItemsPurchased()) {
                        if (item.getItem().getName().equalsIgnoreCase(retailProduct)) {
                            for (LineItemEntity item2 : salesRecord.getItemsPurchased()) {
                                if (!item2.getItem().getName().equalsIgnoreCase(retailProduct)) {
                                    secondProduct = item2;

                                    if (listOfSecondProducts.size() == 0) {
                                        secondProduct.setQuantity(1);
                                        listOfSecondProducts.add(secondProduct);
                                    } else {
                                        Boolean flag = false;
                                        for (int i = 0; i < listOfSecondProducts.size(); i++) {
                                            if (listOfSecondProducts.get(i).getItem().getId().equals(secondProduct.getItem().getId())) {
                                                listOfSecondProducts.get(i).setQuantity(listOfSecondProducts.get(i).getQuantity() + 1);
                                                flag = true;
                                                break;
                                            }
                                        }
                                        if (flag == false) {
                                            secondProduct.setQuantity(1);
                                            listOfSecondProducts.add(secondProduct);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < listOfSecondProducts.size(); i++) {
                System.out.println("Item " + i + " " + listOfSecondProducts.get(i).getItem().getName() + " quantity is " + listOfSecondProducts.get(i).getQuantity());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LineItemEntity highestGrade = null;
        if (listOfSecondProducts.size() != 0) {
            highestGrade = listOfSecondProducts.get(0);
            for (int i = 1; i < listOfSecondProducts.size(); i++) {
                if (listOfSecondProducts.get(i).getQuantity() > highestGrade.getQuantity()) {
                    highestGrade = listOfSecondProducts.get(i);
                }
            }
        }
        if (highestGrade != null) {
            return highestGrade;
        } else {
            return null;
        }
    }

    public LineItemEntity getSecondProductFromFirst(String furniture) {
        System.out.println("getSecondProductFromFirst()" + furniture);
        LineItemEntity secondProduct = new LineItemEntity();
        List<LineItemEntity> listOfSecondProducts = new ArrayList();
        try {
            Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = q.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased() != null && salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity item : salesRecord.getItemsPurchased()) {
                        if (item.getItem().getName().equalsIgnoreCase(furniture)) {
                            for (LineItemEntity item2 : salesRecord.getItemsPurchased()) {
                                if (!item2.getItem().getName().equalsIgnoreCase(furniture)) {
                                    secondProduct = item2;

                                    if (listOfSecondProducts.size() == 0) {
                                        secondProduct.setQuantity(1);
                                        listOfSecondProducts.add(secondProduct);
                                    } else {
                                        Boolean flag = false;
                                        for (int i = 0; i < listOfSecondProducts.size(); i++) {
                                            if (listOfSecondProducts.get(i).getItem().getId().equals(secondProduct.getItem().getId())) {
                                                listOfSecondProducts.get(i).setQuantity(listOfSecondProducts.get(i).getQuantity() + 1);
                                                flag = true;
                                                break;
                                            }
                                        }
                                        if (flag == false) {
                                            secondProduct.setQuantity(1);
                                            listOfSecondProducts.add(secondProduct);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 0; i < listOfSecondProducts.size(); i++) {
                System.out.println("Item " + i + " " + listOfSecondProducts.get(i).getItem().getName() + " quantity is " + listOfSecondProducts.get(i).getQuantity());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LineItemEntity highestGrade = null;
        if (listOfSecondProducts.size() != 0) {
            highestGrade = listOfSecondProducts.get(0);
            for (int i = 1; i < listOfSecondProducts.size(); i++) {
                if (listOfSecondProducts.get(i).getQuantity() > highestGrade.getQuantity()) {
                    highestGrade = listOfSecondProducts.get(i);
                }
            }
        }
        if (highestGrade != null) {
            return highestGrade;
        } else {
            return null;
        }
    }

    public Integer getTotalMenuItemSoldInCountry(String country) {
        System.out.println("getTotalMenuItemSoldInCountry()" + country);
        List<LineItemEntity> sortedFurnitures = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM MenuItemEntity t");
            List<MenuItemEntity> furnitures = q.getResultList();

            for (MenuItemEntity furniture : furnitures) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(furniture);
                lineItem.setQuantity(0);
                sortedFurnitures.add(lineItem);
            }
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getStore() != null) {
                    if (salesRecord.getStore().getCountry().getName().equalsIgnoreCase(country)) {
                        if (salesRecord.getItemsPurchased().size() != 0) {
                            for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                                for (int i = 0; i < sortedFurnitures.size(); i++) {
                                    if (lineItem.getItem().getId() == sortedFurnitures.get(i).getItem().getId()) {
                                        sortedFurnitures.get(i).setQuantity(sortedFurnitures.get(i).getQuantity() + lineItem.getQuantity());

                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Integer total = 0;
        for (int i = 0; i < sortedFurnitures.size(); i++) {
            total += sortedFurnitures.get(i).getQuantity();
        }
        return total;
    }

    public Integer getTotalFurnitureSoldInCountry(String country) {
        System.out.println("getTotalFurnitureSoldInCountry()" + country);
        List<LineItemEntity> sortedFurnitures = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");
            List<FurnitureEntity> furnitures = q.getResultList();

            for (FurnitureEntity furniture : furnitures) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(furniture);
                lineItem.setQuantity(0);
                sortedFurnitures.add(lineItem);
            }
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getStore() != null) {
                    if (salesRecord.getStore().getCountry().getName().equalsIgnoreCase(country)) {
                        if (salesRecord.getItemsPurchased().size() != 0) {
                            for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                                for (int i = 0; i < sortedFurnitures.size(); i++) {
                                    if (lineItem.getItem().getId() == sortedFurnitures.get(i).getItem().getId()) {
                                        sortedFurnitures.get(i).setQuantity(sortedFurnitures.get(i).getQuantity() + lineItem.getQuantity());

                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Integer total = 0;
        for (int i = 0; i < sortedFurnitures.size(); i++) {
            total += sortedFurnitures.get(i).getQuantity();
        }
        return total;
    }

    public Integer getTotalRetailProductsSoldInCountry(String country) {
        System.out.println("getTotalRetailProductsSoldInCountry()" + country);
        List<LineItemEntity> sortedFurnitures = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");
            List<RetailProductEntity> furnitures = q.getResultList();

            for (RetailProductEntity furniture : furnitures) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(furniture);
                lineItem.setQuantity(0);
                sortedFurnitures.add(lineItem);
            }
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getStore() != null) {
                    if (salesRecord.getStore().getCountry().getName().equalsIgnoreCase(country)) {
                        if (salesRecord.getItemsPurchased().size() != 0) {
                            for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                                for (int i = 0; i < sortedFurnitures.size(); i++) {
                                    if (lineItem.getItem().getId() == sortedFurnitures.get(i).getItem().getId()) {
                                        sortedFurnitures.get(i).setQuantity(sortedFurnitures.get(i).getQuantity() + lineItem.getQuantity());

                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Integer total = 0;
        for (int i = 0; i < sortedFurnitures.size(); i++) {
            total += sortedFurnitures.get(i).getQuantity();
        }
        return total;
    }

    public Double getEstimatedCustomerLife() {
        System.out.println("getEstimatedCustomerLife()");
        return 1 / (1 - this.getAverageRetentionRate());
    }

    public List<LineItemEntity> sortBestSellingMenuItem() {
        System.out.println("sortBestSellingMenuItem()");
        List<LineItemEntity> sortedMenuItem = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM MenuItemEntity t");
            List<MenuItemEntity> menuItems = q.getResultList();

            for (MenuItemEntity menuItem : menuItems) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(menuItem);
                lineItem.setQuantity(0);
                sortedMenuItem.add(lineItem);
            }
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                        for (int i = 0; i < sortedMenuItem.size(); i++) {
                            if (lineItem.getItem().getId() == sortedMenuItem.get(i).getItem().getId()) {
                                sortedMenuItem.get(i).setQuantity(sortedMenuItem.get(i).getQuantity() + lineItem.getQuantity());

                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sortedMenuItem;
    }

    public Double getCustomerLifeTimeValue() {
        System.out.println("getCustomerLifeTimeValue()");
        return this.getEstimatedCustomerLife() * this.getAverageCustomerMonetaryValue();
    }

    public Double getAverageRetentionRate() {
        System.out.println("getAverageRetentionRate()");
        try {
            Double retentionRate_09 = this.getRetentionRateByYear(2009);
            Double retentionRate_10 = this.getRetentionRateByYear(2010);
            Double retentionRate_11 = this.getRetentionRateByYear(2011);
            Double retentionRate_12 = this.getRetentionRateByYear(2012);
            Double retentionRate_13 = this.getRetentionRateByYear(2013);

            return (retentionRate_09 + retentionRate_10 + retentionRate_11 + retentionRate_12 + retentionRate_13) / 5;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Double getRetentionRateByYear(Integer year) {
        System.out.println("getRetentionRateByYear()");
        try {
            int numberOfCustomerRetained = 0;
            Calendar cal = Calendar.getInstance();
            cal.clear();

            cal.set(Calendar.YEAR, year + 1);
            cal.set(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            Query q = em.createQuery("SELECT m FROM MemberEntity m where m.joinDate < ?1").setParameter(1, cal.getTime(), TemporalType.DATE);
            List<MemberEntity> members = q.getResultList();
            for (MemberEntity m : members) {

                Query q1 = em.createQuery("select s from SalesRecordEntity s where s.member.id = ?1 and s.createdDate > ?2 ")
                        .setParameter(1, m.getId())
                        .setParameter(2, cal.getTime(), TemporalType.DATE);
                if (!q1.getResultList().isEmpty()) {
                    numberOfCustomerRetained++;
                }
            }
            return (1.0 * numberOfCustomerRetained / q.getResultList().size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public CustomerValueAnalysisBean() {
        System.out.println("\nCustomer Value Analysis Server (EJB) created.");
    }

    @Override
    public Boolean sendMemberLoyaltyPoints(List<MemberEntity> members, Integer loyaltyPoints) {

        return true;
    }

    @Override
    public Integer getRevenueOfJoinDate(Integer year) {
        System.out.println("getRevenueOfJoinDate()");

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        Double totalRevenue = (double) 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                Date date = new Date();
                c.setTime(date);
                c.add(Calendar.DATE, (-365 * year));
                Date churnDate = c.getTime();
                if (member.getJoinDate().getTime() > churnDate.getTime()) {
                    if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                        for (int i = 0; i < member.getPurchases().size(); i++) {
                            totalRevenue += getSalesRecordAmountDueInUSD(member.getPurchases().get(i).getId());
                        }
                    } else {
                    }
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            return totalRevenue.intValue();
        } catch (Exception ex) {
            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
            return totalRevenue.intValue();
        }
    }

    @Override
    public Integer numOfMembersInJoinDate(Integer year) {
        System.out.println("numOfMembersInJoinDate()");

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        Double totalRevenue = (double) 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                Date date = new Date();
                c.setTime(date);
                c.add(Calendar.DATE, (-365 * year));
                Date churnDate = c.getTime();

                Long days = member.getJoinDate().getTime() - churnDate.getTime();
                days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                if (days > 0) {
                    numOfMembersNotChurn++;
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");

            return numOfMembersNotChurn;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
            return numOfMembersNotChurn;
        }
    }

    @Override
    public Integer getTotalNumberOfSalesRecord() {
        Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
        List<SalesRecordEntity> salesRecords = q.getResultList();
        return salesRecords.size();
    }

    @Override
    public Double getRetainedCustomerRetentionRate(List<MemberEntity> retainedMembers) {
        System.out.println("getRetainedCustomerRetentionRate()");

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            List<MemberEntity> members = retainedMembers;
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 730);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0 && days < 365) {
                            numOfMembersNotChurn++;
                            break;
                        }
                    }
                } else {
                }
            }
            return ((double) numOfMembersNotChurn / (double) numOfMembers);
        } catch (Exception ex) {
            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
            return ((double) numOfMembersNotChurn / (double) numOfMembers);
        }
    }

    @Override
    public List<LineItemEntity> sortBestSellingFurniture() {
        System.out.println("sortBestSellingFurniture()");
        List<LineItemEntity> sortedFurnitures = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");
            List<FurnitureEntity> furnitures = q.getResultList();

            for (FurnitureEntity furniture : furnitures) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(furniture);
                lineItem.setQuantity(0);
                sortedFurnitures.add(lineItem);
            }
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                        for (int i = 0; i < sortedFurnitures.size(); i++) {
                            if (lineItem.getItem().getId() == sortedFurnitures.get(i).getItem().getId()) {
                                sortedFurnitures.get(i).setQuantity(sortedFurnitures.get(i).getQuantity() + lineItem.getQuantity());

                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sortedFurnitures;
    }

    @Override
    public List<LineItemEntity> sortBestSellingRetailProducts() {
        System.out.println("sortBestSellingRetailProducts()");
        List<LineItemEntity> sortedRetailProducts = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");
            List<RetailProductEntity> retailProducts = q.getResultList();

            for (RetailProductEntity furniture : retailProducts) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(furniture);
                lineItem.setQuantity(0);
                sortedRetailProducts.add(lineItem);
            }
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                        for (int i = 0; i < sortedRetailProducts.size(); i++) {
                            if (lineItem.getItem().getId() == sortedRetailProducts.get(i).getItem().getId()) {
                                sortedRetailProducts.get(i).setQuantity(sortedRetailProducts.get(i).getQuantity() + lineItem.getQuantity());
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sortedRetailProducts;
    }

    @Override
    public Double getFurnitureTotalRevenue(Long furnitureId) {
        return (double) 10;
    }

    @Override
    public List<MemberEntity> getRetainedMembers() {
        System.out.println("getRetainedMembers()");

        List<MemberEntity> retainedMembers = new ArrayList();

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0) {
                            retainedMembers.add(member);
                            numOfMembersNotChurn++;
                            break;
                        }
                    }
                } else {
                }
            }
            return retainedMembers;
        } catch (Exception ex) {

            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
        }
        return retainedMembers;
    }

    @Override
    public Double averageOrdersPerAcquiredYear() {
        System.out.println("averageOrdersPerAcquiredYear()");

        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                        }
                    }
                } else {
                }
            }
            return ((double) numOfOrders / (double) numOfMembers);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list orders per year:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) numOfOrders / (double) numOfMembers);
    }

    @Override
    public Double averageOrdersPerRetainedMember() {
        System.out.println("averageOrdersPerRetainedMember()");

        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 730);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0 && days < 365) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                        }
                    }
                } else {
                }
            }

            return ((double) numOfOrders / (double) numOfMembers);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list averageOrdersPerRetainedMember:\n" + ex);
            ex.printStackTrace();
            return ((double) numOfOrders / (double) numOfMembers);
        }

    }

    @Override
    public Double averageOrderPriceInAcquiredYear() {
        System.out.println("averageOrderPriceInAcquiredYear()");

        Double totalPriceOfOrders = (double) 0;
        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);;
                        if (days > 0) {
                            totalPriceOfOrders += getSalesRecordAmountDueInUSD(member.getPurchases().get(i).getId());
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                }
            }
            return ((double) totalPriceOfOrders / (double) numOfOrders);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list avg order price per acquired year:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) totalPriceOfOrders / (double) numOfOrders);
    }

    @Override
    public Double averageOrderPriceForRetainedMembers() {
        System.out.println("averageOrderPriceForRetainedMembers()");

        Double totalPriceOfOrders = (double) 0;
        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 730);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0 && days < 365) {
                            totalPriceOfOrders += getSalesRecordAmountDueInUSD(member.getPurchases().get(i).getId());
                            numOfOrders++;
                        }
                    }
                } else {
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            return ((double) totalPriceOfOrders / (double) numOfOrders);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list avg order price per retained member year:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) totalPriceOfOrders / (double) numOfOrders);
    }

    @Override
    public Double averageOrderPrice() {
        System.out.println("averageOrderPrice()");

        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                }
            }
            return ((double) numOfOrders / (double) numOfMembers);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list orders per year:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) numOfOrders / (double) numOfMembers);
    }

    @Override
    public Double getCustomerRetentionRate() {
        System.out.println("getCustomerRetentionRate()");

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (SalesRecordEntity record : member.getPurchases()) {
                        Long days = churnDate.getTime() - record.getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            break;
                        }
                    }
                } else {

                }
            }
            DecimalFormat df = new DecimalFormat("#.00");

            return ((double) numOfMembersNotChurn / (double) numOfMembers);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
            return ((double) numOfMembersNotChurn / (double) numOfMembers);
        }
    }

    //For furnitures only
    @Override
    public Integer getAverageCustomerRecency() {
        System.out.println("getAverageCustomerRecency()");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Integer numOfDaysWithRecord = 0;
        Integer totalDays = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    List<Date> dates = new ArrayList<Date>();
                    Date latest;

                    Boolean purchaseIncludesFurniture = false;
                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        for (int j = 0; j < member.getPurchases().get(i).getItemsPurchased().size(); j++) {
                            if (member.getPurchases().get(i).getItemsPurchased().get(j).getItem().getType().equals("Furniture")) {
                                purchaseIncludesFurniture = true;
                            }
                        }
                    }
                    if (purchaseIncludesFurniture == true) {
                        for (int i = 0; i < member.getPurchases().size(); i++) {
                            dates.add(member.getPurchases().get(i).getCreatedDate());
                        }
                        latest = Collections.max(dates);

                        Long days = date.getTime() - latest.getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        numOfDaysWithRecord++;
                        totalDays += (int) (long) days;
                    }
                } else {
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list recency:\n" + ex);
            ex.printStackTrace();
        }
        if (numOfDaysWithRecord != 0) {
            return totalDays / numOfDaysWithRecord;
        } else {
            return 0;
        }
    }

    public Integer getAverageCustomerRecencyMenuItem() {
        System.out.println("getAverageCustomerRecencyMenuItem()");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Integer numOfDaysWithRecord = 0;
        Integer totalDays = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    List<Date> dates = new ArrayList<Date>();
                    Date latest;
                    Boolean containsMenuItem = false;
                    for (int i = 0; i < member.getPurchases().size(); i++) {

                        if (member.getPurchases().get(i).getItemsPurchased() != null && member.getPurchases().get(i).getItemsPurchased().size() != 0) {
                            for (int j = 0; j < member.getPurchases().get(i).getItemsPurchased().size(); j++) {
                                if (member.getPurchases().get(i).getItemsPurchased().get(j).getItem().getType().equals("Menu Item")) {
                                    containsMenuItem = true;
                                }
                            }
                        }
                    }
                    if (containsMenuItem == true) {
                        for (int i = 0; i < member.getPurchases().size(); i++) {
                            dates.add(member.getPurchases().get(i).getCreatedDate());
                        }
                        latest = Collections.max(dates);

                        Long days = date.getTime() - latest.getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        numOfDaysWithRecord++;
                        totalDays += (int) (long) days;
                    }
                } else {
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list recency:\n" + ex);
            ex.printStackTrace();
        }
        if (numOfDaysWithRecord != 0) {
            return totalDays / numOfDaysWithRecord;
        } else {
            return 0;
        }
    }

    //For furnitures only
    @Override
    public Integer getAverageCustomerFrequency() {
        System.out.println("getAverageCustomerFrequency()");

        Integer numOfPurchases = 0;
        Integer frequency = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    Boolean furnitureExistsInPurchase = false;
                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        for (int j = 0; j < member.getPurchases().get(i).getItemsPurchased().size(); j++) {
                            if (member.getPurchases().get(i).getItemsPurchased().get(j).getItem().getType().equals("Furniture")) {
                                furnitureExistsInPurchase = true;
                            }
                        }
                    }
                    if (furnitureExistsInPurchase == true) {
                        numOfPurchases++;
                        frequency += member.getPurchases().size();
                    }
                } else {
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list recency:\n" + ex);
            ex.printStackTrace();
        }
        if (numOfPurchases != 0) {
            return frequency / numOfPurchases;
        } else {
            return 0;
        }
    }

    public Integer getAverageCustomerFrequencyMenuItem() {
        System.out.println("getAverageCustomerFrequencyMenuItem()");

        Integer numOfPurchases = 0;
        Integer frequency = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    Boolean menuItemExistsInPurchase = false;
                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        for (int j = 0; j < member.getPurchases().get(i).getItemsPurchased().size(); j++) {
                            if (member.getPurchases().get(i).getItemsPurchased().get(j).getItem().getType().equals("Menu Item")) {
                                menuItemExistsInPurchase = true;
                            }
                        }
                    }
                    if (menuItemExistsInPurchase) {
                    numOfPurchases++;
                    frequency += member.getPurchases().size();
                    }
                } else {
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list recency:\n" + ex);
            ex.printStackTrace();
        }
        if (numOfPurchases != 0) {
            return frequency / numOfPurchases;
        } else {
            return 0;
        }
    }

    //For furnitures only
    @Override
    public Integer getAverageCustomerMonetaryValue() {
        System.out.println("getAverageCustomerMonetaryValue()");

        Integer amountOfPurchase = 0;
        Integer numOfPurchases = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    Boolean furnitureExistsInPurchase = false;
                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        for (int j = 0; j < member.getPurchases().get(i).getItemsPurchased().size(); j++) {
                            if (member.getPurchases().get(i).getItemsPurchased().get(j).getItem().getType().equals("Furniture")) {
                                furnitureExistsInPurchase = true;
                            }
                        }
                    }
                    if (furnitureExistsInPurchase) {
                        for (int i = 0; i < member.getPurchases().size(); i++) {
                            numOfPurchases++;
                            amountOfPurchase += getSalesRecordAmountDueInUSD(member.getPurchases().get(i).getId()).intValue();
                        }
                    }
                } else {
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list monetary value:\n" + ex);
            ex.printStackTrace();
        }
        if (numOfPurchases != 0) {
            return amountOfPurchase / numOfPurchases;
        } else {
            return 0;
        }
    }
    
    public Integer getAverageCustomerMonetaryValueMenuItem() {
        System.out.println("getAverageCustomerMonetaryValueMenuItem()");

        Integer amountOfPurchase = 0;
        Integer numOfPurchases = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    Boolean menuItemExistsInPurchase = false;
                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        for (int j = 0; j < member.getPurchases().get(i).getItemsPurchased().size(); j++) {
                            if (member.getPurchases().get(i).getItemsPurchased().get(j).getItem().getType().equals("Menu Item")) {
                                menuItemExistsInPurchase = true;
                            }
                        }
                    }
                    if (menuItemExistsInPurchase) {
                        for (int i = 0; i < member.getPurchases().size(); i++) {
                            numOfPurchases++;
                            amountOfPurchase += getSalesRecordAmountDueInUSD(member.getPurchases().get(i).getId()).intValue();
                        }
                    }
                } else {
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list monetary value:\n" + ex);
            ex.printStackTrace();
        }
        if (numOfPurchases != 0) {
            return amountOfPurchase / numOfPurchases;
        } else {
            return 0;
        }
    }

    @Override
    public Integer customerLifetimeValueOfMember(Long memberId) {
        return 5;
    }

    @Override
    public Integer getCustomerRecency(Long memberId) {
        System.out.println("getCustomerRecency()");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;

        MemberEntity member = em.find(MemberEntity.class, memberId);
        if (member.getPurchases() != null && member.getPurchases().size() != 0) {

            List<Date> dates = new ArrayList<Date>();

            for (int i = 0; i < member.getPurchases().size(); i++) {
                dates.add(member.getPurchases().get(i).getCreatedDate());
            }
            Date latest = Collections.max(dates);

            Long numOfDaysBetween = date.getTime() - latest.getTime();
            days = (int) (long) TimeUnit.DAYS.convert(numOfDaysBetween, TimeUnit.MILLISECONDS);

        } else {
        }
        return days;
    }

    @Override
    public Integer getCustomerFrequency(Long memberId) {
        System.out.println("getCustomerFrequency()");

        Integer numOfPurchases;
        MemberEntity member = em.find(MemberEntity.class, memberId);
        if (member.getPurchases() != null && member.getPurchases().size() != 0) {
            numOfPurchases = member.getPurchases().size();
        } else {
            return 0;
        }
        return numOfPurchases;
    }

    @Override
    public Integer getCustomerMonetaryValue(Long memberId) {
        System.out.println("getCustomerMonetaryValue()");

        Integer totalPriceOfPurchases = 0;
        MemberEntity member = em.find(MemberEntity.class, memberId);
        if (member.getPurchases() != null && member.getPurchases().size() != 0) {
            for (int i = 0; i < member.getPurchases().size(); i++) {
                totalPriceOfPurchases += getSalesRecordAmountDueInUSD(member.getPurchases().get(i).getId()).intValue();
            }
        } else {
            return 0;
        }
        return totalPriceOfPurchases;
    }

    @Override
    public Integer totalCummulativeSpendingOfAge(Integer startAge, Integer endAge) {
        System.out.println("totalCummulativeSpendingOfAge()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            if (member.getAge() != null) {
                if (member.getAge() > startAge && member.getAge() < endAge) {
                    List<SalesRecordEntity> salesRecordOfMember = member.getPurchases();
                    if (salesRecordOfMember != null) {
                        for (SalesRecordEntity salesRecord : salesRecordOfMember) {

                            totalCummulativeSpending += getSalesRecordAmountDueInUSD(salesRecord.getId());
                        }
                    }
                }
            }
        }
        return totalCummulativeSpending;
    }

    @Override
    public Integer totalCummulativeSpendingOfJoinDate(Integer startDate, Integer endDate) {
        System.out.println("totalCummulativeSpendingOfJoinDate()");
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            Long numOfDaysBetween = date.getTime() - member.getJoinDate().getTime();
            days = (int) (long) TimeUnit.DAYS.convert(numOfDaysBetween, TimeUnit.MILLISECONDS);
            if (days > startDate && days < endDate) {
                List<SalesRecordEntity> salesRecordOfMember = member.getPurchases();
                if (salesRecordOfMember != null) {
                    for (SalesRecordEntity salesRecord : salesRecordOfMember) {

                        totalCummulativeSpending += getSalesRecordAmountDueInUSD(salesRecord.getId());
                    }
                }
            }
        }
        return totalCummulativeSpending;
    }

    @Override
    public Double getROfAge() {
        System.out.println("getROfAge()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getAge(), member.getCummulativeSpending());
        }
        return regression.getR();
    }

    @Override
    public Double getStdErrorOfAge() {
        System.out.println("getStdErrorOfAge()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getAge(), member.getCummulativeSpending());
        }
        return regression.getR();
    }

    @Override
    public Double getRSquaredOfAge() {
        System.out.println("getRSquaredOfAge()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getAge(), member.getCummulativeSpending());
        }
        return regression.getRSquare();
    }

    @Override
    public Double getROfIncome() {
        System.out.println("getROfIncome()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getIncome(), member.getCummulativeSpending());
        }
        return regression.getR();
    }

    @Override
    public Double getStdErrorOfIncome() {
        System.out.println("getStdErrorOfIncome()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getIncome(), member.getCummulativeSpending());
        }
        return regression.getR();
    }

    @Override
    public Double getRSquaredOfIncome() {
        System.out.println("getRSquaredOfIncome()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getIncome(), member.getCummulativeSpending());
        }
        return regression.getRSquare();
    }

    @Override
    public Integer totalCummulativeSpendingOfIncome(Integer startIncome, Integer endIncome) {
        System.out.println("totalCummulativeSpendingOfIncome()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            if (member.getIncome() != null) {
                if (member.getIncome() > startIncome && member.getIncome() < endIncome) {
                    List<SalesRecordEntity> salesRecordOfMember = member.getPurchases();
                    if (salesRecordOfMember != null) {
                        for (SalesRecordEntity salesRecord : salesRecordOfMember) {
                            totalCummulativeSpending += getSalesRecordAmountDueInUSD(salesRecord.getId());
                        }
                    }
                }
            }
        }
        return totalCummulativeSpending;
    }

    @Override
    public Integer totalCummulativeSpendingOfCountry(String country) {
        System.out.println("totalCummulativeSpendingOfCountry()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            if (member.getCity().equalsIgnoreCase(country)) {
                List<SalesRecordEntity> salesRecordOfMember = member.getPurchases();
                if (salesRecordOfMember != null) {
                    for (SalesRecordEntity salesRecord : salesRecordOfMember) {

                        totalCummulativeSpending += getSalesRecordAmountDueInUSD(salesRecord.getId());
                    }
                }
            }
        }
        return totalCummulativeSpending;
    }

    @Override
    public Integer averageCummulativeSpending() {
        System.out.println("averageCummulativeSpending()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int totalCummulativeSpending = 0;
        for (int i = 0; i < members.size(); i++) {
            List<SalesRecordEntity> salesRecordOfMember = members.get(i).getPurchases();
            if (salesRecordOfMember != null) {
                for (SalesRecordEntity salesRecord : salesRecordOfMember) {

                    totalCummulativeSpending += getSalesRecordAmountDueInUSD(salesRecord.getId());
                }
            }
        }
        return (totalCummulativeSpending / members.size());
    }

    @Override
    public Integer numOfMembersInAgeGroup(Integer startAge, Integer endAge) {
        System.out.println("numOfMembersInAgeGroup()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int numOfmembersInGroup = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getAge() != null) {
                if (members.get(i).getAge() > startAge && members.get(i).getAge() < endAge) {
                    numOfmembersInGroup++;
                }
            }
        }
        return numOfmembersInGroup;
    }

    @Override
    public Integer numOfMembersInIncomeGroup(Integer startIncome, Integer endIncome) {
        System.out.println("numOfMembersInIncomeGroup()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int numOfmembersInGroup = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getIncome() != null) {
                if (members.get(i).getIncome() > startIncome && members.get(i).getIncome() < endIncome) {
                    numOfmembersInGroup++;
                }
            }
        }
        return numOfmembersInGroup;
    }

    @Override
    public Integer numOfMembersInCountry(String country) {
        System.out.println("numOfMembersInCountry()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int numOfmembersInGroup = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getCity().equalsIgnoreCase(country)) {
                numOfmembersInGroup++;
            }
        }
        return numOfmembersInGroup;
    }

    @Override
    public Double totalMemberRevenue() {
        System.out.println("totalMemberRevenue()");
        Double profit = new Double("0");
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (int i = 0; i < members.size(); i++) {
                if (members.get(i).getPurchases() != null) {
                    List<SalesRecordEntity> salesRecordOfMember = members.get(i).getPurchases();
                    if (salesRecordOfMember != null) {
                        for (SalesRecordEntity salesRecord : salesRecordOfMember) {

                            profit += getSalesRecordAmountDueInUSD(salesRecord.getId());
                        }
                    }
                }
            }
            return profit;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return profit;
    }

    @Override
    public Double totalNonMemberRevenue() {
        System.out.println("totalNonMemberRevenue()");

        Double profit = new Double("0");
        try {
            Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = q.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getMember() == null) {
                    profit += getSalesRecordAmountDueInUSD(salesRecord.getId());
                } else {

                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all non member sales records:\n" + ex);
            ex.printStackTrace();
            return profit;
        }
        return profit;
    }

    @Override
    public List<ItemEntity> viewSimilarProducts(Long itemId) {

        List<ItemEntity> similarProducts = new ArrayList();
        return similarProducts;
    }

    @Override
    public List<ItemEntity> viewUpsellProducts(Long itemId) {

        List<ItemEntity> similarProducts = new ArrayList();
        return similarProducts;
    }

    @Override
    public Integer viewMonthlyReport() {

        return 5;
    }

    @Override
    public Integer viewSalesSummary() {

        return 5;
    }

    @Override
    public List<FurnitureEntity> viewBestSellingFurniture() {
        List<FurnitureEntity> bestFurniture = new ArrayList();
        return bestFurniture;
    }

    @Override
    public List<RetailProductEntity> viewBestSellingRetailProducts() {
        List<RetailProductEntity> bestRetailProducts = new ArrayList();
        return bestRetailProducts;
    }

    @Override
    public List<SalesRecordEntity> viewMemberSalesRecord(Long memberId) {
        List<SalesRecordEntity> salesRecords = new ArrayList();
        return salesRecords;
    }

    @Override
    public Double getSalesRecordAmountDueInUSD(Long salesRecordId) {
        SalesRecordEntity salesRecordEntity = em.getReference(SalesRecordEntity.class, salesRecordId);
        Double exchangeRate = salesRecordEntity.getStore().getCountry().getExchangeRate();
        Double amountDue = salesRecordEntity.getAmountDue();
        Double amountDueInUSD = amountDue / exchangeRate;
        return amountDueInUSD;
    }

}
