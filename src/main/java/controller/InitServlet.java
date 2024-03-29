package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Categoria;
import model.CategoriaDAO;
import model.Offerta;
import model.OffertaDAO;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "initServlet", value = "/initServlet", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        CategoriaDAO serviceCategorie = new CategoriaDAO();
        List<Categoria> categoriaList = serviceCategorie.doRetrieveAll();
        OffertaDAO serviceOfferta = new OffertaDAO();

        List<Offerta> offertaList =  serviceOfferta.doRetrieveActive();

        GregorianCalendar today =  new GregorianCalendar();

        ServletContext context =  this.getServletContext();

        context.setAttribute("categorie", categoriaList);
        context.setAttribute("offerte", offertaList);
        context.setAttribute("lastUpdateOffers", today);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
