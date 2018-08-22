package navid.usermanagementsys.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Gym  extends AbstractDomainClass {

    @Column(unique = true , nullable = false)
    private String name;

    private String address;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private User admin ;

    @JsonBackReference(value = "gym_saloons")
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    private List<Saloon> saloons = new ArrayList<>();

//    ********************************** METHODS *******************************************

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<Saloon> getSaloons() {
        return saloons;
    }

    public void setSaloons(List<Saloon> saloons) {
        this.saloons = saloons;

        if (saloons != null)
        saloons.forEach(saloon -> {
            if (saloon.getGym() != this)
                saloon.setGym(this);
        });
    }

    public void addSaloon(Saloon saloon){
        if (!this.saloons.contains(saloon)){
            this.saloons.add(saloon);
            saloon.setGym(this);
        }
    }

    public void removeSaloon(Saloon saloon){
        this.saloons.remove(saloon);
        saloon.setGym(null);
    }

    @Override
    public String toString() {
        return "Gym{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", admin=" + admin +
                '}';
    }
}
