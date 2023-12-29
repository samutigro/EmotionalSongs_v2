/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for the control of the tax code
 * @author Oldani Marco
 */
public class CFvalidator {
    /**
     * The method implements a tax code check
     * @param cf A parameter of type String that expresses a user's tax ID number
     * @return The method returns a boolean
     */
    public static boolean isValidCF(String cf) {
        String CFRegex = "^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$";
        Pattern pattern = Pattern.compile(CFRegex);
        Matcher matcher = pattern.matcher(cf);
        return matcher.matches();
    }
}