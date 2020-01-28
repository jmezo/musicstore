package hu.flowacademy.musicstore.service;

import hu.flowacademy.musicstore.exception.ValidationException;
import hu.flowacademy.musicstore.persistance.model.Artist;
import hu.flowacademy.musicstore.persistance.model.DTO.ArtistDTO;
import hu.flowacademy.musicstore.persistance.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public List<ArtistDTO> getAll() {
        return artistRepository.findAll().stream().map(ArtistDTO::new).collect(Collectors.toList());
    }

    public ArtistDTO getOne(long id) {
        var artist = artistRepository.findById(id).orElseThrow();
        return new ArtistDTO(artist);
    }

    public ArtistDTO post(ArtistDTO dto) {
        validate(dto);
        var savedArtist = artistRepository.save(dto.toArtist());
        return new ArtistDTO(savedArtist);
    }

    public void put(ArtistDTO dto) {
        validate(dto);
        artistRepository.save(dto.toArtist());
    }

    public void delete(long id) {
        artistRepository.deleteById(id);
    }

    private void validate(ArtistDTO dto) {
        if(dto.getFirstname() == null || "".equals(dto.getFirstname())) {
            throw new ValidationException("first name is empty");
        }
        if(dto.getLastname() == null || "".equals(dto.getLastname())) {
            throw new ValidationException("last name is empty");
        }
    }
}
