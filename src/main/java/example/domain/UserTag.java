package example.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserTag {

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
