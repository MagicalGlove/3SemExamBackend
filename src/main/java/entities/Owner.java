package entities;

import java.io.Serializable;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "owner")
@NamedQuery(name = "Owner.deleteAllRows", query = "DELETE FROM Owner ")
public class Owner implements Serializable {


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


    @OneToMany(mappedBy = "owner")
    private List<Dog> dogs = new ArrayList<>();

    public Owner() {}

    public Owner(String name, String address, String phone, List<Dog> dogs) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dogs = dogs;
    }

    //TODO Change when password is hashed
    // public boolean verifyPassword(String pw) {
    //     return BCrypt.checkpw(pw, password);
    //}


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
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", dogs=" + dogs +
                '}';
    }
}
