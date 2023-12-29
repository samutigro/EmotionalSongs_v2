/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Class that allows you to check the correctness of the email entered by the user
 * @author De Vito Francesco
 */
public class VerificaMail {
    /**
     * Method that checks the correctness of a user's email
     * @param email A parameter of type String that expresses the user's email
     * @return The method returns a boolean indicating whether the email entered by the user is correct
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}