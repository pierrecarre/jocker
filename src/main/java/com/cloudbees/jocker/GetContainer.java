package com.cloudbees.jocker;

import com.cloudbees.jocker.model.Container;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class GetContainer {

    private final Docker daemon;
    private final HttpGet get;

    GetContainer(Docker daemon) {
        this.daemon = daemon;
        get = new HttpGet(daemon.url+"/containers/json");
    }

    public Container[] get() throws IOException {
        get.setHeader("User-Agent", "Docker-Client/1.15 (Jocker;compatbile)");
        get.setHeader("Accept", "application/json");
        try (CloseableHttpClient client = daemon.setupClient()) {
            HttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != SC_OK) {
                throw new IOException("Failed to connect to Docker daemon " + statusCode);
            }
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(EntityUtils.toString(response.getEntity()), Container[].class);
        }

    }


}
