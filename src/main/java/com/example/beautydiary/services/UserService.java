package com.example.beautydiary.services;

import com.example.beautydiary.entities.Beautician;
import com.example.beautydiary.entities.User;
import com.example.beautydiary.repositories.BeauticianRepository;
import com.example.beautydiary.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private BeauticianRepository beauticianRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public UserService(BeauticianRepository beauticianRepository,
                       CustomerRepository customerRepository) {
        this.beauticianRepository = beauticianRepository;
        this.customerRepository = customerRepository;
    }

    public void createUser(User user) throws Exception {
        if (user.getUserType().equals("beautician")) {
        beauticianRepository.save(user.getBeautician());
        } else if (user.getUserType().equals("customer"))
          customerRepository.save(user.getCustomer()) ;
    }

    public User verifyUser(User user) throws Exception {
        User foundUser = beauticianRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if (foundUser == null) {
            foundUser = customerRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        }
        if (foundUser == null) {
            throw new Exception("Username or password incorrect");
        }return foundUser;
    }

    public void updateBeautician(Beautician beautician) {
        beauticianRepository.save(beautician);
    }
}
