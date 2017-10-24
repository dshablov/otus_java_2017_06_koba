package ru.otus;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.otus.servlet.LoginServlet;
import ru.otus.servlet.RegisterServlet;


/**
 * HW12. На входе просим логин\пароль, даем возможность зарегестрироваться. После регистрации можно залогиниться в системе.
 * При повторных логинах в систему сначала смотрим, есть ли юзер с таким логином и паролем в кэше, если есть - в базу не лезем.
 * В параметрах кэша показываем, сколько раз параметр был считан из кэша.
 *
 * Прошу прощения за достаточно странную логику и интерфейс, фантазия внезапно кончилась и я залип :)
 */
public class JettyMain {

    private static final int PORT = 8090;
    private static final String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(LoginServlet.class, "/login");
        context.addServlet(RegisterServlet.class, "/register");
        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
        server.join();


    }
}
