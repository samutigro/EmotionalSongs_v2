/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class for defining and managing playlists
 * @author Auteri Samuele
 * @author Oldani Marco
 */
public class Playlist implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private ArrayList<String> canzoni;
    private Utente autore;
    private String nome;
    public int lunghezza;

    /**
     * Constructor of the class Playlist having two parameters
     * @param nomePlaylist A parameter of type String that expresses the name of the playlist
     * @param utente A parameter of type Utente
     */
    public Playlist(String nomePlaylist, Utente utente){
        this.nome = nomePlaylist;
        this.autore = utente;
        this.lunghezza = 0;
    }

    /**
     * Method that returns all songs in a playlist, if there are any
     * @return The method returns all songs in a playlist via an ArrayList of strings; if there are no songs in the playlist, the method returns null
     */
    public ArrayList<String> getCanzoni(){
        if(lunghezza!=0){
            return this.canzoni;
        }else{
            return null;
        }
    }

    /**
     * Method that returns the playlist name
     * @return The method returns the playlist name as String
     */
    public String getNomePlaylist(){
        return this.nome;
    }

    /**
     * Method that returns the name of the playlist author
     * @return The method returns the name of the playlist author of type Utente
     */
    public Utente getAutore(){
        return this.autore;
    }

    /**
     * Method for adding songs to a playlist
     * @param brani A parameter of type ArrayList that expresses the songs that need to be added to the playlist
     */
    public void addCanzoni(ArrayList<String> brani){
        this.canzoni = brani;
        this.lunghezza = brani.toArray().length;
    }

    /**
     * Method for removing duplicate songs
     * @param lista A parameter of type ArrayList that expresses the songs you want to remove duplicates of
     */
    public static void rimuoviDuplicati(ArrayList<String> lista) {
        HashSet<String> set = new HashSet<>(lista);
        lista.clear();
        lista.addAll(set);
    }

    /**
     * Method for removing duplicates with two parameters
     * @param secondaLista A parameter of type ArrayList of strings
     * @param primaLista A parameter of type ArrayList of strings
     * @return The method returns an ArrayList of strings
     */
    public static ArrayList<String> rimuoviDuplicati(ArrayList<String> secondaLista, ArrayList<String> primaLista) {
        ArrayList<String> risultato = new ArrayList<>(secondaLista);
        risultato.removeAll(primaLista);
        return risultato;
    }
}
