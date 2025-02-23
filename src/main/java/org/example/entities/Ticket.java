package org.example.entities;
import jakarta.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "initial_planet_id", nullable = false)
    private Planet initialPlanet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "final_planet_id", nullable = false)
    private Planet finalPlanet;

    public Ticket() {
    }

    public Ticket(Client client, Planet initialPlanet, Planet finalPlanet) {
        this.client = client;
        this.initialPlanet = initialPlanet;
        this.finalPlanet = finalPlanet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Planet getInitialPlanet() {
        return initialPlanet;
    }

    public void setInitialPlanet(Planet initialPlanet) {
        this.initialPlanet = initialPlanet;
    }

    public Planet getFinalPlanet() {
        return finalPlanet;
    }

    public void setFinalPlanet(Planet finalPlanet) {
        this.finalPlanet = finalPlanet;
    }
}
