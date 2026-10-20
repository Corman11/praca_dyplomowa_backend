package org.example.backend_praca_dyplomowa.Repositories;

import org.example.backend_praca_dyplomowa.Entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
}
