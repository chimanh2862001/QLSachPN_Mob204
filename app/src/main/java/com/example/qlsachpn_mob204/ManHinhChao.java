package com.example.qlsachpn_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        setTitle("Màn Hình Chào");
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                int waited = 0;
                while(waited < 1000){
                    try {
                        sleep(500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    waited+=200;
                }
                Intent intent = new Intent(ManHinhChao.this, MainActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                finish();
            }
        };
        thread.start();
    }
}