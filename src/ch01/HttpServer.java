package ch01;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

/**
 * 这一章主要实现一个静态的HTTP服务器
 * 运行HttpServer 浏览器键入http://localhost:8080/index.html
 * 即可浏览到页面
 */
public class HttpServer {
    /**
     * WEB_ROOT是存放资源文件的地址
     * 也就是当前工作目录下的webroot文件夹
     */
    public static final String WEB_ROOT =
            System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        //创建HttpServer
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            //监听8080端口
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                //建立tcp连接
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                //创建Request对象
                Request request = new Request(input);
                request.parse();

                //创建Response对象
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                //关闭socket
                socket.close();


                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
