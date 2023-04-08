import ch02.Constants;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Test {

    public static void main(String[] args) throws IOException {
        File classPath = new File(Constants.WEB_ROOT);
        String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
        System.out.println(repository);
    }
}
