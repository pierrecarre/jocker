package com.cloudbees.jocker.model;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class Port {

    private int PrivatePort;
    private int PublicPort;
    private String Type; // TODO enum ?

    public int getPrivatePort() {
        return PrivatePort;
    }

    public int getPublicPort() {
        return PublicPort;
    }

    public String getType() {
        return Type;
    }

    @Override
    public String toString() {
        return "Port{" +
                "PrivatePort=" + PrivatePort +
                ", PublicPort=" + PublicPort +
                ", Type='" + Type + '\'' +
                '}';
    }
}
