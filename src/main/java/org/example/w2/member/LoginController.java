package org.example.w2.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/login")
@Log4j2
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        log.info("-------------------------");
        log.info(uid);
        log.info(upw);

        //JSESSION_ID 가 세션저장소에 있으면 반환,없으면 생성
        HttpSession session = req.getSession();

        //DB에서 해당 사용자 정보를 확인해서 사용자정보를 얻어온다.
        try {
            Optional<MemberVO> result = MemberDAO.INSTANCE.get(uid, upw);

            result.ifPresentOrElse( memberVO -> {
                session.setAttribute("uid", memberVO);
                try {
                    resp.sendRedirect("/mypage");
                }catch(Exception e) {
                    e.printStackTrace();
                }
            } , () -> {
                try {
                    resp.sendRedirect("/login");

                }catch(IOException e){
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }
}






