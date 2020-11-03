package Model;

public class Sach {
    private String masach,tensach,tentl,tacgia,NXB;
    private int soluong,giaban,sale;

    public Sach(String masach, String tensach, String tentl, String tacgia, String NXB, int soluong, int giaban, int sale) {
        this.masach = masach;
        this.tensach = tensach;
        this.tentl = tentl;
        this.tacgia = tacgia;
        this.NXB = NXB;
        this.soluong = soluong;
        this.giaban = giaban;
        this.sale = sale;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public Sach() {
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }


    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }
}
