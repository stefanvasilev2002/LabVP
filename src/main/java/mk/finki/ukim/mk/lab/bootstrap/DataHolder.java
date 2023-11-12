package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataHolder {
    public static List<Movie> movies = null;
    public static List<TicketOrder> tickets = null;
    public static List<Production> productions = null;

    @PostConstruct
    public void init() {
        movies = new ArrayList<>();
        tickets = new ArrayList<>();
        productions = new ArrayList<>();

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

        Random random = new Random();

        productions.add(new Production("Prod1", "Macedonia", "Boris Trajkovski 18"));
        productions.add(new Production("Prod2", "Russia", "Boris Trajkovski 19"));
        productions.add(new Production("Prod3", "Spain", "Boris Trajkovski 20"));
        productions.add(new Production("Prod4", "UK", "Boris Trajkovski 21"));
        productions.add(new Production("Prod5", "USA", "Boris Trajkovski 22"));

        for (Movie m : movies){
            m.setProduction(productions.get(random.nextInt(0, productions.size())));
        }
    }
}
