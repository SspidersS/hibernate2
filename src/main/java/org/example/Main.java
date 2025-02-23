package org.example;

import org.example.entities.Client;
import org.example.entities.Planet;
import org.example.entities.Ticket;
import org.example.services.ClientCrudServiceImpl;
import org.example.services.PlanetCrudServiceImpl;
import org.example.services.TicketCrudService;

public class Main {
    public static void main(String[] args) {
        ClientCrudServiceImpl clientService = new ClientCrudServiceImpl();
        PlanetCrudServiceImpl planetService = new PlanetCrudServiceImpl();
        TicketCrudService ticketService = new TicketCrudService();

        Client client = new Client("John Doe");
        clientService.createClient(client);
        System.out.println("Client saved: " + client.getId());

        Planet planet = new Planet("MARS", "Mars");
        planetService.createPlanet(planet);
        System.out.println("Planet saved: " + planet.getId());

        Ticket ticket = new Ticket(client, planet, planet);
        ticketService.createTicket(ticket);
        System.out.println("Ticket saved: " + ticket.getId());

        Ticket fetchedTicket = ticketService.getTicketWithClient(ticket.getId());
        if (fetchedTicket != null) {
            System.out.println("Fetched Ticket for client: " + fetchedTicket.getClient().getName());

            Planet newFinalPlanet = new Planet("VENUS", "Venus");
            planetService.createPlanet(newFinalPlanet);
            fetchedTicket.setFinalPlanet(newFinalPlanet);
            ticketService.updateTicket(fetchedTicket);
            System.out.println("Ticket updated: final planet is now " + fetchedTicket.getFinalPlanet().getName());
        }

        ticketService.deleteTicket(ticket.getId());
        System.out.println("Ticket deleted");

        Client fetchedClient = clientService.getClientById(client.getId());
        if (fetchedClient != null) {
            System.out.println("Fetched Client: " + fetchedClient.getName());
            fetchedClient.setName("Jane Doe");
            clientService.updateClient(fetchedClient);
            System.out.println("Client updated: " + fetchedClient.getName());
        }
        clientService.deleteClient(client.getId());
        System.out.println("Client deleted");
    }
    }

