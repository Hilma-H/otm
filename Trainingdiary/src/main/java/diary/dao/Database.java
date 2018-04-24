/*
 * 
 */
package diary.dao;

import diary.domain.Exercise;
import diary.domain.SportType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements Dao<Exercise> {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        String lause = "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Harjoitus' AND xtype='U')"
                + "CREATE TABLE Harjoitus "
                + "(id integer PRIMARY KEY, laji varchar(20), km float , kesto float, pvm integer)GO";

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            System.out.println("Running command >> " + lause);
            st.executeUpdate(lause);

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    @Override
    public void create(Exercise e) throws Exception {
        //int id, SportType sport, double km, double duration, int date
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Harjoitus ( laji, km, kesto, pvm) "
                + "VALUES ( ?, ?, ?, ?);");
        stmt.setString(1, e.getType().toString());
        stmt.setDouble(2, e.getKm());
        stmt.setDouble(3, e.getDuration());
        stmt.setInt(4, e.getDate());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

  /*  @Override
      public List<Exercise> getAll() throws SQLException {
        //int id, SportType sport, double km, double duration, int date
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Harjoitus");

        ResultSet rs = stmt.executeQuery();
        List<Exercise> exercises = new ArrayList<>();
        while (rs.next()) {
            SportType type = SportType.valueOf(rs.getNString("laji"));
            Double km = rs.getDouble("km");
            Double duration = rs.getDouble("kesto");
            Integer date = rs.getInt("pvm");

            exercises.add(new Exercise( type, km, duration, date));
        }

        rs.close();
        stmt.close();
        connection.close();

        return exercises;
    } */

    @Override
    public List<Exercise> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
