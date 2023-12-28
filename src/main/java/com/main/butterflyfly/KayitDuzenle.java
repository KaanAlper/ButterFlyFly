package com.main.butterflyfly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

/**
 * @author KaanAlper
 */

public class KayitDuzenle extends javax.swing.JDialog {
    private DefaultComboBoxModel<String> BaslangicBolgeModel;
     private DefaultComboBoxModel<String> BaslangicBolgeModel2;  
    private final DefaultTableModel tableModel;
    
    public KayitDuzenle(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        String[] columnNames = {"ID", "Başlangıç Şehir", "Bitiş Şehir", "Zaman"};
        tableModel = new DefaultTableModel(columnNames, 0);       
        Main MainBaglantisi =new Main();
        if(MainBaglantisi.checkTableExists(MainBaglantisi.yol, MainBaglantisi.DATABASE_NAME, MainBaglantisi.REZERVASYON_TABLE_NAME, MainBaglantisi.USER, MainBaglantisi.PASS)){
            if(MainBaglantisi.tablodaVeriBulunuyorMu(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS, MainBaglantisi.REZERVASYON_TABLE_NAME, "ID")){               
                BaslangicBolgeModel = new DefaultComboBoxModel<>();
                BaslangicBolgeModel2 = new DefaultComboBoxModel<>();
                initComponents();
                populateBolgelerComboBox();
                populateBolgelerComboBox2();
                initColumnWidths();
                JTableHeader header = dataTable.getTableHeader();
                header.setReorderingAllowed(false);
                TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>((DefaultTableModel) dataTable.getModel());
                dataTable.setRowSorter(rowSorter); 
                pack();
                setLocationRelativeTo(null);
                veriyiGoster();
            }
            else{
                JOptionPane.showMessageDialog(this, "Daha önce hiç rezervasyon oluşturulmamış. Düzenleme yapılamıyor");
                dispose();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Veri Tabanı bulunamadı önce rezervasyon oluşturmayı dene, olmassa kurucu ile konuşun");
            dispose();
        }        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        IDLabel = new javax.swing.JLabel();
        BaslangicSehirLabel = new javax.swing.JLabel();
        BitisSehirLabel = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        GünAyLabel = new javax.swing.JLabel();
        cmbGun = new javax.swing.JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
        cmbAy = new javax.swing.JComboBox<>(new String[]{"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"});
        jPanel12 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        SaatLabel = new javax.swing.JLabel();
        cmbSaat = new javax.swing.JComboBox<>(new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        jPanel8 = new javax.swing.JPanel();
        DakikaLabel = new javax.swing.JLabel();
        cmbDakika = new javax.swing.JComboBox<>(new String[]{"00","15","30","45"});
        jPanel10 = new javax.swing.JPanel();
        btnTamaminiGoster = new javax.swing.JButton();
        btnGoster = new javax.swing.JButton();
        btnGuncelle = new javax.swing.JButton();
        btnSil = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(7, 11, 21));

        dataTable.setBackground(new java.awt.Color(204, 204, 204));
        dataTable.setForeground(new java.awt.Color(0, 0, 0));
        dataTable.setModel(tableModel);
        dataTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow != -1) {
                // If a row is selected, populate the edit fields
                txtId.setText(dataTable.getValueAt(selectedRow, 0).toString());
                jComboBox2.setSelectedItem(dataTable.getValueAt(selectedRow, 1).toString());
                jComboBox3.setSelectedItem(dataTable.getValueAt(selectedRow, 2).toString());
                String[] zamanParts = dataTable.getValueAt(selectedRow, 3).toString().split(" Saat: ");
                String[] dateParts = zamanParts[0].split("/");
                cmbGun.setSelectedItem(dateParts[0]);
                cmbAy.setSelectedItem(dateParts[1]);
                cmbSaat.setSelectedItem(zamanParts[1].split(":")[0]);
                cmbDakika.setSelectedItem(zamanParts[1].split(":")[1]);
            }
        });
        jScrollPane1.setViewportView(dataTable);

        jPanel7.setBackground(new java.awt.Color(7, 11, 21));

        jPanel11.setLayout(new java.awt.GridBagLayout());

        txtId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        IDLabel.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        IDLabel.setForeground(new java.awt.Color(255, 255, 255));
        IDLabel.setText("ID:");

        BaslangicSehirLabel.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        BaslangicSehirLabel.setForeground(new java.awt.Color(255, 255, 255));
        BaslangicSehirLabel.setText("Başlangıç Şehiri:");

        BitisSehirLabel.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        BitisSehirLabel.setForeground(new java.awt.Color(255, 255, 255));
        BitisSehirLabel.setText("Varış Şehiri:");

        jComboBox2.setModel(BaslangicBolgeModel);

        jComboBox3.setModel(BaslangicBolgeModel2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BaslangicSehirLabel)
                    .addComponent(IDLabel)
                    .addComponent(BitisSehirLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.Alignment.TRAILING, 0, 195, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDLabel)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BaslangicSehirLabel)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BitisSehirLabel)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(7, 11, 21));

        GünAyLabel.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        GünAyLabel.setForeground(new java.awt.Color(255, 255, 255));
        GünAyLabel.setText("Zaman (Gün/Ay):");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(GünAyLabel)
                .addGap(32, 32, 32)
                .addComponent(cmbGun, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbAy, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GünAyLabel)
                    .addComponent(cmbAy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel12.setBackground(new java.awt.Color(7, 11, 21));

        jPanel6.setBackground(new java.awt.Color(7, 11, 21));

        SaatLabel.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        SaatLabel.setForeground(new java.awt.Color(255, 255, 255));
        SaatLabel.setText("Saat:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SaatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSaat, 0, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cmbSaat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(SaatLabel))
        );

        jPanel12.add(jPanel6);

        jPanel8.setBackground(new java.awt.Color(7, 11, 21));

        DakikaLabel.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        DakikaLabel.setForeground(new java.awt.Color(255, 255, 255));
        DakikaLabel.setText("Dakika");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DakikaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbDakika, 0, 104, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(DakikaLabel)
                .addComponent(cmbDakika))
        );

        jPanel12.add(jPanel8);

        jPanel10.setBackground(new java.awt.Color(7, 11, 21));

        btnTamaminiGoster.setText("Tümünü Göster");
        btnTamaminiGoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show all data
                veriyiGoster();
                TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>((DefaultTableModel) dataTable.getModel());
                dataTable.setRowSorter(rowSorter);
                rowSorter.setRowFilter(null);
                jTextField1.setText("");
            }
        });
        jPanel10.add(btnTamaminiGoster);

        btnGoster.setText("ID Filitrelemesi");
        btnGoster.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                veriyiGoster(id);
            }
        });
        jPanel10.add(btnGoster);

        btnGuncelle.setText("Kaydet");
        btnGuncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(txtId.getText());
                String baslangicSehir =(String)jComboBox2.getSelectedItem();
                String bitisSehir =(String)jComboBox3.getSelectedItem();
                String zaman = cmbGun.getSelectedItem() + "/" + cmbAy.getSelectedItem() +
                " Saat: " + cmbSaat.getSelectedItem() + ":" + cmbDakika.getSelectedItem();

                veriyiGuncelle(id, baslangicSehir, bitisSehir, zaman);
                // After updating, refresh the table
                veriyiGoster();
                clearFields();  // Clear fields after update
            }
        });
        jPanel10.add(btnGuncelle);

        btnSil.setText("Sil");
        btnSil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = dataTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.parseInt(dataTable.getValueAt(selectedRow, 0).toString());
                    veriyiSil(id);
                    veriyiGoster();
                    clearFields();  // Clear fields after delete
                } else {
                    JOptionPane.showMessageDialog(KayitDuzenle.this, "Lütfen bir satır seçin.");
                }
            }
        });
        jPanel10.add(btnSil);

        jButton1.setText("Filitrele");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTable();
            }
        });

        jComboBox1 = new JComboBox<>(new String[]{"ID", "Başlangıç Şehir", "Bitiş Şehir", "Zaman"});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(416, 416, 416))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(12, 12, 12)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
     private void populateBolgelerComboBox() {
        BaslangicBolgeModel.removeAllElements();
        Main MainBaglantisi =new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            String sql = "SELECT DISTINCT Sehirler FROM sehirlistesi";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                     BaslangicBolgeModel.addElement(resultSet.getString("Sehirler"));
                }             
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
     private void populateBolgelerComboBox2() {
        BaslangicBolgeModel2.removeAllElements();
        Main MainBaglantisi =new Main();
        try (Connection connection = DriverManager.getConnection(MainBaglantisi.yol2, MainBaglantisi.USER, MainBaglantisi.PASS)) {
            String sql = "SELECT DISTINCT Sehirler FROM sehirlistesi";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                     BaslangicBolgeModel2.addElement(resultSet.getString("Sehirler"));
                }             
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    private void veriyiGoster() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/butterflyfly", "root", "")) {
            String query = "SELECT * FROM rezervasyonlar";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        tableModel.addRow(new Object[]{
                                rs.getInt("ID"),
                                rs.getString("BaslangicSehir"),
                                rs.getString("BitisSehir"),
                                rs.getString("Zaman")
                        });
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void filterTable() {
        String filterText = jTextField1.getText();
        String filterColumn = jComboBox1.getSelectedItem().toString();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>((DefaultTableModel) dataTable.getModel());
        dataTable.setRowSorter(rowSorter);

        RowFilter<Object, Object> rowFilter = RowFilter.regexFilter("(?i)" + filterText, dataTable.getColumnModel().getColumnIndex(filterColumn));
        rowSorter.setRowFilter(rowFilter);
    }
    private void veriyiGoster(int id) {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/butterflyfly", "root", "")) {
            String query = "SELECT * FROM rezervasyonlar WHERE ID = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, id);
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        tableModel.addRow(new Object[]{
                                rs.getInt("ID"),
                                rs.getString("BaslangicSehir"),
                                rs.getString("BitisSehir"),
                                rs.getString("Zaman")
                        });
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void veriyiGuncelle(int id, String baslangicSehir, String bitisSehir, String zaman) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/butterflyfly", "root", "")) {
            String query = "UPDATE rezervasyonlar SET BaslangicSehir = ?, BitisSehir = ?, Zaman = ? WHERE ID = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, baslangicSehir);
                pst.setString(2, bitisSehir);
                pst.setString(3, zaman);
                pst.setInt(4, id);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Veri güncellendi.");
                } else {
                    JOptionPane.showMessageDialog(this, "Belirtilen ID ile kayıt bulunamadı.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void veriyiSil(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/butterflyfly", "root", "")) {
            String query = "DELETE FROM rezervasyonlar WHERE ID = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, id);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Veri silindi.");
                } else {
                    JOptionPane.showMessageDialog(this, "Belirtilen ID ile kayıt bulunamadı.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        txtId.setText("");
        jComboBox2.setSelectedItem("");
        jComboBox3.setSelectedItem("");
        cmbGun.setSelectedIndex(0);
        cmbAy.setSelectedIndex(0);
        cmbSaat.setSelectedIndex(0);
        cmbDakika.setSelectedIndex(0);
    }
    /**/    
    private void initColumnWidths() {
        int[] columnWidths = {50, 110, 110, 150};
        for (int i = 0; i < columnWidths.length; i++) {
           TableColumn column = dataTable.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BaslangicSehirLabel;
    private javax.swing.JLabel BitisSehirLabel;
    private javax.swing.JLabel DakikaLabel;
    private javax.swing.JLabel GünAyLabel;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JLabel SaatLabel;
    private javax.swing.JButton btnGoster;
    private javax.swing.JButton btnGuncelle;
    private javax.swing.JButton btnSil;
    private javax.swing.JButton btnTamaminiGoster;
    private javax.swing.JComboBox<String> cmbAy;
    private javax.swing.JComboBox<String> cmbDakika;
    private javax.swing.JComboBox<String> cmbGun;
    private javax.swing.JComboBox<String> cmbSaat;
    private javax.swing.JTable dataTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables

}