package com.example.gestiondescontacts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyContactHelper extends SQLiteOpenHelper {
    public static final String table_contact= "Contact";
    public static  final String id="ID";
    public static final String nom="Nom";
    public static final String prenom="Prenom";
    public static final String numero="Numero";
    public static final String table_user= "User";
    public static  final String id2="ID";
    public static final String nomUtilisateur="Nom";
    public static final String email="Email";
    public static final String mot_de_passe="Numero";

    String req="create table "+table_contact+"("
            +id+" Integer primary Key autoincrement,"
            +nom+" Text not null,"
            +prenom+" Text not null,"
            +numero+" Text not null"
            +")";
    String req2="create table "+table_user+"("
            +id2+" Integer primary Key autoincrement,"
            +nomUtilisateur+" Text not null,"
            +email+" Text not null,"
            +mot_de_passe+" Text not null"
            +")";
    public MyContactHelper(@Nullable Context context, @Nullable String name,
                           @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(req);db.execSQL(req2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" drop table "+table_contact);
        db.execSQL(" drop table "+table_user);
        onCreate(db);
    }
}
