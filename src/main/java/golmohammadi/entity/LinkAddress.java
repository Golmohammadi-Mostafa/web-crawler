package golmohammadi.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "link_address")
public class LinkAddress implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lid")
    private Integer id;

    @Column(name = "url", nullable = false)
    private String url;

    public LinkAddress() {
    }

    public LinkAddress(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LinkAddress{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
