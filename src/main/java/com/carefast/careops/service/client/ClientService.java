package com.carefast.careops.service.client;

import com.carefast.careops.dto.client.ClientDTO;
import com.carefast.careops.model.client.ClientModel;
import com.carefast.careops.repository.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Optional<ClientModel> findById(int id) {
        Optional<ClientModel> userExists = clientRepository.findById(id);

        return userExists;
    }

    public ClientDTO getClientProfile(int clientId) {
        Optional<ClientModel> client = clientRepository.getClientProfile(clientId);

        if (client.isPresent()) {
            return convertClientDTO(client.get());
        } else {

            return null;
        }
    }

    public ClientDTO convertClientDTO(ClientModel item)  {
        return new ClientDTO(
                item.getClientId(),
                item.getClientName(),
                item.getProjectCode(),
                item.getEmail(),
                item.getStatus()
        );
    }
}
