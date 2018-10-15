package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import model.Item;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceImp implements Service<Item> {
    private String url = "https://api.backendless.com/62BB5CEE-77EA-0CF4-FF5F-CC0044D53D00/" +
            "F7D61915-C063-1526-FF36-62E1417F4900/data/todo";
    private HttpClient client = HttpClientBuilder.create().build();

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
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void update(Item item) {
        HttpPut put = new HttpPut(url + "/" + item.getId());
        try {
            put.setEntity(new StringEntity(item.toJSON()));
            client.execute(put);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(Item item) {
        HttpDelete delete = new HttpDelete(url + "/" + item.getId());
        try {
            client.execute(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


