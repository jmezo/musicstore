package hu.flowacademy.musicstore.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
class JsonLoaderTest {

    private JsonLoader jsonLoader = new JsonLoader();

    @Data
    @NoArgsConstructor
    private static class Dude {
        private String id;
        private String firstname;
        private String lastname;
    }

    @Test
    void loadByFilename() {
        List<Dude> dudes = jsonLoader.loadByFilename("artists", List.class, Dude.class);
        assertNotNull(dudes);
        dudes.forEach(d -> {
            assertNotNull(d.id);
            assertNotNull(d.firstname);
            assertNotNull(d.lastname);
        });

        List<Dude> guys = jsonLoader.loadByFilename("artists", jsonLoader.getReturnType(List.class, Dude.class));
        assertNotNull(dudes);
        guys.forEach(d -> {
            assertNotNull(d.id);
            assertNotNull(d.firstname);
            assertNotNull(d.lastname);
        });
    }

    @Test
    void loadInvalidFilename() {
        assertThrows(IllegalArgumentException.class, () -> jsonLoader.loadByFilename("something else", List.class, Dude.class));
        assertThrows(IllegalArgumentException.class, () -> jsonLoader.loadByFilename("something else", jsonLoader.getReturnType(List.class, Dude.class)));
    }
}