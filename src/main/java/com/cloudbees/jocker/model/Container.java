package com.cloudbees.jocker.model;

import java.util.Set;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class Container {

    private String Id;
    private String Command;
    private String Created;
    private String Path;
    private String[] Args;
    private Config Config;
    private State State;
    private String Image;
    private NetworkSettings NetworkSettings;
    private String SysInitPath;
    private String ResolvConfPath;
    private Volumes Volumes;
    private String Status;
    private Set<Port> Ports;
    private String SizeRw;
    private String SizeRootFs;


}
