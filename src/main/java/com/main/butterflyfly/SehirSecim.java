package com.main.butterflyfly;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kaan Alper
 */

public class SehirSecim extends javax.swing.JFrame {
    public SehirSecim() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        ButonDosyaSec = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        ButonYukle = new javax.swing.JButton();
        ButonDevam = new javax.swing.JButton();
        BolgeOnay = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel5.setBackground(new java.awt.Color(19, 85, 242));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(236, 170, 13));
        jLabel1.setText("Şehir Dosyası Yükleyin");
        jPanel5.add(jLabel1, new java.awt.GridBagConstraints());

        jPanel1.setBackground(new java.awt.Color(0, 165, 243));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jPanel3.setBackground(new java.awt.Color(173, 245, 245));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ButonDosyaSec.setText("XLSX Dosyası Seçin");
        ButonDosyaSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButonDosyaSecActionPerformed(evt);
            }
        });

        jPanel6.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ButonYukle.setBackground(new java.awt.Color(51, 255, 153));
        ButonYukle.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        ButonYukle.setText("Yükle");
        ButonYukle.setEnabled(false);
        ButonYukle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButonYukleActionPerformed(evt);
            }
        });

        ButonDevam.setBackground(new java.awt.Color(8, 222, 23));
        ButonDevam.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        ButonDevam.setText("Devam");
        ButonDevam.setEnabled(false);
        ButonDevam.setPreferredSize(new java.awt.Dimension(75, 31));
        ButonDevam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButonDevamActionPerformed(evt);
            }
        });

        BolgeOnay.setText("Bölgeler Dahil");
        BolgeOnay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BolgeOnayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ButonDosyaSec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(BolgeOnay)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ButonYukle, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ButonDevam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ButonDosyaSec, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButonYukle)
                    .addComponent(ButonDevam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BolgeOnay)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    int x=0;
    private File dosya;
    
    private static void openWebPage(String url) {
       try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    public class SehirlerLoader {
        private List<List<String>> Sehirler;
        public SehirlerLoader(String filePath) {
            this.Sehirler = new ArrayList<>();
            loadSehirler(filePath);
        }

        private void loadSehirler(String filePath) {
            try (FileInputStream fis = new FileInputStream(filePath);
                XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
                XSSFSheet sheet = workbook.getSheetAt(0);
                int baslangicSatir = 1;
                int baslangicSutun = 0; 
                int ilkYazdirilabilirSutun = 2; 
                for (int sutunIndex = baslangicSutun; sutunIndex < sheet.getRow(baslangicSatir).getLastCellNum(); sutunIndex++) {
                    Sehirler.add(new ArrayList<>());
                }
                for (int satirIndex = baslangicSatir; satirIndex <= sheet.getLastRowNum(); satirIndex++) {
                    Row row = sheet.getRow(satirIndex);

                    if (row != null) {
                        org.apache.poi.ss.usermodel.Cell cell1 = row.getCell(baslangicSutun + 1);
                        if (cell1 != null && cell1.getCellType() != CellType.NUMERIC) {
                            for (int sutunIndex = 0; sutunIndex < row.getLastCellNum(); sutunIndex++) {
                                if (!BolgeOnay.isSelected()) {
                                    if (sutunIndex >= ilkYazdirilabilirSutun && row.getCell(sutunIndex).getCellType() != CellType.NUMERIC) {
                                        break;
                                    }
                                }
                                org.apache.poi.ss.usermodel.Cell currentCell = row.getCell(sutunIndex);
                                if (currentCell != null && currentCell.getCellType() != CellType.NUMERIC) {
                                    Sehirler.get(sutunIndex).add(currentCell.toString());
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Sehirler.removeIf(List::isEmpty);
        }
        public List<List<String>> getSehirler() {
            return Sehirler;
        }
}
    
    private void ButonYukleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButonYukleActionPerformed
        jTextArea2.setText("");       
        SehirlerLoader SehirListe = new SehirlerLoader(dosya.toString());
        List<List<String>> Sehirler = SehirListe.getSehirler();       
        for (int i = 0; i < Sehirler.get(0).size(); i++) {
            StringBuilder rowData = new StringBuilder();
            for (int j = 0; j < Sehirler.size(); j++) {
                if (i < Sehirler.get(j).size()) {
                    if (j == 0) {
                        rowData.append((i + 1) + " ");
                    }
                    rowData.append(Sehirler.get(j).get(i)).append("\t");
                }
            }
            jTextArea2.append(rowData.toString() + "\n");
        }
        ButonDevam.setEnabled(true);
    }//GEN-LAST:event_ButonYukleActionPerformed

    private void ButonDosyaSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButonDosyaSecActionPerformed
        if (evt.getSource() == ButonDosyaSec) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                ButonDevam.setEnabled(false);
                dosya = new File(fileChooser.getSelectedFile().getAbsolutePath());
                File sehirlerIsim = new File(fileChooser.getSelectedFile().getName());
                jLabel3.setText(sehirlerIsim.toString());
                ButonYukle.setEnabled(true);
            }
        }
    }//GEN-LAST:event_ButonDosyaSecActionPerformed
 
    private void ButonDevamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButonDevamActionPerformed
        Main MainBaglantisi=new Main();
        if (BolgeOnay.isSelected()) {
            MainBaglantisi.createTable(MainBaglantisi.yol2, "CREATE TABLE IF NOT EXISTS "+ MainBaglantisi.SEHIRLER_TABLE_NAME+
            "(Sehirler VARCHAR(255), " +
            "Bolgeler VARCHAR(255))");
        }
        else{
            MainBaglantisi.createTable(MainBaglantisi.yol2, "CREATE TABLE IF NOT EXISTS " +MainBaglantisi.SEHIRLER_TABLE_NAME+
            "(Sehirler VARCHAR(255))");
        }
        if(MainBaglantisi.tablodaVeriBulunuyorMu(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS, MainBaglantisi.SEHIRLER_TABLE_NAME, "Sehirler")){
            MainBaglantisi.deleteAllRecordsFromTable(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS, MainBaglantisi.SEHIRLER_TABLE_NAME);
        }
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)){
            if (connection != null) {
                SehirlerLoader SehirListe = new SehirlerLoader(dosya.toString());
                List<List<String>> Sehirler = SehirListe.getSehirler();
                int ilkSutunIndex = 0;
                int ikinciSutunIndex = 1;
                int ilkSutunBoyutu = Sehirler.get(ilkSutunIndex).size();
                int minBoyut = ilkSutunBoyutu;
                if (BolgeOnay.isSelected()) {
                    String SehirTable = "INSERT INTO "+MainBaglantisi.SEHIRLER_TABLE_NAME+" (Sehirler, Bolgeler) VALUES (?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(SehirTable)) {
                        for (int i = 0; i < minBoyut; i++) {
                            preparedStatement.setString(1, Sehirler.get(ilkSutunIndex).get(i));
                            preparedStatement.setString(2, Sehirler.get(ikinciSutunIndex).get(i));
                            preparedStatement.executeUpdate();                                                    
                        }
                    }    
                }
                else{
                    String SehirTable = "INSERT INTO "+MainBaglantisi.SEHIRLER_TABLE_NAME+" (Sehirler) VALUES (?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(SehirTable)) {
                        for (int i = 0; i < minBoyut; i++) {
                            preparedStatement.setString(1, Sehirler.get(ilkSutunIndex).get(i));
                            preparedStatement.executeUpdate();                            
                        }
                    }    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }            
        if(MainBaglantisi.AyarDeğisimi==1){
            System.exit(0);
        }
        dispose();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            MainBaglantisi.deleteAllRecordsFromTable(MainBaglantisi.yol2,MainBaglantisi.USER,MainBaglantisi.PASS,MainBaglantisi.KURULUM_TABLE_NAME);
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO " +MainBaglantisi.KURULUM_TABLE_NAME+ "(No) VALUES (?);");
            stmt.setString(1, "1");
            stmt.executeUpdate(); 
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    SifreEkran se=new SifreEkran();
                    se.setVisible(true);               
                }          
            });
        } catch (SQLException ex) {
            Logger.getLogger(SehirSecim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_ButonDevamActionPerformed

    private void BolgeOnayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BolgeOnayActionPerformed
        if(x==0){
            String Link = "SehirlerBolgeler.xlsx";
            String UyariYazisi="Bölge sistemini kurmak istiyorsanız excel belgenizin sütünlarını şu şekilde düzenleyin:\n1.Sütün: id veya boş\n2.Sütun: Şehir isimleri\nDiğer sütünlardan herhangi birine şehirlerin bulunduğu satıra göre bölgelerini yazınız. Örnek\n 1.sütun: 1 2 3 veya boş\n2.sütun: Adana Adıyaman Afyon Karahisar\n 3.sütun Akdeniz Güneydoğu Anadolu Ege\n Hazır Link:";
            JLabel linkLabel = new JLabel("<html><u>" + Link + "</u></html>");
            linkLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    openWebPage("https://github.com/KaanAlper/ButterFlyFly/blob/main/SehirlerBolgeler.xlsx");
                }
            });
            JOptionPane.showMessageDialog(null, new Object[]{UyariYazisi,linkLabel},"DIKKAT",JOptionPane.INFORMATION_MESSAGE);
            x=1;
        }
    }//GEN-LAST:event_BolgeOnayActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox BolgeOnay;
    private javax.swing.JButton ButonDevam;
    private javax.swing.JButton ButonDosyaSec;
    private javax.swing.JButton ButonYukle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables

}
