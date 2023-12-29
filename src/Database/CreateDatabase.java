/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package Database;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.*;
import java.util.stream.StreamSupport;

import static EmotionalSongs.Brani.popola;

/**
 * Database creation class
 * @author Auteri Samuele
 * @author Cermisoni Marco
 * @author Oldani Marco
 * @author De Vito Francesco
 */
public class CreateDatabase {
    /**
     * Method that allows the creation of the database if it does not exist
     * @param user A parameter of type String that expresses the username
     * @param pwd A parameter of type String that expresses the password
     * @return The method returns a Boolean
     */
    public static Boolean CreaDb(String user, String pwd){
        String url = "jdbc:postgresql://localhost:5432/";                    //URL di connessione al database PostgreSQL

        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);

            Statement statement = connection.createStatement();

            String createDatabaseQuery = "CREATE DATABASE" +'"' +"EmotionalSongs"+'"';

            String queryIfExist = "SELECT datname FROM pg_database WHERE datname = 'EmotionalSongs';";

            String creaCanzoni = "CREATE TABLE IF NOT EXISTS canzoni( codcanz character varying(500) COLLATE pg_catalog.default NOT NULL,titolo character varying(500) COLLATE pg_catalog.default NOT NULL,autore character varying(500) COLLATE pg_catalog.default NOT NULL,anno numeric NOT NULL,CONSTRAINT canzoni_pkey PRIMARY KEY (codcanz))";

            String creaEmozioni = "CREATE TABLE IF NOT EXISTS emozioni(codcanz character varying(500) COLLATE pg_catalog.default NOT NULL,emozione character varying(500) COLLATE pg_catalog.default NOT NULL,voto numeric(2,1) NOT NULL,codf character varying(500) COLLATE pg_catalog.default NOT NULL,CONSTRAINT emozioni_pkey PRIMARY KEY (codcanz, emozione, codf),CONSTRAINT emozioni_codcanz_fkey FOREIGN KEY (codcanz)REFERENCES public.canzoni (codcanz) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,CONSTRAINT emozioni_codf_fkey FOREIGN KEY (codf)REFERENCES public.utenti (codf) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)";

            String creaPlaylist = "CREATE TABLE IF NOT EXISTS public.playlist(codcanz character varying(500) COLLATE pg_catalog.default NOT NULL,nomeplaylist character varying(500) COLLATE pg_catalog.default NOT NULL,codf character varying(500) COLLATE pg_catalog.default NOT NULL,CONSTRAINT playlist_pkey PRIMARY KEY (codcanz, nomeplaylist, codf),CONSTRAINT playlist_codf_fkey FOREIGN KEY (codf) REFERENCES public.utenti (codf) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION)";

            String creaUtenti = "CREATE TABLE IF NOT EXISTS public.utenti(codf character varying(500) COLLATE pg_catalog.default NOT NULL,nome character varying(500) COLLATE pg_catalog.default NOT NULL,cognome character varying(500) COLLATE pg_catalog.default NOT NULL,datanascita date NOT NULL,indirizzo character varying(200) COLLATE pg_catalog.default NOT NULL,email character varying(200) COLLATE pg_catalog.default NOT NULL,username character varying(50) COLLATE pg_catalog.default NOT NULL,password character varying(200) COLLATE pg_catalog.default NOT NULL,CONSTRAINT utenti_pkey PRIMARY KEY (codf),CONSTRAINT utenti_username_key UNIQUE (username),CONSTRAINT utenti_username_key1 UNIQUE (username))";

            ResultSet rs = statement.executeQuery(queryIfExist);
            String ris = "";

            while (rs.next()){
                ris = rs.getString(1);
                System.out.println(ris);
            }
            if(ris.equals("EmotionalSongs")){
                System.out.println("Già presente");
                return true;
            }
            else{
                try {
                    statement.executeUpdate(createDatabaseQuery);
                    url = url + "EmotionalSongs";

                    connection.close();
                    connection = DriverManager.getConnection(url, user, pwd);
                    statement = connection.createStatement();
                    statement.execute(creaCanzoni);
                    statement.executeUpdate(creaUtenti);
                    statement.executeUpdate(creaEmozioni);
                    statement.executeUpdate(creaPlaylist);
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Database creato con successo!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}