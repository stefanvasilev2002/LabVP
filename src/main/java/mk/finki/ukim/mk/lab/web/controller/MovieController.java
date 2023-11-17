package mk.finki.ukim.mk.lab.web.controller;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.ProductionService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final ProductionService productionService;
    private final TicketOrderService ticketOrderService;

    public MovieController(MovieService movieService, ProductionService productionService, TicketOrderService ticketOrderService) {
        this.movieService = movieService;
        this.productionService = productionService;
        this.ticketOrderService = ticketOrderService;
    }

    @GetMapping
    public String getMoviesPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("movies", movieService.listAll());
        Movie mostBoughtMovie =  movieService.mostBoughtMovie();
        model.addAttribute("mostBoughtMovie", mostBoughtMovie);
        return "listMovies";
    }

    @PostMapping("/add")
    public String saveMovie(
        @RequestParam String title,
        @RequestParam String summary,
        @RequestParam double rating,
        @RequestParam Long productions,
        @RequestParam Long id) {

            this.movieService.save(title, summary, rating, productions, id);
            return "redirect:/movies";
    }
    @GetMapping("/add-form")
    public String addMoviePage(Model model) {
        List<Production> productions = this.productionService.findAll();
        model.addAttribute("productions", productions);
        return "add-movie";
    }
    @GetMapping("/edit/{id}")
    public String editMoviePage(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id).isPresent()) {
            Movie movie = this.movieService.findById(id).get();
            List<Production> productions = this.productionService.findAll();
            model.addAttribute("productions", productions);
            model.addAttribute("movie", movie);
            model.addAttribute("id", id);
            return "add-movie";
        }
        return "redirect:/movies?error=MovieNotFound";
    }
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }
    @PostMapping("/filter")
    public String filterMovies(@RequestParam String textFilter,
                               @RequestParam double ratingFilter,
                               Model model){
        Movie mostBoughtMovie =  movieService.mostBoughtMovie();
        model.addAttribute("mostBoughtMovie", mostBoughtMovie);
        model.addAttribute("movies",
                movieService.searchMovies(textFilter, String.valueOf(ratingFilter)));
        return "listMovies";
    }
    @PostMapping("/editRating/{id}")
    public String editRating(@PathVariable Long id, @RequestParam double newRating, Model model){
        Movie movie = movieService.findById(id).get();
        movie.setRating((movie.getRating() + newRating) / 2);
        movieService.editTicketRating();
        Movie mostBoughtMovie =  movieService.mostBoughtMovie();
        model.addAttribute("mostBoughtMovie", mostBoughtMovie);
        model.addAttribute("movies",
                movieService.searchMovies("", ""));

        return "listMovies";
    }
}
