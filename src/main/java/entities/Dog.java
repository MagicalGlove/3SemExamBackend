package entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dog")
@NamedQuery(name = "Dog.deleteAllRows", query = "DELETE FROM Dog ")
public class Dog implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "breed")
    private String breed;

    @Column(name = "image")
    private String image;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private String birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;


    @ManyToMany
    @JoinTable(
            name = "walker_dogs",
            joinColumns = @JoinColumn(name = "dog_id"),
            inverseJoinColumns = @JoinColumn(name = "walker_id"))
    private List<Walker> walkers = new ArrayList<>();

    public Dog() {}

    public Dog(String name, String breed, String image, String gender, String birthday, List<Walker> walkers) {
        this.name = name;
        this.breed = breed;
        this.image = image;
        this.gender = gender;
        this.birthday = birthday;
        this.walkers = walkers;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Walker> getWalkers() {
        return walkers;
    }

    public void setWalkers(List<Walker> walkers) {
        this.walkers = walkers;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", owner=" + owner +
                ", walkers=" + walkers +
                '}';
    }
}