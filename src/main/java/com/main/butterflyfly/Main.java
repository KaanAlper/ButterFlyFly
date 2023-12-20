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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    static String Oturum = "dosya.x";
    static File dosya = new File(Oturum);
    static int oturum=0;
    static final String DATABASE_NAME = "butterflyfly";
    static final String KAYIT_TABLE_NAME = "oturum";
    static final String SEHIRLER_TABLE_NAME = "SehirListesi";
    static final String USER = "root";
    static final String PASS = "";
    static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + KAYIT_TABLE_NAME +
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
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            return false;
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

    public static void dosyaOlustur(File dosya) {
        try {
            if (dosya.createNewFile()){}
            else {
                JOptionPane.showMessageDialog(null, "Oturum dosyası oluşturulamadı, uygulamayı yönetici olarak çalıştırın.",
                        "Really Nuggi", JOptionPane.WARNING_MESSAGE);
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
                AnaEkran ak = new AnaEkran();
                ak.setVisible(true);
            });
        } else {
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

    public static void main(String[] args) throws SQLException {
        Statement SQL = null;

        if (dosya.exists()) {
            oturum=1;
        }
        Connection con = DriverManager.getConnection(yol, USER, PASS);
        SQL = con.createStatement();
        boolean databaseExists = checkDatabaseExists(yol, DATABASE_NAME, USER, PASS);
        if (databaseExists) {
            boolean tableExists = checkTableExists(yol, DATABASE_NAME, KAYIT_TABLE_NAME, USER, PASS);
            if (tableExists) {
                if (oturum == 1) {
                    boolean SehirTableExists = checkTableExists(yol, DATABASE_NAME, SEHIRLER_TABLE_NAME, USER, PASS);
                    if (SehirTableExists) {
                        SwingUtilities.invokeLater(() -> {
                            AnaEkran ak = new AnaEkran();
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
                            "Halihazırdı bir oturum var! \nDevam etmek ister misiniz yoksa sıfırlansın mı?",
                            "DIKKAT", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, secenekler, secenekler[0]);
                    if (cevap == JOptionPane.YES_OPTION) {
                        boolean SehirTableExists = checkTableExists(yol, DATABASE_NAME, SEHIRLER_TABLE_NAME, USER, PASS);
                        if (SehirTableExists) {
                            Object[] secenekler2 = {"Devam Et", "Sıfırla"};
                            int cevap2 = JOptionPane.showOptionDialog(null,
                                    "Halihazırdı bir sehir listesi bulunuyor! \nDevam etmek ister misiniz yoksa sıfırlansın mı?",
                                    "DIKKAT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                    null, secenekler2, secenekler2[0]);
                            if (cevap2 == JOptionPane.YES_OPTION) {
                                try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                                    if (connection != null) {
                                        System.out.println("Veritabanına bağlandı!");
                                        String sql = "SELECT Bolgeler, Sehirler " + "FROM sehirlistesi";
                                        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                             ResultSet resultSet = preparedStatement.executeQuery()) {
                                            Map<String, List<String>> gruplanmisVeriler = new HashMap<>();
                                            while (resultSet.next()) {
                                                String column2Value = resultSet.getString("Bolgeler");
                                                String column1Value = resultSet.getString("Sehirler");
                                                gruplanmisVeriler.computeIfAbsent(column2Value, k -> new ArrayList<>()).add(column1Value);
                                            }
                                            StringBuilder message = new StringBuilder();
                                            for (Map.Entry<String, List<String>> entry : gruplanmisVeriler.entrySet()) {
                                                String column2Value = entry.getKey();
                                                List<String> column1Values = entry.getValue();
                                                message.append("").append(column2Value).append("\n----------------------------------------------\n");
                                                for (String value : column1Values) {
                                                    message.append(value).append("\n");
                                                }
                                                message.append("\n");
                                            }
                                            SwingUtilities.invokeLater(() -> {
                                                showScrollableTextDialog(message.toString());
                                            });
                                        }
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                String sql = "DROP TABLE " + SEHIRLER_TABLE_NAME;
                                try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                                    try (Statement statement = connection.createStatement()) {
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
                                createTable(yol + DATABASE_NAME, CREATE_TABLE_QUERY);
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
                JOptionPane.showMessageDialog(null, "Daha önce bu cihazda program çalıştırılmış!!!\n"
                        + "(ama table oluşturulmamış hmm tuhaf neys...)", "DIKKAT", JOptionPane.WARNING_MESSAGE);
                try (Connection connection = DriverManager.getConnection(yol2, USER, PASS)) {
                    String sql = "DROP TABLE IF EXISTS " + KAYIT_TABLE_NAME;
                    String sql2 = "DROP TABLE IF EXISTS " + SEHIRLER_TABLE_NAME;
                    try (Statement statement = connection.createStatement()) {
                        statement.executeUpdate(sql);
                        statement.executeUpdate(sql2);
                        createTable(yol + DATABASE_NAME, CREATE_TABLE_QUERY);
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
            createDatabase(yol, DATABASE_NAME);
            createTable(yol + DATABASE_NAME, CREATE_TABLE_QUERY);
            SwingUtilities.invokeLater(() -> {
                Kayit k = new Kayit();
                k.setVisible(true);
            });
        }
    }
}
