package ch03;

import ch03.connector.http.HttpRequest;
import ch03.connector.http.HttpResponse;

import java.io.IOException;

/**
 * 静态资源请求处理器
 */
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
