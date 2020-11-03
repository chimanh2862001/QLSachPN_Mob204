package Model;

public class TheLoai {
    private String tenTl;
    private String maTl;
    private String vitri;

    public TheLoai(String maTl, String tenTl, String vitri) {
        this.maTl = maTl;
        this.tenTl = tenTl;
        this.vitri = vitri;
    }

    public TheLoai() {
    }

    public String getTenTl() {
        return tenTl;
    }

    public void setTenTl(String tenTl) {
        this.tenTl = tenTl;
    }

    public String getMaTl() {
        return maTl;
    }

    public void setMaTl(String maTl) {
        this.maTl = maTl;
    }

    public String getVitri() {
        return vitri;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }
}
