/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BAB10.DATABASE;

/**
 *
 * @author Zul
 */



import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class GUI_RentalMotor_1 extends javax.swing.JFrame {

    /**
     * Creates new form GUI_DataMatkul
     */
    public GUI_RentalMotor_1() {
        initComponents();
        tampil();
        //Merubah variabel harga dari type data Integer ke String
        txtharga.setEnabled(false);
        
    }

    public void batal() {
        txtpenyewa.setText("");
        txtsewa.setText("");
        txtharga.setText("");
        txttotal.setText("");

    }
    
    //masukkan conection (public Connection conn;)
    public Connection conn;
    //	Tambahkan Variable baru seperti dibawah ini
    String nama,merk,kode,sewa,harga;

    //masukkan method itempilih()
    public void itempilih() {
        ClassAbstract motor = new RentalMotor();
        txtpenyewa.setText(nama);
        txtsewa.setText(sewa);
        if(pilih_motor.getSelectedIndex()==0){
                    merk = motor.merk;
                    txtharga.setText(Integer.toString(motor.harga));
                    txtkode.setText(motor.getkode());
                    }else if(pilih_motor.getSelectedIndex()==1){
                    motor = new Vario();
                    merk = motor.merk;
                    txtharga.setText(Integer.toString(motor.harga));
                    txtkode.setText(motor.getkode());
                    }else{
                    motor = new Scoopy();
                    merk = motor.merk;
                    txtharga.setText(Integer.toString(motor.harga));
                    txtkode.setText(motor.getkode());
                    }

    }
    
public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/OOP_2218090?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_RentalMotor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_RentalMotor.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_RentalMotor.class.getName()).log(Level.SEVERE, null, es);
        }
    }

    //masukkan method koneksi(public void tampil() {
        public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("Nama");
        tabelhead.addColumn("Merk");
        tabelhead.addColumn("Sewa");
        tabelhead.addColumn("Harga");
        tabelhead.addColumn("KodePromo");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_RentalMotor2";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getString(6),});
            }
            tabel_data.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }

    
    //masukkan method tampil()
    
            
    public void refresh() {
        new GUI_RentalMotor_1().setVisible(true);
        this.setVisible(false);
    }


    //masukkan method delete()
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_RentalMotor2 WHERE Nama='" + txtpenyewa.getText() + "'";
                java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }

    //masukkan method cari()
    public void cari() {
        try {
            try ( Statement statement = conn.createStatement()) {
                String sql = "SELECT * FROM tb_RentalMotor2 WHERE `Nama`  LIKE '%" + txtpenyewa.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    txtpenyewa.setText(rs.getString(2));
                    ClassAbstract motor = new RentalMotor();
                    if(pilih_motor.getSelectedIndex()==0){
                    merk = motor.merk;
                    pilih_motor.setSelectedIndex(rs.getInt(3));
                    txtsewa.setText(rs.getString(4));
                    txtharga.setText(rs.getString(5));
                    txtkode.setText(rs.getString(6));
                    }else if(pilih_motor.getSelectedIndex()==1){
                    motor = new Vario();
                    merk = motor.merk;
                    pilih_motor.setSelectedIndex(rs.getInt(3));
                    txtsewa.setText(rs.getString(4));
                    txtharga.setText(rs.getString(5));
                    txtkode.setText(rs.getString(6));
                    }else{
                    motor = new Scoopy();
                    merk = motor.merk;
                    pilih_motor.setSelectedIndex(rs.getInt(3));
                    txtsewa.setText(rs.getString(4));
                    txtharga.setText(rs.getString(5));
                    txtkode.setText(rs.getString(6));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }
    //masukkan method update()
    public void update() {
    ClassAbstract motor = new RentalMotor();
    String nama = txtpenyewa.getText();
    String lama = txtsewa.getText();
    String harga = Integer.toString(motor.harga);
    String merk = "";
   if(pilih_motor.getSelectedIndex()==0){
                    merk = motor.merk;
                    txtharga.setText(Integer.toString(motor.harga));
                    txtkode.setText(motor.getkode());
                    }else if(pilih_motor.getSelectedIndex()==1){
                    motor = new Vario();
                    merk = motor.merk;
                    txtharga.setText(Integer.toString(motor.harga));
                    txtkode.setText(motor.getkode());
                    }else{
                    motor = new Scoopy();
                    merk = motor.merk;
                    txtharga.setText(Integer.toString(motor.harga));
                    txtkode.setText(motor.getkode());
                    }
    String kode = motor.getkode();
    String namalama = nama;

    try {
        Statement statement = conn.createStatement();
        statement.executeUpdate("UPDATE tb_RentalMotor_1 SET Nama='" + nama + "', Merk='" + merk + "', Sewa='" + lama + "', Harga='" + harga + "', KodePromo='" + kode + "' WHERE Nama = '" + namalama + "'");
        statement.close();
        conn.close();
        JOptionPane.showMessageDialog(null, "Update Data Penyewaan!");
    } catch (Exception e) {
        System.out.println("Error : " + e);
    }
    refresh();
}


    
    //masukkan method refresh()
   

    
    //masukkan method insert()
  public void insert() {
    String nama = txtpenyewa.getText();
    String merk="";String harga = "";String kode="";
     ClassAbstract motor = new RentalMotor();
    if (pilih_motor.getSelectedIndex() == 0) {
    merk = "Pilih Motor";
    harga = "0";
    kode = "Masukkan Kode";
} else if (pilih_motor.getSelectedIndex() == 1) {
    motor = new RentalMotor();
    merk = "Nmax";
    harga = Integer.toString(motor.harga);
    kode = motor.getkode();
} else if (pilih_motor.getSelectedIndex() == 2) {
    motor = new Vario();
    merk = "Vario";
    harga = Integer.toString(motor.harga);
    kode = motor.getkode();
} else if (pilih_motor.getSelectedIndex() == 3) {
    motor = new Scoopy();
    merk = "Scoopy";
    harga = Integer.toString(motor.harga);
    kode = motor.getkode();
}

    String lama = txtsewa.getText();
    
    // Perhitungan total berdasarkan lama sewa dan harga

    try {
        koneksi();
        String sql = "INSERT INTO tb_RentalMotor2(Nama, Merk, Sewa, Harga, KodePromo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nama);
            statement.setString(2, merk);
            statement.setString(3, lama);
            statement.setString(4, harga);// Simpan sebagai String
            statement.setString(5, kode); 
            statement.executeUpdate();
        }
        JOptionPane.showMessageDialog(null, "Berhasil Memasukan Data Penyewaan!");
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
    }
    refresh();
}




    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnriwayat = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        pilih_motor = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtpenyewa = new javax.swing.JTextField();
        txtsewa = new javax.swing.JTextField();
        Btnpesan = new javax.swing.JButton();
        txtkode = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnbatal = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        btnubah = new javax.swing.JButton();
        btnFormRentalMotor = new javax.swing.JButton();
        btnCari1 = new javax.swing.JButton();
        txtcari1 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Back = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("Harga/Sewa");

        jLabel6.setText("Rp.");

        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String[]{
                "Nama", "Merk", "Lama Sewa", "Harga Sewa/Hari", "Kode Promo"
            }
        );

        tabel_data.setModel(model);
        tabel_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama", "Merk Motor", "Lama Sewa", "Harga Sewa/hari", "Kode Promo"
            }
        ));
        tabel_data.setColumnSelectionAllowed(true);
        tabel_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_dataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_data);
        tabel_data.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        jLabel5.setText("Hari");

        txttotal.setEditable(false);

        jLabel7.setText("Rp.");

        btnriwayat.setText("Hapus Riwayat");
        btnriwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnriwayatActionPerformed(evt);
            }
        });

        jLabel8.setText("Total Harga");

        pilih_motor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Motor", "Motor NMax", "Motor Vario", "Motor Scoopy" }));
        pilih_motor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilih_motorActionPerformed(evt);
            }
        });

        jLabel9.setText("Motor Yang di Sewa");

        jLabel2.setText("Penyewa");

        jLabel3.setText("Lama Penyewaan");

        Btnpesan.setText("Pesan");
        Btnpesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnpesanActionPerformed(evt);
            }
        });

        txtharga.setEditable(false);

        jLabel10.setText("Kode Promo dari Pemesanan");

        btnbatal.setText("Batal Pesanan");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        btnubah.setText("Update Pesanan");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        btnFormRentalMotor.setText("Kembali");
        btnFormRentalMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormRentalMotorActionPerformed(evt);
            }
        });

        btnCari1.setText("Cari🔍");
        btnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCari1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Home");

        Back.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        jMenu1.add(Back);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Form");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("GUI_RentalMotor");
        jMenu2.add(jCheckBoxMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btnpesan)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCari1))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel8)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(txtsewa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5))
                                .addComponent(txtpenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pilih_motor, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txttotal, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                        .addComponent(txtharga)))
                                .addComponent(jLabel10)
                                .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(50, 50, 50)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnriwayat)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnbatal)
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(101, 101, 101)
                                            .addComponent(btnFormRentalMotor))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnubah)
                                            .addGap(27, 27, 27)
                                            .addComponent(btnrefresh))))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2)
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCari1)
                            .addComponent(txtcari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtpenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pilih_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(txtsewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel8)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(btnriwayat)
                            .addComponent(btnbatal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtkode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnubah)
                            .addComponent(btnrefresh))
                        .addGap(41, 41, 41)
                        .addComponent(btnFormRentalMotor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btnpesan)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pilih_motorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilih_motorActionPerformed
        if(pilih_motor.getSelectedIndex()==0){
            txtharga.setText("0");
        }
        else if(pilih_motor.getSelectedIndex()==1){
            txtharga.setText("45.000");
        }
        else if(pilih_motor.getSelectedIndex()==2){
            txtharga.setText("40.000");
        }
        else if(pilih_motor.getSelectedIndex()==3){
            txtharga.setText("35.000");
        }
    }//GEN-LAST:event_pilih_motorActionPerformed
    
    
        
    private void btnriwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnriwayatActionPerformed
    delete();     
    }//GEN-LAST:event_btnriwayatActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
    batal();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void BtnpesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnpesanActionPerformed
    insert();
    }//GEN-LAST:event_BtnpesanActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        
    }//GEN-LAST:event_BackActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
    refresh();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
        update();
    }//GEN-LAST:event_btnubahActionPerformed

    private void btnFormRentalMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormRentalMotorActionPerformed
        new GUI_RentalMotor().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnFormRentalMotorActionPerformed

    private void tabel_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_dataMouseClicked
       int tabel = tabel_data.getSelectedRow();
        nama = tabel_data.getValueAt(tabel, 0).toString();
        merk = tabel_data.getValueAt(tabel, 1).toString();
        sewa = tabel_data.getValueAt(tabel, 2).toString();
        harga = tabel_data.getValueAt(tabel, 3).toString();
        kode = tabel_data.getValueAt(tabel, 4).toString();
        itempilih();
    }//GEN-LAST:event_tabel_dataMouseClicked

    private void btnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCari1ActionPerformed
        cari();

    }//GEN-LAST:event_btnCari1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_RentalMotor_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_RentalMotor_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_RentalMotor_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_RentalMotor_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_RentalMotor_1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Back;
    private javax.swing.JButton Btnpesan;
    private javax.swing.JButton btnCari1;
    private javax.swing.JButton btnFormRentalMotor;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnriwayat;
    private javax.swing.JButton btnubah;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> pilih_motor;
    private javax.swing.JTable tabel_data;
    private javax.swing.JTextField txtcari1;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtkode;
    private javax.swing.JTextField txtpenyewa;
    private javax.swing.JTextField txtsewa;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
