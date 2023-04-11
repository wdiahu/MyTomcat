package ch03.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wudi
 * @Package ch03.connector.http
 * @date 2023/4/9 12:42
 *
 * HttpConnector实现了Runnable接口，
 * 启动时创建一个线程来负责以下操作
 * (1) 等待HTTP请求
 * (2) 根据每个请求创建一个HttpProcessor对象
 * (3) HttpProcessor对象处理HTTP请求
 */
public class HttpConnector implements Runnable{

    boolean stopped;
    private String scheme = "http";

    public String getScheme() {
        return scheme;
    }

    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket =  new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stopped) {
            // Accept the next incoming connection from the server socket
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            }
            catch (Exception e) {
                continue;
            }
            // Hand this socket off to an HttpProcessor
            HttpProcessor processor = new HttpProcessor(this);
            processor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
