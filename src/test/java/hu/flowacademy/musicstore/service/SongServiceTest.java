package hu.flowacademy.musicstore.service;

import hu.flowacademy.musicstore.exception.ValidationException;
import hu.flowacademy.musicstore.persistance.model.Artist;
import hu.flowacademy.musicstore.persistance.model.DTO.SongDTO;
import hu.flowacademy.musicstore.persistance.model.Genre;
import hu.flowacademy.musicstore.persistance.repository.AlbumRepository;
import hu.flowacademy.musicstore.persistance.repository.ArtistRepository;
import hu.flowacademy.musicstore.persistance.repository.SongRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class SongServiceTest {

    @InjectMocks
    SongService songService;

    @Mock
    SongRepository songRepository;
    @Mock
    ArtistRepository artistRepository;
    @Mock
    AlbumRepository albumRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createSong() {
        var dto = new SongDTO(1L, "title", 2L, "lyrics", LocalDate.now(), "writer", Genre.ROCK, 1L, 1L);

//        when(artistRepository.findById(1l)).thenReturn(Optional<Artist>(new Artist(1L, "firstname", "lastname")));

        dto.setTitle(null);
        Assertions.assertThrows(ValidationException.class, () -> songService.post(dto));
    }
}
