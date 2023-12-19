package com.main.butterflyfly;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {
    static String Oturum = "dosya.x";
    static File dosya = new File(Oturum);
    static int oturum=0;
    static final String DATABASE_NAME = "butterflyfly";
    static final String TABLE_NAME = "oturum";
    static final String USER = "root";
    static final String PASS = "";
    static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
        "(Kullanici_Adi VARCHAR(255), " +
        "Sifre VARCHAR(20))";
    static final String yol="jdbc:mysql://localhost:3306/";
    static final String yol2="jdbc:mysql://localhost:3306/butterflyfly";
    public static boolean checkDatabaseExists(String url, String databaseName, String user, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, databaseName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // Eğer bir sonraki satır varsa, veritabanı mevcuttur.
                }
            }
        } catch (SQLException e) {
            return false; // Bağlantı sağlanamazsa veya sorgu başarısız olursa, veritabanı yoktur.
        }
    }
    public static void createDatabase(String url, String databaseName) {
        try (Connection connection = DriverManager.getConnection(url, USER, PASS);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createTable(String url, String createTableQuery) {
        try (Connection connection = DriverManager.getConnection(url, USER, PASS);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkTableExists(String url, String databaseName, String tableName, String user, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(databaseName, null, tableName, null);

            return resultSet.next(); // Eğer bir sonraki satır varsa, tablo mevcuttur.
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void dosyaOlustur(File dosya) {
        try {
            if (dosya.createNewFile()){}
            else {
                JOptionPane.showMessageDialog(null,"Oturum dosyası oluşturulamadı, uygulamayı yönetici olarak çalıştırın..","Really Nuggi",JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }      
    public static void main(String[] args) throws SQLException {
        Statement SQL=null;

        if (dosya.exists()) {
            oturum=1;
        }
        Connection con= DriverManager.getConnection(yol, USER, PASS);
        SQL = con.createStatement();
        boolean databaseExists = checkDatabaseExists(yol, DATABASE_NAME,USER,PASS);
        if (databaseExists) {
            boolean tableExists = checkTableExists(yol,DATABASE_NAME,TABLE_NAME,USER,PASS);
            if (tableExists) {
                if(oturum==1){

                 /*MYSQL ŞEHİR LİSTESİ SORGUSU, SehirSecim MYSQL Update unutmaaaaaa*/



                }
                else{
                    Object[] secenekler = {"Devam Et","Sıfırla","Iptal"}; 
                    int cevap=JOptionPane.showOptionDialog(null,"Halihazırdı bir oturum var! \nDevam etmek ister misiniz yoksa sıfırlansın mı?","DIKKAT",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,secenekler,secenekler[0]);
                    if(cevap==JOptionPane.YES_OPTION){

                        
                        /*table oluşturulmuş ama dosya.x yok düşün*/


                    }
                    else if(cevap==JOptionPane.NO_OPTION){
                        try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                            // Tabloyu silme sorgusu
                            String sql = "DROP TABLE " + TABLE_NAME;
                            try (Statement statement = connection.createStatement()) {
                                statement.executeUpdate(sql);
                                createTable(yol + DATABASE_NAME, CREATE_TABLE_QUERY);
                                SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    Kayit k=new Kayit();
                                    k.setVisible(true);
                                }});
                            }
                        }   
                        catch (SQLException e) {
                        e.printStackTrace();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"1.olarak az önceki pencereyi almak için mysql e erişimin olması lazım,\nüstelik o ekranı alsan bile bu seçeneği seçecek 2 insandan birisin.\n1. si ise çıkma işlemini test etmeye çalışan ben. Eğer üşenmezsem buraya bir minigame yapıcam.\n Neyse hadi çık bakalım...","Really Nuggi",JOptionPane.WARNING_MESSAGE);
                        System.exit(0);
                    }            
                }
            } else {
                JOptionPane.showMessageDialog(null,"Daha önce bu cihazda program çalıştırılmış!!!\n(ama table oluşturulmamış hmm tuhaf neys...)","DIKKAT",JOptionPane.WARNING_MESSAGE);
                createTable(yol + DATABASE_NAME, CREATE_TABLE_QUERY);
                SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Kayit k=new Kayit();
                    k.setVisible(true);
                }
            });
            }
        }
        else{
            createDatabase(yol, DATABASE_NAME);
            createTable(yol + DATABASE_NAME, CREATE_TABLE_QUERY);
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Kayit k=new Kayit();
                k.setVisible(true);               
            }});
        }
        
        

       
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
    }
}
