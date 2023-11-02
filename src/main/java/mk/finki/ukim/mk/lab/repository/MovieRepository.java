package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    public List<Movie> findAll(){
        return DataHolder.movies;
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
        return DataHolder.movies
                .stream().max(Comparator.comparing(Movie::getTicketsBought)).get();
    }
}