package hu.flowacademy.musicstore.persistance.repository;

import hu.flowacademy.musicstore.persistance.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
