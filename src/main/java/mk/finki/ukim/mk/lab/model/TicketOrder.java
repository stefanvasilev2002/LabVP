package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Random;

@Data
@Entity
public class TicketOrder {
    @Id
    private Long id;
    String movieTitle;
    Long numberOfTickets;
    Long movieId;
    float rating;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;

    public TicketOrder() {

    }

    public TicketOrder(String title, long numberOfTickets, Long movieId, float rating, LocalDateTime created) {
        this.movieTitle = title;
        this.numberOfTickets = numberOfTickets;
        this.movieId = movieId;
        this.rating = rating;
        this.dateCreated = created;
        Random random = new Random();
        this.id = random.nextLong() + random.nextInt();
    }
}
