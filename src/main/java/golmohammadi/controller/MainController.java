package golmohammadi.controller;

import golmohammadi.entity.LinkAddress;
import golmohammadi.repository.LinkAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;

@Controller
@RequestMapping(path="/crawler")
public class MainController {
    @Autowired
    private LinkAddressRepository linkAddressRepository;

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping(path="/add")
    public @ResponseBody String addNewUrl(@RequestParam String url, @RequestParam Integer id){
        LinkAddress linkAddress = new LinkAddress();
        linkAddress.setUrl(url);
        linkAddressRepository.save(linkAddress);
        return "Saved this url:"+url;
    }

    @GetMapping(path = "/get/all")
    public @ResponseBody Iterable<LinkAddress> getAllUsers(){
        Iterable<LinkAddress> users = linkAddressRepository.findAll();
        System.out.println("************** all users : ************************");
        users.forEach(item->{
            System.out.println(" id : "+item.getId());
            System.out.println(" url: "+item.getUrl());
            System.out.println("************** ************************");
        });
        logger.info("************* finished *****************************");
        return users;
    }


    public String addNewUrl( String url){
        LinkAddress linkAddress = new LinkAddress();
        linkAddress.setUrl(url);
        linkAddressRepository.save(linkAddress);
        return "Saved this url:"+url;
    }

    public String addAllUrl( HashSet<String> hashSetUrl){
        HashSet<LinkAddress> set =new HashSet<>();
        hashSetUrl.forEach(item->{
            LinkAddress linkAddress = new LinkAddress();
            linkAddress.setUrl(item);
            set.add(linkAddress);
        });

        linkAddressRepository.saveAll(set);
        return "Saved Successfully:";
    }

    public Iterable<LinkAddress> getAllUrl(){
        Iterable<LinkAddress> urls = linkAddressRepository.findAll();
        System.out.println("************** all users : ************************");
        urls.forEach(item->{
            System.out.println(" id : "+item.getId());
            System.out.println(" url: "+item.getUrl());
            System.out.println("************** ************************");
        });
        System.out.println("************** finished ************************");
        return urls;
    }
}
