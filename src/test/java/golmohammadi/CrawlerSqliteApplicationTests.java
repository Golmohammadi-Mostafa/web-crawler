package golmohammadi;

import golmohammadi.crawler.CrawlingThread;
import golmohammadi.entity.Product;
import golmohammadi.repository.LinkAddressRepository;
import golmohammadi.repository.ProductRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerSqliteApplicationTests {

    @Autowired
    LinkAddressRepository linkAddressRepository;

    @Autowired
    ProductRepository productRepository;

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    Logger logger = Logger.getLogger(CrawlerSqliteApplicationTests.class.getName());

    @Test
    public void testCrawledResult() {
        Iterable<Product> products = productRepository.findAll();
        products.forEach(item -> {
            System.out.println(item);
        });

    }

    @Test
    public void testCrawling() {
        String url = "http://magento-test.finology.com.my/breathe-easy-tank.html";
        try {
            logger.info("Starting Point:" + url);

            //this HashSet prevents crawling of duplicated urls
            HashSet<String> hashSet = new HashSet<String>();

            getLinks(hashSet, url);
        } catch (IOException e) {
            System.err.println("For '" + url + "': " + e.getMessage());
        }
    }

    /**
     * Recursively finds follows all the links in a page, and process them individually
     *
     * @param hashSet holds any url that is just found, and prevents it to be processed
     *                if it has already found
     * @param url     represents the url to process
     * @throws IOException is thrown in the case of any problem in network connection
     */
    private void getLinks(HashSet<String> hashSet, String url) throws IOException {
        try {
            if (!hashSet.contains(url) && !url.contains("account/login")) {

                hashSet.add(url);

                Document document = Jsoup.connect(url).get();
                //check to make sure if this url hasn't processed already
                if (linkAddressRepository.findByUrl(url).isEmpty()) {
                    CrawlingThread crawlingThread = new CrawlingThread(url, document, linkAddressRepository, productRepository);
                    executorService.submit(crawlingThread);
                }

                //find and crawls all the links in that page
                Elements linksOnPage = document.select("a[href]");
                for (Element page : linksOnPage) {
                    if (page.attr("href").startsWith("http")) {
                        getLinks(hashSet, page.attr("href"));
                    }
                }
            }
        } catch (IOException ex) {
            logger.severe("Crawling Failed For URL:" + url);
        }
    }

}
