package br.com.fiap.fintech.singleton;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {
	
    public static Statement createStatement() throws SQLException {
        Statement stmt;
        
        try {
            stmt = DatabaseConnection.getConnection().createStatement();
            return stmt;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    DatabaseUtils() { }

}
