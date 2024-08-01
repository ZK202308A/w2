package org.example.w2.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@WebServlet("/member/join")
@Log4j2
public class MemberJoinController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String uid = req.getParameter("uid");
        String email = req.getParameter("email");

        log.info("UID...................." + uid);

        boolean checkJoin = (boolean)session.getAttribute("checkJoin");

        log.info("checkJoin.................." + checkJoin);

        if(checkJoin == false ) {
            if(uid.equals("aaa") == false){ //동일한 사용자가 존재하지 않는 경우
                log.info("newbie........................");
                session.setAttribute("checkJoin", true);
            }
            //resp.sendRedirect("/member/join");
            resp.getWriter().println("<script>alert('aaa')</script>");
        }else {
            log.info("newbie.......................register");
            session.removeAttribute("checkJoin");
            resp.getWriter().println("<script>alert('success')</script>");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if(session.isNew()) {
            session.setAttribute("checkJoin", false);
        }

        req.getRequestDispatcher("/WEB-INF/member/join.jsp").forward(req, resp);

    }
}
