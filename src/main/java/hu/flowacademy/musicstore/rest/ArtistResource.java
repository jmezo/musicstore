package hu.flowacademy.musicstore.rest;

import hu.flowacademy.musicstore.persistance.model.DTO.ArtistDTO;
import hu.flowacademy.musicstore.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@AllArgsConstructor
public class ArtistResource {
    private final ArtistService artistService;

    @GetMapping
    public List<ArtistDTO> getAll() {
        return artistService.getAll();
    }

    @GetMapping("{id}")
    public ArtistDTO getOne(@PathVariable long id) {
        return artistService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<ArtistDTO> post(@RequestBody ArtistDTO dto) {
        return new ResponseEntity<>(
                artistService.post(dto),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public void put(@RequestBody ArtistDTO dto) {
        artistService.put(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        artistService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
