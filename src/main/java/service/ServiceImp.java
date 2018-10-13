package service;

import model.Item;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ServiceImp implements Service<Item> {
    private String url = "https://api.backendless.com/62BB5CEE-77EA-0CF4-FF5F-CC0044D53D00/" +
            "F7D61915-C063-1526-FF36-62E1417F4900/data/todo";
    private HttpClient client = HttpClientBuilder.create().build();

    public List<Item> getAll() {
        HttpGet getAll = new HttpGet(url);
        try {
            HttpResponse response = client.execute(getAll);
            HttpEntity entity = response.getEntity();
            System.out.println(entity.toString());//todo
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(Item item) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application / json");
        try {
            post.setEntity(new StringEntity(item.toJSON()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void update(Item item) {
        HttpPut put = new HttpPut(url + "/" + item.getId());
        put.setHeader("Content-Type", "application / json");
        try {
            put.setEntity(new StringEntity(item.toJSON()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void delete(Item item) {
        HttpDelete Httpdelete = new HttpDelete(url + "/" + item.getId());
    }
}

