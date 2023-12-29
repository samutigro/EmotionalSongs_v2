/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package Database;

import java.io.Serial;
import java.io.Serializable;
import java.sql.*;

/**
 * Class for setting up and using the database
 * @author Auteri Samuele
 * @author Cermisoni Marco
 * @author Oldani Marco
 */
public class Database implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    //Variabili login
    private final String protocol = "jdbc:postgresql://";
    private final String host = "localhost/";
    private final String db_name = "EmotionalSongs";
    private final String url = protocol + host + db_name;
    private static String user;
    private static String password;

    //Variabili connessione DB
    private static Database database;
    private static Connection connection;
    private static Statement statement;

    /**
     * Constructor of the class Database
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    public Database() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    //Metodo statico per Pattern Singleton
    public static Database getInstance() throws SQLException {
        if (database == null)
            database = new Database();
        return database;
    }

    /**
     * Method to set the username
     * @param u A parameter of type String that expresses the username
     */
    public static void setUser(String u){
        user = u;
    }

    /**
     * Method to set the password
     * @param p A parameter of type String that expresses the password
     */
    public static void setPassword(String p){
        password = p;
    }

    public static Statement getStatement() {
        return statement;
    }

    public static Connection getConnection(){
        return connection;
    }
}