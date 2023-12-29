/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package Database;

import Database.Database;
import EmotionalSongs.Utente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import static EmotionalSongs.Registrazione.registrazione;

/**
 * Interface that is implemented by the server
 * @author Cermisoni Marco
 * @author Oldani Marco
 * @author De Vito Francescoa
 */
public interface InterfacciaDatabase extends Remote{
    Database getInstance() throws SQLException, RemoteException;

    /**
     * Method that implements a user's registration for the application
     * @param user A parameter of type Utente
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    void Registrazione(Utente user) throws RemoteException,SQLException;

    /**
     * Method for counting users enrolled in the application
     * @return The method returns an int indicating the number of registered users
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    int ContaUtenti() throws RemoteException, SQLException;

    /**
     * Method that allows the user to login to the application
     * @param username A parameter of type String that expresses the user's username
     * @param password A parameter of type String that expresses the user's password
     * @return The method returns a Boolean indicating whether or not the login is correct
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    Boolean Login(String username, String password) throws SQLException, RemoteException;

    /**
     * Method that takes the login query
     * @param query A parameter of type Query
     * @return The method returns a type Utente
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    Utente QueryLogin(Query query) throws SQLException, RemoteException;

    /**
     * Method that returns the code of a song
     * @param query A parameter of type Query
     * @return The method returns the code of a song as a String
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    String getCodcan(Query query) throws SQLException, RemoteException;

    /**
     * Method for searching for a song by title
     * @param query A parameter of type Query
     * @return The method returns a matrix of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    String[][] cercaBranoMusicaleTitolo(Query query) throws SQLException, RemoteException;

    /**
     * Method for viewing playlists
     * @param query A parameter of type Query
     * @return The method returns an ArrayList of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    ArrayList<String> QueryVisualizzaPlaylist(Query query) throws SQLException, RemoteException;

    /**
     * Method for searching for a song by author and year
     * @param query A parameter of type Query
     * @return The method returns a matrix of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    String[][] cercaBranoMusicaleAutAnno(Query query) throws SQLException, RemoteException;

    /**
     * Method for creating playlists
     * @param playlist A parameter of type Playlist
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    void RegistraPlaylist(EmotionalSongs.Playlist playlist) throws SQLException, RemoteException;

    /**
     * Method to search for songs within the playlist
     * @param query A parameter of type Query
     * @return The method returns an ArrayList of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    ArrayList<String> QueryRicercaCanzoniGiaInPlaylist(Query query) throws SQLException, RemoteException;

    /**
     * Method to give votes to emotions for songs
     * @param query A parameter of type Query
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    void RegistraVotoEmozione(Query query) throws SQLException, RemoteException;

    /**
     * Method for displaying emotional voting for songs
     * @param query A parameter of type Query
     * @return The method returns an ArrayList of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    ArrayList<String>  QueryCercaVoti (Query query) throws SQLException, RemoteException;
}