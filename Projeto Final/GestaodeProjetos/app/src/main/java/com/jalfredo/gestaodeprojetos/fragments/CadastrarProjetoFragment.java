package com.jalfredo.gestaodeprojetos.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jalfredo.gestaodeprojetos.R;
import com.jalfredo.gestaodeprojetos.model.Projeto;

public class CadastrarProjetoFragment extends Fragment {

    private TextInputEditText edtNome, edtDescricao, edtStatus;
    private DatabaseReference projetosRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastrar_projeto, container, false);

        edtNome = view.findViewById(R.id.edtNome);
        edtDescricao = view.findViewById(R.id.edtDescricao);
        edtStatus = view.findViewById(R.id.edtStatus);

        projetosRef = FirebaseDatabase.getInstance().getReference("projetos");
        view.findViewById(R.id.btnCadastrar).setOnClickListener(v -> cadastrarProjeto());
        return view;

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_cadastrar_projeto, container, false);
    }

    private void cadastrarProjeto() {
        String nome = edtNome.getText().toString();
        String descricao = edtDescricao.getText().toString();
        String status = edtStatus.getText().toString();

        if (nome.isEmpty() || descricao.isEmpty() || status.isEmpty()) {
            Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }


        Projeto projeto = new Projeto(nome, descricao, status);

        projetosRef.push().setValue(projeto)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(getContext(), "Projeto cadastrado!", Toast.LENGTH_SHORT).show();
                    NavHostFragment.findNavController(this).popBackStack();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                });

    }


}