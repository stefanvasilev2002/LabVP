package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Service;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.service.MovieService;

import java.util.List;

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

}
