package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class OffertaDAO {

    public List<Offerta> doRetrieveActive(){
        try (Connection con = ConPool.getConnection()) {
            List <Offerta> l = new ArrayList<>();
            String sql = "SELECT * FROM offerta WHERE data_inizio<=current_date() " +
                    "and data_fine >= current_date()";
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                l.add(this.creaOfferta(rs));
            }
            return l;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Offerta doRetrieveById(int id){
        try (Connection con = ConPool.getConnection()) {
            Offerta o = null;
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM offerta WHERE id="+id);
            if (rs.next()){
                return this.creaOfferta(rs);
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int doSaveOfferta(Offerta offerta){
        try(Connection con =  ConPool.getConnection()){
            PreparedStatement stmt = con.prepareStatement("INSERT INTO offerta(nome, percentuale," +
                    " data_inizio, data_fine) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, offerta.getNome());
            stmt.setFloat(2, offerta.getPercentuale());
            stmt.setDate(3, Date.valueOf(offerta.getDataInizio()));
            stmt.setDate(4, Date.valueOf(offerta.getDataFine()));

            int result = stmt.executeUpdate();

            if(result > 0) {
                ResultSet res = stmt.getGeneratedKeys();
                res.next();
                return res.getInt(1);
            }else{
                throw new RuntimeException();
            }


        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private Offerta creaOfferta(ResultSet res) throws SQLException{
        int id = res.getInt(1);
        String nome = res.getString(2);
        float percentuale = res.getFloat(3);
        Date dataInizio = res.getDate(4);
        Date dataFine = res.getDate(5);

        Offerta offerta = new Offerta();
        offerta.setId(id);
        offerta.setNome(nome);
        offerta.setPercentuale(percentuale);
        offerta.setDataInizio(dataInizio.toLocalDate());
        offerta.setDataFine(dataFine.toLocalDate());

        return offerta;
    }
}
