package org.example.services;

import org.example.dao.TicketDao;
import org.example.dao.TicketDaoImpl;
import org.example.entities.Client;
import org.example.entities.Ticket;
import org.example.entities.Planet;
import org.example.exceptions.TicketValidationException;
import org.example.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;

public class TicketCrudService {
    private final TicketDao ticketDao = new TicketDaoImpl();

    public Ticket createTicket(Ticket ticket) {
        Client client = ticket.getClient();
        if (client == null || client.getId() == 0) {
            throw new TicketValidationException("Квиток повинен містити валідного клієнта.");
        }

        Planet initialPlanet = ticket.getInitialPlanet();
        if (initialPlanet == null || initialPlanet.getId() == null || initialPlanet.getId().isEmpty()) {
            throw new TicketValidationException("Квиток повинен містити валідну початкову планету.");
        }
        Planet finalPlanet = ticket.getFinalPlanet();
        if (finalPlanet == null || finalPlanet.getId() == null || finalPlanet.getId().isEmpty()) {
            throw new TicketValidationException("Квиток повинен містити валідну кінцеву планету.");
        }

        return ticketDao.save(ticket);
    }

    public Ticket getTicketById(Long id) {
        return ticketDao.getById(id);
    }

    public void updateTicket(Ticket ticket) {
        ticketDao.update(ticket);
    }

    public void deleteTicket(Long id) {
        ticketDao.delete(id);
    }

    public Ticket getTicketWithClient(Long ticketId) {
        Ticket ticket;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ticket = session.get(Ticket.class, ticketId);
            if (ticket != null) {
                Hibernate.initialize(ticket.getClient());
            }
        }
        return ticket;
    }

}