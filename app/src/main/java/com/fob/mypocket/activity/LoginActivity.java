package com.fob.mypocket.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fob.mypocket.R;
import com.fob.mypocket.config.FirebaseConfig;
import com.fob.mypocket.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    private EditText fieldEmail, fielPass;
    private Button buttonLogin;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fieldEmail = findViewById(R.id.login_email);
        fielPass = findViewById(R.id.login_pass);
        buttonLogin = findViewById(R.id.login_button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textEmail = fieldEmail.getText().toString();
                String textPass = fielPass.getText().toString();

                if (validateFields(textEmail, textPass)) {
                    user = new User();
                    user.setEmail(textEmail);
                    user.setPass(textPass);
                    loginValidate(user);
                }


            }
        });

        auth = FirebaseConfig.getFirebaseAuth();

    }
    private boolean validateFields(String email, String pass) {

        if (!email.isEmpty()) {
            if (!pass.isEmpty()) {
                return true;
            } else {
                Toast.makeText(this, "Informe sua senha", Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Informe o email", Toast.LENGTH_LONG).show();
            return false;
        }

    }
    private void loginValidate(User user) {

        auth = FirebaseConfig.getFirebaseAuth();
        auth.signInWithEmailAndPassword(user.getEmail(), user.getPass())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            String exception;
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                exception = "Usuário não cadastrado!";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                exception = "Email e senha não correspondem a um usuário cadastrado.";
                            } catch (Exception e) {
                                exception = "Erro ao tentar fazer o login: " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(LoginActivity.this, exception, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}