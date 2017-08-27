package ru.otus.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by vkoba on 27.08.2017.
 */
public class LoginServlet extends HttpServlet {
    private static Map<String, String> credentialStore;

    static {
        credentialStore = new HashMap<>();
        credentialStore.put("admin", "admin");
    }

    private static String getPage() throws IOException {
        Map<String, Object> pageVariables = new HashMap<>();
        return TemplateProcessor.instance().getPage("login", new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        if (isValidCredentials(login, password)) {
            String page = getPage(); //save to the page
            resp.getWriter().println(page);
            setOK(resp);
            //200 OK, cache params
        }
        // 403 forbidden
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private boolean isValidCredentials(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        return Objects.equals(password, credentialStore.get(login));
    }
}
