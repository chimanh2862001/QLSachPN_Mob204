package SQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.HoaDon;

public class HoaDonDao {
    private MySQL mySQL;

    public HoaDonDao(MySQL mySQL) {
        this.mySQL = mySQL;
    }

    public void addHD(HoaDon hoaDon) {
        SQLiteDatabase sqLiteDatabase = mySQL.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDon.getMaHoadon());
        contentValues.put("ngayMua", hoaDon.getDate());
        contentValues.put("maHDCT", hoaDon.getMaHDCT());
        contentValues.put("maSach", hoaDon.getMaSach());
        contentValues.put("soLuong", hoaDon.getSoluong());
        contentValues.put("tongTien", hoaDon.getTongTien());
        sqLiteDatabase.insert("Hoadon", null, contentValues);
    }

    public void updateHD(HoaDon hoaDon) {
        SQLiteDatabase sqLiteDatabase = mySQL.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maHoaDon", hoaDon.getMaHoadon());
        contentValues.put("ngayMua", hoaDon.getDate());
        contentValues.put("maHDCT", hoaDon.getMaHDCT());
        contentValues.put("maSach", hoaDon.getMaSach());
        contentValues.put("soLuong", hoaDon.getSoluong());
        contentValues.put("tongTien", hoaDon.getTongTien());
        sqLiteDatabase.update("Hoadon", contentValues, "maHDCT=?", new String[]{hoaDon.getMaHDCT()});

    }

    public void delteHoaDon(String maHDCT) {
        SQLiteDatabase sqLiteDatabase = mySQL.getReadableDatabase();
        sqLiteDatabase.delete("Hoadon", "maHDCT=?", new String[]{maHDCT});


    }

    public List<HoaDon> getAlll() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = mySQL.getReadableDatabase();
        String gell = "Select*from Hoadon";
        Cursor cursor = sqLiteDatabase.rawQuery(gell, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String mahoadon, date, maHDCT, maSach;
            int soluong, tongtien;
            mahoadon = cursor.getString(0);
            date = cursor.getString(1);
            maHDCT = cursor.getString(2);
            maSach = cursor.getString(3);
            soluong = cursor.getInt(4);
            tongtien = cursor.getInt(5);

            HoaDon hoaDon = new HoaDon(mahoadon, date, maHDCT, maSach, soluong, tongtien);
            hoaDonList.add(hoaDon);
            cursor.moveToNext();
        }
        cursor.close();
        return hoaDonList;

    }


    public List<HoaDon> topSachbanchay() {
        String get1 = "select*from Hoadon where ngayMua like'%2020' order by soLuong desc ";
        List<HoaDon> hoaDonList = new ArrayList<>();
        Cursor cursor = mySQL.getReadableDatabase().rawQuery(get1,null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String mahoadon, date, maHDCT, maSach;
                int soluong, tongtien;
                mahoadon = cursor.getString(0);
                date = cursor.getString(1);
                maHDCT = cursor.getString(2);
                maSach = cursor.getString(3);
                soluong = cursor.getInt(4);
                tongtien = cursor.getInt(5);

                HoaDon hoaDon = new HoaDon(mahoadon, date, maHDCT, maSach, soluong, tongtien);
                hoaDonList.add(hoaDon);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return hoaDonList;

    }
}
