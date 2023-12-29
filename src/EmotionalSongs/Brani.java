/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;
import Database.Database;
import Database.InterfacciaDatabase;
import Database.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for defining application functions
 * @author Auteri Samuele
 * @author De Vito Francesco
 * @author Cermisoni Marco
 */
public class Brani {
    static Registry registry;

    static {
        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 8999);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    static InterfacciaDatabase databaseInterface;

    static {
        try {
            databaseInterface = (InterfacciaDatabase)registry.lookup("SERVER");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for searching for a song by title
     * @param brano A parameter of type String that expresses the song to input to the method, which will return all songs with the song string in the title
     * @param db A parameter of type Database
     * @return Returns a matrix of strings containing song titles and their unique codes
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    public static String[][] cercaBranoMusicale(String brano, Database db) throws SQLException, RemoteException{
        //Database.Query che cerca tutti i brani che hanno nel titolo la string brano
        String q = "select titolo, codcanz from canzoni where titolo like '%"+brano+"%'";
        Query query = new Query(q);
        String[][] matrice = databaseInterface.cercaBranoMusicaleTitolo(query);
        return matrice;
    }


    /**
     * Method for searching for a song by author and year
     * @param autore A parameter of type String that expresses the name of the author whose songs you want to search for
     * @param anno A parameter of type int that expresses the year of release of the song
     * @param db A parameter of type Database
     * @return Returns a matrix of strings containing song titles and their unique codes
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     */
    public static String[][] cercaBranoMusicale(String autore, int anno, Database db) throws SQLException, RemoteException {
        //Database.Query per cercare i titoli in base ad autore e anno
        String q = "select titolo, codcanz from canzoni where autore ='"+autore+"' and anno="+anno+" group by codcanz";
        Query query = new Query(q);
        String[][] matrice = databaseInterface.cercaBranoMusicaleAutAnno(query);
        return matrice;
    }

    /**
     * Method that returns the code of a song
     * @param canzone A parameter of type String that expresses the name of the song
     * @param db A parameter of type Database
     * @return The method returns the code of a song as a String
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public static String getCod(String canzone, Database db) throws SQLException, RemoteException{
        String q = "select codcanz from canzoni where titolo = '" + canzone + "'";
        Query query = new Query(q);
        String codice = databaseInterface.getCodcan(query);
        return codice;
    }

    /**
     * Void method that allows the recording of an emotion for a song through a vote
     * @param query A parameter of type Query
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public static void registraVotoEmozione(Query query) throws SQLException, RemoteException {
        databaseInterface.RegistraVotoEmozione(query);
    }

    /**
     * Method that allows the creation of a playlist
     * @param playlist A parameter of type Playlist
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     */
    public static void registraPlaylist(Playlist playlist) throws SQLException, RemoteException {
        databaseInterface.RegistraPlaylist(playlist);
    }

    /**
     * Method to change the format on the date received as input
     * @param inputDateStr A parameter of type String that expresses the date provided as input
     * @return The method returns a String containing the received date as input with a new format
     */
    public static String convertDateFormat(String inputDateStr) {
        String outputDateStr = null;
        try {
            // Definisce il formato di input
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Parsa la data di input
            Date inputDate =  inputDateFormat.parse(inputDateStr);

            // Definisce il formato di output
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Formatta la data nel nuovo formato
            outputDateStr = outputDateFormat.format(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateStr;
    }

    /**
     * Method to populate the song database
     * @param db A parameter of type Database
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws IOException Exception used to handle input and output errors
     */
    public static void popola(Database db) throws SQLException, IOException, IOException {
        FileReader fr = new FileReader("src/FiveHundredThousandSongs.txt");
        BufferedReader br = new BufferedReader(fr);
        String line="";
        String anno="";
        String codcanz="";
        String autore="";
        String titolo="";
        String query="";
        int xxx=257788*2;
        String [] ar;
        query="insert into canzoni (codcanz,titolo,autore,anno) values (?,?,?,?)";
        PreparedStatement ps = db.getConnection().prepareStatement(query);
        do{ line=br.readLine();
            ar=line.split("<SEP>");
            anno=ar[0];
            codcanz=ar[1];
            autore=ar[2];
            titolo=ar[3];

            ps.setString(1,codcanz);
            ps.setString(2,titolo);
            ps.setString(3,autore);
            ps.setInt(4,Integer.parseInt(anno));
            ps.executeUpdate();
            xxx--;
        }while(xxx!=0);
    }
}