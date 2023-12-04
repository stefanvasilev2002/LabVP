package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Random;

@Data
@Entity
public class Movie {
    @Id
    long id;
    String title;
    String summary;
    double rating;
    int ticketsBought;
    @ManyToOne
    Production production;
    public Movie(String title, String summary, double rating, int ticketsBought) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.ticketsBought = ticketsBought;
        Random random = new Random();
        this.id = random.nextLong();
    }

    public Movie() {

    }

}
