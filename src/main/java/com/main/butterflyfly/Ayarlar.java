
package com.main.butterflyfly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author KaanAlper
 */

public class Ayarlar extends javax.swing.JDialog {

    public Ayarlar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Main MainBaglantisi=new Main();
        MainBaglantisi.createTable(MainBaglantisi.yol2, MainBaglantisi.CREATE_TABLE_WINDOWSTHEME);
        if(MainBaglantisi.checkValueInDatabase("No", "1", MainBaglantisi.WINDOWSTHEME_TABLE_NAME)){
            jToggleButton1.setSelected(true);
            jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png")));
        }
        else{
            jToggleButton1.setSelected(false);
            jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(95, 95, 95));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(26, 27, 29));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ayarlar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 50));

        jPanel3.setBackground(new java.awt.Color(26, 27, 29));
        jPanel3.setPreferredSize(new java.awt.Dimension(88, 88));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/SettingsGear.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 0, -1, -1));

        jPanel4.setBackground(new java.awt.Color(27, 162, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 160, 10));

        jPanel5.setBackground(new java.awt.Color(27, 162, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 90, 20));

        jPanel6.setBackground(new java.awt.Color(27, 162, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 20, 40));

        jToggleButton1.setOpaque(true);
        jToggleButton1.setPreferredSize(new java.awt.Dimension(28, 28));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Programı Sıfırla");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setText("Şehir Listesini Sıfırla");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Şifre Değiştir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Windows'un Temasını Kullan");

        jButton5.setText("Rezervasyonları Sil");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton2)
                .addGap(12, 12, 12)
                .addComponent(jButton1)
                .addGap(12, 12, 12)
                .addComponent(jButton4)
                .addGap(12, 12, 12)
                .addComponent(jButton5))
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 210, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        Main MainBaglantisi=new Main();
        if(jToggleButton1.isSelected()){
            jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/check.png")));
            Connection con2;
            try {       
                con2 = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS);
                if(!MainBaglantisi.tablodaVeriBulunuyorMu(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS, MainBaglantisi.WINDOWSTHEME_TABLE_NAME, "No")){
                    PreparedStatement stmt2 = con2.prepareStatement("INSERT INTO " +MainBaglantisi.WINDOWSTHEME_TABLE_NAME+ "(No) VALUES (?);");
                    stmt2.setString(1, "1");
                    stmt2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Güncellemek için programı yeniden başlatın.");
                }
                else{
                    MainBaglantisi.deleteAllRecordsFromTable(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS , MainBaglantisi.WINDOWSTHEME_TABLE_NAME) ;
                    PreparedStatement stmt2 = con2.prepareStatement("INSERT INTO " +MainBaglantisi.WINDOWSTHEME_TABLE_NAME+ "(No) VALUES (?);");
                    stmt2.setString(1, "1");
                    stmt2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Güncellemek için programı yeniden başlatın.");
                };
            } catch (SQLException ex) {
                Logger.getLogger(Ayarlar.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }
        else{
            jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cross.png")));
            MainBaglantisi.createTable(MainBaglantisi.yol2, MainBaglantisi.CREATE_TABLE_WINDOWSTHEME);
            Connection con2;
            try {       
                con2 = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS);
                if(!MainBaglantisi.tablodaVeriBulunuyorMu(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS, MainBaglantisi.WINDOWSTHEME_TABLE_NAME, "No")){
                    PreparedStatement stmt2 = con2.prepareStatement("INSERT INTO " +MainBaglantisi.WINDOWSTHEME_TABLE_NAME+ "(No) VALUES (?);");
                    stmt2.setString(1, "0");
                    stmt2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Güncellemek için programı yeniden başlatın.");
                }
                else{
                    MainBaglantisi.deleteAllRecordsFromTable(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS , MainBaglantisi.WINDOWSTHEME_TABLE_NAME) ;
                    PreparedStatement stmt2 = con2.prepareStatement("INSERT INTO " +MainBaglantisi.WINDOWSTHEME_TABLE_NAME+ "(No) VALUES (?);");
                    stmt2.setString(1, "0");
                    stmt2.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Güncellemek için programı yeniden başlatın.");
                };
            } catch (SQLException ex) {
                Logger.getLogger(Ayarlar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Object[] secenekler = {"Aç", "Iptal"};
        int cevap = JOptionPane.showOptionDialog(null,
            "Şehir Yenileme Ekranı Açılsın mı??",
            "DIKKAT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, secenekler, secenekler[0]);
        if (cevap == JOptionPane.YES_OPTION) {    
            Main MainBaglantisi=new Main();
            MainBaglantisi.AyarDeğisimi=1;
            dispose();
            SwingUtilities.invokeLater(() -> {
                SehirSecim ss = new SehirSecim();
                ss.setVisible(true);
            });
        }    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Object[] secenekler = {"Aç", "Iptal"};
        int cevap = JOptionPane.showOptionDialog(null,
            "Şifre Yenileme Ekranı Açılsın mı?",
            "DIKKAT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, secenekler, secenekler[0]);
        if (cevap == JOptionPane.YES_OPTION) {    
            Main MainBaglantisi=new Main();
            MainBaglantisi.AyarDeğisimi=1;
            dispose();
            SwingUtilities.invokeLater(() -> {
                Oturum o = new Oturum();
                o.setVisible(true);
            });
        }    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Object[] secenekler = {"Sıfırla", "Iptal"};
        int cevap = JOptionPane.showOptionDialog(null,
            "Sistem Sıfırlansın mı?",
            "DIKKAT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, secenekler, secenekler[0]);
        if (cevap == JOptionPane.YES_OPTION) {    
            Main MainBaglantisi=new Main();
            try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol, MainBaglantisi.USER, MainBaglantisi.PASS)) { 
                String dropDatabaseQuery = "DROP DATABASE " + MainBaglantisi.DATABASE_NAME;
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(dropDatabaseQuery);
                    System.exit(0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Ayarlar.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Object[] secenekler = {"Sıfırla", "Iptal"};
        int cevap = JOptionPane.showOptionDialog(null,
            "Rezervasyonlar Sıfırlansın mı?",
            "DIKKAT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, secenekler, secenekler[0]);
        if (cevap == JOptionPane.YES_OPTION) {    
            Main MainBaglantisi=new Main();
            MainBaglantisi.deleteAllRecordsFromTable(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS, MainBaglantisi.REZERVASYON_TABLE_NAME);
        }
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
