package org.example.backend_praca_dyplomowa.Repositories;

import org.example.backend_praca_dyplomowa.Entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
