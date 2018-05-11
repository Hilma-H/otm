package diary.domain;

/**
 *
 */
import diary.dao.Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiaryService {

    private String dataAddress;
    private Database data;

    /**
     * Määrittelee tietokannan
     *
     * @throws ClassNotFoundException
     */
    public DiaryService() throws ClassNotFoundException {
        this.dataAddress = "jdbc:sqlite:trainingDiary.db";
        this.data = new Database(dataAddress);
    }

    /**
     * Alustaa tietokannan
     */
    public void createTable() {
        data.init();
    }

    /**
     * Tallentaa harjoituksen tietokantaan
     *
     * @param e
     * @throws Exception
     */
    public void saveExercise(Exercise e) throws Exception {
        //tallentaa harjoituksen tietokantaan
        //kutsuu databasen metodeita (jolla tietokantakäskyt)
        data.create(e);
    }

    /**
     * Listaa kaikki tietokannan harjoitukset ja jos niitä ei ole palauttaa
     * tyhjä listan
     *
     * @return listan, jossa kaikki harjoitukset tai tyhjän listan
     * @throws SQLException
     */
    public List<Exercise> viewAll() throws SQLException {
        //hakee kaikki harjoitukset
        if (data.getAll() == null) {
            return new ArrayList<>();
        }
        return data.getAll();
    }

    /**
     * laskee summan harjoituksien kilometreista
     *
     * @return summan harjoitusten kilometreistä tai nollan jos harjoituksia ei
     * ole
     * @throws SQLException
     */
    public Double getKm() throws SQLException {
        if (data.getKm() == null) {
            return 0.0;
        }

        return data.getKm();
    }

    /**
     * laskee harjoitusten yhteiskeston
     *
     * @return harjoituksen kestot yhteensä tai nollan jos niitä ei ole
     * @throws SQLException
     */
    public Double getDurat() throws SQLException {
        if (data.getDuration() == null) {
            return 0.0;
        }

        return data.getDuration();

    }

    public void deleteId(int id) throws SQLException {
        data.delete(id);
    }
}
