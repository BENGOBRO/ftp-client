package org.infotecs;

import java.io.*;
import org.apache.commons.net.ftp.FTPClient;

public class FTPServer {
    private String IP;
    private final FTPClient ftp = new FTPClient();
    private boolean isPassive;

    public boolean connect() {
        try {
            setMode();
            ftp.connect(IP);
            showServerReply();
        } catch(Exception e) {
            if (ftp.isConnected()) {
                disconnect();
            }
            System.out.printf("Failed to connect to the server! Error: %s\n", e.toString());
        }
        return ftp.isConnected();
    }

    private void setMode() {
        if (isPassive) {
            ftp.enterLocalPassiveMode();
        } else {
            ftp.enterLocalActiveMode();
        }
    }

    public boolean signIn(String username, String password) {
        setMode();
        boolean isLogged = false;
        try {
            isLogged = ftp.login(username, password);
        } catch (IOException e) {
            System.out.printf("Error: %s\n", e.toString());
        }
        return isLogged;
    }

    public boolean downloadFile(String remoteFileName, String localFileName) {
        boolean isDownloaded = false;
        setMode();
        ftp.setControlKeepAliveTimeout(300);
        try (OutputStream output = new FileOutputStream(localFileName)) {
            isDownloaded = ftp.retrieveFile(remoteFileName, output);
        } catch (IOException e) {
            System.out.printf("Error: %s\n", e.toString());
        }
        return isDownloaded;
    }

    public boolean uploadFile(String remoteFileName, String localFileName) {
        try {
            ftp.deleteFile(remoteFileName);
        } catch (IOException e) {
            System.out.printf("Error: %s\n", e.toString());
            return false;
        }
        setMode();
        try (InputStream input = new FileInputStream(localFileName)) {
            ftp.appendFile(remoteFileName, input);
            return true;
        } catch (IOException e) {
            System.out.printf("Error: %s\n", e.toString());
            return false;
        }
    }

    public void disconnect() {
        try {
            ftp.disconnect();
            System.out.println("Connection disabled");
        } catch (IOException e) {
            System.out.print("Error: ");
            e.printStackTrace();
        }
    }

    public void setPassive(boolean passive) {
        isPassive = passive;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    private void showServerReply() {
        String[] replies = ftp.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
}
