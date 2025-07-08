package com.jalfredo.desafio2_v2.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.jalfredo.desafio2_v2.R;
import com.jalfredo.desafio2_v2.entity.Cafe;
import com.jalfredo.desafio2_v2.view.ListarCafeActivity;

import java.util.ArrayList;
import java.util.List;

public class LinhaConsultaAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater = null;

    List<Cafe> cafes =  new ArrayList<>();

    private ListarCafeActivity listar;

    public LinhaConsultaAdapter(ListarCafeActivity listar, List<Cafe> cafes ) {
        this.cafes = cafes;
        this.listar = listar;
        this.layoutInflater = (LayoutInflater) this.listar.getSystemService(ListarCafeActivity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return cafes.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_linha, null);

        AppCompatTextView txtViewCodigo = viewLinhaLista.findViewById(R.id.textViewCodigo);
        AppCompatTextView txtNomeCafe = viewLinhaLista.findViewById(R.id.textViewNome);
        AppCompatTextView txtDescricaoCafe = viewLinhaLista.findViewById(R.id.textViewDescricao);
        AppCompatTextView txtValorCafe = viewLinhaLista.findViewById(R.id.textViewValor);
        AppCompatButton buttonCompartilhar = viewLinhaLista.findViewById(R.id.buttonCompartilhar);
        
        txtViewCodigo.setText(String.valueOf(cafes.get(position).getId()));
        txtNomeCafe.setText(cafes.get(position).getNome());
        txtDescricaoCafe.setText(cafes.get(position).getDescricao());
        txtValorCafe.setText(cafes.get(position).getValor());

        buttonCompartilhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String shareContent = "Café: " + cafes.get(position).getNome() + "\n" +
                        "Descrição: " + cafes.get(position).getDescricao() + "\n" +
                        "Valor: " + cafes.get(position).getValor();
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
                listar.startActivity(Intent.createChooser(shareIntent, "Compartilhar via"));
            }
        });

        return viewLinhaLista;
    }









}
