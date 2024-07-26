package org.example.w2.todo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.example.w2.common.PageInfo;
import org.example.w2.common.StringUtil;

import java.io.IOException;


@WebServlet(value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("Todo List GET");

        String pageStr = req.getParameter("page");

        log.info("pageStr: ", pageStr);

        int page = StringUtil.getInt(pageStr, 1);

        PageInfo pageInfo = new PageInfo(page,10,131);

        req.setAttribute("pageInfo", pageInfo);

        req.getRequestDispatcher("/WEB-INF/todo/list.jsp").forward(req, resp);

    }
}
