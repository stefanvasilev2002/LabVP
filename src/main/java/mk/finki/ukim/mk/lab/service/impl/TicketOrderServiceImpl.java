package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.TicketRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {
    private final TicketRepository ticketRepository;
    private final MovieRepository movieRepository;
    @Override
    public TicketOrder placeOrder(String clientName, int numberOfTickets, Long movieId, float rating, LocalDateTime created) {
        TicketOrder ticketOrder = new TicketOrder(movieRepository.findMovieById(movieId).getTitle(), numberOfTickets, movieId, rating, created);
        ticketRepository.save(ticketOrder);
        Movie movie = movieRepository.findMovieById(movieId);
        movieRepository.deleteById(movieId);
        movie.setTicketsBought((int) (movie.getTicketsBought() + ticketOrder.getNumberOfTickets()));
        movieRepository.save(movie);
        return ticketOrder;
    }


    @Override
    public List<TicketOrder> orders() {
        return ticketRepository.findAll();
    }
}
