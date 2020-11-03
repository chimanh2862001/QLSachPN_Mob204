package Model;

public class HoaDon {
    private String  maHoadon;
    private String date,maHDCT,maSach;
    private int soluong,tongTien;


    public HoaDon(String maHoadon, String date, String maHDCT, String maSach, int soluong, int tongTien) {
        this.maHoadon = maHoadon;
        this.date = date;
        this.maHDCT = maHDCT;
        this.maSach = maSach;
        this.soluong = soluong;
        this.tongTien = tongTien;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public HoaDon() {
    }

    public String getMaHoadon() {
        return maHoadon;
    }

    public void setMaHoadon(String maHoadon) {
        this.maHoadon = maHoadon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
