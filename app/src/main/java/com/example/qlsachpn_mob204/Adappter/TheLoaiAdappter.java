package com.example.qlsachpn_mob204.Adappter;

import android.app.AlertDialog;
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

import Model.TheLoai;
import SQL.MySQL;
import SQL.TheLoaiDao;

public class TheLoaiAdappter extends BaseAdapter {
    List<TheLoai> theLoaiList;

    public TheLoaiAdappter(List<TheLoai> theLoaiList) {
        this.theLoaiList = theLoaiList;
    }

    @Override
    public int getCount() {
        return theLoaiList.size();
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
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_theloai,parent,false);
        final TheLoai theLoai=theLoaiList.get(position);
        TextView tvMaTL=convertView.findViewById(R.id.tvMaTL);
        TextView tvTenTl=convertView.findViewById(R.id.tvTenTL);
        ImageView img3=convertView.findViewById(R.id.imgDelTL);
        ImageView img4=convertView.findViewById(R.id.imgUpTL);
        tvMaTL.setText("Mã Thể Loại: "+theLoai.getMaTl());
        tvTenTl.setText("Tên Thể Loại: "+theLoai.getTenTl()+"\n Vị Trí: "+theLoai.getVitri());

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQL mySQL=new MySQL(parent.getContext());
                 String matheloai=theLoaiList.get(position).getMaTl();
                TheLoaiDao theLoaiDao=new TheLoaiDao(mySQL);
                theLoaiDao.del(matheloai);
                theLoaiList.remove(position);
                Toast.makeText(parent.getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma=theLoaiList.get(position).getMaTl();
                MySQL mySQL=new MySQL(parent.getContext());
                final TheLoaiDao theLoaiDao=new TheLoaiDao(mySQL);
                AlertDialog.Builder builder=new AlertDialog.Builder(parent.getContext());
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.update_theloai,parent,false);
                builder.setView(view);
                final AlertDialog alertDialog=builder.show();
                Button button5=view.findViewById(R.id.save);
                Button button6=view.findViewById(R.id.can);
                final EditText edtsMaTL=view.findViewById(R.id.edtsMaTL);
                final EditText edtsTenTL=view.findViewById(R.id.edtsTenTL);
                final EditText edtsVitri=view.findViewById(R.id.edtsVitri);
                edtsMaTL.setText(ma);
                button5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtsMaTL.getText().toString().trim().length()==0 ||edtsTenTL.getText().toString().trim().length()==0
                                    || edtsVitri.getText().toString().trim().length()==0){
                                Toast.makeText(parent.getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                                return;
                        }else {
                            theLoai.setMaTl(edtsMaTL.getText().toString().trim());
                            theLoai.setTenTl(edtsTenTL.getText().toString().trim());
                            theLoai.setVitri(edtsVitri.getText().toString().trim());
                            theLoaiDao.uppTl(theLoai);
                            notifyDataSetChanged();
                            Toast.makeText(parent.getContext(), "Cập Nhập Thành Công", Toast.LENGTH_SHORT).show();
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
        });
        return convertView;
    }
}
