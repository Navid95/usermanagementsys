package navid.usermanagementsys.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Saloon extends AbstractDomainClass {

    @Column(unique = true)
    private String saloonName;

    private int capacity;

    @JsonBackReference(value = "timeTables")
    @OneToMany(mappedBy = "saloon", fetch = FetchType.LAZY , cascade = CascadeType.ALL , orphanRemoval = true )
    private List<TimeTable> timeTables = new ArrayList<>();

//    @JsonBackReference(value = "saloon_gym")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gym gym;

    public String getSaloonName() {
        return saloonName;
    }

    public void setSaloonName(String saloonName) {
        this.saloonName = saloonName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(List<TimeTable> timeTables) {
        if (timeTables != null) {
            this.timeTables = timeTables;
            timeTables.forEach(timeTable -> {
                if (timeTable.getSaloon() != this)
                    timeTable.setSaloon(this);
            });
        }
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public void addTimeTable(TimeTable timeTable){
        if (!this.timeTables.contains(timeTable)){
            this.timeTables.add(timeTable);
            timeTable.setSaloon(this);
        }
    }
    public void removeTimeTable(TimeTable timeTable){
        this.timeTables.remove(timeTable);
        timeTable.setSaloon(null);
    }

    @Override
    public String toString() {
        return "Saloon{" +
                "saloonName='" + saloonName + '\'' +
                ", capacity=" + capacity +
                ", gym=" + gym +
                '}';
    }
}
