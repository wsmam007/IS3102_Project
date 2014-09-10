package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RetailProductEntity extends ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<Supplier_RetailProductEntity> listOfSupplier_RetailProductInfo;
    private String name;

    public void create(String name) {
        setName(name);
    }
    public List<Supplier_RetailProductEntity> getListOfSupplier_RetailProductInfo() {
        return listOfSupplier_RetailProductInfo;
    }

    public void setListOfSupplier_RetailProductInfo(List<Supplier_RetailProductEntity> listOfSupplier_RetailProductInfo) {
        this.listOfSupplier_RetailProductInfo = listOfSupplier_RetailProductInfo;
    }

    public RetailProductEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RetailProductEntity)) {
            return false;
        }
        RetailProductEntity other = (RetailProductEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityManagerBean.RetailProduct[ id=" + id + " ]";
    }

}