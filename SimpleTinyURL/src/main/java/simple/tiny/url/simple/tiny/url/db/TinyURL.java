package simple.tiny.url.simple.tiny.url.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TinyURLMapping")
public class TinyURL {

    @Id
    private String id;
    private String tinyURL;
    private String longURL;

    public TinyURL() {}

    public TinyURL(String tinyURL, String longURL) {
        this.tinyURL = tinyURL;
        this.longURL = longURL;
    }

    public String getId() {
        return id;
    }

    public String getTinyURL() {
        return tinyURL;
    }

    public void setTinyURL(String tinyURL) {
        this.tinyURL = tinyURL;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }
}
