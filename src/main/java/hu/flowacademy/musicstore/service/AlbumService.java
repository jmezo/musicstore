package hu.flowacademy.musicstore.service;

import hu.flowacademy.musicstore.exception.ValidationException;
import hu.flowacademy.musicstore.persistance.model.DTO.AlbumDTO;
import hu.flowacademy.musicstore.persistance.repository.AlbumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;

    public List<AlbumDTO> getAll() {
        return albumRepository.findAll().stream().map(AlbumDTO::new).collect(Collectors.toList());
    }

    public AlbumDTO getOne(long id) {
        var album = albumRepository.findById(id).orElseThrow();
        return new AlbumDTO(album);
    }

    public AlbumDTO post(AlbumDTO dto) {
        validate(dto);
        var savedAlbum = albumRepository.save(dto.toAlbum());
        return new AlbumDTO(savedAlbum);
    }

    public void put(AlbumDTO dto) {
        validate(dto);
        albumRepository.save(dto.toAlbum());
    }

    public void delete(long id) {
        albumRepository.deleteById(id);
    }

    private void validate(AlbumDTO dto) {
        if(dto.getTitle() == null || "".equals(dto.getTitle())) {
            throw  new ValidationException("title is empty");
        }
        if(dto.getCount() == null || dto.getCount() < 1) {
            throw new ValidationException("count must be greater than 0");
        }
    }

}
