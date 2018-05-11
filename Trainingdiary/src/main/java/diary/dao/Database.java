package diary.dao;

/**
 * Luokka vastaa SQL tietokannasta
 */
import diary.domain.Exercise;
import diary.domain.SportType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements Dao<Exercise> {

    private String databaseAddress;

    /**
     * Perustaa tietokantaolion
     *
     * @param databaseAddress
     * @throws ClassNotFoundException
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * Yhdistää tietokannan
     *
     * @return @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    /**
     * Luo tietokantaan Harjoitus -taulun
     *
     * @return
     */
    public String init() {
        String createTable = "CREATE TABLE Harjoitus "
                + "(id integer PRIMARY KEY, laji varchar(20), km float , kesto float, pvm integer)";

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            st.executeUpdate(createTable);
            return "success";
        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            return "not";
        }

    }

    /**
     * Tallentaa tietokantaan harjoituksen, jonka parametrit exercise oliolta
     *
     * @param e
     * @throws Exception
     */
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

    /**
     * Hakee kaikki tietokanna harjoitukset ja tekee niistä listan
     *
     * @return lista kaikista harjoituksista (Exercise olioita)
     * @throws SQLException
     */
    @Override
    public List<Exercise> getAll() throws SQLException {
        //int id, SportType sport, double km, double duration, int date
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Harjoitus");

        ResultSet rs = stmt.executeQuery();
        List<Exercise> exercises = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String type = rs.getString("laji");
            Double km = rs.getDouble("km");
            Double duration = rs.getDouble("kesto");
            Integer date = rs.getInt("pvm");

            exercises.add(new Exercise(id, SportType.valueOf(type), km, duration, date));
        }

        rs.close();
        stmt.close();
        connection.close();

        return exercises;
    }

    /**
     * Hakee kaikkien harjoitusten kilometrimäärän ja summaa ne
     *
     * @return kilometrimäärä yhteensä
     * @throws SQLException
     */
    public Double getKm() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT SUM(km) FROM Harjoitus");

        ResultSet rs = stmt.executeQuery();
        Double kilometers = rs.getDouble("SUM(km)");

        rs.close();
        stmt.close();
        connection.close();

        return kilometers;
    }

    /**
     * Hakee kaikkien harjoitusten keston ja summaa ne
     *
     * @return kesto yhteensä
     * @throws SQLException
     */
    public Double getDuration() throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT SUM(kesto) FROM Harjoitus");

        ResultSet rs = stmt.executeQuery();
        Double duration = rs.getDouble("SUM(kesto)");

        rs.close();
        stmt.close();
        connection.close();

        return duration;
    }

    public void delete(int id) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Harjoitus WHERE id = ?");
        stmt.setInt(1, id);

        stmt.executeUpdate();
        stmt.close();
        connection.close();

    }
}
