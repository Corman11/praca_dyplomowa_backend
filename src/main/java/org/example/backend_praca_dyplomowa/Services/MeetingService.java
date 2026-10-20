package org.example.backend_praca_dyplomowa.Services;


import lombok.AllArgsConstructor;
import org.example.backend_praca_dyplomowa.DTO.Request_User_meetingDTO;
import org.example.backend_praca_dyplomowa.Entity.Meeting;
import org.example.backend_praca_dyplomowa.Entity.User;
import org.example.backend_praca_dyplomowa.Repositories.MeetingRepository;
import org.example.backend_praca_dyplomowa.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MeetingService {



    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;

    public List<Request_User_meetingDTO> getUsers(){
       List<User> all_users = userRepository.findAll();

       return all_users.stream()
               .map(user -> new Request_User_meetingDTO(user.getName(), user.getEmail())
               ).toList();
    }

    public void createMeeting(List<User> invitedUsers, String meetingName) {
        // Create a new Meeting object
        Meeting meeting = new Meeting();
        meeting.setName(meetingName);
        meeting.setParticipants(invitedUsers);

        // Save the meeting to the database
        meetingRepository.save(meeting);


    }


}
