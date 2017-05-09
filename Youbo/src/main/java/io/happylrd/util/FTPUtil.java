package io.happylrd.util;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FTPUtil {

    private static String ftpIp = "127.0.0.1";
    private static String ftpUsername = "youboadmin";
    private static String ftpPassword = "123456";

    private String ip;
    private int port;
    private String username;
    private String password;
    private FTPClient ftpClient;

    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUsername, ftpPassword);

        boolean result = ftpUtil.uploadFile("img", fileList);

        return result;
    }

    private boolean uploadFile(String remotePath, List<File> fileList) throws IOException {
        boolean isUploaded = true;
        FileInputStream fileInputStream = null;

        if (connectServer(this.ip, this.port, this.username, this.password)) {
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File fileItem : fileList) {
                    fileInputStream = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(), fileInputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
                isUploaded = false;
            } finally {
                fileInputStream.close();
                ftpClient.disconnect();
            }
        }

        return isUploaded;
    }

    private boolean connectServer(String ip, int port, String username, String password) {
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public FTPUtil(String ip, int port, String username, String password) {
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
