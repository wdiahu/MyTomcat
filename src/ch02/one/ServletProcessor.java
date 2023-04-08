package ch02.one;

import ch02.Constants;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;
import java.io.File;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet请求处理器
 */
public class ServletProcessor {

    public void process(Request request, Response response) {

        String uri = request.getUri();
        //截取服务名
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            //创建一个URLClassLoader类加载器 从本地文件系统获取class文件并加载
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            //repository指定从哪里加载servlet类
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        Class myClass = null;
        try {
            //将servletName作为类名加载类
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;

        try {
            /**
             * 存在安全隐患
             * 程序员完全可以直接在service方法内部调用Request类、Response类的其他公共方法
             * 优雅的解决方案是采取一个门面类来解决这个问题
             */
            servlet = (Servlet) myClass.newInstance();
            servlet.service(request, response);
        } catch (Exception e) {
            System.out.println(e.toString());
        } catch (Throwable e) {
            System.out.println(e.toString());
        }

    }
}