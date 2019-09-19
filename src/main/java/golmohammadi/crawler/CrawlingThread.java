package golmohammadi.crawler;

import golmohammadi.entity.ExtraInfo;
import golmohammadi.entity.LinkAddress;
import golmohammadi.entity.Product;
import golmohammadi.repository.LinkAddressRepository;
import golmohammadi.repository.ProductRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


public class CrawlingThread implements Runnable {

    LinkAddressRepository linkAddressRepository;

    ProductRepository productRepository;

    String url;
    Document document;

    Logger logger = Logger.getLogger(CrawlingThread.class.getName());

    public CrawlingThread(String url, Document document,
                          LinkAddressRepository linkAddressRepository,
                          ProductRepository productRepository) {
        this.url = url;
        this.document=document;
        this.linkAddressRepository = linkAddressRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run() {
        //check to make sure if this url hasn't processed already
        if (linkAddressRepository.findByUrl(url).isEmpty()) {

            //save the found url
            LinkAddress linkAddress = linkAddressRepository.save(new LinkAddress(url));

            logger.info("Try to connect: " + url);

            //explore the page to find if it represent any product
            Element description = document.getElementById("description");
            if (description != null) {
                Elements values = description.getElementsByClass("value");
                Element first = values.first();
                if (first != null) {
                    Elements parags = first.getElementsByTag("p");
                    if (parags.first() != null) {
                        String productName = document.getElementsByClass("page-title").first().getElementsByClass("base").text();
                        String price = document.getElementsByClass("price").first().text();
                        String details = parags.first().text();
                        Set<ExtraInfo> extraInfos = new HashSet<>();

                        logger.info("Product: " + productName);
                        logger.info("price: " + price);
                        logger.info("Details: " + details);
                        logger.info("+++++++++ E X T R A ++++++++++++");
                        for (Element elm : document.getElementById("product-attribute-specs-table").getElementsByTag("th")) {
                            String extraLbl = elm.text();
                            String extraVal = document.select("td[data-th=" + extraLbl + "]").text();
                            logger.info(extraLbl + ":" + extraVal);
                            extraInfos.add(new ExtraInfo(extraLbl, extraVal));
                        }
                        logger.info("++++++++++++++++++++++++++++++");
                        productRepository.save(new Product(productName, price, details, linkAddress, extraInfos));
                    }
                }
            }

        }
    }
}
