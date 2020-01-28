package hu.flowacademy.musicstore.persistance.model.DTO;

import hu.flowacademy.musicstore.persistance.model.Album;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumDTO {
    private Long id;

    private String title;

    private Long count;

    public AlbumDTO(Album album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.count = album.getCount();
    }

    public Album toAlbum() {
        return new Album(id, title, count);
    }

}
