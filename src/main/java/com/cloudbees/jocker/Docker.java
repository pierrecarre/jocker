package com.cloudbees.jocker;

import java.io.IOException;


/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class Docker {

    final String url;

    /**
     * @param url docker daemon we want to talk to. Can be remote (tcp://) or local (unix://)
     */
    public Docker(String url) {
        this.url = url.replace("tcp://","https://");
    }

    public Docker() {
        this(getDocker_host());
    }

    private static String getDocker_host() {
        String dh = System.getenv("DOCKER_HOST");
        return dh != null ? dh : "unix:///var/run/docker.sock";
    }

    /**
     * Uses <a href="https://docs.docker.com/reference/api/docker_remote_api_v1.15/#list-containers"/>
     */
    public GetContainer getContainers() throws IOException {
        return new GetContainer(this);
    }

}
