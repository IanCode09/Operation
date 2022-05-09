package com.carefast.careops.service.employee;

import com.carefast.careops.model.INSYS.InsysEmployeeModel;
import com.carefast.careops.repository.INSYS.InsysEmployeeRepository;
import com.carefast.careops.repository.employee.EmployeeRepository;
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
public class JWTEmployeeService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private InsysEmployeeRepository insysEmployeeRepository;
    @Autowired
    private ValidationUtils validationUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (validationUtils.emailValidation(username)) {
            Optional<InsysEmployeeModel> insysEmployeeModel = insysEmployeeRepository.findByEmail(username);

            if (insysEmployeeModel.isPresent()) {
                return new User(insysEmployeeModel.get().getIdEmployee()+"-"+insysEmployeeModel.get().getEmployeeEmail(), insysEmployeeModel.get().getEmployeePassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        } else {
            throw new UsernameNotFoundException("Email Salah");
        }
    }
}
