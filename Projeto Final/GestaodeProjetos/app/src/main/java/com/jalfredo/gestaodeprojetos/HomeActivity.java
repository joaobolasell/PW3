package com.jalfredo.gestaodeprojetos;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView navView;
    private NavController navController;
    private FirebaseAuth auth;

    private static final String CONTACT_EMAIL_ADDRESS = "joaobolasell@hotmail.com";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Inicializa o Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Configura a navegação
        navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // Vincula o BottomNavigationView com o NavController
        NavigationUI.setupWithNavController(navView, navController);

        // Configura o listener personalizado para tratar o logout
        navView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_logout) {
                mostrarDialogoConfirmacaoLogout();
                return false; // Impede a navegação padrão para este item
            } else if (item.getItemId() == R.id.nav_sobre) {
                showAboutDialog();
                return false;
            } else if (item.getItemId() == R.id.nav_email){
                enviarEmail();
                return false;
            }


            // Navegação normal para os outros itens
            return NavigationUI.onNavDestinationSelected(item, navController);
        });
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void enviarEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        // Usar mailto: garante que apenas apps de e-mail respondam
        emailIntent.setData(Uri.parse("mailto:"));
        // Destinatário do e-mail
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{CONTACT_EMAIL_ADDRESS});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contato sobre o App Gestão de Projetos");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Olá,\n\nTenho uma dúvida/sugestão sobre o aplicativo...");
        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar e-mail usando..."));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "Nenhum aplicativo de e-mail encontrado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sobre o Aplicativo");

         builder.setIcon(R.drawable.logoif);

        // Substitua pelos seus dados reais
        String message =
                "Desenvolvido por João Alfredo Bolaséll\n" +
                "Disciplina de Programação para Web 3\n\n" +
                "Este aplicativo tem como objetivo gerenciar projetos, " +
                "permitindo cadastrar, visualizar, editar e excluir informações de Projetos."+
                "A ideia parte-se do TCC que estou desenvolvendo.";

        builder.setMessage(message);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Verifica se usuário está logado
        if (auth.getCurrentUser() == null) {
            redirecionarParaLogin();
        }
    }

    private void mostrarDialogoConfirmacaoLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmar Saída")
                .setMessage("Deseja realmente sair da aplicação?")
                .setPositiveButton("Sim", (dialog, which) -> fazerLogout())
                .setNegativeButton("Não", null)
                .show();
    }

    private void fazerLogout() {
        try {
            auth.signOut();
            redirecionarParaLogin();
        } catch (Exception e) {
            // Log de erro pode ser adicionado aqui
            redirecionarParaLogin(); // Garante que o usuário saia mesmo com erro
        }
    }

    private void redirecionarParaLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish(); // Remove a MainActivity da pilha de atividades
    }

    // Método para limpar listeners do Firebase se necessário
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Adicionar aqui a limpeza de listeners se estiver usando Realtime Database
    }


}