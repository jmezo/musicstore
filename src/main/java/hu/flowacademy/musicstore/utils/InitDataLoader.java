package hu.flowacademy.musicstore.utils;

import hu.flowacademy.musicstore.persistance.model.Album;
import hu.flowacademy.musicstore.persistance.repository.AlbumRepository;
import hu.flowacademy.musicstore.persistance.repository.ArtistRepository;
import hu.flowacademy.musicstore.persistance.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
@AllArgsConstructor
public class InitDataLoader {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final JsonLoader jsonLoader = new JsonLoader();

    @PostConstruct
    public void init() {
        List<Album> albums = jsonLoader.loadByFilename("artists", jsonLoader.getReturnType(List.class, Album.class));
//        albumRepository.saveAll(albums);
//        albumRepository.save(albums.get(0));
    }

}
