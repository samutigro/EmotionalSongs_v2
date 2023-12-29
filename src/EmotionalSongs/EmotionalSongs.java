/*
Cermisoni Marco, MATRICOLA 748739, VA
Oldani Marco, MATRICOLA 748243, VA
De Vito Francesco, MATRICOLA 749044, VA
Auteri Samuele, MATRICOLA 749710, VA
*/
package EmotionalSongs;

import Database.*;
import Database.serverES;

import javax.swing.*;
import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

import static Database.CreateDatabase.CreaDb;

/**
 * Progetto Laboratorio B: "Emotional Songs", anno scolastico 2022-2023; contains the main.
 * @author Cermisoni Marco
 * @author Auteri Samuele
 * @version 2.0
 */
public class EmotionalSongs {
    static String user ;
    static String pwd ;
    public static Boolean flag;

    /**
     * Launches the application
     * @param args Main arguments
     * @throws SQLException Exception that occurs in Java when there's an error while working with a database using SQL (Structured Query Language) operations; SQL exceptions are typically thrown when there are issues such as: connection errors, syntax errors, constraint violations, data type mismatches, transaction issues, deadlocks and resource exhaustion.
     * @throws RemoteException Exception that occurs in Java applications using the Remote Method Invocation (RMI) technology; this exception is thrown when issues arise during the invocation of remote methods through RMI; some of the situations that can cause a RemoteException include: connection issues, remote exceptions, class not found, timeouts, security issues and serialization issues.
     * @throws NotBoundException Exception that occurs in the context of the Remote Method Invocation (RMI) technology; it is part of the java.rmi package and is thrown when a client tries to access or invoke a remote object that is not currently bound in the RMI registry.
     */
    public static void main(String[] args) throws SQLException, IOException, NotBoundException {
        //Creazione del frame principale
        JFrame frame = new JFrame("Input String");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(null);

        //Etichette per le stringhe
        JLabel label1 = new JLabel("Inserire username Postgres: ");
        label1.setBounds(10, 20, 200, 20);
        frame.add(label1);

        JLabel label2 = new JLabel("Inserire Password Postgres: ");
        label2.setBounds(10, 60, 200, 20);
        frame.add(label2);

        //Campi di testo per l'input delle stringhe
        JTextField textField1 = new JTextField(40);
        textField1.setBounds(200, 20, 100, 20);
        frame.add(textField1);

        JPasswordField textField2 = new JPasswordField(40);
        textField2.setBounds(200, 60, 100, 20);
        frame.add(textField2);

        //Bottone per confermare l'input
        JButton button = new JButton("Conferma");
        button.setBounds(100, 100, 100, 30);
        frame.add(button);

        //Azione del bottone
        button.addActionListener(e -> {
            frame.setVisible(false);
            user = textField1.getText();
            pwd = textField2.getText();
            Database.setUser(user);
            Database.setPassword(pwd);

            try {
                flag = CreaDb(user, pwd);
                System.out.println(flag);
                Database db = Database.getInstance();
                serverES serverImpl = new serverES();
                Registry registry = LocateRegistry.createRegistry(8999);
                registry.rebind("SERVER", serverImpl);

                clienteES emsong = new clienteES();
            } catch (org.postgresql.util.PSQLException ex) {
                JOptionPane.showMessageDialog(null, "Errore di autenticazione: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (AccessException ex) {
                throw new RuntimeException(ex);
            } catch (NotBoundException ex) {
                throw new RuntimeException(ex);
            }catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }

            if(flag!=true){
                ThreadPopola t = new ThreadPopola(user, pwd);
            }
        });

        //Mostra il frame
        frame.setSize(350,200);
        frame.setVisible(true);
    }
}