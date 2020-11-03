package com.example.qlsachpn_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.qlsachpn_mob204.Adappter.HoaDonAdap;

import java.util.List;

import Model.HoaDon;
import SQL.HoaDonDao;
import SQL.MySQL;

public class TopSachBanChay extends AppCompatActivity {
   MySQL mySQL;
   ListView Lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach_ban_chay);
        mySQL=new MySQL(TopSachBanChay.this);
        Lv=findViewById(R.id.Lv);
        HoaDonDao hoaDonDao=new HoaDonDao(mySQL);
        List<HoaDon> hoaDonList=hoaDonDao.topSachbanchay();
        if (hoaDonList.size()<=3){
            HoaDonAdap hoaDonAdap=new HoaDonAdap(hoaDonList);
            Lv.setAdapter(hoaDonAdap);
        }else {
            for (int i=0;i<3;i++){
               hoaDonList.get(i);
               HoaDonAdap hoaDonAdap=new HoaDonAdap(hoaDonList);
               Lv.setAdapter(hoaDonAdap);

            }
        }
    }
}