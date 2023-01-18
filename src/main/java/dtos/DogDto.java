package dtos;

import entities.Dog;
import entities.Owner;
import entities.Walker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link Dog} entity
 */
public class DogDto implements Serializable {
    private final Long id;
    private final String name;
    private final String breed;
    private final String image;
    private final String gender;
    private final String birthday;
    private final OwnerDto ownerDto;
    private final List<WalkerDto> walkerDtos;

    public DogDto(Long id, String name, String breed, String image, String gender, String birthday, OwnerDto ownerDto, List<WalkerDto> walkerDtos) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.image = image;
        this.gender = gender;
        this.birthday = birthday;
        this.ownerDto = ownerDto;
        this.walkerDtos = walkerDtos;
    }

//    public DogDto(Dog dog){
//        if (dog.getId() != null)
//            this.id = dog.getId();
//        this.name = dog.getName();
//        this.breed = dog.getBreed();
//        this.image = dog.getImage();
//        this.gender = dog.getGender();
//        this.birthday = dog.getBirthday();
//        this.ownerDto = new OwnerDto(dog.getOwner());
//        this.walkerDtos = new ArrayList<>();
//        for (Dog dog : dog.getWalkers());
//
//    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getImage() {
        return image;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public OwnerDto getOwnerDto() {
        return ownerDto;
    }

    public List<WalkerDto> getWalkerDtos() {
        return walkerDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogDto entity = (DogDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.breed, entity.breed) &&
                Objects.equals(this.image, entity.image) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.birthday, entity.birthday) &&
                Objects.equals(this.ownerDto, entity.ownerDto) &&
                Objects.equals(this.walkerDtos, entity.walkerDtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, image, gender, birthday, ownerDto, walkerDtos);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "breed = " + breed + ", " +
                "image = " + image + ", " +
                "gender = " + gender + ", " +
                "birthday = " + birthday + ", " +
                "ownerDto = " + ownerDto + ", " +
                "walkerDtos = " + walkerDtos + ")";
    }
}