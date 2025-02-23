package org.example.dao;

import org.example.entities.Ticket;
import java.util.List;

public interface TicketDao {
    Ticket save(Ticket ticket);

    Ticket getById(Long id);

    List<Ticket> getAll();

    void update(Ticket ticket);

    void delete(Long id);
}
