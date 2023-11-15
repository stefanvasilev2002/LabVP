package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/filter")
public class FilterController {
    private final MovieService movieService;
    public FilterController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping
    String filterMovies(Model model){
        Movie mostBoughtMovie =  movieService.mostBoughtMovie();
        model.addAttribute("mostBoughtMovie", mostBoughtMovie);
        model.addAttribute("movies",
                movieService.searchMovies((String) model.getAttribute("textFilter"),
                        (String) model.getAttribute("ratingFilter")));
        return "listMovies";
    }
}
