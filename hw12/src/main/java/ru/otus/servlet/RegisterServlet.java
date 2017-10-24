package ru.otus.servlet;

import ru.otus.domain.UserDataSet;
import ru.otus.entityframework.dbservice.CachedHibernateDbService;
import ru.otus.entityframework.dbservice.DbService;
import ru.otus.template.TemplateUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static ru.otus.util.ServletUtils.writeResponse;

/**
 * User: Vladimir Koba
 * Date: 23.10.2017
 * Time: 23:26
 */
public class RegisterServlet extends HttpServlet {
    private DbService userDbService;

    public RegisterServlet() {
        userDbService = new CachedHibernateDbService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeResponse(resp, TemplateUtils.getPage("register.html", userCreated()), HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDbService.save(new UserDataSet(null, req.getParameter("login"), req.getParameter("pass")));
        writeResponse(resp, TemplateUtils.getPage("login.html", userCreated()), HttpServletResponse.SC_OK);
    }

    private Map<String, Object> userCreated() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userCreated", true);
        return map;
    }
}
