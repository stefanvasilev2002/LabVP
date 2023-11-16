package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.TicketOrder;
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

    public TicketOrderController(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @PostMapping
    public String submitOrder(@RequestParam String movieName,
                              @RequestParam int numTickets,
                              Model model){

        TicketOrder ticket = ticketOrderService.placeOrder(movieName, "Stefan Vasilev", "0:0:0:1", numTickets);

        model.addAttribute("ticket", ticket);

        return "orderConfirmation";
    }
}
