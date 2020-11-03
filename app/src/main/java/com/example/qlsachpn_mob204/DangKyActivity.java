package com.example.qlsachpn_mob204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.NguoiDung;
import SQL.MySQL;
import SQL.NguoiDungDao;

public class DangKyActivity extends AppCompatActivity {
    EditText  edtId,edtName,edtUser,edtPass,edtP,edtPhone,edtDiachi;
    Button dangky,button14;
    MySQL mySQL;
    String ma="[A-Z][a-zA-Z0-9 ]{4,14}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
       mySQL=new MySQL(DangKyActivity.this);
        edtId=findViewById(R.id.edtId);
        edtName=findViewById(R.id.edtName);
        edtUser=findViewById(R.id.edtUser);
        edtPass=findViewById(R.id.edtPass);
        edtP=findViewById(R.id.edtsP);
        edtPhone=findViewById(R.id.edtPhone);
        edtDiachi=findViewById(R.id.edtDiachi);

        dangky=findViewById(R.id.dangky);
        button14=findViewById(R.id.button14);

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtName.getText().toString().trim().matches(ma)){
                    Toast.makeText(DangKyActivity.this, "Chữ cái đầu tên người dùng viết hoa và Độ dài >=5 và <=15", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(edtName.getText().toString().trim().length()<5 || edtName.getText().toString().trim().length()>15){
                    Toast.makeText(DangKyActivity.this, "Tên người dùng Tối thiếu 5 ký tự và tối da 15", Toast.LENGTH_SHORT).show();
                    return;
                }
                
              else  if (edtId.getText().toString().trim().length()==0 ||edtName.getText().toString().trim().length()==0
                    || edtUser.getText().toString().trim().length()==0 || edtPass.getText().toString().trim().length()==0||
                        edtP.getText().toString().trim().length()==0|| edtPhone.getText().toString().trim().length()==0
                || edtDiachi.getText().toString().trim().length()==0){
                    Toast.makeText(DangKyActivity.this, "No null", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    if (edtPass.getText().toString().trim().equals(edtP.getText().toString().trim())){
                        NguoiDungDao nguoiDungDao=new NguoiDungDao(mySQL);
                        NguoiDung nguoiDung=new NguoiDung();

                        nguoiDung.setId(edtId.getText().toString().trim());
                        nguoiDung.setHoten(edtName.getText().toString().trim());
                        nguoiDung.setUsername(edtUser.getText().toString().trim());
                        nguoiDung.setPass(edtPass.getText().toString().trim());
                        nguoiDung.setSdt(edtPhone.getText().toString().trim());
                        nguoiDung.setDiachi(edtDiachi.getText().toString().trim());
                        nguoiDungDao.addND(nguoiDung);
                        Log.e("Đang Ky","Thanh Cong");
                        Intent intent=new Intent(DangKyActivity.this,MainActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(DangKyActivity.this, "Mật Khẩu Không giống nhau", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangKyActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}