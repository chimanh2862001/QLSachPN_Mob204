package com.example.qlsachpn_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Model.NguoiDung;
import SQL.MySQL;
import SQL.NguoiDungDao;

public class MainActivity extends AppCompatActivity {
       EditText edtUser,edtPass;
       CheckBox checkBox;
       MySQL mySQL;
       Button button10;
       List<NguoiDung> nguoiDungList;
       TextView textView8;
       
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUser=findViewById(R.id.edtUser);
        edtPass=findViewById(R.id.edtPass);
        checkBox=findViewById(R.id.checkBox);
        textView8=findViewById(R.id.textView8);
        mySQL=new MySQL(MainActivity.this);
        NguoiDungDao nguoiDungDao=new NguoiDungDao(mySQL);
        nguoiDungList=nguoiDungDao.getAll();
        checkBox.setSelected(true);

        button10=findViewById(R.id.button10);
        this.load();


        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DangKyActivity.class);
                startActivity(intent);

            }
        });
        
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (edtUser.getText().toString().trim().equals("manh") && edtPass.getText().toString().trim().equals("12345")){
//                    Intent intent=new Intent(MainActivity.this,MenuActivity.class);
//                    startActivity(intent);
//                }else {
//                    Toast.makeText(MainActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
//                }

                for (NguoiDung s:nguoiDungList){
                    System.out.println(edtUser.getText().toString().trim());
                    System.out.println(edtPass.getText().toString().trim());
                    if (s.getUsername().equals(edtUser.getText().toString().trim())
                            && s.getPass().equals(edtPass.getText().toString().trim())){
                        Intent intent=new Intent(MainActivity.this,MenuActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "\n" +
                                "Logged in successfully", Toast.LENGTH_SHORT).show();
                        break;
                    }
//                    else {
//                        Toast.makeText(MainActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });
    }

    public void saveNho(View view){
        SharedPreferences sharedPreferences=getSharedPreferences("abc",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String us=edtUser.getText().toString().trim();
        String pas=edtPass.getText().toString().trim();
        editor.putString("us",us);
        editor.putString("pas",pas);
        editor.commit();

    }
    private void load(){
        SharedPreferences sharedPreferences=getSharedPreferences("abc",MODE_PRIVATE);
        String uss=sharedPreferences.getString("us",null);
        String pass=sharedPreferences.getString("pas",null);

        edtUser.setText(uss);
        edtPass.setText(pass);
    }
}