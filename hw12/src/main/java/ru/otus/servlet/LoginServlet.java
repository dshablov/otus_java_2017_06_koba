package ru.otus.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by vkoba on 27.08.2017.
 */
public class LoginServlet extends HttpServlet {
    private static Map<String, String> credentialStore;
    //По-хорошему здесь нужна map, но из-за отсутствия ролевой модели можно обойтись и списком
    private static List<String> tokenStore;

    static {
        credentialStore = new HashMap<>();
        credentialStore.put("admin", "admin");
        tokenStore = new ArrayList<>();
    }

    private static String getPage(String templateName, Map<String, Object> params) throws IOException {
        return TemplateProcessor.instance().getPage(templateName, params);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = getPage("login.html", new HashMap<>()); //save to the page
        resp.getWriter().println(page);
        setOK(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("pass");
        if (isValidCredentials(login, password)) {
            String page = getPage("cache.html", new HashMap<>()); //save to the page
            resp.getWriter().println(page);
            setOK(resp);

            //200 OK, cache params
        } else {
            String page = getPage("login.html", failureLogin());
            resp.getWriter().println(page);
            set403(resp);
        }
    }

    private void saveToCookie(HttpServletResponse response, String requestLogin) {
        String uuid = UUID.randomUUID().toString();
        tokenStore.add(uuid);
        Cookie cacheStatToken = new Cookie("cacheStatToken", uuid);
        cacheStatToken.setMaxAge(600);
        response.addCookie(cacheStatToken);
    }

    private Map<String, Object> failureLogin() {
        Map<String, Object> result = new HashMap<>();
        result.put("failureLogin", true);
        return result;
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void set403(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    private boolean isValidCredentials(String login, String password) {
        if (login == null || password == null) {
            return false;
        }
        return Objects.equals(password, credentialStore.get(login));
    }
}
