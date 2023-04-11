package ch03.startup;

import ch03.connector.http.HttpConnector;

/**
 * @author wudi
 * @Package ch03.startup
 * @date 2023/4/9 12:43
 */
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
