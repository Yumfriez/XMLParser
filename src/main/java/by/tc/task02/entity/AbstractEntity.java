package by.tc.task02.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AbstractEntity implements Serializable{
    private String name;
    private int level;
    private Map<String,String> attributes;

    public AbstractEntity() {
        attributes=new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(String key, String value){
        attributes.put(key,value);

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        if (level != that.level) return false;
        if (!name.equals(that.name)) return false;
        return attributes.equals(that.attributes);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + level;
        result = 31 * result + attributes.hashCode();
        return result;
    }

}
