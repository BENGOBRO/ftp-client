package org.infotecs;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FTPTest {

    FTPServer server;

    @BeforeTest
    public void createServer() {
        server = new FTPServer();
    }

    @Test(dataProvider = "someIP", dataProviderClass = DP.class)
    public void testConnection(String IP, boolean expected) {
        server.setIP(IP);
        Assert.assertEquals(server.connect(), expected, "FAILED testConnection");
    }

    @Test(dependsOnMethods = "testConnection", dataProvider = "someLoginsAndPasswords", dataProviderClass = DP.class)
    public void testSignIn(String login, String password, boolean expected) {
        server.connect();
        Assert.assertEquals(server.signIn(login, password), expected, "FAILED testSignIn");
    }

    @Test(dependsOnMethods = "testSignIn", dataProvider = "someFileNames", dataProviderClass = DP.class)
    public void testDownloading(String remoteFileName, String localFileName, boolean expected) {
        Assert.assertEquals(server.downloadFile(remoteFileName, localFileName), expected, "FAILED testDownloading");
    }

    @Test(dependsOnMethods = "testSignIn", dataProvider = "someFileNames", dataProviderClass = DP.class)
    public void testUploading(String remoteFileName, String localFileName, boolean expected) {
        Assert.assertEquals(server.uploadFile(remoteFileName, localFileName), expected, "FAILED testUploading");
    }

    @Test
    public void testDisconnection() {
        server.disconnect();
    }
}
