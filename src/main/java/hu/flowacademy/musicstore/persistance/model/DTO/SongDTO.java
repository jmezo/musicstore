package hu.flowacademy.musicstore.persistance.model.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import hu.flowacademy.musicstore.persistance.model.Album;
import hu.flowacademy.musicstore.persistance.model.Genre;
import hu.flowacademy.musicstore.persistance.model.Song;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongDTO {
    private Long id;

    private String title;

    private Long length;

    private String lyrics;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate year;

    private String writerName;

    private Genre genre;

    private Long artistId;

    private Long albumId;

    public SongDTO(Song song) {
        this.id = song.getId();
        this.title = song.getTitle();
        this.length = song.getLength();
        this.lyrics = song.getLyrics();
        this.year = song.getYear();
        this.writerName = song.getWriterName();
        this.genre = song.getGenre();
        this.artistId = song.getArtist().getId();
        this.albumId = song.getAlbum().getId();
    }

    public Song toSong() {
        return Song.builder().id(id).title(title).length(length).lyrics(lyrics).year(year).genre(genre).build();
    }
}
