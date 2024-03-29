package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;


import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "GestioneProdottoServlet", value = "/gestione-prodotto")
public class GestioneProdottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();

        synchronized (session){
            Utente u =  ((Utente) session.getAttribute("utente"));

            if(u != null){
                if(u.isAdmin()){
                    String idString =  request.getParameter("id");
                    if(idString != null){
                        try{
                            int id = Integer.parseInt(idString);
                            ProdottoDAO service =  new ProdottoDAO();

                            Prodotto p =  service.doRetrieveById(id);

                            if(p != null){
                                request.setAttribute("prodotto", p);
                                String address =  "/WEB-INF/admin/gestioneProdotto.jsp";

                                GregorianCalendar lastUpdateOffers = (GregorianCalendar)
                                        this.getServletContext().getAttribute("lastUpdateOffers");
                                OffertaDAO serviceOfferta = new OffertaDAO();

                                if(lastUpdateOffers.before(new GregorianCalendar())){
                                    List<Offerta> offertaList =  serviceOfferta.doRetrieveActive();

                                    this.getServletContext().setAttribute("offerte", offertaList);
                                    this.getServletContext().setAttribute("lastUpdateOffers", new GregorianCalendar());
                                }

                                RequestDispatcher dispatcher =  request.getRequestDispatcher(address);

                                dispatcher.forward(request, response);
                            }else{
                                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                            }

                        }catch (NumberFormatException e){
                            throw new RuntimeException();
                        }
                    }else{
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }else{
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }else{
                response.sendRedirect("login-page");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
