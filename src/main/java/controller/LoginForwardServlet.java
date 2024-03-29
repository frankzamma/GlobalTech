package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "LoginForwardServlet", value = "/login-page")
public class LoginForwardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente u =  (Utente) session.getAttribute("utente");

        if (u != null) {
            response.sendRedirect("index.html");
        } else {
            String address = "/WEB-INF/public/loginPage.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);

            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
