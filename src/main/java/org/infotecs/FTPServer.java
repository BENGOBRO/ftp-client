package org.infotecs;

import java.io.*;
import org.apache.commons.net.ftp.FTPClient;

public class FTPServer {

    private static final int PORT = 21;
    private String IP;
    private final FTPClient ftp = new FTPClient();
    private boolean isPassive;

    public boolean connect() {
        try {
            setMode();
            ftp.connect(IP, PORT);
            showServerReply();
        } catch(Exception e) {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                }
                catch (final IOException f) {
                }
            }
            System.out.printf("Failed to connect to the server! Error: %s\n", e.toString());
        } finally {
            return ftp.isConnected();
        }
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
            System.out.println("Download successful!");
        } catch (IOException e) {
            System.out.printf("Error: %s\n", e.toString());
        }
        return isDownloaded;
    }

    public void uploadFile(String remoteFileName, String localFileName) {
        try {
            ftp.deleteFile(remoteFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setMode();
        try (InputStream input = new FileInputStream(localFileName)) {
            ftp.appendFile(remoteFileName, input);
            System.out.println("Upload successful!");
        } catch (IOException e) {
            System.out.printf("Error: %s\n", e.toString());
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
