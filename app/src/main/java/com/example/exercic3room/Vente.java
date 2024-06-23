package com.example.exercic3room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vente {
    @PrimaryKey(autoGenerate = true)
     int id;
     int numV;
     String typeCarburant ;
    int QteVendue ;

    public Vente(int numV, String typeCarburant, int qteVendue) {
        this.numV = numV;
        this.typeCarburant = typeCarburant;
        QteVendue = qteVendue;
    }

    public Vente() {

    }

    public int get_id() {
        return id;
    }

    public int getNumV() {
        return numV;
    }

    public String getTypeCarburant() {
        return typeCarburant;
    }

    public int getQteVendue() {
        return QteVendue;
    }
}
