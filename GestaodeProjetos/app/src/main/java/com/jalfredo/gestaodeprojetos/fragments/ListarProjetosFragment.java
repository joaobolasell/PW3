package com.jalfredo.gestaodeprojetos.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;


import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jalfredo.gestaodeprojetos.R;
import com.jalfredo.gestaodeprojetos.adapter.ProjetoAdapter;
import com.jalfredo.gestaodeprojetos.model.Projeto;

import java.util.ArrayList;
import java.util.List;

public class ListarProjetosFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProjetoAdapter adapter;
    private List<Projeto> listaProjetos = new ArrayList<>();
    private DatabaseReference projetosRef;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_listar_projetos, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProjetoAdapter(getContext(), listaProjetos);
        recyclerView.setAdapter(adapter);

        projetosRef = FirebaseDatabase.getInstance().getReference("projetos");
        carregarProjetos();

        view.findViewById(R.id.fabAddProjeto).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(
                    R.id.action_nav_listar_projetos_to_nav_cadastrar_projeto);
        });

        return view;
    }

    private void carregarProjetos() {
        projetosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaProjetos.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Projeto projeto = snapshot.getValue(Projeto.class);
                    if (projeto != null) {
                        projeto.setId(snapshot.getKey());
                        listaProjetos.add(projeto);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Tratar erro
            }
        });
    }


}