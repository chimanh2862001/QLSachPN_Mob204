package com.example.qlsachpn_mob204;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qlsachpn_mob204.Adappter.TheLoaiAdappter;

import java.util.List;

import Model.NguoiDung;
import Model.TheLoai;
import SQL.MySQL;
import SQL.NguoiDungDao;
import SQL.TheLoaiDao;

public class TheLoaiActivity extends AppCompatActivity {
   MySQL mySQL;
   ListView Lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        Lv=findViewById(R.id.Lv);
        mySQL=new MySQL(this);
        TheLoaiDao theLoaiDao=new TheLoaiDao(mySQL);
        List<TheLoai> theLoaiList=theLoaiDao.getAll();
        TheLoaiAdappter theLoaiAdappter=new TheLoaiAdappter(theLoaiList);
        Lv.setAdapter(theLoaiAdappter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId()==R.id.addbill){
            final MySQL mySQL=new MySQL(TheLoaiActivity.this);
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            View view= LayoutInflater.from(this).inflate(R.layout.add_theloai,null);
            builder.setView(view);
            final AlertDialog alertDialog=builder.show();
            final EditText edtsMaTL=view.findViewById(R.id.edtMaTL);
            final EditText edtsTenTL=view.findViewById(R.id.edtTenTL);
            final EditText edtsVitri=view.findViewById(R.id.edtVitri);
            Button save=view.findViewById(R.id.save);
            Button can=view.findViewById(R.id.can);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if (edtsMaTL.getText().toString().trim().length()==0 ||edtsTenTL.getText().toString().trim().length()==0
                            || edtsVitri.getText().toString().trim().length()==0){
                        Toast.makeText(TheLoaiActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        TheLoai theLoai=new TheLoai();

                        theLoai.setMaTl(edtsMaTL.getText().toString().trim());
                        theLoai.setTenTl(edtsTenTL.getText().toString().trim());
                        theLoai.setVitri(edtsVitri.getText().toString().trim());
                        TheLoaiDao theLoaiDao=new TheLoaiDao(mySQL);
                        theLoaiDao.addTL(theLoai);
                     List<TheLoai> theLoaiList=theLoaiDao.getAll();
                     TheLoaiAdappter theLoaiAdappter=new TheLoaiAdappter(theLoaiList);
                        Lv.setAdapter(theLoaiAdappter);
                        Toast.makeText(TheLoaiActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();;

                    }
                }
            });
            can.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }
}