package navid.usermanagementsys.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Permission extends AbstractDomainClass {


    @Column(unique = true)
    private String name;

//    @Column(updatable = false)
    private String objectName;

    private String description;

//    ********************************** METHODS *******************************************


    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
