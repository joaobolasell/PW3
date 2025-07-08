package com.jalfredo.desafio1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listaViagensPesca);
        Viagem [] viagens = Viagem.viagens;
        ArrayAdapter<Viagem> listAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, viagens);
        lista.setAdapter(listAdapter);
        lista.setOnItemClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent(this, LagoPalmas.class);
            startActivity(intent);
        }
        if (position == 1) {
            Intent intent = new Intent(this, RioSono.class);
            startActivity(intent);
        }
        if (position == 2) {
            Intent intent = new Intent(this, SanJavier.class);
            startActivity(intent);
        }
    }
}