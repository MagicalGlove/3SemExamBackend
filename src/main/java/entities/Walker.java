package entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "walker")
@NamedQuery(name = "Walker.deleteAllRows", query = "DELETE FROM Walker ")
public class Walker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "walker_dogs",
            joinColumns = @JoinColumn(name = "walker_id"),
            inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<Dog> dogs = new ArrayList<>();

    public Walker() {}

    public Walker(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Walker(Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Walker(String name, String address, String phone, List<Dog> dogs) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dogs = dogs;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Walker)) return false;
        Walker walker = (Walker) o;
        return getId().equals(walker.getId()) && Objects.equals(getName(), walker.getName()) && Objects.equals(getAddress(), walker.getAddress()) && Objects.equals(getPhone(), walker.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getPhone());
    }

    @Override
    public String toString() {
        return "Walker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", dogs=" + dogs +
                '}';
    }
}
