package diary.ui;

import diary.domain.SportType;
import diary.domain.Exercise;
import diary.domain.DiaryService;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private DiaryService service;
    private Scene newExercise;
    private Scene viewExercises;
    private Scene statistics;
    private VBox eList;

    public Node createExerciseNode(Exercise done) {
        HBox box = new HBox(10);
        Label label  = new Label(done.getContent());
        label.setMinHeight(28);
        box.setPadding(new Insets(0,5,0,5));
        box.getChildren().addAll(label);
        return box;
    }
    
    public void redrawlist() throws SQLException {
        eList.getChildren().clear();

        List<Exercise> exList = service.viewAll();
        exList.forEach(exe -> {
            eList.getChildren().add(createExerciseNode(exe));
        });
    }

    @Override
    public void init() throws ClassNotFoundException{
        service = new DiaryService();
        service.createTable();
    }
    
    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
        
        //New Exercise
        BorderPane asettelu = new BorderPane();

        HBox layoutH = new HBox();
        layoutH.setSpacing(10);

        Button newExe = new Button("Uusi harjoitus");
        Button exe = new Button("Harjoitukseni");
        Button sta = new Button("Tilastoja");

        layoutH.getChildren().add(newExe);
        layoutH.getChildren().add(exe);
        layoutH.getChildren().add(sta);

        GridPane layoutG = new GridPane();
        layoutG.setHgap(60);
        layoutG.setVgap(10);

        ComboBox<SportType> options = new ComboBox<>();
        options.getItems().setAll(SportType.values());

        TextField pvm = new TextField();
        TextField km = new TextField();
        TextField kesto = new TextField();
        Button uusi = new Button("Lisää harjoitus");

        layoutG.add(new Label("     Pvm (ppmmvv)     "), 1, 1);
        layoutG.add(new Label("        Laji          "), 1, 2);
        layoutG.add(new Label("Kilometrit (double km)"), 1, 3);
        layoutG.add(new Label("    Kesto (douple h)  "), 1, 4);
        layoutG.add(pvm, 2, 1);
        layoutG.add(options, 2, 2);
        layoutG.add(km, 2, 3);
        layoutG.add(kesto, 2, 4);
        layoutG.add(uusi, 1, 5);

        asettelu.setTop(layoutH);
        asettelu.setCenter(layoutG);



        uusi.setOnAction((event) -> {
            //int id, enum laji, double km, double kesto, string pvm
            Exercise e = new Exercise(options.getValue(), Double.parseDouble(km.getText()),
                    Double.parseDouble(kesto.getText()), Integer.parseInt(pvm.getText()));
            try {
                service.saveExercise(e);
                pvm.clear();
                km.clear();
                kesto.clear();
                options.setValue(null);
                redrawlist();
                System.out.println("Onnistui");

            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        newExe.setOnAction((event) -> {
            primaryStage.setScene(newExercise);
            System.out.println("uusi");
        });

        exe.setOnAction((event) -> {
            primaryStage.setScene(viewExercises);
            System.out.println("harjoitukseni");
        });

        sta.setOnAction((event) -> {
            primaryStage.setScene(statistics);
            System.out.println("tilastot");
        });

        newExercise = new Scene(asettelu, 500, 400);

        //View exercises
        ScrollPane eScroll = new ScrollPane();
        BorderPane pane = new BorderPane(eScroll);

        HBox layout2 = new HBox();
        layout2.setSpacing(10);

        eList = new VBox(10);
        eList.setMaxHeight(100);
        eList.setMaxWidth(280);
        redrawlist();

        eScroll.setContent(eList);

        pane.setTop(layout2);
        pane.setCenter(eScroll);

        Button newExe2 = new Button("Uusi harjoitus");
        Button exe2 = new Button("Harjoitukseni");
        Button sta2 = new Button("Tilastoja");

        layout2.getChildren().add(newExe2);
        layout2.getChildren().add(exe2);
        layout2.getChildren().add(sta2);

        newExe2.setOnAction((event) -> {
            primaryStage.setScene(newExercise);
            System.out.println("uusi");
        });

        exe2.setOnAction((event) -> {
            primaryStage.setScene(viewExercises);
            System.out.println("harjoitukseni");
        });

        sta2.setOnAction((event) -> {
            primaryStage.setScene(statistics);
            System.out.println("tilastot");
        });

        //tilastoja diaryserviseltä jossain järkevässä muodossa
        viewExercises = new Scene(pane, 500, 400);

        //Statistics
        BorderPane pane2 = new BorderPane();
        HBox layout3 = new HBox();
        layout3.setSpacing(10);
        Button newExe3 = new Button("Uusi harjoitus");
        Button exe3 = new Button("Harjoitukseni");
        Button sta3 = new Button("Tilastoja");
        layout3.getChildren().add(newExe3);
        layout3.getChildren().add(exe3);
        layout3.getChildren().add(sta3);

        newExe3.setOnAction((event) -> {
            primaryStage.setScene(newExercise);
            System.out.println("uusi");
        });

        exe3.setOnAction((event) -> {
            primaryStage.setScene(viewExercises);
            System.out.println("harjoitukseni");
        });

        sta3.setOnAction((event) -> {
            primaryStage.setScene(statistics);
            System.out.println("tilastot");
        });

        //treenikilometrit
        VBox layout4 = new VBox();
        layout4.setSpacing(10);
        Label kilometers = new Label("Kilometreja yhteensä: " + service.getKm() + " kilometriä");
        layout4.getChildren().add(kilometers);
        //käytetty aika
        Label duration = new Label("Harjoitusten kesto yhteensä: " + service.getDurat() + " tuntia");
        layout4.getChildren().add(duration);
        pane2.setTop(layout3);
        pane2.setLeft(layout4);
        
        statistics = new Scene(pane2, 500, 400);

        // Primary stage
        primaryStage.setTitle("Harjoituspäiväkirja");
        primaryStage.setScene(newExercise);
        primaryStage.show();
    }

}
