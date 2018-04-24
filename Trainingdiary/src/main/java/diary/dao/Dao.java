
package diary.dao;

import java.sql.*;
import java.util.*;
import diary.domain.Exercise;

public interface Dao<T> {

    void create(T e) throws Exception;

    List<Exercise> getAll() throws SQLException;

}

