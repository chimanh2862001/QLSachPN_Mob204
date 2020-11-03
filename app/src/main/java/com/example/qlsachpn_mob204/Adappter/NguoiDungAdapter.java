package com.example.qlsachpn_mob204.Adappter;

import android.app.AlertDialog;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsachpn_mob204.R;

import java.util.List;

import Model.NguoiDung;
import SQL.MySQL;
import SQL.NguoiDungDao;

public class NguoiDungAdapter  extends BaseAdapter {
    List<NguoiDung> nguoiDungList;

    public NguoiDungAdapter(List<NguoiDung> nguoiDungList) {
        this.nguoiDungList = nguoiDungList;
    }

    @Override
    public int getCount() {
        return nguoiDungList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_user,parent,false);
        final NguoiDung nguoiDung=nguoiDungList.get(position);
        TextView tvTen=convertView.findViewById(R.id.tvTen);
        TextView tvUser=convertView.findViewById(R.id.tvUser);
        final ImageView imgdel=convertView.findViewById(R.id.imgdel);
        ImageView imgSua=convertView.findViewById(R.id.imgSua);
        tvTen.setText("Id: "+nguoiDung.getId()+"--Họ Tên: "+nguoiDung.getHoten());
        tvUser.setText("UserName: "+nguoiDung.getUsername()+"--SDT: "+nguoiDung.getSdt()+"\n Địa Chỉ: "+nguoiDung.getDiachi());

        if ((position+1)%2==0){
         tvTen.setTextColor(Color.parseColor("#21D129"));
         tvUser.setTextColor(Color.parseColor("#21D129"));
        }else {
            tvUser.setTextColor(Color.parseColor("#F41807"));
            tvTen.setTextColor(Color.parseColor("#F41807"));

        }
        imgdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQL mySQL=new MySQL(parent.getContext());
                NguoiDungDao nguoiDungDao=new NguoiDungDao(mySQL);
                String us=nguoiDungList.get(position).getId();
                nguoiDungDao.deleteND(us);
                nguoiDungList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(parent.getContext(),"Xóa Thành Công",Toast.LENGTH_LONG).show();

            }
        });

        imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String rphone="0\\d{9,10}";
              MySQL mySQL=new MySQL(parent.getContext());
//              NguoiDungDao nguoiDungDao=new NguoiDungDao(mySQL);
              final String suaid=nguoiDungList.get(position).getId();
                AlertDialog.Builder builder=new AlertDialog.Builder(parent.getContext());
              View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.updateuser,parent,false);
              builder.setView(view);
              final AlertDialog alertDialog=builder.show();
                final EditText edtsId=view.findViewById(R.id.edtsId);
                final EditText edtsHoten=view.findViewById(R.id.edtsHoten);
                final EditText edtsUsername=view.findViewById(R.id.edtsUsername);
                final EditText edtsPass=view.findViewById(R.id.edtsPass);
                final EditText edtsPhone=view.findViewById(R.id.edtsPhone);
                final EditText edtsDiachi=view.findViewById(R.id.edtsDiachi);
                Button button3=view.findViewById(R.id.button3);
                Button button4=view.findViewById(R.id.button4);
                edtsId.setText(suaid);

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtsId.getText().toString().trim().length()==0 ||edtsHoten.getText().toString().trim().length()==0||
                                edtsUsername.getText().toString().trim().length()==0||edtsPass.getText().toString().trim().length()==0||
                                edtsPhone.getText().toString().trim().length()==0 || edtsDiachi.getText().toString().trim().length()==0){
                            Toast.makeText(parent.getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                            return;
                        }else if (!edtsPhone.getText().toString().trim().matches(rphone)){
                            Toast.makeText(parent.getContext(), "Sai Định Dạng SDT", Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            MySQL mySQL1=new MySQL(parent.getContext());
                            NguoiDungDao nguoiDungDao1=new NguoiDungDao(mySQL1);

                            nguoiDung.setId(edtsId.getText().toString().trim());
                            nguoiDung.setHoten(edtsHoten.getText().toString().trim());
                            nguoiDung.setPass(edtsPass.getText().toString().trim());
                            nguoiDung.setSdt(edtsPhone.getText().toString().trim());
                            nguoiDung.setDiachi(edtsDiachi.getText().toString().trim());
                            nguoiDungDao1.upDateND(nguoiDung);
                            Log.e("Sua","Thanh Cong");
                            notifyDataSetChanged();
                            alertDialog.dismiss();
                        }
                    }
                });

                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

            }

        });


        return convertView;
    }
}
