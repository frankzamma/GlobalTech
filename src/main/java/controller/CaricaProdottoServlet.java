package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CaricaProdottoServlet", value = "/carica-prodotto")
@MultipartConfig
public class CaricaProdottoServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address ;

        if(request.getParameter("prod") != null){
            address  = "WEB-INF/result/caricamentoProdottoResult.jsp";


         }else {


             ArrayList<String> errorPar = new ArrayList<>();

             String nome = request.getParameter("nome");

             if (nome == null || nome.length() < 3)
                 errorPar.add("nome");

             String marca = request.getParameter("marca");

             if (marca == null || marca.length() < 3)
                 errorPar.add("marca");

             String colore = request.getParameter("colore");

             if (colore == null || colore.length() < 3)
                 errorPar.add("colore");

             float prezzo;
             if (request.getParameter("prezzo") != null) {
                 try {
                     prezzo = Float.parseFloat(request.getParameter("prezzo"));
                 } catch (NumberFormatException e) {
                     prezzo = -1;
                 }
             } else {
                 prezzo = -1;
             }

             if (prezzo < 0.01)
                 errorPar.add("prezzo");

             String descrizione = request.getParameter("descrizione");

             boolean batteria;

             if (request.getParameter("batteria") != null && request.getParameter("batteria").equals("true")) {
                 batteria = true;
             } else {
                 batteria = false;
             }

             String ramTipo = request.getParameter("ram_tipo"), ramUnit = request.getParameter("ram_unit");
             int ramQuantita = 0;

             if (ramTipo == null)
                 errorPar.add("ram_tipo");
             else if (ramTipo.equalsIgnoreCase("nessuna")) {
                 ramTipo = null;
             } else {
                 if (!ramTipo.matches("^DDR[3-5]$")) {
                     errorPar.add("ram_tipo");
                 } else {
                     if (request.getParameter("ram_quantita") != null) { //ram_quantita
                         try {
                             ramQuantita = Integer.parseInt(request.getParameter("ram_quantita"));
                         } catch (NumberFormatException e) {
                             ramQuantita = -1;
                         }
                     } else
                         ramQuantita = -1;

                     if (ramUnit == null || !ramUnit.toLowerCase().matches("^[kgm]b$"))
                         errorPar.add("ram_unit");
                 }
             }

             String ramQuantityAndUnit = null;
             if (ramQuantita < 0)
                 errorPar.add("ram_quantita");
             else
                 ramQuantityAndUnit = ramQuantita + ramUnit;


             String nomeCpu = request.getParameter("cpu_nome");
             int disponibilita;

             if (request.getParameter("disponibilita") != null) {
                 try {
                     disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
                 } catch (NumberFormatException e) {
                     disponibilita = -1;
                 }
             } else {
                 disponibilita = -1;
             }

             if (disponibilita < 1)
                 errorPar.add("disponibilita");

             String sistemaOperativo = request.getParameter("sistema_operativo");


             if (errorPar.isEmpty()) {
                 Prodotto prod = new Prodotto();

                 prod.setNome(nome);
                 prod.setMarca(marca);
                 prod.setColore(colore);
                 prod.setPrezzoListino(prezzo);
                 prod.setDescrizione(descrizione);
                 prod.setSistemaOperativo(sistemaOperativo);
                 prod.setTipoRam(ramTipo);
                 prod.setQuantitaRam(ramQuantityAndUnit);
                 prod.setCpuNome(nomeCpu);
                 prod.setBatteria(batteria);
                 prod.setDisponibilita(disponibilita);

                 ProdottoDAO service = new ProdottoDAO();

                 int id = service.doSaveProdotto(prod);

                 prod.setId(id);

                 request.setAttribute("prodotto", prod);

                 address = "/WEB-INF/result/caricamentoProdottoResult.jsp";
             } else {
                 request.setAttribute("error_parameter", errorPar);
                 address = "formCaricamentoProdotto.jsp";
             }
         }
        RequestDispatcher dispatcher =  request.getRequestDispatcher(address);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}