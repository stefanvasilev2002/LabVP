package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    public List<Movie> findAll();
    public List<Movie> findMoviesByTitleContainingAndRatingGreaterThanEqual(String text, double rating);
    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.ticketsBought t GROUP BY m.id ORDER BY COUNT(t) DESC")
    public Movie findMovieWithMostTickets();
    public Movie findMovieByRatingGreaterThan(double rating);
    public void deleteById(Long id);
    public Optional<Movie> findById(Long id);

    Movie findMovieById(Long id);
}
