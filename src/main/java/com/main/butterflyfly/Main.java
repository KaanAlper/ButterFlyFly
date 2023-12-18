package com.main.butterflyfly;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {
    
    String oturum_kurulumu = "CREATE TABLE IF NOT EXISTS Oturum" + "(Kullanici_Adi VARCHAR(255), " + "Sifre VARCHAR(20))";
    
    private static String yol="jdbc:mysql://localhost:3306/butterfly";
    public static void main(String[] args) throws SQLException {

       
         /* 
        Işletim sisteminin temasını İçeri Aktarır
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

       */
        
        /*Frame i Çalıştırır*/
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Kayit k=new Kayit();
                k.setVisible(true);
                
            }
            
        });
       
       
        
    }
}
