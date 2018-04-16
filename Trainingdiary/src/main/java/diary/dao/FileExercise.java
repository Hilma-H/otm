/*
 * tiedon tallentaminen?
 */
package diary.dao;

import diary.domain.Exercise;
import diary.domain.SportType;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileExercise implements Dao {

    public List<Exercise> exercises;
    private String file;

    public FileExercise(String file) throws Exception {
        exercises = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                Exercise ex = new Exercise(generateId(), sType, parts[2], parts[2], parts[3]);
                exercises.add(ex);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }

    }

    private int generateId() {
        return exercises.size() + 1;
    }

    @Override
    public Exercise create(Exercise ex) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List getAll() {
        return exercises;
    }

    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Exercise ex : exercises) {
                writer.write(ex.getId() + ";" + ex.getType() + ";" + ex.getKm() +
                        ";" + ex.getDuration() + ";" + ex.getDate() + "\n");
            }
        }
    }
}
