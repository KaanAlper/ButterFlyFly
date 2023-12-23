package com.main.butterflyfly;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class Main {
    static String Oturum = "dosya.x";
    static File dosya = new File(Oturum);
    static int oturum=0;
    static int SifremiUnuttum=0;
    static final String DATABASE_NAME = "butterflyfly";
    static final String KAYIT_TABLE_NAME = "oturum";
    static final String BLOCK_TABLE_NAME = "block";
    static final String SEHIRLER_TABLE_NAME = "SehirListesi";
    static final String KURULUM_TABLE_NAME = "KurulumNo";
    static final String BENIHATIRLA_TABLE_NAME = "BeniHatirla";
    static final String USER = "root";
    static final String PASS = "";
    static final String kayitKA = "Kullanici_Adi";
    static final String kayitS = "Sifre";
    static final String blockK = "Kurtarma";
    static final String CREATE_TABLE_KAYIT = "CREATE TABLE IF NOT EXISTS " + KAYIT_TABLE_NAME +
        "("+kayitKA+" VARCHAR(255), " +
        kayitS+" VARCHAR(20))";
    static final String CREATE_TABLE_BLOCK = "CREATE TABLE IF NOT EXISTS " + BLOCK_TABLE_NAME +
        "(availability VARCHAR(255), "+ blockK +" VARCHAR(20))";
    static final String CREATE_TABLE_KURULUM = "CREATE TABLE IF NOT EXISTS " + KURULUM_TABLE_NAME +
    "(No VARCHAR(255))";
    static final String CREATE_TABLE_BENIHATIRLA = "CREATE TABLE IF NOT EXISTS " + BENIHATIRLA_TABLE_NAME +
    "(No VARCHAR(255))";
    static final String yol="jdbc:mysql://localhost:3306/";
    static final String yol2="jdbc:mysql://localhost:3306/butterflyfly";

    public static boolean checkDatabaseExists(String url, String databaseName, String user, String password) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, databaseName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean checkValueInDatabase(String columnName, String valueToCheck, String TABLE_NAME) {
        try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
            String sqlQuery = "SELECT " + columnName + " FROM "+TABLE_NAME+" WHERE " + columnName + " = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                    preparedStatement.setString(1, valueToCheck);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        return resultSet.next(); // Eğer resultSet'te bir sonuç varsa true, yoksa false döner.
                    }
                }

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunda da false dönebilirsiniz.
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

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static boolean checkIfColumnExists(Connection connection, String tableName, String columnName) throws SQLException {
        try (ResultSet resultSet = connection.getMetaData().getColumns(null, null, tableName, columnName)) {
            return resultSet.next(); // Sütun varsa true, yoksa false döner
        }
    }
    
    private static boolean tablodaVeriBulunuyorMu2S(String url, String kullaniciAdi, String sifre, String tabloAdi, String sutunAdi1, String sutunAdi2) {
        // Bağlantıyı aç
        try (Connection connection = DriverManager.getConnection(url, kullaniciAdi, sifre)) {
            // Veri kontrolü için SQL sorgusu
            String sql = "SELECT COUNT(*) FROM " + tabloAdi + " WHERE " + sutunAdi1 + " IS NOT NULL AND " + sutunAdi2 + " IS NOT NULL";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                // Sorgudan dönen sonuçları al
                resultSet.next();
                int rowCount = resultSet.getInt(1);

                // Veri var mı yok mu kontrolü ve sonucu döndür
                return rowCount > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunda false döndür
        }
    }
    
    public static boolean tablodaVeriBulunuyorMu(String url, String kullaniciAdi,String sifre, String tabloAdi, String sutunAdi) {
        // Bağlantıyı aç
        try (Connection connection = DriverManager.getConnection(url, kullaniciAdi,sifre)) {
            // Veri kontrolü için SQL sorgusu
            String sql = "SELECT COUNT(*) FROM " + tabloAdi + " WHERE " + sutunAdi + " IS NOT NULL";

            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                // Sorgudan dönen sonuçları al
                resultSet.next();
                int rowCount = resultSet.getInt(1);

                // Veri var mı yok mu kontrolü ve sonucu döndür
                return rowCount > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunda false döndür
        }
    }
    
    public static void dosyaOlustur(File dosya) {
        try {
            if (!dosya.exists()){
                if (!dosya.createNewFile()){
                    JOptionPane.showMessageDialog(null, "Oturum dosyası oluşturulamadı, uygulamayı yönetici olarak çalıştırın.",
                    "Really Nuggi", JOptionPane.WARNING_MESSAGE);
                }
            }        
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showScrollableTextDialog(String message) {
        JTextArea textArea = new JTextArea(message);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        Object[] secenekler = {"Devam Et","Tekrar Şehir Listesi Ekle"};
        int cevap=JOptionPane.showOptionDialog(
                null,
                scrollPane,
                "Hazır Şehir Listesi", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, secenekler, secenekler[0]);
        if(cevap==JOptionPane.YES_OPTION){
            dosyaOlustur(dosya);
            
            SwingUtilities.invokeLater(() -> {
                SifreEkran ak = new SifreEkran();
                ak.setVisible(true);
            });
            
        } else if(cevap==JOptionPane.NO_OPTION) {
            try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                String sql2 = "DROP TABLE " + SEHIRLER_TABLE_NAME;
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(sql2);
                    dosyaOlustur(dosya);
                    SwingUtilities.invokeLater(() -> {
                        SehirSecim ss = new SehirSecim();
                        ss.setVisible(true);
                    });
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void deleteAllRecordsFromTable(String URL, String username, String password, String TABLE_NAME) {
    try (Connection connection = DriverManager.getConnection(URL, username, password)) {
        // Silme işlemi için SQL ifadesi
        String sql = "DELETE FROM " + TABLE_NAME;
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // SQL ifadesini çalıştır ve tablonun içindeki tüm verileri sil
           preparedStatement.executeUpdate();
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Veriler silinirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
    }
}
    

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
        
        Statement SQL = null;
        if (dosya.exists()) {
            oturum=1;
        }
        Connection con = DriverManager.getConnection(yol, USER, PASS);
        SQL = con.createStatement();
        createTable(yol + DATABASE_NAME, CREATE_TABLE_BENIHATIRLA);
        createTable(yol + DATABASE_NAME, CREATE_TABLE_BLOCK);
        createTable(yol + DATABASE_NAME, CREATE_TABLE_KURULUM);
        createTable(yol + DATABASE_NAME, CREATE_TABLE_KAYIT);
        
        boolean databaseExists = checkDatabaseExists(yol, DATABASE_NAME, USER, PASS);
        if (databaseExists) {           
            boolean blockExists = checkTableExists(yol, DATABASE_NAME, BLOCK_TABLE_NAME, USER, PASS);
            if(blockExists&&checkValueInDatabase("availability", "1", BLOCK_TABLE_NAME)){
                                                JOptionPane.showMessageDialog(null,
                                        "Sistem Bloklanmış. Kilidi açtırmak için lütfen yönetici ile görüşün",
                                        "There is nothing we can do  -Napolyon", JOptionPane.ERROR_MESSAGE);
                                System.exit(0);
                
            }   
            else{
                Connection con2 = DriverManager.getConnection(yol2, USER, PASS);
                if(!tablodaVeriBulunuyorMu(yol2, USER, PASS, BENIHATIRLA_TABLE_NAME, "No")){
                    PreparedStatement stmt2 = con2.prepareStatement("INSERT INTO " +BENIHATIRLA_TABLE_NAME+ "(No) VALUES (?);");
                    stmt2.setString(1, "0");
                    stmt2.executeUpdate();
                }
                if(tablodaVeriBulunuyorMu(yol2, USER, PASS, KURULUM_TABLE_NAME, "No")){
                    deleteAllRecordsFromTable(yol2, USER, PASS, KURULUM_TABLE_NAME);
                    PreparedStatement stmt = con2.prepareStatement("INSERT INTO " +KURULUM_TABLE_NAME+ "(No) VALUES (?);");
                    stmt.setString(1, "1");
                    stmt.executeUpdate();
                }
                if(!tablodaVeriBulunuyorMu(yol2, USER, PASS, BLOCK_TABLE_NAME,"availability")){                    
                    PreparedStatement stmt = con2.prepareStatement("INSERT INTO " +BLOCK_TABLE_NAME+ "(availability,"+blockK+") VALUES (?,?);");
                    stmt.setString(1, "0");
                    stmt.setString(2, "0");
                    stmt.executeUpdate();
                }
                boolean tableExists = checkTableExists(yol, DATABASE_NAME, KAYIT_TABLE_NAME, USER, PASS);
                if (tableExists) {               
                    if  (tablodaVeriBulunuyorMu2S(yol2, USER, PASS, KAYIT_TABLE_NAME, kayitKA, kayitS)){
                        if (oturum == 1) {
                            boolean SehirTableExists = checkTableExists(yol, DATABASE_NAME, SEHIRLER_TABLE_NAME, USER, PASS);
                            if (SehirTableExists) {
                                deleteAllRecordsFromTable(yol2, USER, PASS, KURULUM_TABLE_NAME);
                                PreparedStatement stmt = con2.prepareStatement("INSERT INTO " +KURULUM_TABLE_NAME+ "(No) VALUES (?);");
                                stmt.setString(1, "0");
                                stmt.executeUpdate();   
                                SwingUtilities.invokeLater(() -> {
                                    SifreEkran ak = new SifreEkran();
                                    ak.setVisible(true);
                                });
                                
                            } else {
                                SwingUtilities.invokeLater(() -> {
                                    SehirSecim ss = new SehirSecim();
                                    ss.setVisible(true);
                                });
                            }
                        } else {
                            Object[] secenekler = {"Devam Et", "Sıfırla", "Iptal"};
                            int cevap = JOptionPane.showOptionDialog(null,
                                    "Halihazırda bir oturum var! \nDevam etmek ister misiniz yoksa sıfırlansın mı?",
                                    "DIKKAT", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    null, secenekler, secenekler[0]);
                            if (cevap == JOptionPane.YES_OPTION) {
                                boolean SehirTableExists = checkTableExists(yol, DATABASE_NAME, SEHIRLER_TABLE_NAME, USER, PASS);
                                if (SehirTableExists) {
                                    Object[] secenekler2 = {"Devam Et", "Sıfırla"};
                                    int cevap2 = JOptionPane.showOptionDialog(null,
                                            "Halihazırda bir sehir listesi bulunuyor! \nDevam etmek ister misiniz yoksa sıfırlansın mı?",
                                            "DIKKAT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                            null, secenekler2, secenekler2[0]);
                                    if (cevap2 == JOptionPane.YES_OPTION) {
                                        try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                                            if (connection != null) {

                                                // 'Bolgeler' sütunu var mı diye kontrol et
                                                boolean bolgelerVarMi = checkIfColumnExists(connection, "sehirlistesi", "Bolgeler");

                                                // Gruplama sorgusu
                                                String sql;
                                                if (bolgelerVarMi) {
                                                    // 'Bolgeler' sütunu varsa
                                                    sql = "SELECT Bolgeler, Sehirler FROM sehirlistesi";
                                                } else {
                                                    // 'Bolgeler' sütunu yoksa
                                                    sql = "SELECT Sehirler FROM sehirlistesi";
                                                }

                                                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                                     ResultSet resultSet = preparedStatement.executeQuery()) {

                                                    // 2. sütuna göre gruplanmış verileri içine ekleyeceğimiz liste
                                                    Map<String, List<String>> gruplanmisVeriler = new HashMap<>();

                                                    while (resultSet.next()) {
                                                        String bolge = bolgelerVarMi ? resultSet.getString("Bolgeler") : null;
                                                        String sehir = resultSet.getString("Sehirler");

                                                        if (bolgelerVarMi) {
                                                            // 'Bolgeler' sütunu varsa ona göre gruplandır
                                                            gruplanmisVeriler.computeIfAbsent(bolge, k -> new ArrayList<>()).add(sehir);
                                                        } else {
                                                            // 'Bolgeler' sütunu yoksa 1. sütunu alfabetik olarak gruplandır
                                                            gruplanmisVeriler.computeIfAbsent("AlfabetikSiralama", k -> new ArrayList<>()).add(sehir);
                                                        }
                                                    }
                                                    StringBuilder message=new StringBuilder();
                                                    // Eğer 2. sütun yoksa, 1. sütunu alfabetik olarak sırala
                                                    if (!bolgelerVarMi) {
                                                        List<String> alfabetikSiralama = new ArrayList<>();
                                                        gruplanmisVeriler.values().forEach(alfabetikSiralama::addAll);
                                                        Collections.sort(alfabetikSiralama);
                                                        gruplanmisVeriler.put("AlfabetikSiralama", alfabetikSiralama);
                                                    }

                                                    // Gruplanmış verileri yazdırma
                                                    for (Map.Entry<String, List<String>> entry : gruplanmisVeriler.entrySet()) {
                                                        String groupKey = entry.getKey();
                                                        List<String> values = entry.getValue();
                                                        message.append("\n").append(groupKey).append("\n----------------------------------------------\n");
                                                        for (String value : values) {
                                                                message.append(value).append("\n");
                                                            }
                                                    }
                                                    SwingUtilities.invokeLater(() -> {
                                                    showScrollableTextDialog(message.toString());
                                                        });

                                                }}
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        String sql = "DROP TABLE IF EXISTS" + SEHIRLER_TABLE_NAME;
                                        try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                                            try (Statement statement = connection.createStatement()) {
                                                dosyaOlustur(dosya);
                                                statement.executeUpdate(sql);
                                                SwingUtilities.invokeLater(() -> {
                                                    SehirSecim ss = new SehirSecim();
                                                    ss.setVisible(true);
                                                });
                                            }
                                        }
                                    }
                                } else {
                                    dosyaOlustur(dosya);
                                    SwingUtilities.invokeLater(() -> {
                                        SehirSecim ss = new SehirSecim();
                                        ss.setVisible(true);
                                    });

                                }
                            } else if (cevap == JOptionPane.NO_OPTION) {
                                try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                                    String sql = "DROP TABLE IF EXISTS " + KAYIT_TABLE_NAME;
                                    String sql2 = "DROP TABLE IF EXISTS " + SEHIRLER_TABLE_NAME;
                                    try (Statement statement = connection.createStatement()) {
                                        statement.executeUpdate(sql);
                                        statement.executeUpdate(sql2);
                                        createTable(yol + DATABASE_NAME, CREATE_TABLE_KAYIT);
                                        SwingUtilities.invokeLater(() -> {
                                            Kayit k = new Kayit();
                                            k.setVisible(true);
                                        });
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "1.olarak az önceki pencereyi almak için mysql e erişimin olması lazım,\n"
                                                + "üstelik o ekranı alsan bile bu seçeneği seçecek 2 insandan birisin.\n"
                                                + "1. si ise çıkma işlemini test etmeye çalışan ben. Eğer üşenmezsem buraya bir minigame yapıcam.\n"
                                                + " Neyse hadi çık bakalım...",
                                        "Really Nuggi", JOptionPane.WARNING_MESSAGE);
                                System.exit(0);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Kullanici ve Sifre Degerleri Yazilmamış\n"
                            + "Sistem Sifirlanacak", "DIKKAT", JOptionPane.WARNING_MESSAGE);
                        try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                        String sql = "DROP TABLE IF EXISTS " + KAYIT_TABLE_NAME;
                       
                        try (Statement statement = connection.createStatement()) {
                            statement.executeUpdate(sql);
                            
                            SwingUtilities.invokeLater(() -> {
                                Kayit k = new Kayit();
                                k.setVisible(true);
                            });
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Daha önce bu cihazda program çalıştırılmış!!!\n"
                            + "(ama table oluşturulmamış hmm tuhaf neys...)", "DIKKAT", JOptionPane.WARNING_MESSAGE);
                    try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                        String sql = "DROP TABLE IF EXISTS " + KAYIT_TABLE_NAME;
                        String sql2 = "DROP TABLE IF EXISTS " + SEHIRLER_TABLE_NAME;
                        try (Statement statement = connection.createStatement()) {
                            statement.executeUpdate(sql);
                            statement.executeUpdate(sql2);
                            createTable(yol + DATABASE_NAME, CREATE_TABLE_KAYIT);
                            SwingUtilities.invokeLater(() -> {
                                Kayit k = new Kayit();
                                k.setVisible(true);
                            });
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            
            
            
        } else {
            createDatabase(yol, DATABASE_NAME);
            Connection con2 = DriverManager.getConnection(yol2, USER, PASS);            
            if(tablodaVeriBulunuyorMu(yol2, USER, PASS, KURULUM_TABLE_NAME, "No")){
            deleteAllRecordsFromTable(yol2, USER, PASS, KURULUM_TABLE_NAME);
            PreparedStatement stmt = con2.prepareStatement("INSERT INTO " +KURULUM_TABLE_NAME+ "(No) VALUES (?);");
            stmt.setString(1, "1");
            stmt.executeUpdate();
                }
            if(!tablodaVeriBulunuyorMu(yol2, USER, PASS, BLOCK_TABLE_NAME,"availability")){                    
                    PreparedStatement stmt = con2.prepareStatement("INSERT INTO " +BLOCK_TABLE_NAME+ "(availability,"+blockK+") VALUES (?,?);");
                    stmt.setString(1, "0");
                    stmt.setString(2, "0");
                    stmt.executeUpdate();
                }
            if(!tablodaVeriBulunuyorMu(yol2, USER, PASS, KURULUM_TABLE_NAME, "No")){
                PreparedStatement stmt = con2.prepareStatement("INSERT INTO " +KURULUM_TABLE_NAME+ "(No) VALUES (?);");
                stmt.setString(1, "0");
                stmt.executeUpdate();
            }
            createTable(yol + DATABASE_NAME, CREATE_TABLE_KAYIT);
            SwingUtilities.invokeLater(() -> {
                Kayit k = new Kayit();
                k.setVisible(true);
            });
        }
    }
}
