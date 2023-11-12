package mk.finki.ukim.mk.lab.model;

import lombok.Data;

import java.util.Random;

@Data
public class Movie {
    String title;
    String summary;
    double rating;
    int ticketsBought;
    long id;
    Production production;
    public Movie(String title, String summary, double rating, int ticketsBought) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.ticketsBought = ticketsBought;
        Random random = new Random();
        this.id = random.nextLong();
    }
}
