package com.mubashir.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class search extends AppCompatActivity {
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    EditText forSearch;
    String name;
    String Father_Name;
    String Record;
    String Contact_Number;
    String Date_of_Birth;
    String Date_of_Visit;
    String Vaccine_given;
    String next_vac;
    String Age;
    String mode_of_birth;
    String Next_Appointment;
    Button b;
    SimpleDateFormat sdf;
    String myFormat;
    Calendar myCalendar;
    String message;
    String phoneNumber;
    DatePickerDialog.OnDateSetListener date;

    ImageView imageView_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search);
        imageView_search=(ImageView)findViewById(R.id.imageView_letsearch);
        forSearch=(EditText)findViewById(R.id.editText_letsearch);
        b=findViewById(R.id.slogan_name);
        if (ContextCompat.checkSelfPermission(search.this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Ask for permision
            ActivityCompat.requestPermissions(this,new String[] { Manifest.permission.SEND_SMS}, 1);
        }
        else {
// Permission has already been granted
        }
        myCalendar = Calendar.getInstance();
        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(search.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
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
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query query = FirebaseDatabase.getInstance().getReference("User_data")
                        .orderByChild("Date of Birth").equalTo(forSearch.getText().toString());
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    name=String.valueOf(dataSnapshot.child("Name").getValue());
                    Father_Name=String.valueOf(dataSnapshot.child("Father Name").getValue());
                    Record=String.valueOf(dataSnapshot.child("Record").getValue());
                    Contact_Number=String.valueOf(dataSnapshot.child("Contact Number").getValue());
                    Date_of_Visit=String.valueOf(dataSnapshot.child("Date of Visit").getValue());
                    Date_of_Birth=String.valueOf(dataSnapshot.child("Date of Birth").getValue());
                    Next_Appointment=String.valueOf(dataSnapshot.child("Next Appointment").getValue());
                    next_vac=String.valueOf(dataSnapshot.child("Next Vaccine Date").getValue());
                    Vaccine_given=String.valueOf(dataSnapshot.child("Vaccine Given").getValue());
                    Age=String.valueOf(dataSnapshot.child("Age").getValue());
                    mode_of_birth= String.valueOf(dataSnapshot.child("Mode of Birth").getValue());
                 //   Toast.makeText(getApplicationContext(),"The name is "+ String.valueOf(name),Toast.LENGTH_LONG).show();
                     //   Toast.makeText(getApplicationContext(),"else executed",Toast.LENGTH_LONG).show();
                        AlertDialog alertDialog = new AlertDialog.Builder(search.this).create();
                        alertDialog.setTitle("Your Child Record");
                        alertDialog.setMessage("Name: "+name+"\n"+"Father_Name: "+Father_Name+"\n"+"Record: "+Record+"\n"+
                                "Contact_Number: "+Contact_Number+"\n"+"Date of Visit: "+Date_of_Visit
                                +"\n"+"Date of Birth: "+Date_of_Birth
                                +"\n"+"Age: "+Age
                                +"\n"+"Next Appointment: "+Next_Appointment
                                +"\n"+"Next Vaccine Date: "+next_vac
                                +"\n"+"Vaccine Given: "+Vaccine_given
                                +"\n" + "Mode of Birth: "+mode_of_birth
                        );
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.show();





                        //Do something with the individual node here`enter code here`
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

                    Query query2 = FirebaseDatabase.getInstance().getReference("User_data")
                            .orderByChild("Name").equalTo(forSearch.getText().toString());
                    query2.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            name = String.valueOf(dataSnapshot.child("Name").getValue());
                            Father_Name = String.valueOf(dataSnapshot.child("Father Name").getValue());
                            Record = String.valueOf(dataSnapshot.child("Record").getValue());
                            Contact_Number = String.valueOf(dataSnapshot.child("Contact Number").getValue());
                            Date_of_Visit = String.valueOf(dataSnapshot.child("Date of Visit").getValue());
                            Date_of_Birth = String.valueOf(dataSnapshot.child("Date of Birth").getValue());
                            Next_Appointment = String.valueOf(dataSnapshot.child("Next Appointment").getValue());
                            next_vac = String.valueOf(dataSnapshot.child("Next Vaccine Date").getValue());
                            Vaccine_given = String.valueOf(dataSnapshot.child("Vaccine Given").getValue());
                            Age = String.valueOf(dataSnapshot.child("Age").getValue());
                            mode_of_birth= String.valueOf(dataSnapshot.child("Mode of Birth").getValue());
                            AlertDialog alertDialog = new AlertDialog.Builder(search.this).create();
                            alertDialog.setTitle("Your Child Record");
                            alertDialog.setMessage("Name: " + name + "\n" + "Father_Name: " + Father_Name + "\n" + "Record: " +Record+ "\n" +
                                    "Contact_Number: " + Contact_Number + "\n" + "Date of Visit: " + Date_of_Visit
                                    + "\n" + "Date of Birth: " + Date_of_Birth
                                    + "\n" + "Age: " + Age
                                    + "\n" + "Next Appointment: " + Next_Appointment
                                    + "\n" + "Next Vaccine Date: " + next_vac
                                    + "\n" + "Vaccine Given: " + Vaccine_given
                                    + "\n" + "Mode of Birth: " +mode_of_birth
                            );
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                            alertDialog.show();




                            //Do something with the individual node here`enter code here`
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }


                    });



                Query query3 = FirebaseDatabase.getInstance().getReference("User_data")
                        .orderByChild("Contact Number").equalTo(forSearch.getText().toString());
                query3.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        name = String.valueOf(dataSnapshot.child("Name").getValue());
                        Father_Name = String.valueOf(dataSnapshot.child("Father Name").getValue());
                        Record = String.valueOf(dataSnapshot.child("Record").getValue());
                        Contact_Number = String.valueOf(dataSnapshot.child("Contact Number").getValue());
                        Date_of_Visit = String.valueOf(dataSnapshot.child("Date of Visit").getValue());
                        Date_of_Birth = String.valueOf(dataSnapshot.child("Date of Birth").getValue());
                        Next_Appointment = String.valueOf(dataSnapshot.child("Next Appointment").getValue());
                        next_vac = String.valueOf(dataSnapshot.child("Next Vaccine Date").getValue());
                        Vaccine_given = String.valueOf(dataSnapshot.child("Vaccine Given").getValue());
                        Age = String.valueOf(dataSnapshot.child("Age").getValue());
                        mode_of_birth= String.valueOf(dataSnapshot.child("Mode of Birth").getValue());
                        AlertDialog alertDialog = new AlertDialog.Builder(search.this).create();
                        alertDialog.setTitle("Your Child Record");
                        alertDialog.setMessage("Name: " + name + "\n" + "Father_Name: " + Father_Name + "\n" + "Record: " +Record+ "\n" +
                                "Contact_Number: " + Contact_Number + "\n" + "Date of Visit: " + Date_of_Visit
                                + "\n" + "Date of Birth: " + Date_of_Birth
                                + "\n" + "Age: " + Age
                                + "\n" + "Next Appointment: " + Next_Appointment
                                + "\n" + "Next Vaccine Date: " + next_vac
                                + "\n" + "Vaccine Given: " + Vaccine_given
                                + "\n" + "Mode of Birth: " +mode_of_birth
                        );
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                        alertDialog.show();




                        //Do something with the individual node here`enter code here`
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });

                }




    });
    }
    public void GotoBack(View view){
        Intent intent=new Intent(this,record.class);
        startActivity(intent);
        finish();

    }
    private void updateLabel() {
        myFormat = "dd-MMM-yyyy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);

        forSearch.setText(sdf.format(myCalendar.getTime()));

    }


}
