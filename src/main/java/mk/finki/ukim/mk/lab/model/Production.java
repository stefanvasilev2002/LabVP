package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;

@Data
@Entity
public class Production {
    @Id
    private Long id;
    private String name;
    private String country;
    private String address;

    public Production(String name, String country, String address) {
        this.name = name;
        this.country = country;
        this.address = address;
        Random random = new Random();
        this.id = random.nextLong();
    }

    public Production() {

    }
}
