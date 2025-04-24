import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scraping {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://nytimes.com")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .get();

        // Find all anchor tags (links) in the document
        Elements links = doc.getElementsByTag("a");

        // Print all the links
        for (Element link : links) {
            String url = link.attr("href");
            String text = link.text();
            System.out.println(text + " -> " + url);
        }

        // Find elements with specific attributes (like email links)
        Elements emailLinks = doc.getElementsByAttributeValueContaining("href", "mailto:");
        for (Element email : emailLinks) {
            System.out.println("Email found: " + email.attr("href").replace("mailto:", ""));
        }
    }
}
