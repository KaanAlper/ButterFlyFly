package com.main.butterflyfly;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kaan Alper
 */

public class AnaEkran extends javax.swing.JFrame {
    public AnaEkran() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1181, 692));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(6, 11, 21));
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jButton2.setBackground(new java.awt.Color(35, 91, 106));
        jButton2.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Bilet Kaydı Olustur");
        jButton2.setMaximumSize(new java.awt.Dimension(73, 28));
        jButton2.setMinimumSize(new java.awt.Dimension(73, 28));
        jButton2.setPreferredSize(new java.awt.Dimension(73, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 290;
        gridBagConstraints.ipady = 24;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(5, 7, 4, 7);
        jPanel6.add(jButton2, gridBagConstraints);

        jButton8.setBackground(new java.awt.Color(35, 91, 106));
        jButton8.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("Kayıt Düzenleme ve Iptal Islemleri");
        jButton8.setPreferredSize(new java.awt.Dimension(73, 28));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 290;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 9, 2, 6);
        jPanel6.add(jButton8, gridBagConstraints);

        jButton9.setBackground(new java.awt.Color(35, 91, 106));
        jButton9.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Ayarlar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 290;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 4, 6);
        jPanel6.add(jButton9, gridBagConstraints);

        jButton10.setBackground(new java.awt.Color(35, 91, 106));
        jButton10.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("Çıkıs");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 284;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 11, 3, 5);
        jPanel6.add(jButton10, gridBagConstraints);

        jButton11.setBackground(new java.awt.Color(35, 91, 106));
        jButton11.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Kayıt Görüntüleme");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 290;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(2, 9, 4, 6);
        jPanel6.add(jButton11, gridBagConstraints);

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 410, 270));

        jPanel1.setBackground(new java.awt.Color(6, 11, 21));

        jButton1.setBackground(new java.awt.Color(6, 11, 21));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logout-xxl.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 0, 40, 40));

        jPanel7.setBackground(new java.awt.Color(7, 11, 21));

        jLabel3.setBackground(new java.awt.Color(7, 11, 21));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cooltext449619985441150.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 146, 740, 80));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/degisikresim_1.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Connection con2;
        Main MainBaglantisi=new Main();
        Object[] secenekler = {"Oturumu Kapat", "İptal"};
        int cevap = JOptionPane.showOptionDialog(null,
            "Outurumdan çıkılsın mı mı?",
            "DIKKAT", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, secenekler, secenekler[0]);
        if (cevap == JOptionPane.YES_OPTION) {
            if(MainBaglantisi.checkValueInDatabase("No", "1", MainBaglantisi.BENIHATIRLA_TABLE_NAME)){
                try {
                    if(MainBaglantisi.tablodaVeriBulunuyorMu(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS, MainBaglantisi.BENIHATIRLA_TABLE_NAME, "No")){
                        MainBaglantisi.deleteAllRecordsFromTable(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS, MainBaglantisi.BENIHATIRLA_TABLE_NAME);
                    }
                    con2 = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS);
                    PreparedStatement stmt = con2.prepareStatement("INSERT INTO " +MainBaglantisi.BENIHATIRLA_TABLE_NAME+ "(No) VALUES (?);");
                    stmt.setString(1, "0");
                    stmt.executeUpdate();
                    System.exit(0);
                } catch (SQLException ex) {
                    Logger.getLogger(SifreEkran.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                System.exit(0);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton10ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables

}
