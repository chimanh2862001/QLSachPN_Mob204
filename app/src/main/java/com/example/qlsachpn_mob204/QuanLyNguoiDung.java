package com.example.qlsachpn_mob204;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsachpn_mob204.Adappter.NguoiDungAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import Model.NguoiDung;
import SQL.MySQL;
import SQL.NguoiDungDao;

public class QuanLyNguoiDung extends AppCompatActivity {
   ListView Lv;
    MySQL mySQL;
    String rphone="0\\d{9,10}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nguoi_dung);
        Lv=findViewById(R.id.Lv);
        mySQL=new MySQL(this);
        NguoiDungDao nguoiDungDao=new NguoiDungDao(mySQL);
        List<NguoiDung> nguoiDungList=nguoiDungDao.getAll();

        Comparator<NguoiDung> comparator=new Comparator<NguoiDung>() {
            @Override
            public int compare(NguoiDung o1, NguoiDung o2) {
                return o1.getHoten().compareTo(o2.getHoten());
            }
        };
        Collections.sort(nguoiDungList,comparator);
        NguoiDungAdapter nguoiDungAdapter=new NguoiDungAdapter(nguoiDungList);
        Lv.setAdapter(nguoiDungAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.them){

            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            View view= LayoutInflater.from(this).inflate(R.layout.adduser,null);
            builder.setView(view);


            final EditText edtId=view.findViewById(R.id.edtId);
            final EditText edtHoten=view.findViewById(R.id.edtHoten);
            final EditText edtUser=view.findViewById(R.id.edtUserName);
            final EditText edtPass=view.findViewById(R.id.edtPass);
            final EditText edtPhone=view.findViewById(R.id.edtPhone);
            final EditText edtDiachi=view.findViewById(R.id.edtDiachi);

            final AlertDialog alertDialog = builder.show();
            Button button=view.findViewById(R.id.button);
            Button button2=view.findViewById(R.id.button2);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edtId.getText().toString().trim().length()==0 || edtHoten.getText().toString().trim().length()==0
                    || edtUser.getText().toString().trim().length()==0 || edtPass.getText().toString().trim().length()==0
                    || edtPhone.getText().toString().trim().length()==0|| edtDiachi.getText().toString().trim().length()==0){
                        Toast.makeText(QuanLyNguoiDung.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                        return;

                    }else if (!edtPhone.getText().toString().trim().matches(rphone)){
                        Toast.makeText(QuanLyNguoiDung.this, "Sai định dạng SDT", Toast.LENGTH_SHORT).show();
                        return;


                    }else {
                        NguoiDung nguoiDung = new NguoiDung();
                        nguoiDung.setId(edtId.getText().toString().trim());
                        nguoiDung.setHoten(edtHoten.getText().toString().trim());
                        nguoiDung.setUsername(edtUser.getText().toString().trim());
                        nguoiDung.setPass(edtPass.getText().toString().trim());
                        nguoiDung.setSdt(edtPhone.getText().toString().trim());
                        nguoiDung.setDiachi(edtDiachi.getText().toString().trim());
                        NguoiDungDao nguoiDungDao = new NguoiDungDao(mySQL);
                        nguoiDungDao.addND(nguoiDung);
                        List<NguoiDung> nguoiDungList = nguoiDungDao.getAll();
                        ////
                        Comparator<NguoiDung>  comparator=new Comparator<NguoiDung>() {
                            @Override
                            public int compare(NguoiDung o1, NguoiDung o2) {
                                return o1.getHoten().compareTo(o2.getHoten());
                            }
                        };
                        Collections.sort(nguoiDungList,comparator);
                        ///
                        NguoiDungAdapter nguoiDungAdapter = new NguoiDungAdapter(nguoiDungList);
                        Lv.setAdapter(nguoiDungAdapter);
                        Toast.makeText(QuanLyNguoiDung.this, "Thêm Thành công", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });


        }else if(item.getItemId()==R.id.doimatkhau){

        }else if (item.getItemId()==R.id.dangxuat){
            Intent intent=new Intent(QuanLyNguoiDung.this,MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
    public void timUS(View view){

        EditText edtUS=findViewById(R.id.edtUS);
        String user=edtUS.getText().toString().trim();
        if (user.isEmpty()){
            edtUS.setError("Không được để trống");
        }
        NguoiDungDao nguoiDungDao=new NguoiDungDao(mySQL);
        List<NguoiDung> nguoiDungList=nguoiDungDao.searUser(user);
        if (nguoiDungList.size()==0){
            edtUS.setError("Không tìm thấy user");
        }else{
            NguoiDungAdapter nguoiDungAdapter=new NguoiDungAdapter(nguoiDungList);
            Lv.setAdapter(nguoiDungAdapter);
        }
    }
}