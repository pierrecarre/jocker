package com.cloudbees.jocker;

import com.cloudbees.jocker.model.Container;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.boon.json.ObjectMapper;
import org.boon.json.ObjectMapperFactory;

import java.io.IOException;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

/**
 * Implements https://docs.docker.com/reference/api/docker_remote_api_v1.15/#list-containers
 *
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class ListContainers {

    private final Docker daemon;
    private final HttpGet get;

    ListContainers(Docker daemon) {
        this.daemon = daemon;
        get = new HttpGet(daemon.url+"/containers/json");
    }

    public List<Container> execute() throws IOException {
        get.setHeader("User-Agent", "Docker-Client/1.15 (Jocker;compatbile)");
        get.setHeader("Accept", "application/json");
        try (CloseableHttpClient client = daemon.setupClient()) {
            HttpResponse response = client.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != SC_OK) {
                throw new IOException("Failed to connect to Docker daemon " + statusCode);
            }
            ObjectMapper boon =  ObjectMapperFactory.create();
            return boon.readValue(response.getEntity().getContent(), List.class, Container.class);
        }
    }


}
