/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class for defining a user
 * @author Auteri Samuele
 * @author De Vito Francesco
 */
public class Utente implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String nome;
    private final String cognome;
    private final String codiceFiscale;
    private final String indirizzo;
    private String data;
    private String email;
    private String id;
    private String password;

    /**
     * Method that returns the user's name
     * @return The method returns the user's name as a String
     */
    public String getNome() {
        return nome;
    }

    /**
     * Method that returns the user's last name
     * @return The method returns the user's last name as a String
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Method that returns the user's social security number
     * @return The method returns the user's social security number as a String
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Method that returns the user's date of birth
     * @return The method returns the user's date of birth as a String
     */
    public String getData() {
        return data;
    }

    /**
     * Method that returns the user's email
     * @return The method returns the user's email as a String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method that returns the user's address
     * @return The method returns the user's address as a String
     */
    public String getIndirizzo(){
        return indirizzo;
    }

    /**
     * Method that returns the user's username
     * @return The method returns the user's username as a String
     */
    public String getId() {
        return id;
    }

    /**
     * Method that returns the user's password
     * @return The method returns the user's password as a String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Constructor of the class Utente
     * @param nome A parameter of type String that expresses the user's name
     * @param cognome A parameter of type String that expresses the user's last name
     * @param codiceFiscale A parameter of type String that expresses the user's social security number
     * @param data A parameter of type String that expresses the user's date of birth
     * @param indirizzo A parameter of type String that expresses the user's address
     * @param email A parameter of type String that expresses the user's email
     * @param id A parameter of type String that expresses the user's username
     * @param password A parameter of type String that expresses the user's password
     */
    public Utente(String nome, String cognome, String codiceFiscale, String data, String indirizzo, String email, String id, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.data = data;
        this.indirizzo = indirizzo;
        this.email = email;
        this.id = id;
        this.password = password;
    }
}