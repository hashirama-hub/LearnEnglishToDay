package com.example.learnenglishtoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginForm extends AppCompatActivity {
    EditText email, password;
    Button btnLogin;
    DBHelper db;
    TextView txtGoSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnSignIn);
        txtGoSignUp = findViewById(R.id.txtGoSignup);
        db = new DBHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = String.valueOf(email.getText());
                String pass = String.valueOf(password.getText());
                if (user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(LoginForm.this, "Please input all field", Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkuserpass = db.checkUserNamePassWord(user, pass);
                    if (checkuserpass){
                        Toast.makeText(LoginForm.this, "Login successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginForm.this, MainActivity.class);
                        intent.putExtra("email", user);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginForm.this, "Email or password is invalid", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        txtGoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpForm.class);
                startActivity(intent);
            }
        });
    }
}