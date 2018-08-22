package navid.usermanagementsys.domain;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class AbstractDomainClass implements DomianObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date dateCreated;
    private Date LastUpdated;

// ********************************************* METHODS

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps(){
        LastUpdated = new Date();

        if (dateCreated == null)
            dateCreated = new Date();
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateLastUpdated() {
        return LastUpdated;
    }

}
