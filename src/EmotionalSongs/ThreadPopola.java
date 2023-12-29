/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;

import Database.Database;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.SQLException;
import static EmotionalSongs.Brani.popola;

/**
 * Class to populate the database
 * @author Auteri Samuele
 */
public class ThreadPopola extends Thread {


    private final String user;
    private final String pwd;

    /**
     * Constructor of the class ThreadPopola
     * @param user A parameter of type String that expresses the username
     * @param pwd A parameter of type String that expresses the password
     */
    public ThreadPopola(String user, String pwd){
        this.user = user;
        this.pwd = pwd;
        this.start();
    }

    /**
     * Run method that puts the thread in ready state
     */
    @Override
    public void run() {
        Database.setUser(user);
        Database.setPassword(pwd);
        Database db = null;
        try {
            db = Database.getInstance();
            popola(db);
            System.out.println("Completato");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
