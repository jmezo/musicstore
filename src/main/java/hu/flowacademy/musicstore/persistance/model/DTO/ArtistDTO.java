package hu.flowacademy.musicstore.persistance.model.DTO;

import hu.flowacademy.musicstore.persistance.model.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDTO {
    private Long id;

    private String firstname;

    private String lastname;

    public ArtistDTO(Artist artist) {
        this.id = artist.getId();
        this.firstname = artist.getFirstname();
        this.lastname = artist.getLastname();
    }

    public Artist toArtist() {
        return new Artist(id, firstname, lastname);
    }
}
