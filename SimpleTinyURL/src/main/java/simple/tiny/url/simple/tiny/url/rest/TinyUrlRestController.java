package simple.tiny.url.simple.tiny.url.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import simple.tiny.url.simple.tiny.url.db.MongoDBConnection;
import simple.tiny.url.simple.tiny.url.db.TinyURL;
import simple.tiny.url.simple.tiny.url.db.TinyURLRepository;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;

@RestController
@RequestMapping("")
public class TinyUrlRestController {

    private final MongoDBConnection mongoDBConnection;

    @Autowired
    public TinyUrlRestController(TinyURLRepository tinyURLRepository) {
        this.mongoDBConnection = new MongoDBConnection(tinyURLRepository);
    }

    @GetMapping("")
    public ModelAndView hello(){
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @GetMapping("/{shortURL}")
    public RedirectView getLongURL(@PathVariable String shortURL) throws UnsupportedEncodingException {
        Optional<TinyURL> result = mongoDBConnection.searchRecordInDB("tinyURL", "http://127.0.0.1:8081/" + shortURL);

        if(result.isPresent()){
            String url = result.get().getLongURL();
            System.out.println(url);
            int equalsIndex = url.indexOf('=');
            String strippedUrl = url.substring(equalsIndex + 1);
            System.out.println(strippedUrl);

            String decodedURL = URLDecoder.decode(strippedUrl, "UTF-8");

            return new RedirectView(decodedURL);
        }

        return new RedirectView("https://www.google.com");
    }

    @PostMapping("/shortenUrl")
    @ResponseBody
    public String shortenUrl(@RequestBody String longUrl){
        String shortUrl = generateShortUrl(longUrl);
        mongoDBConnection.insertRecordIntoDB(shortUrl, longUrl);
        return shortUrl;
    }

    private String generateShortUrl(String longUrl) {
        return "http://127.0.0.1:8081/" + longUrl.hashCode();
    }
}