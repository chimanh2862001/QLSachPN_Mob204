package com.example.qlsachpn_mob204.Adappter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsachpn_mob204.R;
import com.example.qlsachpn_mob204.SachActivity;

import java.util.ArrayList;
import java.util.List;

import Model.Sach;
import Model.TheLoai;
import SQL.MySQL;
import SQL.SachDao;
import SQL.TheLoaiDao;

public class SachAdapter extends BaseAdapter {
    List<Sach> sachList;

    public SachAdapter(List<Sach> sachList) {
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
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
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_sach,parent,false);
        final Sach sach=sachList.get(position);
        TextView tvTenSach=convertView.findViewById(R.id.tvTenSach);
//        TextView tvGia=convertView.findViewById(R.id.tvGia);
        ImageView imgdelSach=convertView.findViewById(R.id.imgdelSach);
        ImageView imgUpSach=convertView.findViewById(R.id.imgUpSach);

        imgdelSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQL mySQL=new MySQL(parent.getContext());
                SachDao sachDao=new SachDao(mySQL);
                String masach=sachList.get(position).getMasach();
                sachDao.delete(masach);
                sachList.remove(position);
                notifyDataSetChanged();
            }
        });

        tvTenSach.setText("ID: "+sach.getMasach()+"\nTên Sách: "+sach.getTensach()+"\nThể Loại: "+sach.getTentl()
                +"\nGiá Bán: "+sach.getGiaban()+"\nSố lượng: "+sach.getSoluong()+"\t Sale: "+sach.getSale()+"%");
//        tvGia.setText("Gia Sach: "+sach.getGiaban()+"\nSo luong: "+sach.getSoluong());

        imgUpSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> ttl=new ArrayList<>();
                AlertDialog.Builder builder=new AlertDialog.Builder(parent.getContext());
                final String maus=sachList.get(position).getMasach();
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.update_sach,parent,false);
                builder.setView(view);
                final AlertDialog alertDialog=builder.show();
                MySQL mySQL=new MySQL(parent.getContext());
                TheLoaiDao theLoaiDao=new TheLoaiDao(mySQL);
                List<TheLoai> theLoaiList=theLoaiDao.getAll();
        ///
                for (int i=0;i<theLoaiList.size();i++){
                    TheLoai theLoai=theLoaiList.get(i);
                    String tl=theLoai.getTenTl();
                    ttl.add(tl);
                }
                ////

                EditText edttMaSach=view.findViewById(R.id.edttMaSach);
                final EditText edttTenSach=view.findViewById(R.id.edttTenSach);
                final EditText edttNXB=view.findViewById(R.id.edttNXB);
                final EditText edttGiaBan=view.findViewById(R.id.edttGiaBan);
                final EditText edttSoluong=view.findViewById(R.id.edttSoluong);
                final EditText edttTacgGia=view.findViewById(R.id.edttTacGia);
                final Spinner spinner=view.findViewById(R.id.tspinner);
                      final EditText edtsSale=view.findViewById(R.id.edtsSale);
                ArrayAdapter arrayAdapter=new ArrayAdapter(parent.getContext(), android.R.layout.simple_list_item_1,ttl);
                spinner.setAdapter(arrayAdapter);
                final String sachcq="[A-Z][a-zA-Z0-9 ]{9,19}";

                Button button7=view.findViewById(R.id.button7);
                Button button8=view.findViewById(R.id.button8);
                edttMaSach.setText(maus);

                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String abc=edtsSale.getText().toString().trim();
                        int sal=Integer.parseInt(abc);
                        if (edttTenSach.getText().toString().length()==0 || edttNXB.getText().toString().length()==0
                            || edttGiaBan.getText().toString().length()==0 || edttSoluong.getText().toString().length()==0
                                || edttTacgGia.getText().toString().length()==0)
                        {

                            Toast.makeText(parent.getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();

                        }else if (!edttTenSach.getText().toString().trim().matches(sachcq)){
                            Toast.makeText(parent.getContext(), "Chữ cái đầu viết hoa và độ dài lớn hơn 10 và nhỏ hơn 20", Toast.LENGTH_SHORT).show();

                            return;

                        }else if (sal>100){
                            Toast.makeText(parent.getContext(), "Sale tối đa 100%", Toast.LENGTH_SHORT).show();
                              return;
                        }else {
                            MySQL mySQL=new MySQL(parent.getContext());
                            SachDao sachDao=new SachDao(mySQL);
                            sach.setMasach(maus);
                            sach.setTensach(edttTenSach.getText().toString().trim());
                            sach.setTentl(spinner.getSelectedItem().toString());
                            sach.setNXB(edttNXB.getText().toString().trim());
                            sach.setSoluong(Integer.parseInt(edttSoluong.getText().toString().trim()));
                            sach.setGiaban(Integer.parseInt(edttGiaBan.getText().toString().trim()));
                            sach.setTacgia(edttTacgGia.getText().toString().trim());
                            sach.setSale(Integer.parseInt(edtsSale.getText().toString().trim()));
                            sachDao.upDateSach(sach);
                            notifyDataSetChanged();
                            alertDialog.dismiss();
                        }
                    }
                });

                button8.setOnClickListener(new View.OnClickListener() {
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
