package com.main.butterflyfly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Kaan Alper
 */

public class KayitOlustur extends javax.swing.JDialog {
    
    private DefaultComboBoxModel<String> BaslangicBolgeModel;
    private DefaultListModel<String> sehirListModel;
    private DefaultComboBoxModel<String> BitisBolgeModel;
    private DefaultListModel<String> sehirListModel2;
    private String secilenSehir;
    private String secilenSehir2;
    private String formattedDateTime;
    
    public KayitOlustur(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        BaslangicBolgeModel = new DefaultComboBoxModel<>();
        sehirListModel = new DefaultListModel<>();
        BitisBolgeModel = new DefaultComboBoxModel<>();
        sehirListModel2 = new DefaultListModel<>();
        initComponents();       
        populateBolgelerComboBox();
        populateBolgelerComboBox2();       
        String selectedBolge = (String) BaslangıcBolgeBox.getSelectedItem();
        String selectedBolge2 = (String) BitisBolgeBox.getSelectedItem();
        updateSehirList(selectedBolge);
        updateSehirList2(selectedBolge2);
    }
    
    public JPanel createDateTimePickerPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        // Ay
        String[] monthNames = {"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};
        JComboBox<String> monthComboBox = new JComboBox<>(monthNames);
        JLabel AyLabel=new JLabel("    Ay:");
        AyLabel.setForeground(new java.awt.Color(255,255,255));
        panel.add(AyLabel);
        panel.add(monthComboBox);
        monthComboBox.setBackground(new java.awt.Color(0, 0, 0));
        monthComboBox.setForeground(new java.awt.Color(255,255,255));

        // Gün
        String[] dayValues = new String[31];
        for (int i = 1; i <= 31; i++) {
            dayValues[i - 1] = String.format("%02d", i);
        }
        JComboBox<String> dayComboBox = new JComboBox<>(dayValues);
        dayComboBox.setBackground(new java.awt.Color(0, 0, 0));
        dayComboBox.setForeground(new java.awt.Color(255,255,255));
        JLabel GunLabel=new JLabel("    Gun:");
        GunLabel.setForeground(new java.awt.Color(255,255,255));
        panel.add(GunLabel);
        panel.add(dayComboBox);

        // Saat
        String[] hourValues = new String[24];
        for (int i = 0; i < 24; i++) {
            hourValues[i] = String.format("%02d", i);
        }
        JComboBox<String> hourComboBox = new JComboBox<>(hourValues);
        JLabel SaatLabel=new JLabel("    Saat:");
        SaatLabel.setForeground(new java.awt.Color(255,255,255));
        panel.add(SaatLabel);
        panel.add(hourComboBox);
        hourComboBox.setBackground(new java.awt.Color(0, 0, 0));
        hourComboBox.setForeground(new java.awt.Color(255,255,255));

        // Dakika
        String[] minuteValues = {"00", "15", "30", "45"};
        JComboBox<String> minuteComboBox = new JComboBox<>(minuteValues);
        JLabel DakikaLabel=new JLabel("    Dakika:");
        DakikaLabel.setForeground(new java.awt.Color(255,255,255));
        panel.add(DakikaLabel);
        panel.add(minuteComboBox);
        minuteComboBox.setBackground(new java.awt.Color(0, 0, 0));
        minuteComboBox.setForeground(new java.awt.Color(255,255,255));
        panel.setBackground(new java.awt.Color(7, 11, 21));
        panel.setForeground(new java.awt.Color(255,255,255));
        JButton submitButton = new JButton("Ekle");
        submitButton.setBackground(Color.ORANGE);
        submitButton.setForeground(new java.awt.Color(0,0,0));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMonth = (String) monthComboBox.getSelectedItem();
                String selectedDay = (String) dayComboBox.getSelectedItem();
                String selectedHour = (String) hourComboBox.getSelectedItem();
                String selectedMinute = (String) minuteComboBox.getSelectedItem();
                formattedDateTime = selectedDay +" / "+ selectedMonth+ "  Saat: " + selectedHour + ":" + selectedMinute;
                Zaman.setText(formattedDateTime);
                if(secilenSehir!=null && secilenSehir2!=null){
                    OnaylaButon.setEnabled(true);
                    IptalButon.setEnabled(true);
                }
            }
        });
        panel.add(new JLabel());
        panel.add(submitButton);
        return panel;
    }
       
    private void populateBolgelerComboBox() {
        BaslangicBolgeModel.removeAllElements();
        Main MainBaglantisi =new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            boolean bolgelerVarMi = checkIfColumnExists(connection, "sehirlistesi", "Bolgeler");

            if (bolgelerVarMi) {
                String sql = "SELECT DISTINCT Bolgeler FROM sehirlistesi";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        BaslangicBolgeModel.addElement(resultSet.getString("Bolgeler"));
                    }
                }
            } else {
                BaslangicBolgeModel.addElement("Şehirler");
                String sql = "SELECT DISTINCT Sehirler FROM sehirlistesi";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        sehirListModel.addElement(resultSet.getString("Sehirler"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void populateBolgelerComboBox2() {
        BitisBolgeModel.removeAllElements();
        Main MainBaglantisi =new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            boolean bolgelerVarMi = checkIfColumnExists(connection, "sehirlistesi", "Bolgeler");

            if (bolgelerVarMi) {
                String sql = "SELECT DISTINCT Bolgeler FROM sehirlistesi";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        BitisBolgeModel.addElement(resultSet.getString("Bolgeler"));
                    }
                }
            } else {
                BitisBolgeModel.addElement("Şehirler");
                String sql = "SELECT DISTINCT Sehirler FROM sehirlistesi";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        sehirListModel2.addElement(resultSet.getString("Sehirler"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }  
    
    private void updateSehirList(String selectedBolge) {
        sehirListModel.clear();
        Main MainBaglantisi = new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            boolean bolgelerVarMi = checkIfColumnExists(connection, "sehirlistesi", "Bolgeler");

            String sql;
            if (bolgelerVarMi) {
                sql = "SELECT Sehirler FROM sehirlistesi WHERE Bolgeler = ?";
            } else {
                sql = "SELECT Sehirler FROM sehirlistesi";
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                if (bolgelerVarMi && !selectedBolge.equals("Şehirler")) {
                    preparedStatement.setString(1, selectedBolge);
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        sehirListModel.addElement(resultSet.getString("Sehirler"));
                    }
                }
                BaslangicSehirList.setFixedCellWidth(150); 
                BaslangicSehirList.setFixedCellHeight(20); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void updateSehirList2(String selectedBolge) {
        sehirListModel2.clear();
        Main MainBaglantisi = new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            boolean bolgelerVarMi = checkIfColumnExists(connection, "sehirlistesi", "Bolgeler");
            String sql;
            if (bolgelerVarMi) {
                // 'Bolgeler' sütunu mevcut ise, sadece seçilen bölgeye ait şehirleri getir
                sql = "SELECT Sehirler FROM sehirlistesi WHERE Bolgeler = ?";
            } else {
                // 'Bolgeler' sütunu yoksa, tüm şehirleri getirme
                sql = "SELECT Sehirler FROM sehirlistesi";
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                if (bolgelerVarMi && !selectedBolge.equals("Şehirler")) {
                    preparedStatement.setString(1, selectedBolge);
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        sehirListModel2.addElement(resultSet.getString("Sehirler"));
                    }
                }
                BitisSehirList.setFixedCellWidth(150);
                BitisSehirList.setFixedCellHeight(20);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private boolean checkIfColumnExists(Connection connection, String tableName, String columnName) throws SQLException {
        try (ResultSet resultSet = connection.getMetaData().getColumns(null, null, tableName, columnName)) {
            return resultSet.next();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        AnaYazi = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        BaslangicSehir = new javax.swing.JTextField();
        Zaman = new javax.swing.JTextField();
        BitisSehir = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        IptalButon = new javax.swing.JButton();
        OnaylaButon = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        BitisBolgeBox = new javax.swing.JComboBox<>();
        BitisSehirListKaydirma = new javax.swing.JScrollPane();
        BitisSehirList = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        BaslangıcBolgeBox = new javax.swing.JComboBox<>();
        BaslangicSehirListKaydirma = new javax.swing.JScrollPane();
        BaslangicSehirList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jPanel2.setBackground(new java.awt.Color(7, 11, 21));

        AnaYazi.setFont(new java.awt.Font("Consolas", 1, 30)); // NOI18N
        AnaYazi.setForeground(new java.awt.Color(255, 255, 255));
        AnaYazi.setText("Yeni Uçuş Rezervasyonu Oluştur");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(AnaYazi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(AnaYazi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        jPanel6.setBackground(new java.awt.Color(94, 109, 138));

        BaslangicSehir.setEditable(false);

        Zaman.setEditable(false);

        BitisSehir.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Zaman, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BaslangicSehir, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BitisSehir, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Zaman)
                    .addComponent(BaslangicSehir)
                    .addComponent(BitisSehir))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        IptalButon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Cross.png"))); // NOI18N
        IptalButon.setEnabled(false);
        IptalButon.setPreferredSize(new java.awt.Dimension(32, 32));
        IptalButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IptalButonActionPerformed(evt);
            }
        });

        OnaylaButon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Check.png"))); // NOI18N
        OnaylaButon.setEnabled(false);
        OnaylaButon.setPreferredSize(new java.awt.Dimension(32, 32));
        OnaylaButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnaylaButonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(OnaylaButon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IptalButon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IptalButon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OnaylaButon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(94, 109, 138));

        jPanel5.setBackground(new java.awt.Color(7, 11, 21));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());
        jPanel5.add(createDateTimePickerPanel(), BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(7, 11, 21));
        jPanel4.setMaximumSize(new java.awt.Dimension(198, 202));

        BitisBolgeBox.setBackground(new java.awt.Color(222, 220, 220));
        BitisBolgeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Şehirler" }));
        BitisBolgeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BitisBolgeBoxActionPerformed(evt);
            }
        });
        BitisBolgeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBolge2 = (String) BitisBolgeBox.getSelectedItem();
                updateSehirList2(selectedBolge2);
            }
        });
        BitisBolgeBox.setModel(BitisBolgeModel);

        BitisSehirList.setBackground(new java.awt.Color(222, 220, 220));
        BitisSehirList.setModel(sehirListModel2);
        BitisSehirList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        BitisSehirListKaydirma.setViewportView(BitisSehirList);
        BitisSehirList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = BitisSehirList.getSelectedIndex();
                    if (index != -1) {
                        secilenSehir2 = sehirListModel2.getElementAt(index);
                        if(!secilenSehir.equals(secilenSehir2)||!secilenSehir2.equals(secilenSehir)){
                            BitisSehir.setText(secilenSehir2+" 'a");
                            if(secilenSehir!=null && formattedDateTime!=null){
                                OnaylaButon.setEnabled(true);
                                IptalButon.setEnabled(true);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Başlangıç ile varış noktası aynı olamaz!\nLütfen tekrar seçim yapınız.", "NANII!", JOptionPane.WARNING_MESSAGE);

                            BitisSehir.setText(null);
                            OnaylaButon.setEnabled(false);
                        }
                    }
                }
            }
        });
        BitisSehirList.setModel(sehirListModel2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BitisBolgeBox, 0, 186, Short.MAX_VALUE)
                    .addComponent(BitisSehirListKaydirma, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BitisBolgeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BitisSehirListKaydirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(7, 11, 21));
        jPanel3.setMaximumSize(new java.awt.Dimension(198, 202));

        BaslangıcBolgeBox.setBackground(new java.awt.Color(222, 220, 220));
        BaslangıcBolgeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Şehirler" }));
        BaslangıcBolgeBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BaslangıcBolgeBoxActionPerformed(evt);
            }
        });
        BaslangıcBolgeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBolge = (String) BaslangıcBolgeBox.getSelectedItem();
                updateSehirList(selectedBolge);
            }
        });
        BaslangıcBolgeBox.setModel(BaslangicBolgeModel);

        BaslangicSehirList.setBackground(new java.awt.Color(222, 220, 220));
        BaslangicSehirList.setModel(sehirListModel);
        BaslangicSehirList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        BaslangicSehirListKaydirma.setViewportView(BaslangicSehirList);
        BaslangicSehirList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = BaslangicSehirList.getSelectedIndex();
                    if (index != -1) {
                        secilenSehir = sehirListModel.getElementAt(index);
                        if(!secilenSehir.equals(secilenSehir2)||!secilenSehir2.equals(secilenSehir)){
                            BaslangicSehir.setText(secilenSehir+" 'dan");
                            if(secilenSehir2!=null && formattedDateTime!=null){
                                OnaylaButon.setEnabled(true);
                                IptalButon.setEnabled(true);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Başlangıç ile varış noktası aynı olamaz!\nLütfen tekrar seçim yapınız.", "NANII!", JOptionPane.WARNING_MESSAGE);

                            BaslangicSehir.setText(null);
                            OnaylaButon.setEnabled(false);
                        }

                    }
                }
            }
        });
        BaslangicSehirList.setModel(sehirListModel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BaslangıcBolgeBox, 0, 186, Short.MAX_VALUE)
                    .addComponent(BaslangicSehirListKaydirma, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BaslangıcBolgeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BaslangicSehirListKaydirma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BaslangıcBolgeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaslangıcBolgeBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BaslangıcBolgeBoxActionPerformed

    private void BitisBolgeBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BitisBolgeBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BitisBolgeBoxActionPerformed

    private void OnaylaButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnaylaButonActionPerformed
        Main MainBaglantisi = new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            MainBaglantisi.createTable(MainBaglantisi.yol2, MainBaglantisi.CREATE_TABLE_REZERVASYON);
            PreparedStatement stmt=connection.prepareStatement("INSERT INTO "+MainBaglantisi.REZERVASYON_TABLE_NAME+ "(BaslangicSehir, BitisSehir, Zaman) VALUES (?,?,?);");
            stmt.setString(1,secilenSehir);
            stmt.setString(2,secilenSehir2);
            stmt.setString(3,formattedDateTime);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KayitOlustur.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Kayıt başarı ile oluşturuldu.", "Rezervasyon", JOptionPane.WARNING_MESSAGE);

    }//GEN-LAST:event_OnaylaButonActionPerformed

    private void IptalButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IptalButonActionPerformed
        BitisSehir.setText(null);
        BaslangicSehir.setText(null);
        Zaman.setText(null);
        OnaylaButon.setEnabled(false);
        IptalButon.setEnabled(false);
    }//GEN-LAST:event_IptalButonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AnaYazi;
    private javax.swing.JTextField BaslangicSehir;
    private javax.swing.JList<String> BaslangicSehirList;
    private javax.swing.JScrollPane BaslangicSehirListKaydirma;
    private javax.swing.JComboBox<String> BaslangıcBolgeBox;
    private javax.swing.JComboBox<String> BitisBolgeBox;
    private javax.swing.JTextField BitisSehir;
    private javax.swing.JList<String> BitisSehirList;
    private javax.swing.JScrollPane BitisSehirListKaydirma;
    private javax.swing.JButton IptalButon;
    private javax.swing.JButton OnaylaButon;
    private javax.swing.JTextField Zaman;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
