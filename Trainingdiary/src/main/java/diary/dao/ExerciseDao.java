
package diary.dao;
import diary.domain.Exercise;
import diary.domain.SportType;
import java.sql.SQLException;
import java.util.List;



public class ExerciseDao implements Dao<Exercise, Integer>{

    @Override
    public Exercise findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Exercise> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public void add(Exercise o) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
