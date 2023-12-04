package mk.finki.ukim.mk.lab.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.io.Serializable;

class UserFullName implements Serializable {
    private String name;
    private String surname;

    public UserFullName(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
@Converter
class UserFullNameConverter implements AttributeConverter<UserFullName, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(UserFullName attribute) {
        if (attribute == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting UserFullname to database column", e);
        }
    }

    @Override
    public UserFullName convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        try {
            return objectMapper.readValue(dbData, UserFullName.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting database column to UserFullname", e);
        }
    }
}
@Entity
@Data
public class AppUser {
    @Id
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany
    private List<ShoppingCart> carts;
    @Convert(converter = UserFullNameConverter.class)
    private UserFullName fullname;
    public AppUser(String username) {
        this.username = username;
    }

    public AppUser() {

    }
}
