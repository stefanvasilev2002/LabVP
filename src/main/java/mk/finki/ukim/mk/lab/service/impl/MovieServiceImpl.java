package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Service;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.service.MovieService;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text, String rating) {
        return movieRepository.searchMovies(text, rating);
    }

    @Override
    public Movie mostBoughtMovie() {
        return movieRepository.mostBoughtMovie();
    }

    @Override
    public void save(String title, String summary, double rating, Long productions, Long id) {
        movieRepository.addMovie(title, summary, rating, productions, id);
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
        movieRepository.editTicketRatings();
    }

}
