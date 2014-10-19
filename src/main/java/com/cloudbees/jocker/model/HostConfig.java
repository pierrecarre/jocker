package com.cloudbees.jocker.model;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class HostConfig {

    private String Binds;
    private String ContainerIDFile;
    private String[] LxcConf;
    private boolean Privileged;
    private PortBindings PortBindings;
    private String[] Links;
    private boolean PublishAllPorts;
    private String[] CapAdd;
    private String[] CapDrop;

}
