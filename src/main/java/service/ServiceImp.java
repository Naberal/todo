package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import model.Item;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ServiceImp implements Service<Item> {
    private Properties properties = new Properties();
    private String url;
    private HttpClient client = HttpClientBuilder.create().build();

    public ServiceImp() {
        try {
            properties.load(new FileInputStream(new File("src/main/resources/application.properties").getAbsoluteFile()));
            url = properties.getProperty("url") + "/" + properties.getProperty("ApplicationID")
                    + "/" + properties.getProperty("RESTAPIkey") + "/data/todo";
            System.out.println(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAll() {
        List<Item> all = new ArrayList<Item>();
        HttpGet getAll = new HttpGet(url);
        try {
            HttpResponse response = client.execute(getAll);
            String line = new Scanner(response.getEntity().getContent()).nextLine();
            JSONArray objects = JSON.parseArray(line);
            all.addAll(objects.toJavaList(Item.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all;
    }

    public void save(Item item) {
        HttpPost post = new HttpPost(url);
        try {
            post.setEntity(new StringEntity(item.toJSON()));
            client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Item item) {
        HttpPut put = new HttpPut(url + "/" + item.getObjectId());
        try {
            put.setEntity(new StringEntity(item.toJSON()));
            client.execute(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Item item) {
        HttpDelete delete = new HttpDelete(url + "/" + item.getObjectId());
        try {
            client.execute(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


