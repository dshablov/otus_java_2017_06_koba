package ru.otus.servlet;

import ru.otus.entityframework.dbservice.CacheInfo;
import ru.otus.entityframework.dbservice.CachedHibernateDbService;
import ru.otus.entityframework.dbservice.DbService;
import ru.otus.template.TemplateUtils;
import ru.otus.validation.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.otus.util.ServletUtils.writeResponse;

/**
 * Created by vkoba on 27.08.2017.
 */
public class LoginServlet extends HttpServlet {

    private DbService userDbService;
    private CacheInfo cacheInfo;

    public LoginServlet() {
        cacheInfo = new CacheInfo(0);
        userDbService = new CachedHibernateDbService(cacheInfo);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeResponse(resp, TemplateUtils.getPage("login.html", new HashMap<>()), HttpServletResponse.SC_OK); //save to the page, HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (new ValidationService(userDbService).isValidCredentials(req.getParameter("login"), req.getParameter("pass"))) {
            writeResponse(resp, TemplateUtils.getPage("cache.html", TemplateUtils.hitParams(cacheInfo.hits())), HttpServletResponse.SC_OK); //save to the page, HttpServletResponse.SC_OK);
        } else {
            writeResponse(resp, TemplateUtils.getPage("login.html", new HashMap<>()), HttpServletResponse.SC_FORBIDDEN);
        }
    }


    
}
