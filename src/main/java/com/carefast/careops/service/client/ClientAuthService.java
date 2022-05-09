package com.carefast.careops.service.client;

import com.carefast.careops.dto.client.AuthModelClientResponse;
import com.carefast.careops.dto.client.request.ClientLoginRequestDTO;
import com.carefast.careops.model.client.ClientModel;
import com.carefast.careops.repository.client.ClientRepository;
import com.carefast.careops.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientAuthService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthModelClientResponse clientAuth(ClientLoginRequestDTO clientLoginRequestDTO) {
        Optional<ClientModel> userExists = clientRepository.clientAuth(clientLoginRequestDTO.getEmail());
        if (userExists.isPresent()) {
            boolean passwordMatch = bCryptPasswordEncoder.matches(clientLoginRequestDTO.getPassword(), userExists.get().getPassword());

            if (userExists.isPresent() & passwordMatch) {

                String token = TokenUtils.generateToken(userExists.get().getEmail());
                String refreshToken = TokenUtils.generateRefreshToken();

                AuthModelClientResponse authModelClientResponse = new AuthModelClientResponse();
                authModelClientResponse.setClientId(userExists.get().getClientId());
                authModelClientResponse.setClientName(userExists.get().getClientName());
                authModelClientResponse.setProjectCode(userExists.get().getProjectCode());
                authModelClientResponse.setEmail(userExists.get().getEmail());
                authModelClientResponse.setStatus(userExists.get().getStatus());
                authModelClientResponse.setToken(token);
                authModelClientResponse.setRefreshToken(refreshToken);

                return authModelClientResponse;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
