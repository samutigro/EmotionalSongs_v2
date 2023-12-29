/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package Database;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class that defines queries
 * @author Auteri Samuele
 */
public class Query implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String query;

    /**
     * Constructor of the class Query
     * @param query A parameter of type String that expresses the query
     */
    public Query(String query){
        this.query = query;
    }

    /**
     * Method that returns the query
     * @return The method returns as a String the query
     */
    public String getQuery() {
        return query;
    }
}