package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketOrder, Long> {
    public List<TicketOrder> findAll();
    public List<TicketOrder> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}
