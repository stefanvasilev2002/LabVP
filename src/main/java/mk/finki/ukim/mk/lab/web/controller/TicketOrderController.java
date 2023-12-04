package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.AppUser;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    public TicketOrderController(TicketOrderService ticketOrderService, MovieService movieService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
    }

    @PostMapping
    public String submitOrder(@RequestParam Long movieId,
                              @RequestParam int numTickets,
                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateCreated,
                              Model model){
        if (movieId == null){
            return "redirect:/movies";
        }
        TicketOrder ticket = ticketOrderService.placeOrder(
                movieService.findById(movieId).get().getTitle(),
                numTickets,
                movieId,
                (float) movieService.findById(movieId).get().getRating(),
                dateCreated);

        model.addAttribute("ticket", ticket);
        model.addAttribute("orders", ticketOrderService.orders());
        model.addAttribute("user", new AppUser("stefanvasilev"));

        return "orderConfirmation";
    }
}
