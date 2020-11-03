package com.example.qlsachpn_mob204.Adappter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qlsachpn_mob204.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.HoaDon;
import Model.Sach;
import SQL.HoaDonDao;
import SQL.MySQL;
import SQL.SachDao;

public class  HoaDonAdap extends BaseAdapter {
    List<HoaDon> hoaDonList;

    public HoaDonAdap(List<HoaDon> hoaDonList) {
        this.hoaDonList = hoaDonList;
    }

    @Override
    public int getCount() {
        return hoaDonList.size();
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
         convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_hoadon,parent,false);
         final HoaDon hoaDon=hoaDonList.get(position);
        ImageView imgdelHd=convertView.findViewById(R.id.imgdelHd);
        ImageView imgupHd=convertView.findViewById(R.id.imgupHd);
        TextView tv=convertView.findViewById(R.id.tv);
        tv.setText("Ma Hoa Don: "+hoaDon.getMaHoadon()+"\nDate: "+hoaDon.getDate()
                +"\nMã Sách: "+hoaDon.getMaSach()+"\t -- \tMã HDCT: "+hoaDon.getMaHDCT()
                +"\nSố lượng: "+hoaDon.getSoluong()+"\nTổng Tiền: "+hoaDon.getTongTien());

        imgdelHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mahd=hoaDonList.get(position).getMaHDCT();
                MySQL mySQL=new MySQL(parent.getContext());
                HoaDonDao hoaDonDao=new HoaDonDao(mySQL);
                hoaDonDao.delteHoaDon(mahd);
                hoaDonList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(parent.getContext(), "Dell Finally", Toast.LENGTH_SHORT).show();
            }
        });

        imgupHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySQL mySQL=new MySQL(parent.getContext());
                List<String> namebook=new ArrayList<>();
                SachDao sachDao=new SachDao(mySQL);
                final List<Sach> sachList=sachDao.getAllSach();

                ///
                for (int i=0;i<sachList.size();i++){
                    String mBook=sachList.get(i).getMasach();
                    namebook.add(mBook);
                }
                ////
                String ma=hoaDonList.get(position).getMaHDCT();
                AlertDialog.Builder builder=new AlertDialog.Builder(parent.getContext());
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.update_hoadon,parent,false);
                builder.setView(view);
                final AlertDialog alertDialog=builder.show();

                final EditText edtsMaHoadon=view.findViewById(R.id.edtsMaHoadon);
                final EditText edtsDate=view.findViewById(R.id.edtsDate);
                final EditText edtsmaHDCT=view.findViewById(R.id.edtsmaHDCT);
                final EditText edtsSoluong=view.findViewById(R.id.edtsSoluong);
                final Spinner spinner=view.findViewById(R.id.spinner);
                edtsmaHDCT.setText(ma);

                ArrayAdapter arrayAdapter=new ArrayAdapter(parent.getContext(), android.R.layout.simple_list_item_1,namebook);
                spinner.setAdapter(arrayAdapter);

                Button chonngay=view.findViewById(R.id.chonngay);
                Button button19=view.findViewById(R.id.button19);
                Button button12=view.findViewById(R.id.button12);

                chonngay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar calendar=Calendar.getInstance();
                        int year=calendar.get(Calendar.YEAR);
                        int month=calendar.get(Calendar.MONTH);
                        int date=calendar.get(Calendar.DATE);
                        DatePickerDialog datePickerDialog=new DatePickerDialog(parent.getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String valudate=dayOfMonth+"/"+month+"/"+year;
                                edtsDate.setText(edtsDate.getText()+"\n"+valudate);
                            }
                        },year,month,date);
                        datePickerDialog.show();
                    }

                });


                button19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tong=0;
                        String soluong=edtsSoluong.getText().toString().trim();
                        int so=Integer.parseInt(soluong);

                        if (edtsMaHoadon.getText().toString().trim().length()==0||
                                edtsDate.getText().toString().trim().length()==0
                                || edtsSoluong.getText().toString().trim().length()==0
                        || edtsmaHDCT.getText().toString().trim().length()==0){
                            Toast.makeText(parent.getContext(), "No null", Toast.LENGTH_SHORT).show();
                            return;
                        }else {

                                 for (int i=0;i<sachList.size();i++){
                                     if (spinner.getSelectedItem().toString().equals(sachList.get(i).getMasach())){

                                         tong+=Integer.parseInt(edtsSoluong.getText().toString().trim())*sachList.get(i).getGiaban();
                                         MySQL mySQL=new MySQL(parent.getContext());
                                         HoaDonDao hoaDonDao=new HoaDonDao(mySQL);
                                         hoaDon.setMaHoadon(edtsMaHoadon.getText().toString().trim());
                                         hoaDon.setMaHDCT(edtsmaHDCT.getText().toString().trim());
                                         hoaDon.setDate(edtsDate.getText().toString().trim());
                                         hoaDon.setMaSach(spinner.getSelectedItem().toString());
                                         hoaDon.setSoluong(Integer.parseInt(edtsSoluong.getText().toString().trim()));
                                         hoaDon.setTongTien(tong);
                                         Log.e("UPHD","TC");

                                         hoaDonDao.updateHD(hoaDon);
                                         alertDialog.dismiss();
                                         notifyDataSetChanged();
                                     }
//                                     else  if (so>sachList.get(i).getSoluong() || ){
//                                         Toast.makeText(parent.getContext(), "Số lượng tồn kho k đủ", Toast.LENGTH_SHORT).show();
//                                         return;
//
//                                     }
                                 }


                        }

                    }
                });

                button12.setOnClickListener(new View.OnClickListener() {
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
