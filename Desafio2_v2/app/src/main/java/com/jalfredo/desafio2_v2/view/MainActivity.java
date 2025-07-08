package com.jalfredo.desafio2_v2.view;


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

import com.jalfredo.desafio2_v2.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView lista = this.findViewById(R.id.listaMenu);
        String[] itens = {"Cadastrar Café", "Listar Cafés"};
        ArrayAdapter<String> arrayItens = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itens);
        lista.setAdapter(arrayItens);
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
            Intent intent = new Intent(this, AdicionarCafeActivity.class);
            startActivity(intent);
        }
        if (position == 1) {
            Intent intent = new Intent(this, ListarCafeActivity.class);
            startActivity(intent);
        }


    }
}