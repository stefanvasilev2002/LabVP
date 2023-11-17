package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String submitOrder(@RequestParam(required = false) Long movieId,
                              @RequestParam int numTickets,
                              Model model){
        if (movieId == null){
            return "redirect:/movies";
        }
        TicketOrder ticket = ticketOrderService.placeOrder(movieService.findById(movieId).get().getTitle(),
                "Stefan Vasilev",
                "0:0:0:1",
                numTickets,
                movieId,
                (float) movieService.findById(movieId).get().getRating());

        model.addAttribute("ticket", ticket);
        model.addAttribute("orders", ticketOrderService.orders());

        return "orderConfirmation";
    }
}
