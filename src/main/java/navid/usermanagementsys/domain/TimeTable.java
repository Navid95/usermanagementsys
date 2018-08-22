package navid.usermanagementsys.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.OffsetTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class TimeTable extends AbstractDomainClass {

    private OffsetTime startTime;
    private OffsetTime endTime;

    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Field field;

    @ManyToOne(fetch = FetchType.LAZY )
    private Saloon saloon;

    private DayOfWeek dayOfWeek;

    public TimeTable() {
    }

    public TimeTable(OffsetTime startTime, OffsetTime endTime, Field field, Saloon saloon, DayOfWeek dayOfWeek) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.field = field;
        this.saloon = saloon;
        this.dayOfWeek = dayOfWeek;
    }

    public OffsetTime getStartTime() {
        return startTime;
    }

    public void setStartTime(OffsetTime startTime) {
        this.startTime = startTime;
    }

    public OffsetTime getEndTime() {
        return endTime;
    }

    public void setEndTime(OffsetTime endTime) {
        this.endTime = endTime;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Saloon getSaloon() {
        return saloon;
    }

    public void setSaloon(Saloon saloon) {
        this.saloon = saloon;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "TimeTable{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", field=" + field +
                ", saloon=" + saloon +
                ", dayOfWeek=" + dayOfWeek +
                '}';
    }
}
