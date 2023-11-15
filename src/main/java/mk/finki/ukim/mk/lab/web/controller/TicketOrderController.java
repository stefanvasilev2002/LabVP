package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    @PostMapping
    String getOrderInfo(Model model){
        TicketOrder ticket = (TicketOrder) model.getAttribute("ticket");

        model.addAttribute("ticket", ticket);

        return "orderConfirmation";
    }
}
