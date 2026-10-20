package org.example.backend_praca_dyplomowa.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

}
