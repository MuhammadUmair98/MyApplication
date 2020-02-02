package com.mubashir.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class record extends AppCompatActivity {
    Button add;
    ImageView image;
    TextView logoText, sloganText;
    Calendar myCalendar;
    SimpleDateFormat sdf;
    Date n_appoint;
    static long seconds;
    static long hours;
    static long minutes;
    static long millSec;
    Date present_day;
    String myFormat;
    static String name;
    static String Father_Name;
    long diff;
    Long realm;
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener datee;
    DatePickerDialog.OnDateSetListener dateee;
    ImageView imageView_dateofbirth,imageview_appoinmentdate,imageView_v_date;
    TextInputLayout textInputLayout_childname, textInputLayout_fathername,
            textInputLayout_number,textInputLayout_dateofbirth,
            textInputLayout_modeofbirth,textInputLayout_nextappoinmnt,textInputLayout_vaccine,textInputLayout_nextv_date;
    EditText editText_name,editText_fathername,editText_contactnumber,
            editText_dataofbirth,editText_modeofbirth,editText_nextappoinmnt,editText_vaccine,editText_v_date;
    EditText editText_dateofVisit;
    EditText editText_age;
    EditText editText_record;
    static String Next_Appointment;
    String formattedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_record);
      //  button_date_of_birth=(Button)findViewById(R.id.button_dateofbirth);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        imageview_appoinmentdate=(ImageView)findViewById(R.id.appoinment_imageview);
        imageView_v_date=(ImageView)findViewById(R.id.vdate_imageview);
        add = findViewById(R.id.login);
        textInputLayout_childname=(TextInputLayout)findViewById(R.id.textil_childname);
        textInputLayout_fathername=(TextInputLayout)findViewById(R.id.textil_childfathername);
        textInputLayout_number=(TextInputLayout)findViewById(R.id.textil_contnumber);
        textInputLayout_dateofbirth=(TextInputLayout)findViewById(R.id.textil_dateofbirt);
        textInputLayout_modeofbirth=(TextInputLayout)findViewById(R.id.textil_modeofbirth);
        textInputLayout_nextappoinmnt=(TextInputLayout)findViewById(R.id.textil_nextappoinmnt);
        textInputLayout_vaccine=(TextInputLayout)findViewById(R.id.textil_vaxine);
        textInputLayout_nextv_date=(TextInputLayout)findViewById(R.id.textil_nextv_date);
        editText_name=(EditText)findViewById(R.id.editText_childname);
        editText_record=(EditText)findViewById(R.id.editText_recordno);
        editText_fathername=(EditText)findViewById(R.id.editText_childfathername);
        editText_contactnumber=(EditText)findViewById(R.id.editText_contactnumber);
        editText_dataofbirth=(EditText)findViewById(R.id.editText_dateofbirth);
        editText_modeofbirth=(EditText)findViewById(R.id.editText_modeofbirth);
        editText_nextappoinmnt=(EditText)findViewById(R.id.editText_nextappontmnt);
        editText_vaccine=(EditText)findViewById(R.id.editText_vaxine);
        editText_v_date=(EditText)findViewById(R.id.editText_nextvdata);
        editText_dateofVisit=(EditText)findViewById(R.id.editText_dateofVisit);
        editText_age=(EditText)findViewById(R.id.editText_Age);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = df.format(c);
        String message;
        String phoneNumber;
        editText_dateofVisit.setText(String.valueOf(formattedDate));

        imageView_dateofbirth=(ImageView)findViewById(R.id.dateofbirth_imageview);
        myCalendar = Calendar.getInstance();
        imageView_dateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(record.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
        imageview_appoinmentdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(record.this, datee, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        imageView_v_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(record.this, dateee, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //click listener//
         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }

        };
        datee = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabele();

            }

        };
        dateee = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelee();

            }

        };

    }
    private void updateLabele() {
        myFormat = "dd-MMM-yyyy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText_nextappoinmnt.setText(sdf.format(myCalendar.getTime()));

    }

    private void updateLabel() {
        myFormat = "dd-MMM-yyyy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);



//        Date endDate=(Date)editText_dateofVisit.getText();
        editText_dataofbirth.setText(sdf.format(myCalendar.getTime()));
        String startDate=String.valueOf(editText_dataofbirth.getText());
        String endDate=String.valueOf(editText_dateofVisit.getText());
        Log.i("Format",startDate);
      //  Toast.makeText(getApplicationContext(),startDate,Toast.LENGTH_LONG).show();
        try {
               Date d = sdf.parse(startDate);
            Date date = sdf.parse(endDate);
            Calendar start = Calendar.getInstance();
            start.setTime(d);

            Calendar end = Calendar.getInstance();
            end.setTime(date);

            int monthsBetween = 0;
            int dateDiff = end.get(Calendar.DAY_OF_MONTH)-start.get(Calendar.DAY_OF_MONTH);

            if(dateDiff<0) {
                int borrrow = end.getActualMaximum(Calendar.DAY_OF_MONTH);
                dateDiff = (end.get(Calendar.DAY_OF_MONTH)+borrrow)-start.get(Calendar.DAY_OF_MONTH);
                monthsBetween--;

                if(dateDiff>0) {
                    monthsBetween++;
                }
            }
            else {
                monthsBetween++;
            }
            monthsBetween += end.get(Calendar.MONTH)-start.get(Calendar.MONTH);
            monthsBetween  += (end.get(Calendar.YEAR)-start.get(Calendar.YEAR))*12;



            editText_age.setText(String.valueOf(monthsBetween)+" Months Old");
             Toast.makeText(getApplicationContext(),String.valueOf(d),Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Log.i("Message",e.getMessage());
        }

    }
    private void updateLabelee() {
        myFormat = "dd-MMM-yyyy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        editText_v_date.setText(sdf.format(myCalendar.getTime()));


    }
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
    public void DataSave(View view){
        try {

            Next_Appointment=String.valueOf(editText_nextappoinmnt.getText());
            name=editText_name.getText().toString();
            Father_Name=editText_fathername.getText().toString();
             n_appoint = sdf.parse(Next_Appointment);
             present_day = sdf.parse(formattedDate);
            diff = n_appoint.getTime()-present_day.getTime();
             seconds = diff / 1000;
             minutes = seconds / 60;
             hours = minutes / 60;
             if(seconds>=86340) {
                  realm = seconds - 86340;
             }
             else{
                 realm=seconds;
             }
             millSec=realm*1000;
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong("Value",millSec);
            editor.apply();
            SharedPreferences preferencefather = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editorfather = preferences.edit();
            editor.putString("Father",Father_Name);
            editor.apply();
            SharedPreferences preferenceson = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editorson = preferences.edit();
            editor.putString("Son",name);
            editor.apply();
            SharedPreferences preferencedate = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editordate = preferences.edit();
            editor.putString("Date",Next_Appointment);
            editor.apply();
            SharedPreferences preferencenumber = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editornumber = preferences.edit();
            editor.putString("Number",editText_contactnumber.getText().toString());
            editor.apply();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            if (!String.valueOf(editText_name.getText()).isEmpty() && !String.valueOf(editText_record.getText()).isEmpty() && !String.valueOf(editText_fathername.getText()).isEmpty() && !String.valueOf(editText_contactnumber.getText()).isEmpty() && !String.valueOf(editText_dataofbirth.getText()).isEmpty() && !String.valueOf(editText_nextappoinmnt.getText()).isEmpty() ) {
                DatabaseReference myRef = database.getReference("User_data").push();
                myRef.child("Date").setValue(editText_dataofbirth.getText().toString());
                myRef.child("Name").setValue(editText_name.getText().toString().toLowerCase());
                myRef.child("Father Name").setValue(editText_fathername.getText().toString());
                myRef.child("Contact Number").setValue(editText_contactnumber.getText().toString());
                myRef.child("Date of Birth").setValue(editText_dataofbirth.getText().toString());
                myRef.child("Date of Visit").setValue(editText_dateofVisit.getText().toString());
                myRef.child("Vaccine Given").setValue(editText_vaccine.getText().toString());
                myRef.child("Next Vaccine Date").setValue(editText_v_date.getText().toString());
                myRef.child("Age").setValue(editText_age.getText().toString());
                myRef.child("Mode of Birth").setValue(editText_modeofbirth.getText().toString());
                myRef.child("Next Appointment").setValue(editText_nextappoinmnt.getText().toString());
                myRef.child("Record").setValue(editText_record.getText().toString());

                startService(new Intent(this, RSSPullService.class));

                Toast.makeText(getApplicationContext(), "Data Stored Successfully", Toast.LENGTH_LONG).show();
             String   phoneNumber=String.valueOf(editText_contactnumber.getText());
              String  message="Dear "+name +" s/d/o of "+Father_Name+" your data is successfully saved your appointment" +
                        " date is "+Next_Appointment;

                sendSMS(phoneNumber,message);

            } else {
                Toast.makeText(getApplicationContext(), "Please fill all forms to continue", Toast.LENGTH_LONG).show();
            }

        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }
    private void sendSMS(String phoneNumber, String message) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }


}
