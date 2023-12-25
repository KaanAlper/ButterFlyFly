/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.main.butterflyfly;

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
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author halis
 */

public class KayitOlustur extends javax.swing.JDialog {
    
    private DefaultComboBoxModel<String> bolgeComboBoxModel;
    private DefaultListModel<String> sehirListModel;
    private DefaultComboBoxModel<String> bolgeComboBoxModel2;
    private DefaultListModel<String> sehirListModel2;
    private String secilenSehir;
    private String secilenSehir2;
    
    public KayitOlustur(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        bolgeComboBoxModel = new DefaultComboBoxModel<>();
        sehirListModel = new DefaultListModel<>();
        bolgeComboBoxModel2 = new DefaultComboBoxModel<>();
        sehirListModel2 = new DefaultListModel<>();
        initComponents();
        
        jComboBox1.setModel(bolgeComboBoxModel);
        jList2.setModel(sehirListModel);
        jComboBox2.setModel(bolgeComboBoxModel2);
        jList3.setModel(sehirListModel2);
        populateBolgelerComboBox();
        populateBolgelerComboBox2();
        String selectedBolge = (String) jComboBox1.getSelectedItem();
        String selectedBolge2 = (String) jComboBox2.getSelectedItem();
        updateSehirList(selectedBolge);
        updateSehirList2(selectedBolge2);
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBolge = (String) jComboBox1.getSelectedItem();
                updateSehirList(selectedBolge);
            }
        });
        jComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedBolge2 = (String) jComboBox2.getSelectedItem();
                updateSehirList2(selectedBolge2);
            }
        });
       jList2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = jList2.getSelectedIndex();
                    if (index != -1) {
                        secilenSehir = sehirListModel.getElementAt(index);
                        System.out.println("Seçilen Şehir: " + secilenSehir);
                    }
                }
            }
        });
        jList3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int index = jList3.getSelectedIndex();
                    if (index != -1) {
                        secilenSehir2 = sehirListModel2.getElementAt(index);
                        System.out.println("Seçilen Şehir: " + secilenSehir2);
                    }
                }
            }
        });
        
    
    }
    
private void populateBolgelerComboBox() {
        bolgeComboBoxModel.removeAllElements(); // Temizleme işlemi
        Main MainBaglantisi =new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            // Check if 'Bolgeler' column exists
            boolean bolgelerVarMi = checkIfColumnExists(connection, "sehirlistesi", "Bolgeler");

            if (bolgelerVarMi) {
                String sql = "SELECT DISTINCT Bolgeler FROM sehirlistesi";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        bolgeComboBoxModel.addElement(resultSet.getString("Bolgeler"));
                    }
                }
            } else {
                // 'Bolgeler' column does not exist, add a default item to the ComboBox
                bolgeComboBoxModel.addElement("Şehirler");

                // Fetch all 'Sehirler' and add them to the list directly
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
        bolgeComboBoxModel2.removeAllElements(); // Temizleme işlemi
        Main MainBaglantisi =new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            // Check if 'Bolgeler' column exists
            boolean bolgelerVarMi = checkIfColumnExists(connection, "sehirlistesi", "Bolgeler");

            if (bolgelerVarMi) {
                String sql = "SELECT DISTINCT Bolgeler FROM sehirlistesi";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        bolgeComboBoxModel2.addElement(resultSet.getString("Bolgeler"));
                    }
                }
            } else {
                // 'Bolgeler' column does not exist, add a default item to the ComboBox
                bolgeComboBoxModel2.addElement("Şehirler");

                // Fetch all 'Sehirler' and add them to the list directly
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
    sehirListModel.clear(); // Temizleme işlemi
    Main MainBaglantisi = new Main();
    try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {

        // Check if 'Bolgeler' column exists
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
                    sehirListModel.addElement(resultSet.getString("Sehirler"));
                }
            }

            // Listeyi belirli bir boyutta sabit tutma
            jList2.setFixedCellWidth(150); // Örnek olarak genişliği 150 piksel
            jList2.setFixedCellHeight(20); // Örnek olarak yüksekliği 20 piksel
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void updateSehirList2(String selectedBolge) {
    sehirListModel2.clear(); // Temizleme işlemi
    Main MainBaglantisi = new Main();
    try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {

        // Check if 'Bolgeler' column exists
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

            // Listeyi belirli bir boyutta sabit tutma
            jList3.setFixedCellWidth(150); // Örnek olarak genişliği 150 piksel
            jList3.setFixedCellHeight(20); // Örnek olarak yüksekliği 20 piksel
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
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Şehirler" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jList2.setModel(sehirListModel);
        jList2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 186, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Şehirler" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jList3.setModel(sehirListModel2);
        jList3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jList3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox2, 0, 186, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KayitOlustur dialog = new KayitOlustur(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
