import ch02.Constants;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimitiveServlet implements Servlet {

    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = response.getWriter();
        out.print(Constants.HEAD_OK);
        out.println("Hello. Roses are red.");
        out.print("Violets are blue.");
        //其他人完全可以在这里调用Request的其他一些方法 造成了安全隐患
        //Request request1 = (Request) request;
        //request1.parse();

        // RequestFacade进一步封装了Request，程序员无法调用Request其他一些公共方法
        // RequestFacade facade = (RequestFacade)request;
    }

    public void destroy() {
        System.out.println("destroy");
    }

    public String getServletInfo() {
        return null;
    }

    public ServletConfig getServletConfig() {
        return null;
    }

}
