package com.jalfredo.gestaodeprojetos.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import java.util.List;
import android.content.Context;
import android.view.ViewGroup;

import com.jalfredo.gestaodeprojetos.R;
import com.jalfredo.gestaodeprojetos.fragments.ListarProjetosFragment;
import com.jalfredo.gestaodeprojetos.fragments.ListarProjetosFragmentDirections;
import com.jalfredo.gestaodeprojetos.model.Projeto;








import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProjetoAdapter extends RecyclerView.Adapter<ProjetoAdapter.ProjetoViewHolder> {

    private Context context;
    private List<Projeto> listaProjetos;

    public ProjetoAdapter(Context context, List<Projeto> listaProjetos) {
        this.context = context;
        this.listaProjetos = listaProjetos;
    }

    @NonNull
    @Override
    public ProjetoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_projeto, parent, false);
        return new ProjetoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjetoViewHolder holder, int position) {
        Projeto projeto = listaProjetos.get(position);
        holder.txtNome.setText(projeto.getNome());
        holder.txtDescricao.setText(projeto.getDescricao());
        holder.txtStatus.setText(projeto.getStatus());

        holder.btnExcluir.setOnClickListener(v -> confirmarExclusao(position));
        holder.btnEditar.setOnClickListener(v -> editarProjeto(projeto, holder.itemView));
    }

    private void confirmarExclusao(int position) {
        new AlertDialog.Builder(context)
                .setTitle("Excluir Projeto")
                .setMessage("Deseja realmente excluir este projeto?")
                .setPositiveButton("Sim", (dialog, which) -> excluirProjeto(position))
                .setNegativeButton("Não", null)
                .show();
    }

    private void excluirProjeto(int position) {
        String id = listaProjetos.get(position).getId();
        DatabaseReference projetoRef = FirebaseDatabase.getInstance()
                .getReference("projetos").child(id);

        projetoRef.removeValue()
                .addOnSuccessListener(aVoid -> Toast.makeText(context,
                        "Projeto excluído", Toast.LENGTH_SHORT).show());
    }

    private void editarProjeto(Projeto projeto, View view) {
        ListarProjetosFragmentDirections.ActionNavListarProjetosToNavEditarProjeto action =
                ListarProjetosFragmentDirections.actionNavListarProjetosToNavEditarProjeto(
                        projeto.getId(),
                        projeto.getNome(),
                        projeto.getDescricao(),
                        projeto.getStatus()
                );
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public int getItemCount() {
        return listaProjetos.size();
    }

    static class ProjetoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtDescricao, txtStatus;
        Button btnEditar, btnExcluir;

        public ProjetoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnExcluir = itemView.findViewById(R.id.btnExcluir);
        }
    }


}
