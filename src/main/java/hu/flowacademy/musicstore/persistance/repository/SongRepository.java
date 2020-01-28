package hu.flowacademy.musicstore.persistance.repository;

import hu.flowacademy.musicstore.persistance.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
