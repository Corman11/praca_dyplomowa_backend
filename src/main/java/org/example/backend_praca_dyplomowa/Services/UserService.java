package org.example.backend_praca_dyplomowa.Services;


import org.example.backend_praca_dyplomowa.Entity.User;
import org.example.backend_praca_dyplomowa.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    public User registerUser(){
//
//    }
}
