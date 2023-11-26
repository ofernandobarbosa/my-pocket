package com.fob.mypocket.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText fieldName, fieldEmail, fieldPass;
    private FirebaseAuth auth;
    private Button button;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        fieldName = findViewById(R.id.register_name);
        fieldEmail = findViewById(R.id.register_email);
        fieldPass = findViewById(R.id.register_pass);
        button = findViewById(R.id.register_button);
        auth = FirebaseConfig.getFirebaseAuth();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textName = fieldName.getText().toString();
                String textEmail = fieldEmail.getText().toString();
                String textPass = fieldPass.getText().toString();

                if (validateFields(textName, textEmail, textPass)) {
                    user = new User();
                    user.setName(textName);
                    user.setPass(textPass);
                    user.setEmail(textEmail);
                    registerUser(user);
                }

            }

        });

    }

    public void registerUser(User user) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPass())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                        } else {
                            String exception = "";
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                exception = "Digite uma senha mais forte";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                exception = "Digite um email válido!";
                            } catch (FirebaseAuthUserCollisionException e) {
                                exception = "Esse email já está cadastrado";
                            } catch (Exception e) {
                                exception = "Erro ao cadastrar usuário: " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(RegisterUserActivity.this, exception, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    private boolean validateFields(String name, String email, String pass) {
        if (!name.isEmpty()) {
            if (!email.isEmpty()) {
                if (!pass.isEmpty()) {
                    return true;
                } else {
                    Toast.makeText(RegisterUserActivity.this,
                            "Preencha sua senha",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                Toast.makeText(RegisterUserActivity.this,
                        "Preencha seu email",
                        Toast.LENGTH_LONG).show();
                return false;
            }
        } else {
            Toast.makeText(RegisterUserActivity.this,
                    "Preencha seu nome",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

}