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
public class GUI_RentalMotor extends javax.swing.JFrame {

    /**
     * Creates new form GUI_DataMatkul
     */
    public GUI_RentalMotor() {
        initComponents();
        tampil();
        txtharga.setText("0");
        //Merubah variabel harga dari type data Integer ke String
        txtharga.setEnabled(false);
        
    }

    public void batal() {
        txtpenyewa.setText("");
        txtsewa.setText("");
        txtharga.setText("");
        txttotal.setText("");
        merkgroup.clearSelection();
    }
    
    //masukkan conection (public Connection conn;)
    public Connection conn;
    //	Tambahkan Variable baru seperti dibawah ini
    String nama,merk,total,sewa,harga;

    //masukkan method itempilih()
    public void itempilih() {
        txtpenyewa.setText(nama);
        txtsewa.setText(sewa);
        if(r1.isSelected()){
                    merk = "Nmax";
                    }else{
                    merk = "Vario";
                    }
        txtharga.setText(harga);
        txttotal.setText(total);

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
        tabelhead.addColumn("Total");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_RentalMotor1";
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
        new GUI_RentalMotor().setVisible(true);
        this.setVisible(false);
    }


    //masukkan method delete()
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_RentalMotor1 WHERE Nama='" + txtpenyewa.getText() + "'";
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
                String sql = "SELECT * FROM tb_RentalMotor1 WHERE `Nama`  LIKE '%" + txtpenyewa.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql); //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    txtpenyewa.setText(rs.getString(2));
                    if(r1.isSelected()){
                    rs.getString(3);

                    }else{
                     rs.getString(3);
                    }
                    txtsewa.setText(rs.getString(4));
                    txtharga.setText(rs.getString(5));
                    txttotal.setText(rs.getString(6));
                    
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
    String nama = txtpenyewa.getText();
    String lama = txtsewa.getText();
    String harga = "0";
    String merk = "";
    String total = "0";

    if (r1.isSelected()) {
        merk = "Nmax";
        harga = "40000";
        total = "80000";
    } else {
        merk = "Vario";
        harga = "35000";
        total = "70000";
    }

    String namalama = nama;

    try {
        Statement statement = conn.createStatement();
        statement.executeUpdate("UPDATE tb_RentalMotor1 SET Nama='" + nama + "', Merk='" + merk + "', Sewa='" + lama + "', Harga='" + harga + "', Total='" + total + "' WHERE Nama = '" + namalama + "'");
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
    String merk = r1.isSelected() ? "Nmax" : "Vario";
    String lama = txtsewa.getText();
    String harga = r1.isSelected() ? "40000" : "35000";

    // Perhitungan total berdasarkan lama sewa dan harga
    int lamaSewa = Integer.parseInt(lama);
    int hargaSewa = Integer.parseInt(harga);
    int total = lamaSewa * hargaSewa;

    try {
        koneksi();
        String sql = "INSERT INTO tb_RentalMotor1(Nama, Merk, Sewa, Harga, Total) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, nama);
            statement.setString(2, merk);
            statement.setString(3, lama);
            statement.setString(4, harga);
            statement.setInt(5, total); // Simpan total sebagai Integer
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
        merkgroup = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_data = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnriwayat = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnubah = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtpenyewa = new javax.swing.JTextField();
        txtsewa = new javax.swing.JTextField();
        Btnpesan = new javax.swing.JButton();
        txtharga = new javax.swing.JTextField();
        btnbatal = new javax.swing.JButton();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        btnrefresh = new javax.swing.JButton();
        btnFormRentalMotor = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();

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
                "Nama", "Merk Motor", "Lama Sewa", "Harga Sewa/hari", "Total Pembayaran"
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

        jLabel7.setText("Rp.");

        btnriwayat.setText("Hapus Riwayat");
        btnriwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnriwayatActionPerformed(evt);
            }
        });

        jLabel8.setText("Total Harga");

        btnubah.setText("Update Pesanan");
        btnubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnubahActionPerformed(evt);
            }
        });

        jLabel9.setText("Motor Yang di Sewa");

        jLabel1.setText("Rental Motor");

        jLabel2.setText("Penyewa");

        jLabel3.setText("Lama Penyewaan");

        txtsewa.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtsewaInputMethodTextChanged(evt);
            }
        });

        Btnpesan.setText("Pesan");
        Btnpesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnpesanActionPerformed(evt);
            }
        });

        txtharga.setEditable(false);

        btnbatal.setText("Batal Pesanan");
        btnbatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbatalActionPerformed(evt);
            }
        });

        merkgroup.add(r1);
        r1.setText("NMAX");
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });

        merkgroup.add(r2);
        r2.setText("Vario");
        r2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r2ActionPerformed(evt);
            }
        });

        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        btnFormRentalMotor.setText("Form Rental Motor");
        btnFormRentalMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormRentalMotorActionPerformed(evt);
            }
        });

        btnCari.setText("Cari🔍");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
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
                                    .addComponent(jLabel9)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txttotal, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                            .addComponent(txtharga)))
                                    .addComponent(r2)
                                    .addComponent(r1)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(Btnpesan)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnriwayat)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnbatal)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnubah)
                                    .addGap(27, 27, 27)
                                    .addComponent(btnrefresh)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCari))
                            .addComponent(btnFormRentalMotor)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel1)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(txtpenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(r1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(r2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
                            .addComponent(jLabel6))
                        .addComponent(jLabel8)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCari)
                            .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnriwayat)
                                    .addComponent(btnbatal)
                                    .addComponent(btnubah)
                                    .addComponent(btnrefresh))))
                        .addGap(41, 41, 41)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFormRentalMotor)
                    .addComponent(Btnpesan))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnubahActionPerformed
     update();
    }//GEN-LAST:event_btnubahActionPerformed
    
    
    
    private void btnriwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnriwayatActionPerformed
    delete();
    }//GEN-LAST:event_btnriwayatActionPerformed

    private void btnbatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbatalActionPerformed
    batal();
    }//GEN-LAST:event_btnbatalActionPerformed

    private void BtnpesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnpesanActionPerformed
    insert();  
    }//GEN-LAST:event_BtnpesanActionPerformed

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
    refresh();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void btnFormRentalMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormRentalMotorActionPerformed
    new GUI_RentalMotor_1().setVisible(true); 
    dispose();
    }//GEN-LAST:event_btnFormRentalMotorActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
    cari();   
       
    }//GEN-LAST:event_btnCariActionPerformed

    private void tabel_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_dataMouseClicked
    int tabel = tabel_data.getSelectedRow();
        nama = tabel_data.getValueAt(tabel, 0).toString();
        merk = tabel_data.getValueAt(tabel, 1).toString();
        sewa = tabel_data.getValueAt(tabel, 2).toString();
        harga = tabel_data.getValueAt(tabel, 3).toString();
        total = tabel_data.getValueAt(tabel, 4).toString();
        itempilih();
    }//GEN-LAST:event_tabel_dataMouseClicked

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
     txtharga.setText("40000");
    }//GEN-LAST:event_r1ActionPerformed

    private void r2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r2ActionPerformed
       txtharga.setText("35000");
    }//GEN-LAST:event_r2ActionPerformed

    private void txtsewaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtsewaInputMethodTextChanged
    
    }//GEN-LAST:event_txtsewaInputMethodTextChanged

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI_RentalMotor().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnpesan;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnFormRentalMotor;
    private javax.swing.JButton btnbatal;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnriwayat;
    private javax.swing.JButton btnubah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.ButtonGroup merkgroup;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JTable tabel_data;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtpenyewa;
    private javax.swing.JTextField txtsewa;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
