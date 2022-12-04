package com.example.learnenglishtoday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpForm extends AppCompatActivity {
    EditText name, email, password, repassword;
    Button btnRegister;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);
        name = findViewById(R.id.edtName);
        email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);
        repassword = findViewById(R.id.edtRepassword);
        btnRegister = findViewById(R.id.btnSignUp);
        db = new DBHelper(this);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = String.valueOf(email.getText());
                String user = String.valueOf(email.getText());
                String pass = String.valueOf(password.getText());
                String repass = String.valueOf(repassword.getText());
                if (mail.isEmpty() || user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                    Toast.makeText(SignUpForm.this, "Các trường không được để trống", Toast.LENGTH_LONG).show();
                }else if (!mail.contains("@gmail.com")) {
                    Toast.makeText(SignUpForm.this, "Email khong hop le", Toast.LENGTH_LONG).show();
                }else if (!checkPass(pass)){
                    Toast.makeText(SignUpForm.this, "Mat khau nhieu hon 8 ki tu", Toast.LENGTH_LONG).show();
                }else{
                    if (pass.equals(repass)){
                        Boolean checkUser = db.checkUserName(user);
                        if (!checkUser){
                            Boolean insert = db.insertData(mail, pass);
                            if (insert){
                                Toast.makeText(SignUpForm.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginForm.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUpForm.this, "Đăng kí không hành công", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SignUpForm.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpForm.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
    public Boolean checkPass(String pass){
        if (pass.length() < 8) return false;
        return true;
    }
}