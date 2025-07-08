package com.jalfredo.desafio2_v2.view;

import static android.media.CamcorderProfile.getAll;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.jalfredo.desafio2_v2.R;
import com.jalfredo.desafio2_v2.adapter.LinhaConsultaAdapter;
import com.jalfredo.desafio2_v2.dao.AppDatabase;
import com.jalfredo.desafio2_v2.dao.CafeDAO;
import com.jalfredo.desafio2_v2.entity.Cafe;

import java.util.List;

public class ListarCafeActivity extends AppCompatActivity {

    private ListView listCafes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar_cafe);

        listCafes = this.findViewById(R.id.listViewCafes);
        CafeDAO cafeDAO = AppDatabase.getInstance(getApplicationContext()).createCafeDAO();
        getAll(cafeDAO.getallCafes());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    protected void getAll(List<Cafe> cafes) {
        listCafes.setAdapter(new LinhaConsultaAdapter(this, cafes));
    }
}