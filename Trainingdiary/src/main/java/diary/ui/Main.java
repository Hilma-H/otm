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
        Button dele = new Button("Poista");
        label.setMinHeight(28);
        box.setPadding(new Insets(0,5,0,5));
        box.getChildren().addAll(label);
        box.getChildren().addAll(dele);
        return box;
    }
    
    public void redrawlist() throws SQLException {
        eList.getChildren().clear();

        List<Exercise> exeList = service.viewAll();
        exeList.forEach(exe -> {
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
        GridPane layoutG = new GridPane();
        layoutG.setHgap(15);
        layoutG.setVgap(10);
        
        Button newExe = new Button("Uusi harjoitus");
        Button exe = new Button("Harjoitukseni");
        Button sta = new Button("Tilastoja");
        Button addExe = new Button("Lisää harjoitus");
        
        ComboBox<SportType> options = new ComboBox<>();
        options.getItems().setAll(SportType.values());

        TextField pvm = new TextField();
        TextField km = new TextField();
        TextField durat = new TextField();
        
        layoutG.add(newExe, 1, 1);
        layoutG.add(exe, 2, 1);
        layoutG.add(sta, 3, 1);
        layoutG.add(new Label("Pvm (ppmmvv)"), 1, 2);
        layoutG.add(new Label("Laji"), 1, 3);
        layoutG.add(new Label("Kilometrit (double km)"), 1, 4);
        layoutG.add(new Label("Kesto (douple h)"), 1, 5);
        layoutG.add(pvm, 2, 2);
        layoutG.add(options, 2, 3);
        layoutG.add(km, 2, 4);
        layoutG.add(durat, 2, 5);
        layoutG.add(addExe, 2, 6);


        addExe.setOnAction((event) -> {
            //int id, enum laji, double km, double kesto, string pvm
            Exercise e = new Exercise(options.getValue(), Double.parseDouble(km.getText()),
                    Double.parseDouble(durat.getText()), Integer.parseInt(pvm.getText()));
            try {
                service.saveExercise(e);
                pvm.clear();
                km.clear();
                durat.clear();
                options.setValue(null);
                redrawlist();

            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        newExe.setOnAction((event) -> {
            primaryStage.setScene(newExercise);
        });

        exe.setOnAction((event) -> {
            primaryStage.setScene(viewExercises);
        });

        sta.setOnAction((event) -> {
            primaryStage.setScene(statistics);
        });

        newExercise = new Scene(layoutG, 500, 400);

        //View exercises
        ScrollPane eScroll = new ScrollPane();
        BorderPane pane2 = new BorderPane(eScroll);
        GridPane layoutH = new GridPane();

        HBox layout2 = new HBox();
        layout2.setSpacing(10);

        eList = new VBox(10);
        eList.setMaxHeight(100);
        eList.setMaxWidth(280);
        redrawlist();

        eScroll.setContent(eList);

        pane2.setTop(layout2);
        pane2.setCenter(eScroll);

        Button newExe2 = new Button("Uusi harjoitus");
        Button exe2 = new Button("Harjoitukseni");
        Button sta2 = new Button("Tilastoja");

        layout2.getChildren().add(newExe2);
        layout2.getChildren().add(exe2);
        layout2.getChildren().add(sta2);

        newExe2.setOnAction((event) -> {
            primaryStage.setScene(newExercise);
        });

        exe2.setOnAction((event) -> {
            primaryStage.setScene(viewExercises);
        });

        sta2.setOnAction((event) -> {
            primaryStage.setScene(statistics);
        });

        viewExercises = new Scene(pane2, 500, 400);

        //Statistics
        BorderPane pane3 = new BorderPane();
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
        });

        exe3.setOnAction((event) -> {
            primaryStage.setScene(viewExercises);
        });

        sta3.setOnAction((event) -> {
            primaryStage.setScene(statistics);
        });

        //treenikilometrit
        VBox layout4 = new VBox();
        layout4.setSpacing(10);
        Label kilometers = new Label("Kilometreja yhteensä: " + service.getKm() + " kilometriä");
        layout4.getChildren().add(kilometers);
        //käytetty aika
        Label duration = new Label("Harjoitusten kesto yhteensä: " + service.getDurat() + " tuntia");
        layout4.getChildren().add(duration);
        pane3.setTop(layout3);
        pane3.setLeft(layout4);
        
        statistics = new Scene(pane3, 500, 400);

        // Primary stage
        primaryStage.setTitle("Harjoituspäiväkirja");
        primaryStage.setScene(newExercise);
        primaryStage.show();
    }

}
