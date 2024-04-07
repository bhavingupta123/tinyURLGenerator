package simple.tiny.url.simple.tiny.url.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class MongoDBConnection {

    private final TinyURLRepository tinyURLRepository;

    @Autowired
    public MongoDBConnection(TinyURLRepository tinyURLRepository) {
        this.tinyURLRepository = tinyURLRepository;
    }

    public void insertRecordIntoDB(String shortURL, String longURL) {
        TinyURL tinyURL = new TinyURL(shortURL, longURL);
        tinyURLRepository.save(tinyURL);
    }

    public Optional<TinyURL> searchRecordInDB(String fieldToSearch, String value){
        if ("tinyURL".equals(fieldToSearch)) {
            return tinyURLRepository.findByTinyURL(value);
        } else if ("longURL".equals(fieldToSearch)) {
            return tinyURLRepository.findByLongURL(value);
        } else {
            throw new IllegalArgumentException("Invalid field to search: " + fieldToSearch);
        }
    }
}