package com.jalfredo.desafio2_v2.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jalfredo.desafio2_v2.R;
import com.jalfredo.desafio2_v2.dao.AppDatabase;
import com.jalfredo.desafio2_v2.entity.Cafe;

public class AdicionarCafeActivity extends AppCompatActivity {


    EditText nome;
    EditText descricao;
    EditText valor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adicionar_cafe);

        Button botao = findViewById(R.id.buttonAdd);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = findViewById(R.id.editTextNome);
                descricao = findViewById(R.id.editTextDescricao);
                valor = findViewById(R.id.editTextNumberValor);

                Cafe cafe = new Cafe();
                cafe.setNome(nome.getText().toString());
                cafe.setDescricao(descricao.getText().toString());
                cafe.setValor(valor.getText().toString());

                AppDatabase.getInstance(getApplicationContext()).createCafeDAO().insert(cafe);
                Toast.makeText(getApplicationContext(), "Café foi Cadastrado!", Toast.LENGTH_LONG).show();
                limparCampos();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void limparCampos() {
        nome = findViewById(R.id.editTextNome);
        descricao = findViewById(R.id.editTextDescricao);
        valor = findViewById(R.id.editTextNumberValor);
        nome.setText("");
        descricao.setText("");
        valor.setText("");
    }







}