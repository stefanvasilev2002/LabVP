package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.apache.catalina.LifecycleState;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderService{
    TicketOrder placeOrder(String clientName, int numberOfTickets, Long movieId, float rating, LocalDateTime created);
    List<TicketOrder> orders ();
}
