package org.nttdata.bootcamp.quarkus.login.ws.vo;

public class SessionVO {
    private String numTarget;
    private String password;

    public String getNumTarget() {
        return numTarget;
    }

    public void setNumTarget(String numTarget) {
        this.numTarget = numTarget;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
