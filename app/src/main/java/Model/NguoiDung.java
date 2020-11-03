package Model;

public class NguoiDung {
    private String id,hoten,username,pass,sdt,diachi;

    public NguoiDung(String id, String hoten, String username, String pass, String sdt, String diachi) {
        this.id = id;
        this.hoten = hoten;
        this.username = username;
        this.pass = pass;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public NguoiDung() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
