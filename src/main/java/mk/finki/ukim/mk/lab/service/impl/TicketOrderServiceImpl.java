package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.TicketRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {
    private final TicketRepository ticketRepository;
    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets, Long movieId, float rating) {
        return ticketRepository.addTicket(new TicketOrder(movieTitle, clientName, address, (long) numberOfTickets, movieId, rating));
    }


    @Override
    public List<TicketOrder> orders() {
        return ticketRepository.getOrders();
    }
}
