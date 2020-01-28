package hu.flowacademy.musicstore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@Slf4j
public class JsonLoader {

    public static final String PATH_FORMAT = "src/main/resources/mocks/%s.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T loadByFilename(String s, JavaType t) {
        try {
            return objectMapper.readValue(getRawLines(s), t);
        } catch (JsonProcessingException e) {
            log.error("JSON read failed, maybe collection type[{}] not able to parse: {}", t, e.getMessage());
            return null;
        }
    }

    public <T> T loadByFilename(String s, Class<?> collection, Class<?> generic) {
        try {
            return objectMapper.readValue(getRawLines(s), getReturnType(collection, generic));
        } catch (JsonProcessingException e) {
            log.error("JSON read failed, maybe collection type[{}] or generic type [{}] not able to parse: {}", collection, generic, e.getMessage());
            return null;
        }
    }

    public JavaType getReturnType(Class<?> collection, Class<?> generic) {
        return objectMapper.getTypeFactory().constructCollectionLikeType(collection, generic);
    }

    private String getRawLines(String s) {
        try {
            return String.join("", Files.readAllLines(Paths.get(String.format(PATH_FORMAT, s))));
        } catch (IOException e) {
            log.error("Error when reading the file {}: {}", s, e.getMessage());
            return null;
        }
    }

}
