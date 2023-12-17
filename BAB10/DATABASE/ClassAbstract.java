/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BAB10.DATABASE;





/**
 *
 * @author Zul
 */
public abstract class ClassAbstract {
    protected String nama;
    protected int total,harga, sewa;
    protected double  diskon;
    protected String kode, merk;
    // Mengubah atribut nama ke protected agar atribut tidak dapat digunakan kelas lain
    public abstract double getDiskon();
    public abstract int getTotal();
    public abstract double Diskon();
    public abstract int total();
    public abstract String getStatus();
    public abstract String Status();
    public abstract String Status(String nama);  
    public abstract String getnama();
    public abstract String getkode();
    public abstract String getmerk();
    public abstract void setkode(String kode1);
    public abstract void setnama(String nama);
    public abstract void datanama(String nama);
    public abstract void setsewa(int sewa);
    public abstract void setmerk(String merk);
    public abstract void setharga(int harga);
    public abstract int getharga();
    public abstract int getsewa();
    public abstract boolean Cek(String nama, String Merk);
    public abstract boolean Cektotal(String merk);
    
}
    

