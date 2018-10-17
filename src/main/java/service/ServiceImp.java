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

/**
 * Class implements {@link Service}
 *
 * @author Khmelyar Volodymer
 */
public class ServiceImp implements Service<Item> {
    private Properties properties = new Properties();
    private String url;
    private HttpClient client = HttpClientBuilder.create().build();

    /**
     * Constructor for a class in which the url is set up to receive and assign data.
     * Url is taken from application.properties file
     */
    public ServiceImp() {
        try {
            properties.load(new FileInputStream(new File("src/main/resources",
                    "application.properties")));
            url = properties.getProperty("url") + "/" + properties.getProperty("ApplicationID")
                    + "/" + properties.getProperty("RESTAPIkey") + "/data/todo";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method of extracting data from a site
     *
     * @return list of data
     */
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

    /**
     * Method of data save on a site
     *
     * @param item data for save
     */
    public void save(Item item) {
        HttpPost post = new HttpPost(url);
        try {
            post.setEntity(new StringEntity(item.toJSON()));
            client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method of data update on a site
     *
     * @param item data for update
     */
    public void update(Item item) {
        HttpPut put = new HttpPut(url + "/" + item.getObjectId());
        try {
            put.setEntity(new StringEntity(item.toJSON()));
            client.execute(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method of delete data on a site
     *
     * @param item data for delete
     */
    public void delete(Item item) {
        HttpDelete delete = new HttpDelete(url + "/" + item.getObjectId());
        try {
            client.execute(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


