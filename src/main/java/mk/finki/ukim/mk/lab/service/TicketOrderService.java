package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface TicketOrderService{
    TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets, Long movieId, float rating);
    List<TicketOrder> orders ();
}
