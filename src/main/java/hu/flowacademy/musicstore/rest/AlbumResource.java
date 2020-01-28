package hu.flowacademy.musicstore.rest;

import hu.flowacademy.musicstore.persistance.model.DTO.AlbumDTO;
import hu.flowacademy.musicstore.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@AllArgsConstructor
public class AlbumResource {
    private final AlbumService albumService;

    @GetMapping
    public List<AlbumDTO> getAll() {
        return albumService.getAll();
    }

    @GetMapping("{id}")
    public AlbumDTO getOne(@PathVariable long id) {
        return albumService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> post(@RequestBody AlbumDTO dto) {
        return new ResponseEntity<>(
                albumService.post(dto),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public void put(@RequestBody AlbumDTO dto) {
        albumService.put(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
