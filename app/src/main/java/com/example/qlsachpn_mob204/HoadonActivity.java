package com.example.qlsachpn_mob204;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.qlsachpn_mob204.Adappter.HoaDonAdap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.HoaDon;
import Model.Sach;
import SQL.HoaDonDao;
import SQL.MySQL;
import SQL.SachDao;

public class HoadonActivity extends AppCompatActivity {
  ListView Lv;
  MySQL mySQL;
  List<String> nameBook;
    List<Sach> sachList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);
        Lv=findViewById(R.id.Lv);
        mySQL=new MySQL(HoadonActivity.this);
        HoaDonDao hoaDonDao=new HoaDonDao(mySQL);
        List<HoaDon> hoaDonList=hoaDonDao.getAlll();
        HoaDonAdap hoaDonAdap=new HoaDonAdap(hoaDonList);
        nameBook=new ArrayList<>();
        Lv.setAdapter(hoaDonAdap);
        SachDao sachDao=new SachDao(mySQL);
         sachList=sachDao.getAllSach();
        for (int i=0;i<sachList.size();i++){
            String maSach=sachList.get(i).getMasach();
            nameBook.add(maSach);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()==R.id.addbill){

            AlertDialog.Builder builder=new AlertDialog.Builder(HoadonActivity.this);
            View view= LayoutInflater.from(this).inflate(R.layout.add_hoadon,null);
            builder.setView(view);
            final AlertDialog alertDialog=builder.show();
            final EditText edtMaHoadon=view.findViewById(R.id.edtMaHoadon);
            final EditText edtDate=view.findViewById(R.id.edtDate);
            final EditText edtmaHDCT=view.findViewById(R.id.edtmaHDCT);
            final EditText edtSoluong=view.findViewById(R.id.edtSoluong);
            final EditText edtTongtien=view.findViewById(R.id.edtTongtien);
            final Spinner spinner=view.findViewById(R.id.spinner);
            Button button13=view.findViewById(R.id.button13);
            Button button15=view.findViewById(R.id.button15);
            Button button16=view.findViewById(R.id.button16);

            final ArrayAdapter arrayAdapter=new ArrayAdapter(HoadonActivity.this, android.R.layout.simple_list_item_1,nameBook);
            spinner.setAdapter(arrayAdapter);

            button13.setOnClickListener(new View.OnClickListener() {
                HoaDon hoaDon=new HoaDon();
                @Override
                public void onClick(View v) {
                    Calendar cal=Calendar.getInstance();
                    int Year =cal.get(Calendar.YEAR);
                    int Month=cal.get(Calendar.MONTH);
                    int Day=cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog date=new DatePickerDialog(HoadonActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            String stringOfDate = dayOfMonth + "/" + month + "/" + year;
                            edtDate.setText(edtDate.getText()+ " \n" + stringOfDate);

                        }


                    }, Year, Month, Day);

                    date.show();
                }
            });

            button15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tongt=0;
                    int gia=0;
                    if (edtMaHoadon.getText().toString().trim().length()==0 || edtDate.getText().toString().length()==0
                       || edtmaHDCT.getText().toString().trim().length()==0 || edtSoluong.getText().toString().trim().length()==0
//                        ||
//                            edtTongtien.getText().toString().trim().length()==0
                    ){
                        Toast.makeText(HoadonActivity.this, "No null", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        for (int i=0;i<sachList.size();i++){
                            if (spinner.getSelectedItem().toString().trim().equals(sachList.get(i).getMasach())){
                                String tong=edtTongtien.getText().toString().trim();
//                                 tongt=Integer.parseInt(tong);
                                tongt+=Integer.parseInt(edtSoluong.getText().toString().trim())*sachList.get(i).getGiaban();
                                System.out.println(tongt);
                                gia+=sachList.get(i).getGiaban();
                                HoaDonDao hoaDonDao=new HoaDonDao(mySQL);
                                HoaDon hoaDon=new HoaDon();
                                hoaDon.setMaHoadon(edtMaHoadon.getText().toString().trim());
                                hoaDon.setDate(edtDate.getText().toString().trim());
                                hoaDon.setMaHDCT(edtmaHDCT.getText().toString().trim());
                                hoaDon.setMaSach(spinner.getSelectedItem().toString().trim());
                                hoaDon.setSoluong(Integer.parseInt(edtSoluong.getText().toString().trim()));
                                hoaDon.setTongTien(tongt);
                                hoaDonDao.addHD(hoaDon);

                                List<HoaDon> hoaDonList=hoaDonDao.getAlll();
                                HoaDonAdap hoaDonAdap=new HoaDonAdap(hoaDonList);
                                Lv.setAdapter(hoaDonAdap);
                                Log.e("THEMHD","HDTC");
                                alertDialog.dismiss();

                            }else {

                            }
                        }
//                        edtTongtien.setText(Integer.parseInt(edtSoluong.getText().toString().trim())*gia);
//                        edtTongtien.setText(tongt);






                    }
                }
            });
            button16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}