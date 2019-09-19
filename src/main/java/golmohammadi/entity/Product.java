package golmohammadi.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pid")
    private Integer id;

    String name;
    String price;
    String description;

    @ManyToOne
    @JoinColumn(name = "link_add_id")
    LinkAddress linkAddress;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_extra",
            joinColumns = @JoinColumn(name = "pid"),
            inverseJoinColumns = @JoinColumn(name = "eid")
    )
    Set<ExtraInfo> extraInfos = new HashSet<>();

    public Product() {
    }

    public Product(String name, String price, String description,
                   LinkAddress linkAddress, Set<ExtraInfo> extraInfos) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.linkAddress = linkAddress;
        this.extraInfos = extraInfos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkAddress getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(LinkAddress linkAddress) {
        this.linkAddress = linkAddress;
    }

    public Set<ExtraInfo> getExtraInfos() {
        return extraInfos;
    }

    public void setExtraInfos(Set<ExtraInfo> extraInfos) {
        this.extraInfos = extraInfos;
    }

    /*    @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price='" + price + '\'' +
                    ", description='" + description + '\'' +
                    ", linkAddress=" + linkAddress +
                    '}';
        }*/
    @Override
    public String toString() {
        return "+++++++++++++++++++++++++++++++++++++++++++ P R O D U C T +++++++++++++++++++++++++++++++++++++++++++\n" +
                "\n Name:" + name +
                "\n Price:" + price +
                "\n Description:" + description +
                "\n Extra information:" + extraInfos.toString() +
                "\n";
    }
}
