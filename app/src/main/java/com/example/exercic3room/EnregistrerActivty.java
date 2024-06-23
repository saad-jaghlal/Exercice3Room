package com.example.exercic3room;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EnregistrerActivty extends AppCompatActivity {
    TextView numvente;
    EditText typeCarburant,txtQte;
    Button btnAjouter ;
    Vente vente;
    ExecutorService databaseExecutor;
    AccessDb db;
    VenteDao venteDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer);
        numvente = findViewById(R.id.txtNum);
//       todo ***** Q4 ****
        Intent intent = getIntent();
        int num = Integer.parseInt(intent.getStringExtra("Num")) ;
        numvente.setText(intent.getStringExtra("Num"));
        typeCarburant = findViewById(R.id.txtTypecarburant);
        txtQte = findViewById(R.id.txtQte);
        btnAjouter = findViewById(R.id.Ajouter);
//      todo *****  Q5 ******
        databaseExecutor =  Executors.newSingleThreadExecutor();
        db = AccessDb.getInstance(this);
        venteDao = db.venteDao();
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String type = typeCarburant.getText().toString();
                String quantite = txtQte.getText().toString();
                if (!type.isEmpty() && !quantite.isEmpty()) {
                    vente = new Vente(num, type, Integer.parseInt(quantite));
                    new AlertDialog.Builder(EnregistrerActivty.this)
                            .setTitle("Confirmation")
                            .setMessage("Êtes-vous sûr de vouloir ajouter cette vente ?")
                            .setPositiveButton("oui", (dialog, which) -> {
                                databaseExecutor.execute(() -> {
                                    venteDao.AjouterVente(vente);
                                    runOnUiThread(() -> {
                                        Toast.makeText(EnregistrerActivty.this, "Ajouté avec succès", Toast.LENGTH_SHORT).show();
                                        // Optionally, finish this activity to return to the previous one
                                        // finish();
                                    });
                                });
                            })
                            .setNegativeButton("non", (dialog, which) -> {
                                // Do nothing if the user cancels
                                dialog.cancel();
                            })
                            .show();

                }
            }
        });

    }
}