package SQL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.NguoiDung;
import Model.TheLoai;

public class TheLoaiDao  {
    private MySQL mySQL;

    public TheLoaiDao(MySQL mySQL) {
        this.mySQL = mySQL;
    }
    public void addTL(TheLoai theLoai){
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase sqLiteDatabase=mySQL.getWritableDatabase();
        contentValues.put("maTheLoai",theLoai.getMaTl());
        contentValues.put("tenTheLoai",theLoai.getTenTl());
        contentValues.put("viTri",theLoai.getVitri());
        sqLiteDatabase.insert("Theloai",null,contentValues);
    }

    public void uppTl(TheLoai theLoai){
        ContentValues contentValues=new ContentValues();
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        contentValues.put("maTheLoai",theLoai.getMaTl());
        contentValues.put("tenTheLoai",theLoai.getTenTl());
        contentValues.put("viTri",theLoai.getVitri());
        sqLiteDatabase.update("Theloai",contentValues,"maTheLoai=?",new String[]{theLoai.getMaTl()});
    }

    public List<TheLoai> getAll(){
        List<TheLoai> theLoaiList=new ArrayList<>();
        String gell_all="Select*from Theloai";
        SQLiteDatabase sqLiteDatabase=mySQL.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(gell_all,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String matl,tentl,vitri;
            matl=cursor.getString(0);
            tentl=cursor.getString(1);
            vitri=cursor.getString(2);
            TheLoai theLoai=new TheLoai(matl,tentl,vitri);
            theLoaiList.add(theLoai);
            cursor.moveToNext();
        }
        cursor.close();
        return theLoaiList;
    }

    public void del(String matheloai){
        SQLiteDatabase sqLiteDatabase=mySQL.getWritableDatabase();
                sqLiteDatabase.delete("Theloai","matheloai=?",new String[]{matheloai});
    }
}
