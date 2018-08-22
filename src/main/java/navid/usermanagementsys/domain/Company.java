package navid.usermanagementsys.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Company extends AbstractDomainClass {

    @Column(unique = true)
    private String name;
    private String description;
    private String address;
    private String adminName;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST , orphanRemoval = true)
//    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.PERSIST , orphanRemoval = true)
    @JoinTable
//    @JsonBackReference(value = "personnels")
//    @JsonManagedReference(value = "personnels")
    private List<Personnel> personnels = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
//    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private User admin;

//    ********************************** METHODS *******************************************

    public Company(){}

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Personnel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(List<Personnel> personnels) {
        personnels.forEach(personel -> {
            if (personel.getCompany() != this)
                personel.setCompany(this);
        });
        this.personnels = personnels;
    }

    public void addPersonel(Personnel personnel){

        if(!personnels.contains(personnel)){
            personnels.add(personnel);
            personnel.setCompany(this);
        }
    }

    public void deletePersonel(Personnel personnel){
        if(personnels.contains(personnel)){
            personnels.remove(personnel);
        }
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", adminName='" + adminName + '\'' +
                ", personnels=" + personnels +
                ", admin=" + admin +
                '}';
    }
}
