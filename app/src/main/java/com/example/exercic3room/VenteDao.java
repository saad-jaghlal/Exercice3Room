package com.example.exercic3room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface VenteDao {

// todo  ********** Q2 **********
    @Insert
    void AjouterVente(Vente... ventes);
    // todo  ********** Q1 **********
    @Query("SELECT * FROM Vente WHERE numV=:numV")
    Vente Rechercher(int numV );

}
