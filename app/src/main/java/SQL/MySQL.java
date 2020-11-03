package SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import androidx.annotation.Nullable;

public class MySQL  extends SQLiteOpenHelper {
    public MySQL( Context context) {
        super(context, "MyData", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Nguoidung="CREATE TABLE Nguoidung("+"Id NVARCHAR(15) primary key," + "hoTen NVARCHAR(50), "+"username NVARCHAR(50),"+"" +
                "passWord NCHAR(10),"+"" +
                "phone NVARCHAR(50),"+" diachi NVARCHAR(100))";

        String Theloai="CREATE TABLE Theloai("+"" +
                "maTheLoai  NCHAR(10) PRIMARY KEY,"+"" +
                "tenTheLoai  NVARCHAR(50),"+" viTri  NVARCHAR(50))";

        String tb_Sach="CREATE TABLE Sach("+"maSach  NCHAR(10) PRIMARY KEY,"+"tenSach nvarchar(35)," +
                ""+"tenTheLoai nvarchar(50), "+"tacGia  NVARCHAR(50),"+"NXB  NVARCHAR(50)," +
                ""+" giaBan INT,"+" soLuong INT,"+" sale INT)";
        String Hoadon1="CREATE TABLE Hoadon("+" maHoaDon  NCHAR(20) ,"+"ngayMua  NCHAR(30),"+" maHDCT NCHAR(20) PRIMARY KEY," +
                ""+"maSach NCHAR(10),"+" soLuong INT,"+"tongTien INT )";
        String HDCT ="CREATE TABLE HDCT("+" maHDCT  NCHAR(20) PRIMARY KEY," + " maHoaDon  NCHAR(20) , "+ " maSach  NCHAR(20),"+" soLuongMua int)";
        db.execSQL(Nguoidung);
        db.execSQL(tb_Sach);
        db.execSQL(Hoadon1);
        db.execSQL(HDCT);
        db.execSQL(Theloai);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
