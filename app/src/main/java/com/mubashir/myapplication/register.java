package com.mubashir.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {
    TextInputLayout textInputLayout_email,textInputLayout_password,textInputLayout_name;
    EditText editText_email,editText_password,editText_name;
    private FirebaseAuth mAuth;
    String email="";
    String password="";
    String name="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        textInputLayout_email = (TextInputLayout) findViewById(R.id.textil_email);
        textInputLayout_password = (TextInputLayout) findViewById(R.id.textil_password);
        textInputLayout_name = (TextInputLayout) findViewById(R.id.textil_name);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_name = (EditText) findViewById(R.id.editText_name);
        mAuth = FirebaseAuth.getInstance();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent=new Intent(register.this,dashboard.class);
            startActivity(intent);
            finish();
        }

}
public void onSignUp(View view){
    name=String.valueOf(editText_name.getText());
    email = String.valueOf(editText_email.getText());
    password = String.valueOf(editText_password.getText());
    if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "The User is Successfully Logged in", Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent toLogin =new Intent(register.this,login.class);
                            startActivity(toLogin);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(register.this, "Authentication failed.Please try again",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    else{
        Toast.makeText(getApplicationContext(),"Please enter all credentials to proceed",Toast.LENGTH_LONG).show();
    }
}
    }

