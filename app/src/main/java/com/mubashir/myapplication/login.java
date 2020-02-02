package com.mubashir.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    TextInputLayout textInputLayout_email,textInputLayout_password;
    EditText editText_email,editText_password;
    TextView textview;
    String email,password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        textInputLayout_email=(TextInputLayout)findViewById(R.id.textil_email);
        textInputLayout_password=(TextInputLayout)findViewById(R.id.textil_password);
        editText_email=(EditText)findViewById(R.id.editText_email);
        editText_password=(EditText)findViewById(R.id.editText_password);
        textview=(TextView)findViewById(R.id.editText4);
        mAuth = FirebaseAuth.getInstance();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent=new Intent(login.this,dashboard.class);
            startActivity(intent);
            finish();
        }

    }

    public void login_with_Firebase(View view){
        email=String.valueOf(editText_email.getText());
        password=String.valueOf(editText_password.getText());
        if(!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                finish();

                            }
                            else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Your Credentials are Wrong Please try again", Toast.LENGTH_LONG).show();


                            }

                            // ...
                        }
                    });
          //  Intent intent = new Intent(getApplicationContext(), dashboard.class);
       //     startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Please enter all the credentials to continue",Toast.LENGTH_LONG).show();
        }
    }
    public void signup(View view){
        Intent intent=new Intent(getApplicationContext(),register.class);
        startActivity(intent);
    }
}
