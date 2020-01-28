package hu.flowacademy.musicstore.service;

import hu.flowacademy.musicstore.exception.ValidationException;
import hu.flowacademy.musicstore.persistance.model.DTO.ArtistDTO;
import hu.flowacademy.musicstore.persistance.repository.ArtistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
;



public class ArtistServiceTest {

    @InjectMocks
    ArtistService artistService;


    @Mock
    ArtistRepository artistRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createArtist() {
        var dto = new ArtistDTO(1L, "jano", "");
        assertThrows(ValidationException.class, () -> artistService.post(dto));
        var dto2 = ArtistDTO.builder().lastname("mezo").build();
        assertThrows(ValidationException.class, () -> artistService.post(dto2));
    }
}
