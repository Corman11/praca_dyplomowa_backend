package org.example.backend_praca_dyplomowa.Controller;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.backend_praca_dyplomowa.DTO.Request_User_meetingDTO;
import org.example.backend_praca_dyplomowa.Entity.User;
import org.example.backend_praca_dyplomowa.Repositories.MeetingRepository;
import org.example.backend_praca_dyplomowa.Services.MeetingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/meeting")
@RequiredArgsConstructor
@RestController
public class MeetingController {

    private final MeetingRepository meetingRepository;
    private final MeetingService meetingService;

    @GetMapping("/getUsers")
    public ResponseEntity<List<Request_User_meetingDTO>> getUsers(){

        return ResponseEntity.ok(meetingService.getUsers());


    }

    @PostMapping("/createMeeting")
    public ResponseEntity<String> createMeeting(List<Request_User_meetingDTO> invitedUsers, String meetingName) {
        // Convert Request_User_meetingDTO to User entities
        List<User> users = invitedUsers.stream()
                .map(dto -> new User(dto.getName(), dto.getEmail()))
                .toList();

        // Call the service method to create the meeting
        meetingService.createMeeting(users, meetingName);

        return ResponseEntity.ok("Meeting created successfully");
    }

}
