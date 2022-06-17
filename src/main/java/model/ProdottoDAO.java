package model;


import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAO {
    public List<Prodotto> doRetrieveAll(){
        try(Connection con = ConPool.getConnection()){
            String sql ="SELECT * from prodotto";
            Statement stmt = con.createStatement();

            ResultSet res =  stmt.executeQuery(sql);
            List<Prodotto> list =  new ArrayList<>();
            while(res.next()){
                Prodotto i = this.creaProdotto(res);

                list.add(i);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Prodotto doRetrieveById(int id){
        try(Connection con = ConPool.getConnection()){
            String sql ="SELECT * from prodotto" ;
            Statement statement = con.createStatement();
            ResultSet res =  statement.executeQuery(sql);
            res.next();

            return  this.creaProdotto(res);

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public static int doRetrieveNextId(){
        try(Connection con = ConPool.getConnection()){
            String sql =  "SELECT max(id) from prodotto";

            PreparedStatement stmt =  con.prepareStatement(sql);

            ResultSet res =  stmt.executeQuery();

            if(res.next()){
                return res.getInt(1) + 1;
            }else{
                return 0; //non stati ancora inseriti prodotti
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private Prodotto creaProdotto(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String nome = res.getString("nome");
        String marca = res.getString("marca");
        String colore = res.getString("colore");
        float prezzoListino = res.getFloat("prezzo_listino");
        String descrizione = res.getString("descrizione");
        String sistemaOpeativo =  res.getString("so");
        String ramTipo = res.getString("ram_tipo");
        int ramQuantita = res.getInt("ram_quantita");
        String nomeCpu = res.getString("cpu_nome");
        boolean batteria =  res.getBoolean("batteria");
        int disponibilita =  res.getInt("disponibilita");

        Prodotto p = new Prodotto();

        p.setId(id);
        p.setNome(nome);
        p.setMarca(marca);
        p.setColore(colore);
        p.setPrezzoListino(prezzoListino);
        p.setDescrizione(descrizione);
        p.setSistemaOperativo(sistemaOpeativo);
        p.setTipoRam(ramTipo);
        p.setQuantitaRam(ramQuantita);
        p.setCpuNome(nomeCpu);
        p.setBatteria(batteria);
        p.setDisponibilita(disponibilita);
        return p;
    }
}