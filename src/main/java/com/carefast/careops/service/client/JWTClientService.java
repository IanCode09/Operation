package com.carefast.careops.service.client;

import com.carefast.careops.model.client.ClientModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.client.ClientRepository;
import com.carefast.careops.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JWTClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private ValidationUtils validationUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (validationUtils.emailValidation(username)) {
            Optional<ClientModel> clientModel = clientRepository.findByEmail(username);

            if (clientModel.isPresent()) {
                return new User(clientModel.get().getClientId()+"-"+clientModel.get().getEmail(), clientModel.get().getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } else {
            throw new UsernameNotFoundException("Email Salah");
        }
    }
}
