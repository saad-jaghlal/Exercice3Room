package com.example.exercic3room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Vente.class},version = 1)
public abstract class AccessDb extends RoomDatabase {
    private static volatile AccessDb INSTANCE;

    public abstract VenteDao venteDao();

    public static AccessDb getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AccessDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AccessDb.class, "venteDatabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
