package model;

import com.alibaba.fastjson.JSON;

/**
 * Class model for items
 *
 * @author Khmelyar Volodymyr
 */
public class Item {
    private String item;
    private String objectId;

    public Item() {
    }

    public Item(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
