package ch02;

import java.io.File;

public class Constants {
  public static final String WEB_ROOT =
    System.getProperty("user.dir") + File.separator  + "webroot";

  public static final String HEAD_OK = """
                    HTTP/1.1 200 OK
                    
                    """;
}