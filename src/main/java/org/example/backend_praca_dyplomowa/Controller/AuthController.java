package org.example.backend_praca_dyplomowa.Controller;

import lombok.AllArgsConstructor;
import org.example.backend_praca_dyplomowa.DTO.JWTAuthResponse;
import org.example.backend_praca_dyplomowa.DTO.LoginDTO;
import org.example.backend_praca_dyplomowa.DTO.RegisterDTO;
import org.example.backend_praca_dyplomowa.DTO.Request_UserDTO;
import org.example.backend_praca_dyplomowa.Entity.User;
import org.example.backend_praca_dyplomowa.Services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
    //Ddodac obsługe błedów runtime aby wysyłąc odpowiednie API użytkownikowi
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    //To niedałąaa uzytkownik ma dostep
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUsers")
    public ResponseEntity<List<Request_UserDTO>> getUsers(){
        return ResponseEntity.ok(
                authService.getAllUsers()
        );
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/getUsers")
//    public ResponseEntity<List<Request_UserDTO>> getSpecificUsers(){
//        return ResponseEntity.ok(
//                authService.getAllUsers()
//        );
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUser/{id}")
    public ResponseEntity<Request_UserDTO> getUser(@PathVariable long id){
        return ResponseEntity.ok(
                authService.getUser(id)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/getUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){

        authService.deleteUser(id);

        return new ResponseEntity<>("Data deleted successfully", HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/getUser/updateRole/{id}")
    public ResponseEntity<String> updateUserRole(@PathVariable long id, @RequestParam String newRole) {
        String response = authService.updateUserRole(id, newRole);
        return new ResponseEntity<>(response, HttpStatus.OK);




    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/getUsers")
//    public ResponseEntity<List<Request_UserDTO>> getUsers(){
//        return ResponseEntity.ok(
//                authService.getAllUsers()
//        );
//    }

}