package ru.otus.servlet;

import ru.otus.template.TemplateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static ru.otus.util.ServletUtils.writeResponse;

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
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeResponse(resp, TemplateUtils.getPage("login.html", new HashMap<>()), HttpServletResponse.SC_OK); //save to the page, HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isValidCredentials(req.getParameter("login"), req.getParameter("pass"))) {
            writeResponse(resp, TemplateUtils.getPage("cache.html", cacheParams()), HttpServletResponse.SC_OK); //save to the page, HttpServletResponse.SC_OK);
        } else {
            writeResponse(resp, TemplateUtils.getPage("login.html", failureLogin()), HttpServletResponse.SC_FORBIDDEN);
        }
    }

    private Map<String, Object> cacheParams() {
        Map<String,Object> map = new HashMap<>();
        map.put("success",5);
        map.put("failure",3);
        return map;
    }


    private Map<String, Object> failureLogin() {
        Map<String, Object> result = new HashMap<>();
        result.put("failureLogin", true);
        return result;
    }


}
