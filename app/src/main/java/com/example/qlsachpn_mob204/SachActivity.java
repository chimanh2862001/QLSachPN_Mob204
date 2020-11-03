package com.example.qlsachpn_mob204;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qlsachpn_mob204.Adappter.SachAdapter;

import java.util.ArrayList;
import java.util.List;

import Model.Sach;
import Model.TheLoai;
import SQL.MySQL;
import SQL.SachDao;
import SQL.TheLoaiDao;

public class SachActivity extends AppCompatActivity {
    String tl[]={"CNTT","TOANHOC","VANHOC"};
    ListView Lv;
    List<String> ttl;
    Button button11;
    MySQL mySQL;
    TheLoai theLoai= null;
    List<TheLoai> theLoaiList;
    String sachcq="[A-Z][a-zA-Z0-9 ]{9,19}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        Lv=findViewById(R.id.Lv);
        button11=findViewById(R.id.button11);
        mySQL=new MySQL(SachActivity.this);
        SachDao sachDao=new SachDao(mySQL);
        TheLoaiDao theLoaiDao=new TheLoaiDao(mySQL);
        ttl=new ArrayList<>();
        theLoaiList=theLoaiDao.getAll();

        for (int i=0;i<theLoaiList.size();i++){
            TheLoai theLoai=theLoaiList.get(i);
            String tl=theLoai.getTenTl();
            ttl.add(tl);
        }
        List<Sach> sachList=sachDao.getAllSach();

        SachAdapter sachAdapter=new SachAdapter(sachList);
        Lv.setAdapter(sachAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.addbill){
            AlertDialog.Builder builder=new AlertDialog.Builder(SachActivity.this);
            View view= LayoutInflater.from(this).inflate(R.layout.add_sach,null);
            builder.setView(view);

            final AlertDialog alertDialog=builder.show();

            final EditText edtMaSach=view.findViewById(R.id.edtMaSach);
            final EditText edtTenSach=view.findViewById(R.id.edtTenSach);
            final EditText edtTacGia=view.findViewById(R.id.edtTacGia);
            final EditText edtNXB=view.findViewById(R.id.edtNXB);
            final EditText edtGiaBan=view.findViewById(R.id.edtGiaBan);
            final EditText edtSoluong=view.findViewById(R.id.edtSoluong);
            final Spinner spinner=view.findViewById(R.id.spinner);
            final EditText edtSale=view.findViewById(R.id.edtSale);
//            final EditText edtTl=view.findViewById(R.id.edtTL);
            Button button5=view.findViewById(R.id.button5);
            Button button6=view.findViewById(R.id.button6);

//            TheLoaiDao theLoaiDao=new TheLoaiDao(mySQL);
//            List<TheLoai> theLoaiList=theLoaiDao.getAll();
//
//            for (int i=0;i<theLoaiList.size();i++){
//                TheLoai theLoai=theLoaiList.get(i);
//                String tl=theLoai.getTenTl();
//            }
//            String tenSach = edtTenSach.getText().toString().trim();
//            String s1 = tenSach.substring(0, 1).toUpperCase();
//
//            int d = tenSach.length();
//            String s2 = tenSach.substring(1, d);
//
//            edtTenSach.setText(s1 + s2);

            ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,ttl);
            spinner.setAdapter(arrayAdapter);

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String abc=edtSale.getText().toString().trim();
                    int sal=Integer.parseInt(abc);
//                    if (ttl.size()<0){
//                        Toast.makeText(SachActivity.this, "Vui long them the loai", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            if (position > 0)
//                                theLoai = theLoaiList.get(position);
//
//                            else {
//                                theLoai = null;
//                            }
//                        }
//                    });
                    if (edtMaSach.getText().toString().trim().length()==0||edtTenSach.getText().toString().trim().length()==0||
                            edtTacGia.getText().toString().trim().length()==0|| edtNXB.getText().toString().trim().length()==0||
                            edtGiaBan.getText().toString().trim().length()==0||edtSoluong.getText().toString().trim().length()==0
                            || edtSale.getText().toString().trim().length()==0){
                        Toast.makeText(SachActivity.this, "Không được để trống", Toast.LENGTH_SHORT).show();
                        return;
//                    }
//                    else if(edtTenSach.getText().toString().trim().length()<=10
//                            || edtTenSach.getText().toString().trim().length()>=20) {
////                        edtTenSach.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
////                        if (edtTenSach.getText().toString().trim().substring(0, 1)
////                                != edtTenSach.getText().toString().trim().substring(0, 1).toUpperCase()) {
////                            System.out.println(edtTenSach.getText().toString().trim().substring(0,1));
////                            Toast.makeText(SachActivity.this, "Chữ Cái đầu phải viết hoa", Toast.LENGTH_SHORT).show();
////                            return;
////                        } else {
//                        Toast.makeText(SachActivity.this, "Tên Sách phải lớn 10 và nhỏ hơn 20 ", Toast.LENGTH_SHORT).show();
//                        return;
                    }
                    else if (!edtTenSach.getText().toString().trim().matches(sachcq)){

                        Toast.makeText(SachActivity.this, "Chữ cái đầu viết hoa và độ dài lớn hơn 10 và nhỏ hơn 20", Toast.LENGTH_SHORT).show();
                        return ;

////                        String tenSach = edtTenSach.getText().toString().trim();
////                        String s1 = tenSach.substring(0, 1).toUpperCase();
////                       int d = tenSach.length();
//                       String s2 = tenSach.substring(1, d);
//                        edtTenSach.setText(s1 + s2);

                    }else  if (sal>100){
                        Toast.makeText(SachActivity.this, "Sale tối đa 100%", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        Sach sach = new Sach();
                        sach.setMasach(edtMaSach.getText().toString().trim());
                        sach.setTensach(edtTenSach.getText().toString().trim());
                        sach.setTacgia(edtTacGia.getText().toString().trim());
                        sach.setTentl(spinner.getSelectedItem().toString().trim());
                        sach.setNXB(edtNXB.getText().toString().trim());
                        sach.setGiaban(Integer.parseInt(edtGiaBan.getText().toString().trim()));
                        sach.setSoluong(Integer.parseInt(edtSoluong.getText().toString().trim()));
                        sach.setSale(Integer.parseInt(edtSale.getText().toString().trim()));
                        SachDao sachDao = new SachDao(mySQL);
                        sachDao.addSach(sach);
                        List<Sach> sachList = sachDao.getAllSach();
                        SachAdapter sachAdapter = new SachAdapter(sachList);
                        Lv.setAdapter(sachAdapter);
                        Log.e("sd", "abc");
                        alertDialog.dismiss();
                    }






                }
            });
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }

    public void searchSach(View view){
            EditText edttim=findViewById(R.id.edtTim);
            String timsach=edttim.getText().toString().trim();
            if (timsach.isEmpty()){
                edttim.setError("Nhập tên sách cần tìm");
            }
            SachDao sachDao=new SachDao(mySQL);
            List<Sach> sachList=sachDao.timKiemSach(timsach);
            if (sachList.size()==0){
                edttim.setError("Khong tim thay sach nao");

            }else {
                SachAdapter sachAdapter=new SachAdapter(sachList);
                Lv.setAdapter(sachAdapter);
            }

          }

          public void sachSale(View view){
             SachDao sachDao=new SachDao(mySQL);
             List<Sach> sachList=sachDao.sachSale();
             if (sachList.size()==0){
                 button11.setError("Khong co sach Sale");

             }else {
                 SachAdapter sachAdapter=new SachAdapter(sachList);
                 Lv.setAdapter(sachAdapter);
             }
          }
}