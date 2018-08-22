package navid.usermanagementsys.domain;

import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Personnel extends User{

    private String firstName;
    private String lastName;
    private long nationalId;
    private long perssonnelId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonManagedReference(value = "company")
    @JsonBackReference(value = "company")
    private Company company;

//    ********************************** METHODS *******************************************

//    public Personnel(String s){
//        System.out.println("In Personnel Json constructor");
//    }

    public Personnel(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    public long getPerssonnelId() {
        return perssonnelId;
    }

    public void setPerssonnelId(long perssonnelId) {
        this.perssonnelId = perssonnelId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
        if (!company.getPersonnels().contains(this))
            company.addPersonel(this);
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalId=" + nationalId +
                ", perssonnelId=" + perssonnelId +
                ", birthDate=" + birthDate +
                '}';
    }
}
