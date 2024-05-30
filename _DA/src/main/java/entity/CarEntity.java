package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cars")
public class CarEntity {
    @Id
    @Column(name = "car_number")
    private String number;
    @Column(name = "model")
    private String model;

    @ManyToMany
    @JoinTable(
            name = "auto_owner_cars",
            joinColumns = @JoinColumn(name = "car_number"),
            inverseJoinColumns = @JoinColumn(name = "auto_ownerFID")
    )
    private Set<AutoOwnerEntity> autoOwnerEntities = new HashSet<>();

    public Set<AutoOwnerEntity> getAutoOwnerEntities() {
        return autoOwnerEntities;
    }

    public void setAutoOwnerEntities(Set<AutoOwnerEntity> autoOwnerEntities) {
        this.autoOwnerEntities = autoOwnerEntities;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
