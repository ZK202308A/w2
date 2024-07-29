package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.StringUtil;

import java.io.IOException;

@WebServlet(value="/todo/remove")
@Log4j2
public class TodoRemoveController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doPost");

        String tnoStr = req.getParameter("tno");

        Integer tno = StringUtil.getInt(tnoStr, -1);

        boolean result = true;

        resp.sendRedirect("/todo/list?result="+result);


    }
}
