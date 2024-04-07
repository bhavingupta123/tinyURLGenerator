package simple.tiny.url.simple.tiny.url.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface TinyURLRepository extends MongoRepository<TinyURL, String> {
    Optional<TinyURL> findByTinyURL(String tinyURL);
    Optional<TinyURL> findByLongURL(String longURL);
}
