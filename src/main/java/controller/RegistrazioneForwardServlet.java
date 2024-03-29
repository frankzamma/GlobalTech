package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "RegistrazioneForwardServlet", value = "/registrazione")
public class RegistrazioneForwardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =  request.getSession();
        synchronized (session){
            Utente utente = (Utente) session.getAttribute("utente");

            if(utente != null){
                response.sendRedirect("/index.html");
            }else{
                String address = "/WEB-INF/public/formRegistrazione.jsp";
                RequestDispatcher dispatcher = request.getRequestDispatcher(address);

                dispatcher.forward(request, response);

            }
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
