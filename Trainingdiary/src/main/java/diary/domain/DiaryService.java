/*
 * Sovellusluokka, tehdään metodi jota voidaan käyttää main metodissa (saa mainin tiedot tekstikentistä)
 * metodi joka hoitaa tallentamisen
 * 
 */
package diary.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import diary.dao.Database;
import diary.domain.Exercise;
import diary.domain.SportType;
import java.sql.SQLException;

public class DiaryService {

    private String dataAddress;
    private Database data;

    public DiaryService() throws ClassNotFoundException {
        this.dataAddress = "jdbc:sqlite:trainingDiary.db";
        this.data = new Database(dataAddress);
    }

    public void createTable() {
        data.init();
    }

    public void saveExercise(Exercise e) throws Exception {
        //tallentaa harjoituksen tietokantaan
        //kutsuu databasen metodeita (jolla tietokantakäskyt)
        data.create(e);
    }

    public void viewAll() throws SQLException {
        //hakee metodeilla kaikki harjoitukset ja näyttää ne järkevästi
        data.getAll();
    }
}
