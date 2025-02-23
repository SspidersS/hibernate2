package org.example.dao;
import org.example.entities.Ticket;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TicketDaoImpl implements TicketDao {

    @Override
    public Ticket save(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(ticket);
            tx.commit();
            return ticket;
        } catch (Exception ex) {
            if (tx != null && tx.getStatus().canRollback()) {
                try {
                    tx.rollback();
                } catch (Exception rbEx) {
                    System.err.println("Rollback failed: " + rbEx.getMessage());
                }
            }
            throw ex;
        }
    }

    @Override
    public Ticket getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    @Override
    public List<Ticket> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Ticket", Ticket.class).list();
        }
    }

    @Override
    public void update(Ticket ticket) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(ticket);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.getStatus().canRollback()) {
                try {
                    tx.rollback();
                } catch (Exception rbEx) {
                    System.err.println("Rollback failed: " + rbEx.getMessage());
                }
            }
            throw ex;
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.remove(ticket);
            }
            tx.commit();
        } catch (Exception ex) {
            if (tx != null && tx.getStatus().canRollback()) {
                try {
                    tx.rollback();
                } catch (Exception rbEx) {
                    System.err.println("Rollback failed: " + rbEx.getMessage());
                }
            }
            throw ex;
        }
    }
}