package com.cloudbees.jocker.model;

import java.util.Arrays;
import java.util.Set;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class Container {

    private String Id;
    private String Image;
    private String Command;
    private String Created;
    private String Status;
    private Set<Port> Ports;
    private String SizeRw;
    private String SizeRootFs;

    public String getId() {
        return Id;
    }

    public String getImage() {
        return Image;
    }

    public String getCommand() {
        return Command;
    }

    public String getCreated() {
        return Created;
    }

    public String getStatus() {
        return Status;
    }

    public Set<Port> getPorts() {
        return Ports;
    }

    public String getSizeRw() {
        return SizeRw;
    }

    public String getSizeRootFs() {
        return SizeRootFs;
    }

    @Override
    public String toString() {
        return "Container{" +
                "Id='" + Id + '\'' +
                ", Image='" + Image + '\'' +
                ", Command='" + Command + '\'' +
                ", Created='" + Created + '\'' +
                ", Status='" + Status + '\'' +
                ", Ports=" + Ports +
                ", SizeRw='" + SizeRw + '\'' +
                ", SizeRootFs='" + SizeRootFs + '\'' +
                '}';
    }
}
