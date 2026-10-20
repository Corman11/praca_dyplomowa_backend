package org.example.backend_praca_dyplomowa.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.backend_praca_dyplomowa.Component.FilterJWTAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/api/user")
@Controller
public class UserController {

    FilterJWTAuthentication filterJWTAuthentication;


    @GetMapping("/me")
    public ResponseEntity<?> login(HttpServletRequest request) {
        // Get JWT token from HTTP request

        String userId = (String) request.getAttribute("userId");
        String email = (String) request.getAttribute("email");
        String role = (String) request.getAttribute("role");



        if (userId == null) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Użytkownik niezalogowany"));
        }

        return ResponseEntity.ok(Map.of(
                "userId", userId,
                "email", email,
                "role", role
        ));
    }

    @PostMapping("/meeting_create")
    public String create_meeting() {
        return "login";
    }


    //Tymczasowe
//    do implementacji nalezy dac obsługe map
    @PostMapping("/track_create")
    public String create_track() {
        return "login";
    }



}


