package diary.ui;

import diary.domain.SportType;
import diary.domain.Exercise;
import diary.domain.DiaryService;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Scene newExercise;
    private Scene viewExercises;


    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException {
        //New Exercise
        BorderPane asettelu = new BorderPane();

        HBox layoutH = new HBox();
        layoutH.setSpacing(10);
        Button exercise = new Button("Uusi harjoitus");
        Button statistic = new Button("Harjoitukseni");

        layoutH.getChildren().add(exercise);
        layoutH.getChildren().add(statistic);

        GridPane layoutG = new GridPane();

        ComboBox<SportType> options = new ComboBox<>();
        options.getItems().setAll(SportType.values());

        TextField pvm = new TextField();
        TextField km = new TextField();
        TextField kesto = new TextField();
        Button uusi = new Button("Lisää harjoitus");

        layoutG.add(new Label("    Pvm (integer)   "), 1, 1);
        layoutG.add(new Label("    Laji    "), 1, 2);
        layoutG.add(new Label(" Kilometrit (double)"), 1, 3);
        layoutG.add(new Label("   Kesto  (double)  "), 1, 4);
        layoutG.add(pvm, 2, 1);
        layoutG.add(options, 2, 2);
        layoutG.add(km, 2, 3);
        layoutG.add(kesto, 2, 4);
        layoutG.add(uusi, 1, 5);

        asettelu.setTop(layoutH);
        asettelu.setCenter(layoutG);

        DiaryService service = new DiaryService();
        service.createTable();

        uusi.setOnAction((event) -> {
            //int id, enum laji, double km, double kesto, string pvm
            Exercise e = new Exercise( options.getValue(), Double.parseDouble(km.getText()),
                    Double.parseDouble(kesto.getText()), Integer.parseInt(pvm.getText()));
            try {
                service.saveExercise(e);
                //jos onnistui niin tyhjentää kentät?
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        exercise.setOnAction((event) -> {
            primaryStage.setScene(newExercise);
            System.out.println("uusi");
        });

        statistic.setOnAction((event) -> {
            primaryStage.setScene(viewExercises);
            System.out.println("tilastot");
        });

        newExercise = new Scene(asettelu, 500, 400);

        //View exercises
        HBox layout2 = new HBox();
        layout2.setSpacing(10);
        Button exercise2 = new Button("Uusi harjoitus");
        Button statistic2 = new Button("Harjoitukseni");

        layout2.getChildren().add(exercise2);
        layout2.getChildren().add(statistic2);

        exercise2.setOnAction((event) -> {
            primaryStage.setScene(newExercise);
            System.out.println("uusi");
        });

        statistic2.setOnAction((event) -> {
            primaryStage.setScene(viewExercises);
            System.out.println("tilastot");
        });

        //tilastoja diaryserviseltä jossain järkevässä muodossa
        viewExercises = new Scene(layout2, 300, 250);

        // Primary stage
        primaryStage.setTitle("Harjoituspäiväkirja");
        primaryStage.setScene(newExercise);
        primaryStage.show();
    }

}
