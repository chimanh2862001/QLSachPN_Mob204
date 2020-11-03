package SQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.HoaDon;
import Model.Sach;

public class SachDao {
    private MySQL mySQL;

    public SachDao(MySQL mySQL) {
        this.mySQL = mySQL;
    }

    public void  addSach(Sach sach){
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("maSach",sach.getMasach());
        contentValues.put("tenSach",sach.getTensach());
        contentValues.put("tenTheLoai",sach.getTentl());
        contentValues.put("tacGia",sach.getTacgia());
        contentValues.put("NXB",sach.getNXB());
        contentValues.put("giaBan",sach.getGiaban());
        contentValues.put("soLuong",sach.getSoluong());
        contentValues.put("sale",sach.getSale());
        sqLiteDatabase.insert("Sach",null,contentValues);

    }
    public void upDateSach(Sach sach){
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("maSach",sach.getMasach());
        contentValues.put("tenSach",sach.getTensach());
        contentValues.put("tenTheLoai",sach.getTentl());
        contentValues.put("tacGia",sach.getTacgia());
        contentValues.put("NXB",sach.getNXB());
        contentValues.put("giaBan",sach.getGiaban());
        contentValues.put("soLuong",sach.getSoluong());
        contentValues.put("sale",sach.getSale());
        sqLiteDatabase.update("Sach",contentValues,"maSach=?",new String[]{sach.getMasach()});
    }
    public void delete(String masach){
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        sqLiteDatabase.delete("Sach","masach=?",new String[]{masach});
    }

    public List<Sach> getAllSach(){
       List<Sach> sachList=new ArrayList<>();
       SQLiteDatabase sqLiteDatabase= mySQL.getWritableDatabase();
       String get_allSach="Select*from sach";
        Cursor cursor=sqLiteDatabase.rawQuery(get_allSach,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String masach,tensach,tentheloai,tacgia,NXB;
            int giaban,soluong,sale;
            masach=cursor.getString(0);
            tensach=cursor.getString(1);
            tentheloai=cursor.getString(2);
            tacgia=cursor.getString(3);
            NXB=cursor.getString(4);
            giaban=cursor.getInt(5);
            soluong=cursor.getInt(6);
            sale=cursor.getInt(7);
            Sach sach=new Sach(masach,tensach,tentheloai,tacgia,NXB,giaban,soluong,sale);
            sachList.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return sachList;
    }
    public List<Sach> timKiemSach(String timSach){
          List<Sach> sachList=new ArrayList<>();
          String search="Select*from Sach where tenSach like '%"+timSach+"%' ";
//        String sql = "SELECT * FROM USER WHERE username LIKE '%" + TimUsername + "%'";
           Cursor cursor=mySQL.getReadableDatabase().rawQuery(search,null);
           if (cursor.getCount()>0){
               cursor.moveToFirst();
              while (!cursor.isAfterLast()){
                   String masach=cursor.getString(0);
                   String tensach=cursor.getString(1);
                   String tentheloai=cursor.getString(2);
                   String tagia=cursor.getString(3);
                   String NXB=cursor.getString(4);
                   int  giaban=cursor.getInt(5);
                   int soluong=cursor.getInt(6);
                   int sale=cursor.getInt(7);

                   Sach sach=new Sach(masach,tensach,tentheloai,tagia,NXB,soluong,giaban,sale);
                   sachList.add(sach);

                   cursor.moveToNext();

              }
              cursor.close();

           }
           cursor.close();
           return sachList;

    }
    public List<Sach>  sachSale(){
        List<Sach> sachList=new ArrayList<>();
        String sale1="Select*from Sach where sale not like '0'";
        Cursor cursor=mySQL.getReadableDatabase().rawQuery(sale1,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String masach=cursor.getString(0);
            String tensach=cursor.getString(1);
            String tentheloai=cursor.getString(2);
            String tagia=cursor.getString(3);
            String NXB=cursor.getString(4);
            int  giaban=cursor.getInt(5);
            int soluong=cursor.getInt(6);
            int sale=cursor.getInt(7);

            Sach sach=new Sach(masach,tensach,tentheloai,tagia,NXB,giaban,soluong,sale);
            sachList.add(sach);

            cursor.moveToNext();
        }
        cursor.close();
        return sachList;

    }

}
