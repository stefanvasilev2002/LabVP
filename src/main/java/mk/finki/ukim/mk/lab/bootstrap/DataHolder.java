package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movies = null;
    public static List<TicketOrder> tickets = null;

    @PostConstruct
    public void init() {
        movies = new ArrayList<>();
        tickets = new ArrayList<>();
        movies.add(new Movie("Movie1", "Desc1", 3, 0));
        movies.add(new Movie("Movie2", "Desc2", 2.4, 0));
        movies.add(new Movie("Movie3", "Desc3", 5, 0));
        movies.add(new Movie("Movie4", "Desc4", 3.4, 0));
        movies.add(new Movie("Movie5", "Desc5", 4.4, 0));
        movies.add(new Movie("Movie6", "Desc6", 2.3, 0));
        movies.add(new Movie("Movie7", "Desc7", 4.2, 0));
        movies.add(new Movie("Movie8", "Desc8", 1.4, 0));
        movies.add(new Movie("Movie9", "Desc9", 3.1, 0));
        movies.add(new Movie("Movie10", "Desc10", 4.4, 0));

    }
}
