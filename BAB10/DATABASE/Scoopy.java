/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BAB10.DATABASE;





/**
 *
 * @author Zul
 */
public class Scoopy extends ClassAbstract implements Rental{
        public Scoopy() {
        this.harga = 35000; 
        this.kode = "S0120";
        this.merk = "Motor Scoopy";

    }
    @Override
    public void setkode(String kode1){
        this.kode = kode1;
    }
   
    @Override
     public int total(){
        total = (harga*sewa);
        return total;
    }
    @Override
    public void setnama(String nama){
        this.nama = nama;
    }
    @Override
    public String getnama(){
        return nama;
    }
    @Override
    public String getkode(){
        return kode;
    }
    @Override
    public void datanama(String nama){
        this.nama = nama;
    }
    @Override
    public void setsewa(int sewa) {
        this.sewa = sewa;
    }
    @Override
    public int getsewa() {
        return sewa;
    }
    @Override
    public String getmerk(){
        return merk;
    }
    @Override
    public void setmerk(String merk){
        this.merk = merk;
    }

    @Override
    public int getharga() {
        return harga;
    }

    @Override
    public void setharga(int harga) {
        this.harga = harga;
    }
    @Override
    public boolean Cek(String nama, String merk){
    if (nama != null && nama.equals(getnama()) && merk.equals(getmerk())) {
        Status();
        return true;
    }
    Status(nama);
    return false;
}
    public boolean Cektotal(String merk){
    if (merk.equals(getmerk())) {
        total();
        return true;
    }
    Data();
    return false;
    }
    @Override
    public double Diskon() {
        diskon = getTotal() - (getDiskon() * getTotal());
        return diskon;
    }
    @Override
    public int getTotal() {
        return total();
    }

    @Override
    public double getDiskon() {
        return 0.15; // Diskon 15%
    }

    @Override
    public String getStatus() {
        return "Status Vario";
    }

    @Override
    public String Status() {
        return " baru saja menyewa ";
    }

    @Override
    public String Status(String nama) {
        return " belum pernah menyewa ";
    }
    @Override
    public String Data() {
        throw new UnsupportedOperationException("Data Tidak Boleh Kosong"); 
    }

}
