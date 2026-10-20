package org.example.backend_praca_dyplomowa.Services;

import lombok.AllArgsConstructor;
import org.example.backend_praca_dyplomowa.Component.JwtTokenProvider;
import org.example.backend_praca_dyplomowa.DTO.LoginDTO;
import org.example.backend_praca_dyplomowa.DTO.RegisterDTO;
import org.example.backend_praca_dyplomowa.DTO.Request_UserDTO;
import org.example.backend_praca_dyplomowa.Entity.Role;
import org.example.backend_praca_dyplomowa.Entity.User;
import org.example.backend_praca_dyplomowa.Repositories.RoleRepository;
import org.example.backend_praca_dyplomowa.Repositories.UserRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginDTO loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        // Implement registration logic here

        if (registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            User user = new User();
            user.setUsername(registerDTO.getUsername());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("User Role not set."));
            user.getRoles().add(userRole);
            userRepository.save(user);
            return "User registered successfully";
        } else {
            throw new IllegalArgumentException("Passwords do not match");

        }
    }

    @Override
    public List<Request_UserDTO> getAllUsers() {
        List<User> users =  userRepository.findAll();

        List<Request_UserDTO> user_request = new ArrayList<>();


        ///UZYWAC STREAM API
        for(User user: users){
            Request_UserDTO userDTO = new Request_UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setAccoutCreationDate(user.getAccount_creationDate());
            userDTO.setEmail(user.getEmail());

            user_request.add(userDTO);
        }

        return user_request;
    }

    @Override
    public Request_UserDTO getUser(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));

        Request_UserDTO requestUserDTO = new Request_UserDTO();

        requestUserDTO.setId(user.getId());
        requestUserDTO.setUsername(user.getName());
        requestUserDTO.setEmail(user.getEmail());
        requestUserDTO.setAccoutCreationDate(user.getAccount_creationDate());

        return requestUserDTO;
    }

    @Override
    public void deleteUser(long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));

        userRepository.delete(user);

    }


    public String updateUserRole(long id, String new_role){

        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User with id " + id + " not found"));

        Role role = roleRepository.findByName(new_role).orElseThrow(() -> new RuntimeException("Role not found"));

        Set<Role> roles = user.getRoles();

        roles.add(role);

        user.setRoles(roles);

        userRepository.save(user);

        return "User role updated successfully";

    }


    }




