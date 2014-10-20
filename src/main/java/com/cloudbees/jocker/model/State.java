package com.cloudbees.jocker.model;

import org.boon.core.Dates;

import java.util.Date;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class State {

    private boolean Running;
    private int Pid;
    private int ExitCode;
    private String StartedAt;
    private boolean Ghost;

    public boolean isRunning() {
        return Running;
    }

    public int getPid() {
        return Pid;
    }

    public int getExitCode() {
        return ExitCode;
    }

    public Date getStartedAt() {
        // Can't use native conversion to Date because Docker API return sub-milisecond date precision
        return Dates.fromJsonDate(StartedAt);
    }

    public boolean isGhost() {
        return Ghost;
    }
}
