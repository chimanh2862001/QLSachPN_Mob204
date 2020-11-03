package SQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.NguoiDung;

public class NguoiDungDao {
    private MySQL mySQL;

    public NguoiDungDao(MySQL mySQL) {
        this.mySQL = mySQL;
    }

    public void addND(NguoiDung nguoiDung){
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Id",nguoiDung.getId());
        contentValues.put("hoTen",nguoiDung.getHoten());
        contentValues.put("username",nguoiDung.getUsername());
        contentValues.put("passWord",nguoiDung.getPass());
        contentValues.put("phone",nguoiDung.getSdt());
        contentValues.put("diachi",nguoiDung.getDiachi());
        sqLiteDatabase.insert("Nguoidung",null,contentValues);
    }

    public List<NguoiDung> getAll(){
        List<NguoiDung> nguoiDungList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        String get_all="Select*from Nguoidung";
        Cursor cursor=sqLiteDatabase.rawQuery(get_all,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String id,hoten,username,password,phone,diachi;
            id=cursor.getString(0);
            hoten=cursor.getString(1);
            username=cursor.getString(2);
            password=cursor.getString(3);
            phone=cursor.getString(4);
            diachi=cursor.getString(5);
            NguoiDung nguoiDung=new NguoiDung(id,hoten,username,password,phone,diachi);
            nguoiDungList.add(nguoiDung);
            cursor.moveToNext();

        }
        cursor.close();
        return nguoiDungList;
    }

    public void upDateND(NguoiDung nguoiDung){
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Id",nguoiDung.getId());
        contentValues.put("hoTen",nguoiDung.getHoten());
        contentValues.put("username",nguoiDung.getUsername());
        contentValues.put("passWord",nguoiDung.getPass());
        contentValues.put("phone",nguoiDung.getSdt());
        contentValues.put("diachi",nguoiDung.getDiachi());
        sqLiteDatabase.update("Nguoidung",contentValues,"Id=?",new String[]{nguoiDung.getId()});
    }

    public void deleteND(String id){
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        sqLiteDatabase.delete("Nguoidung","id=?",new String[]{id});

    }
    public List<NguoiDung> searUser(String user){

        String search="Select*from Nguoidung where username like '%"+user+"%' ";
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        List<NguoiDung> nguoiDungList=new ArrayList<>();
        Cursor cursor=sqLiteDatabase.rawQuery(search,null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String id,hoten,username,password,phone,diachi;
                id=cursor.getString(0);
                hoten=cursor.getString(1);
                username=cursor.getString(2);
                password=cursor.getString(3);
                phone=cursor.getString(4);
                diachi=cursor.getString(5);
                NguoiDung nguoiDung=new NguoiDung(id,hoten,username,password,phone,diachi);
                nguoiDungList.add(nguoiDung);
                cursor.moveToNext();

            }
            cursor.close();
        }
        return nguoiDungList;

    }


}
