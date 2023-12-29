/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;

import java.util.regex.Pattern;

/**
 * Class that controls a user's physical address
 * @author De Vito Francesco
 */
public class IndirizzoVerificatore {
    private static final Pattern CAP_PATTERN = Pattern.compile("^\\d{5}$");
    private static final Pattern NUMERO_CIVICO_PATTERN = Pattern.compile("^\\d+$");

    /**
     * Method that performs a series of checks on the correctness of the physical address provided by the user
     * @param indirizzo A parameter of type String that expresses the user's address
     * @return The method returns a boolean that represents whether the user-provided physical address is valid
     */
    public static boolean isIndirizzoValid(String indirizzo) {
        String[] indirizzoParts = indirizzo.split(",");

        if (indirizzoParts.length != 5) {
            return false;
        }

        String viaPiazza = indirizzoParts[0].trim();
        String numeroCivico = indirizzoParts[1].trim();
        String cap = indirizzoParts[2].trim();
        String comune = indirizzoParts[3].trim();
        String provincia = indirizzoParts[4].trim();

        if (!CAP_PATTERN.matcher(cap).matches() || !NUMERO_CIVICO_PATTERN.matcher(numeroCivico).matches()) {
            return false;
        }
        return true;
    }
}