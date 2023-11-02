package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketOrder {
    String movieTitle;
    String clientName;
    String clientAddress;
    Long numberOfTickets;
}
