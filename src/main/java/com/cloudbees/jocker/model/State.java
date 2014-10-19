package com.cloudbees.jocker.model;

import java.util.Date;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class State {

    private boolean Running;
    private int Pid;
    private int ExitCode;
    private Date StartedAt;
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
        return StartedAt;
    }

    public boolean isGhost() {
        return Ghost;
    }
}
