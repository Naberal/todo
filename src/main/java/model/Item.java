package model;

import com.alibaba.fastjson.JSON;

public class Item {
    private String id;
    private String item;

    public Item() {
    }

    public Item(String item) {
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
