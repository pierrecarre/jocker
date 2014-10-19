package com.cloudbees.jocker.model;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class Config {

    private String Hostname;
    private String User;
    private int Memory;
    private int MemorySwap;
    private boolean AttachStdin;
    private boolean AttachStdout;
    private boolean AttachStderr;
    private String PortSpecs;
    private boolean Tty;
    private boolean OpenStdin;
    private boolean StdinOnce;
    private String Env;
    private String[] Cmd;
    private String Dns;
    private String Image;
    private Volumes Volumes;
    private String VolumesFrom;
    private String WorkingDir;
}
