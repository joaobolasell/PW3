package com.jalfredo.gestaodeprojetos;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //declara instância do FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //inicializa a instância do FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // verifica se o usuário está autenticado
        verificarUsuarioLogado();

        // Configuração dos listeners dos botões
        findViewById(R.id.btnLogin).setOnClickListener(v -> fazerLogin());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void fazerLogin() {
        String email = ((EditText) findViewById(R.id.lEmail)).getText().toString();
        String senha = ((EditText) findViewById(R.id.lSenha)).getText().toString();

        // Validação dos campos
        if (email.isEmpty() || senha.isEmpty()) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Preencha todos os campos",
                    Snackbar.LENGTH_SHORT).show();
            return;
        }

        // Tentativa de login
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, task -> {
                    // Verifica se o login foi bem-sucedido
                    // Se sim, redireciona para a Home do app
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, HomeActivity.class));
                        finish();
                    } else {
                        if (task.getException() != null) {
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            String errorCode = e.getErrorCode();
                            String errorMsg = e.getMessage();

                            if (errorCode.equals("ERROR_INVALID_CREDENTIAL") ||
                                    errorCode.equals("ERROR_USER_NOT_FOUND")) {

                                Log.d("AUTH_FLOW", "Redirecionando para Login. Motivo: " + errorCode);
                                Snackbar.make(findViewById(android.R.id.content),
                                        "E-mail ou senha incorretos. Verifique e tente novamente.",
                                        Snackbar.LENGTH_LONG).show();

                            } else {
                                Snackbar.make(findViewById(android.R.id.content),
                                        "Erro: " + errorMsg,
                                        Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void verificarUsuarioLogado() {

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Se já estiver logado, vai direto para Home do app
            startActivity(new Intent(this, HomeActivity.class));
            finish(); // Remove esta activity da pilha
        }


    }


}