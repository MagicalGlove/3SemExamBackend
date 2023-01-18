package dtos;

import entities.Dog;
import entities.Owner;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Owner} entity
 */
public class OwnerDto implements Serializable {

    private final Long id;
    private final String name;
    private final String address;
    private final String phone;
    private final List<DogDto> dogs;

    public OwnerDto(Long id, String name, String address, String phone, List<DogDto> dogs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.dogs = dogs;
    }

//    public OwnerDto(Owner owner) {
//        if(owner.getId() != null)
//            this.id = owner.getId();
//        this.name = owner.getName();
//        this.address = owner.getAddress();
//        this.phone = owner.getPhone();
//        this.dogs = new ArrayList<>();
//        for (Dog dog : owner.getDogs()) {
//            this.dogs.add(new DogDto(dog));
//        }
//
//    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<DogDto> getDogs() {
        return dogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerDto entity = (OwnerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.dogs, entity.dogs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, dogs);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "address = " + address + ", " +
                "phone = " + phone + ", " +
                "dogs = " + dogs + ")";
    }
}