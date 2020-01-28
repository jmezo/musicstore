package hu.flowacademy.musicstore.rest;

import hu.flowacademy.musicstore.persistance.model.DTO.SongDTO;
import hu.flowacademy.musicstore.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
@AllArgsConstructor
public class SongResource {
    private final SongService songService;

    @GetMapping
    public List<SongDTO> getAll() {
        return songService.getAll();
    }

    @GetMapping("{id}")
    public SongDTO getOne(@PathVariable long id) {
        return songService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<SongDTO> post(@RequestBody SongDTO dto) {
        return new ResponseEntity<>(
                songService.post(dto),
                HttpStatus.CREATED
        );
    }

    @PutMapping
    public void put(@RequestBody SongDTO dto) {
        songService.put(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        songService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
