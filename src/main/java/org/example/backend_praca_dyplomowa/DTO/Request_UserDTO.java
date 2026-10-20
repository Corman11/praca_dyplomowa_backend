package org.example.backend_praca_dyplomowa.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Request_UserDTO {
    Long id;
    String username;
    String email;
    LocalDateTime accoutCreationDate;
}
