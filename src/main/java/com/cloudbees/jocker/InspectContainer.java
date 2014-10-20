package com.cloudbees.jocker;

import com.cloudbees.jocker.model.Container;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.boon.json.JsonParser;
import org.boon.json.JsonParserAndMapper;
import org.boon.json.JsonParserFactory;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

/**
 * Implements https://docs.docker.com/reference/api/docker_remote_api_v1.15/#list-containers
 *
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class InspectContainer {

    private final Docker daemon;
    private final HttpGet get;

    InspectContainer(Docker daemon, String id) {
        this.daemon = daemon;
        get = new HttpGet(daemon.url+"/containers/"+id+"/json");
    }


    public Container execute() throws IOException {
        get.setHeader("User-Agent", "Docker-Client/1.15 (Jocker;compatbile)");
        get.setHeader("Accept", "application/json");
        try (CloseableHttpClient client = daemon.setupClient()) {
            HttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != SC_OK) {
                throw new IOException("Failed to connect to Docker daemon " + statusCode);
            }
            JsonParserAndMapper boon = new JsonParserFactory().create();
            return boon.parse(Container.class, response.getEntity().getContent());
        }
    }


}
