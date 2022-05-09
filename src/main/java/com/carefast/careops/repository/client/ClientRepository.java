package com.carefast.careops.repository.client;

import com.carefast.careops.model.client.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientModel, Integer> {
    @Query("SELECT c FROM ClientModel c " +
            "WHERE c.email = :email")
    Optional<ClientModel> findByEmail(String email);

    @Query("SELECT c FROM ClientModel c " +
            "WHERE c.clientId = :clientId " +
            "AND c.status = 'Y' ")
    Optional<ClientModel> getClientProfile(int clientId);

    @Query("SELECT c FROM ClientModel c " +
            "WHERE c.email = :email " +
            "AND c.status = 'Y' ")
    Optional<ClientModel> clientAuth(String email);
}
