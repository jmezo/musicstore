package hu.flowacademy.musicstore.service;

import hu.flowacademy.musicstore.exception.ValidationException;
import hu.flowacademy.musicstore.persistance.model.DTO.SongDTO;
import hu.flowacademy.musicstore.persistance.model.Song;
import hu.flowacademy.musicstore.persistance.repository.AlbumRepository;
import hu.flowacademy.musicstore.persistance.repository.ArtistRepository;
import hu.flowacademy.musicstore.persistance.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public List<SongDTO> getAll() {
        return songRepository.findAll().stream().map(SongDTO::new).collect(Collectors.toList());
    }

    public SongDTO getOne(long id) {
        var Song = songRepository.findById(id).orElseThrow();
        return new SongDTO(Song);
    }

    public SongDTO post(SongDTO dto) {
        var song = getFromDto(dto);
        validate(song);
        var savedSong = songRepository.save(song);
        return new SongDTO(savedSong);
    }

    public void put(SongDTO dto) {
        songRepository.save(getFromDto(dto));
    }

    public void delete(long id) {
        songRepository.deleteById(id);
    }

    private Song getFromDto(SongDTO dto) {
        var song = dto.toSong();
        var artist = artistRepository.findById(dto.getArtistId()).orElseThrow();
        var album = albumRepository.findById(dto.getAlbumId()).orElseThrow();
        song.setArtist(artist);
        song.setAlbum(album);
        return song;
    }

    private void validate(Song song) {
        if(song.getTitle() == null || "".equals(song.getTitle())) {
            throw new ValidationException("missing title");
        }
        if(song.getAlbum() == null || song.getArtist() == null || song.getGenre() == null) {
            throw new ValidationException("missing stuff");
        }
        if("".equals(song.getLyrics())) {
            song.setLyrics(null);
        }
        if(song.getWriterName() == null || "".equals(song.getWriterName())) {
            var artistFirstname = song.getArtist().getFirstname();
            var artistLastname = song.getArtist().getLastname();
            song.setWriterName(artistFirstname + " " + artistLastname);
        }
    }
}
