package ru.otus;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.otus.servlet.LoginServlet;

public class JettyMain {

    private static final int PORT = 8090;
    private static final String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(LoginServlet.class, "/login");
        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
        server.join();
    }
}
