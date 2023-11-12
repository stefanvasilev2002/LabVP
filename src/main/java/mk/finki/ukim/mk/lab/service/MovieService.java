package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text, String rating);
    Movie mostBoughtMovie();
    void save(String title, String summary, double rating, Long productions, Long id);

    Optional<Movie> findById(Long id);

    void deleteById(Long id);
}
