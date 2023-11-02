package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {
    public TicketOrder addTicket(TicketOrder ticketOrder){
        DataHolder.tickets.add(ticketOrder);
        Movie movie = DataHolder.movies
                .stream().filter(x->x.getTitle().equals(ticketOrder.getMovieTitle()))
                .findFirst().get();
        movie.setTicketsBought((int) (movie.getTicketsBought() + ticketOrder.getNumberOfTickets()));
        return ticketOrder;
    }

}
