package hu.flowacademy.musicstore.service;

import hu.flowacademy.musicstore.exception.ValidationException;
import hu.flowacademy.musicstore.persistance.model.DTO.AlbumDTO;
import hu.flowacademy.musicstore.persistance.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlbumServiceTest {

    @InjectMocks
    AlbumService albumService;


    @Mock
    AlbumRepository albumRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createAlbum() {
        var dto = AlbumDTO.builder().title("asd").count(0L).build();
        assertThrows(ValidationException.class, () -> albumService.post(dto));
        var dto2 = AlbumDTO.builder().title("").count(2L).build();
        assertThrows(ValidationException.class, () -> albumService.post(dto2));
    }
}
