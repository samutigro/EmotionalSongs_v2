/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;

import java.util.regex.Pattern;

/**
 * A class for defining controls for a user's password
 * @author De Vito Francesco
 */
public class ControlloPw {
    /**
     * The method is used to carry out checks on the password entered during registration
     * @param password A parameter of type String that expresses the user's password
     * @return The method returns a boolean indicating whether the password entered by the user meets the conditions for password controls
     */
    public static boolean isPasswordValid(String password) {
        //Verifica la lunghezza minima di 8 caratteri
        if (password.length() <= 8) {
            return false;
        }

        //Verifica la presenza di almeno una lettera maiuscola
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            return false;
        }

        //Verifica la presenza di almeno una lettera minuscola
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            return false;
        }

        //Verifica la presenza di almeno un numero
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            return false;
        }

        //Verifica la presenza di almeno un carattere speciale tra questi: !@#$%^&*()
        if (!Pattern.compile("[!@#\\$%^&*()]+").matcher(password).find()) {
            return false;
        }
        //Se tutti i criteri sono soddisfatti, la password è valida
        return true;
    }
}