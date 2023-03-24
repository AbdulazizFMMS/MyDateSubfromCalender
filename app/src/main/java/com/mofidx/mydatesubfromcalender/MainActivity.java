package com.mofidx.mydatesubfromcalender;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
Button btntoday,btnbirthday,btncalculate;
TextView tv_res;

DatePickerDialog.OnDateSetListener dateSetListener1,dateSetListener2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnbirthday=findViewById(R.id.btnbirth);
        btncalculate=findViewById(R.id.btncalculate);
        btntoday=findViewById(R.id.btntoday);

        tv_res= findViewById(R.id.tv_result);


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        // عين تاريخ اليوم في نص من نوع فورمات dd/MM/yyyy وضعه في نص البوتن
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/MM/yyyy");
        String datenow = simpleDateFormat.format(Calendar.getInstance().getTime());
        btntoday.setText(datenow);


        btnbirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,dateSetListener1,
                        year,month,day);

                // لون شفاف لخلفية الديت بيكر
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });


        // يستقبل القيم اللي تم اختيارها في الديت بيكر
        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month= month +1;
                String dateofbirth = day+"/"+month+"/"+year;

                btnbirthday.setText(dateofbirth);

            }
        };



        btntoday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Material_Dialog_MinWidth,dateSetListener2,
                        year,month,day);

                // لون شفاف لخلفية الديت بيكر
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });


        // يستقبل القيم اللي تم اختيارها في الديت بيكر
        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                month= month +1;
                String dateofbirth = day+"/"+month+"/"+year;

                btntoday.setText(dateofbirth);

            }
        };


        btncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String startDate = btnbirthday.getText().toString();
                String endDate = btntoday.getText().toString();

                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    Date date1 = simpleDateFormat1.parse(startDate);
                    Date date2 = simpleDateFormat1.parse(endDate);

                    assert date1 != null;
                    long sdate = date1.getTime();
                    assert date2 != null;
                    long edate = date2.getTime();

                    if (sdate <= edate ){
                        Period period = new Period(sdate,edate, PeriodType.days());

                        int aha = period.getDays();

                        Period period1 = new Period(sdate,edate, PeriodType.yearMonthDay());
                        int years = period1.getYears();
                        int months = period1.getMonths();
                        int days = period1.getDays();


                        tv_res.setText(aha+" is the total Days \n"+years+"year |"+months+"months| "+days);
                    }else {
                        Toast.makeText(getApplicationContext(),"يجب ان لايكون تاريخ الميلاد اكبر من تاريخ اليوم !",Toast.LENGTH_LONG).show();
                    }

                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }




            }
        });



    }



}
