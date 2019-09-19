package golmohammadi.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
@Service
public class WebCrawlerWithDepth {
  private static final int MAX_DEPTH = 2;
  private HashSet<String> links;

  public WebCrawlerWithDepth() {
    links = new HashSet<String>();
  }

  public HashSet<String> getPageLinks(String URL, int depth) {
    if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
      System.out.println(">> Depth: " + depth + " [" + URL + "]");
      try {
        links.add(URL);

        Document document = Jsoup.connect(URL).get();
        Elements linksOnPage = document.select("a[href]");

        depth++;
        for (Element page : linksOnPage) {
          getPageLinks(page.attr("abs:href"), depth);
        }
      } catch (IOException e) {
        System.err.println("For '" + URL + "': " + e.getMessage());
      }
    }
    return links;
  }

//  public static void main(String[] args) {
//      new WebCrawlerWithDepth().getPageLinks("http://magento-test.finology.com.my/what-is-new.html", 0);
//  }
}