/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package Database;

import EmotionalSongs.Utente;
import static EmotionalSongs.Registrazione.login;
import static EmotionalSongs.Registrazione.registrazione;
import static EmotionalSongs.Registrazione.contaUtenti;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Server-side method management class
 * @author Auteri Samuele
 * @author Cermisoni Marco
 * @author Oldani Marco
 * @author De Vito Francesco
 */
public class serverES extends UnicastRemoteObject implements InterfacciaDatabase {
    public static final long serialVersionUID = 1L;
    Database db;
    Utente user;

    /**
     * Constructor of the class serverES
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    public serverES() throws RemoteException, SQLException {
        super();
        db = new Database();
    }

    /**
     * Launches the server
     * @param args Main arguments
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    public static void main(String[] args) throws RemoteException, SQLException {
        serverES serverImpl = new serverES();
        Registry registry = LocateRegistry.createRegistry(8999);
        registry.rebind("SERVER", serverImpl);
    }

    public Database getInstance() throws SQLException, RemoteException {
        return db.getInstance();
    }

    /**
     * Method that implements a user's registration for the application
     * @param user A parameter of type Utente
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    public void Registrazione(Utente user) throws RemoteException, SQLException {
        registrazione(user, db);
    }

    /**
     * Method for counting users enrolled in the application
     * @return The method returns an int indicating the number of registered users
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    public int ContaUtenti() throws RemoteException, SQLException{
        return contaUtenti(db);
    }

    /**
     * Method that allows the user to login to the application
     * @param username A parameter of type String that expresses the user's username
     * @param password A parameter of type String that expresses the user's password
     * @return The method returns a Boolean indicating whether or not the login is correct
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public Boolean Login (String username, String password) throws SQLException, RemoteException{
        Boolean flag;
        flag = login(username, password, db);
        return flag;
    }

    /**
     * Method that takes the login query
     * @param query A parameter of type Query
     * @return The method returns a type Utente
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public Utente QueryLogin(Query query) throws SQLException, RemoteException{
        Statement stm = db.getInstance().getStatement();
        ResultSet rs = stm.executeQuery(query.getQuery());

        while(rs.next()){
            String codF = rs.getString(1);
            String nome = rs.getString(2);
            String cognome = rs.getString(3);
            String dataNascita = rs.getString(4);
            String indirizzo = rs.getString(5);
            String email = rs.getString(6);
            String username = rs.getString(7);
            String password = rs.getString(8);
            user = new EmotionalSongs.Utente(nome, cognome, codF, dataNascita, indirizzo, email, username, password);
        }
        return user;
    }

    /**
     * Method that returns the code of a song
     * @param query A parameter of type Query
     * @return The method returns the code of a song as a String
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public String getCodcan(Query query) throws SQLException, RemoteException {
        Statement stm = db.getStatement();
        ResultSet rs = stm.executeQuery(query.getQuery());
        String codice = null;
        while (rs.next()){
            codice = rs.getString(1);
        }
        return codice;
    }

    /**
     * Method for searching for a song by title
     * @param query A parameter of type Query
     * @return The method returns a matrix of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    public String[][] cercaBranoMusicaleTitolo(Query query) throws SQLException{
        Statement stm = db.getStatement();
        ResultSet rs = stm.executeQuery(query.getQuery());
        rs.next();

        //Raccolta dei brani in un array con i rispettivi codici
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayCod = new ArrayList<>();

        while(rs.next()){
            String tit = rs.getString(1);
            String cod = rs.getString(2);
            arrayList.add(tit);
            arrayCod.add(cod);
        }

        //Cambio gli arraylist in array
        Object[] arrayCanz = arrayList.toArray();
        Object[] arrayCodici = arrayCod.toArray();

        //Creo una matrice e la riempio in modo da avere due colonne; nella prima ci sarà il titolo e nella seconda colonna il suo codice
        String[][] matrice = new String[arrayList.size()][2];

        for(int i=0; i<matrice.length; i++){
            matrice[i][0] = arrayCanz[i].toString();
            matrice[i][1] = arrayCodici[i].toString();
        }
        return matrice;
    }

    /**
     * Method for viewing playlists
     * @param query A parameter of type Query
     * @return The method returns an ArrayList of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public ArrayList<String> QueryVisualizzaPlaylist(Query query) throws SQLException, RemoteException{
        Statement stm = db.getStatement();
        ResultSet rs = stm.executeQuery(query.getQuery());
        ArrayList<String> arrayList = new ArrayList<>();

        while(rs.next()){
            String ris = rs.getString(1);
            arrayList.add(ris);
        }
        return arrayList;
    }

    /**
     * Method for searching for a song by author and year
     * @param query A parameter of type Query
     * @return The method returns a matrix of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public String[][] cercaBranoMusicaleAutAnno(Query query) throws SQLException, RemoteException{
        Statement stm = db.getStatement();
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayCod = new ArrayList<>();
        ResultSet rs = stm.executeQuery(query.getQuery());

        //Raccolta dei brani in un array e dei rispettivi codici
        while(rs.next()){
            String tit = rs.getString(1);
            String codice = rs.getString(2);
            arrayList.add(tit);
            arrayCod.add(codice);
        }

        //Cambio gli arraylist in array
        Object[] arrayCanz = arrayList.toArray();
        Object[] arrayCodici = arrayCod.toArray();

        //Creo una matrice e la riempio in modo da avere due colonne; nella prima ci sarà il titolo e nella seconda colonna il suo codice
        String[][] matrice = new String[arrayList.size()][2];

        for(int i=0; i<matrice.length; i++){
            matrice[i][0] = arrayCanz[i].toString();
            matrice[i][1] = arrayCodici[i].toString();
        }
        return matrice;

    }

    /**
     * Method for creating playlists
     * @param playlist A parameter of type Playlist
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public void RegistraPlaylist(EmotionalSongs.Playlist playlist) throws SQLException, RemoteException{
        Statement stm = db.getStatement();

        if((playlist.lunghezza)!=0){
            Object[] array = playlist.getCanzoni().toArray();

            for(int i=0; i<array.length; i++){
                String query="insert into playlist(codcanz,nomeplaylist,codf) Values('"+array[i]+"','"+playlist.getNomePlaylist()+"','"+playlist.getAutore().getCodiceFiscale()+"')";
                Query q = new Query(query);
                stm.executeUpdate(q.getQuery());
            }
        }
    }

    /**
     * Method to search for songs within the playlist
     * @param query A parameter of type Query
     * @return The method returns an ArrayList of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public ArrayList<String> QueryRicercaCanzoniGiaInPlaylist(Query query) throws SQLException, RemoteException{
        Statement stm = db.getStatement();
        ResultSet rs = stm.executeQuery(query.getQuery());
        ArrayList<String> arrayList = new ArrayList<>();

        while(rs.next()){
            String ris = rs.getString(1);
            arrayList.add(ris);
        }
        return arrayList;
    }

    /**
     * Method to give votes to emotions for songs
     * @param query A parameter of type Query
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public void RegistraVotoEmozione(Query query) throws SQLException, RemoteException{
        Statement stm = db.getStatement();
        stm.executeUpdate(query.getQuery());
    }

    /**
     * Method for displaying emotional voting for songs
     * @param query A parameter of type Query
     * @return The method returns an ArrayList of strings
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public ArrayList<String>  QueryCercaVoti (Query query) throws SQLException, RemoteException{
        Statement stm = db.getStatement();
        ResultSet rs = stm.executeQuery(query.getQuery());
        ArrayList<String> arrayList = new ArrayList<>();

        while(rs.next()){
            String ris1 = rs.getString(1);
            String ris2 = rs.getString(2);
            arrayList.add(ris1);
            arrayList.add(ris2);
        }
        return arrayList;
    }
}