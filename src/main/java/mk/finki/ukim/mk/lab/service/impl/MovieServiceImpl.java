package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.repository.TicketRepository;
import org.springframework.stereotype.Service;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.service.MovieService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(MovieRepository movieRepository, TicketRepository ticketRepository, ProductionRepository productionRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.productionRepository = productionRepository;
        movieRepository.deleteAll();
        ticketRepository.deleteAll();
        productionRepository.deleteAll();
        List<Movie> temp = new ArrayList<>();
        temp.add(new Movie("Movie1", "Desc1", 3, 0));
        temp.add(new Movie("Movie2", "Desc2", 2.4, 0));
        temp.add(new Movie("Movie3", "Desc3", 5, 0));
        temp.add(new Movie("Movie4", "Desc4", 3.4, 0));
        temp.add(new Movie("Movie5", "Desc5", 4.4, 0));
        temp.add(new Movie("Movie6", "Desc6", 2.3, 0));
        temp.add(new Movie("Movie7", "Desc7", 4.2, 0));
        temp.add(new Movie("Movie8", "Desc8", 1.4, 0));
        temp.add(new Movie("Movie9", "Desc9", 3.1, 0));
        temp.add(new Movie("Movie10", "Desc10", 4.4, 0));

        productionRepository.save(new Production("Prod1", "Macedonia", "Boris Trajkovski 18"));
        productionRepository.save(new Production("Prod2", "Russia", "Boris Trajkovski 19"));
        productionRepository.save(new Production("Prod3", "Spain", "Boris Trajkovski 20"));
        productionRepository.save(new Production("Prod4", "UK", "Boris Trajkovski 21"));
        productionRepository.save(new Production("Prod5", "USA", "Boris Trajkovski 22"));

        Random random = new Random();
        for (Movie m : temp){
            m.setProduction(productionRepository.findAll().get(random.nextInt(0, productionRepository.findAll().size())));
            movieRepository.save(m);
        }
        movieRepository.flush();

    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll().stream().sorted(Comparator.comparing(Movie::getTitle)).collect(Collectors.toList());
    }

    @Override
    public List<Movie> searchMovies(String text, String rating) {
        return movieRepository.findMoviesByTitleContainingAndRatingGreaterThanEqual(
                text, Double.parseDouble(rating));
    }

    @Override
    public Movie mostBoughtMovie() {
        return movieRepository.findAll().get(0);
    }

    @Override
    public void save(String title, String summary, double rating, Long productions, Long id) {
        Movie movie = new Movie(title, summary, rating, movieRepository.findMovieById(id).getTicketsBought());
        movie.setProduction(productionRepository.findProductionById(productions));
        movieRepository.save(movie);
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findAll().stream().filter(x->x.getId() == id).findFirst();
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public void editTicketRating() {
        for(TicketOrder order : ticketRepository.findAll()){
            ticketRepository.delete(order);
            order.setRating((float) findById(order.getMovieId()).get().getRating());
            ticketRepository.save(order);
        }
    }

    @Override
    public void saveNewMovie(String title, String summary, double rating, Long productions, Long id) {
        if (movieRepository.findMovieById(id) != null){
            Movie movie = movieRepository.findMovieById(id);
            movieRepository.delete(movie);
            movie.setTitle(title);
            movie.setRating(rating);
            movie.setSummary(summary);
            movieRepository.save(movie);
            return;
        }
        Movie movie = new Movie(title, summary, rating, 0);
        movie.setProduction(productionRepository.findProductionById(productions));
        movieRepository.save(movie);
    }
}