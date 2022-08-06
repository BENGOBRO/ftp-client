package org.infotecs;

import java.net.*;

public class FTPServer {

    private String host = "";
    private final int PORT = 8888;

    private String username;
    private String password;
    private Socket client;
    //private JsonParser jsonParser;

    public void connect() {

        try {
            //ftp = new FTPClient();
        } catch(Exception e) {
            System.out.println("Error: can't connect to server");
        }
    }

    public void setHost (String host) {
        this.host = host;
    }
}
