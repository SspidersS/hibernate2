package org.example.services;
import org.example.dao.ClientDao;
import org.example.dao.ClientDaoImpl;
import org.example.entities.Client;

import java.util.List;

public class ClientCrudServiceImpl implements ClientCrudService{
    private final ClientDao clientDao = new ClientDaoImpl();

    @Override
    public void createClient(Client client) {
        clientDao.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientDao.getById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.getAll();
    }

    @Override
    public void updateClient(Client client) {
        clientDao.update(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientDao.delete(id);
    }
}