package golmohammadi.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "extra_info")
public class ExtraInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eid")
    private Integer id;

    String extraKey;
    String extraValue;

    public ExtraInfo() {
    }

    public ExtraInfo(String extraKey, String extraValue) {
        this.extraKey = extraKey;
        this.extraValue = extraValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtraKey() {
        return extraKey;
    }

    public void setExtraKey(String extraKey) {
        this.extraKey = extraKey;
    }

    public String getExtraValue() {
        return extraValue;
    }

    public void setExtraValue(String extraValue) {
        this.extraValue = extraValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtraInfo extraInfo = (ExtraInfo) o;

        if (id != null ? !id.equals(extraInfo.id) : extraInfo.id != null) return false;
        if (extraKey != null ? !extraKey.equals(extraInfo.extraKey) : extraInfo.extraKey != null) return false;
        return extraValue != null ? extraValue.equals(extraInfo.extraValue) : extraInfo.extraValue == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (extraKey != null ? extraKey.hashCode() : 0);
        result = 31 * result + (extraValue != null ? extraValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return extraKey+":"+extraValue+" | ";
    }
}
