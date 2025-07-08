package com.jalfredo.gestaodeprojetos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jalfredo.gestaodeprojetos.R;
import com.jalfredo.gestaodeprojetos.model.Projeto;



public class EditarProjetoFragment extends Fragment {

    private TextInputEditText edtNome, edtDescricao, edtStatus;
    private DatabaseReference projetoRef;
    private String projetoId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_projeto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNome = view.findViewById(R.id.edtNome);
        edtDescricao = view.findViewById(R.id.edtDescricao);
        edtStatus = view.findViewById(R.id.edtStatus);

        projetoRef = FirebaseDatabase.getInstance().getReference("projetos");

        // Recebe os argumentos passados via Safe Args
        if (getArguments() != null) {
            EditarProjetoFragmentArgs args = EditarProjetoFragmentArgs.fromBundle(getArguments());
            projetoId = args.getId();
            edtNome.setText(args.getNome());
            edtDescricao.setText(args.getDescricao());
            edtStatus.setText(args.getStatus());
        }

        view.findViewById(R.id.btnAtualizar).setOnClickListener(v -> atualizarProjeto());
    }

    private void atualizarProjeto() {
        String nome = edtNome.getText().toString();
        String descricao = edtDescricao.getText().toString();
        String strStatus = edtStatus.getText().toString();

        if (nome.isEmpty() || descricao.isEmpty() || strStatus.isEmpty()) {
            Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }


        Projeto projeto = new Projeto(nome, descricao, strStatus);

        projetoRef.child(projetoId).setValue(projeto)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(getContext(), "Projeto atualizado!", Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(requireView());
                    navController.popBackStack();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                });
    }


}