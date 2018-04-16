
package diary.dao;

import java.sql.*;
import java.util.*;
import diary.domain.Exercise;

public interface Dao<T, K> {

    Exercise create(Exercise ex) throws Exception;

    List<Exercise> getAll();

}

