package ru.otus.util;

import ru.otus.template.TemplateUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: Vladimir Koba
 * Date: 23.10.2017
 * Time: 23:31
 */
public class ServletUtils {
    public static void writeResponse(HttpServletResponse resp, String page, int status) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(status);
        try(PrintWriter pw = resp.getWriter()) {
            pw.println(page);
        }
    }
}
