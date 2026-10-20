package org.example.backend_praca_dyplomowa.Services;

import org.example.backend_praca_dyplomowa.DTO.LoginDTO;
import org.example.backend_praca_dyplomowa.DTO.RegisterDTO;
import org.example.backend_praca_dyplomowa.DTO.Request_UserDTO;
import org.example.backend_praca_dyplomowa.Entity.User;

import java.util.List;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
    List<Request_UserDTO> getAllUsers();
    Request_UserDTO getUser(long id);
    void deleteUser(long id);

    String updateUserRole(long id, String new_role);
}
