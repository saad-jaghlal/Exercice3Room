package com.example.exercic3room;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    EditText txtNum;
    Button btnValider;
    Intent intent;
    AccessDb db;
    VenteDao venteDao;
    Vente vente;
    private ExecutorService databaseWriteExecutor;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNum = findViewById(R.id.txtNum);
        btnValider =  findViewById(R.id.valider);
        intent = new Intent(this, EnregistrerActivty.class);
//      todo  */*/*/*/*/ Q3 /*/*/*/*/
        db = AccessDb.getInstance(this);
        venteDao = db.venteDao();
        databaseWriteExecutor = Executors.newSingleThreadExecutor();
        btnValider.setOnClickListener(v -> {
            if (!txtNum.getText().toString().isEmpty()) {
                int num = Integer.parseInt(txtNum.getText().toString());
                databaseWriteExecutor.execute(() -> {
                    vente = venteDao.Rechercher(num);

                runOnUiThread(() -> {
                        if (vente != null && vente.getNumV() == num) {
                            Toast.makeText(MainActivity.this, "Numéro de vente existe déjà dans la base de données.", Toast.LENGTH_SHORT).show();
                            txtNum.setText("");
                        } else {
                            Log.d("Mytag", "num: " + num + " id: " + (vente != null ? vente.getNumV() : "null") + " " + (vente != null ? vente.getQteVendue() : "null") + " " + (vente != null ? vente.getTypeCarburant() : "null"));
                            intent.putExtra("Num", txtNum.getText().toString());
                            startActivity(intent);
                        }
                    });
                });
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}