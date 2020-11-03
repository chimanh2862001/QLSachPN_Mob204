package com.example.qlsachpn_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {
     ImageView img1,img2,img3,img4,img5,img6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.imgDelTL);
        img4=findViewById(R.id.imgUpTL);
        img5=findViewById(R.id.img5);
        img6=findViewById(R.id.img6);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentq=new Intent(MenuActivity.this,QuanLyNguoiDung.class);
                startActivity(intentq);

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,TheLoaiActivity.class);
                startActivity(intent);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MenuActivity.this,SachActivity.class);
                startActivity(intent1);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,HoadonActivity.class);

                startActivity(intent);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MenuActivity.this,TopSachBanChay.class);
                startActivity(intent);
            }
        });
    }
}