package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    public List<Movie> findAll(){
        return DataHolder.movies.isEmpty() ? new ArrayList<>() : DataHolder.movies;
    }
    public List<Movie> searchMovies(String text, String rating){
        if (Objects.equals(text, "") && Objects.equals(rating, "")){
            return DataHolder.movies;
        }
        else if (Objects.equals(text, "")){
            return DataHolder.movies
                    .stream()
                    .filter(x->x.getRating() >= Float.parseFloat(rating))
                    .collect(Collectors.toList());
        }
        else if (Objects.equals(rating, "")){
            return DataHolder.movies
                    .stream()
                    .filter(x->x.getTitle().contains(text)
                            || x.getSummary().contains(text))
                    .collect(Collectors.toList());
        }
        else {
            return DataHolder.movies
                    .stream()
                    .filter(x->(x.getTitle().contains(text)
                            || x.getSummary().contains(text)) &&
                            x.getRating() >= Float.parseFloat(rating))
                    .collect(Collectors.toList());
        }
    }
    public Movie mostBoughtMovie(){
        Movie movie = !DataHolder.movies.isEmpty() ? DataHolder.movies
                .stream().max(Comparator.comparing(Movie::getTicketsBought)).get() : new Movie("","",0, 0);
        if (movie.getTicketsBought() == 0){
            return new Movie("No movies bought", "", 0, 0);
        }
        return movie;
    }

    public void addMovie(String title, String summary, double rating, Long productions, Long id) {
        if (DataHolder.movies.stream().anyMatch(x->x.getId() == id)){
            Movie movie = DataHolder.movies.stream().filter(x->x.getId() == id).findFirst().get();
            movie.setTitle(title);
            movie.setRating(rating);
            movie.setSummary(summary);
        }
        else {
            Movie movie = new Movie(title, summary, rating, 0);
            movie.setProduction(DataHolder.productions.stream().filter(x-> Objects.equals(x.getId(), productions)).findFirst().get());
            DataHolder.movies.add(movie);
        }
    }

    public void deleteById(Long id) {
        DataHolder.movies.removeIf(x->x.getId() == id);
    }
}
