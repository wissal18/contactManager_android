package com.example.gestiondescontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.Transliterator;

import java.util.ArrayList;

public class ContactManager {
    Context context;
    SQLiteDatabase mabase;

    public ContactManager(Context context) {
        this.context = context;
    }

    public void ouvrir(){

        MyContactHelper helper=new MyContactHelper(context,"contactDB.db",null,1);
        mabase=helper.getWritableDatabase();
    }

    public void insererContact(String ch1, String ch2, String ch3){
        ContentValues v=new ContentValues();
        v.put(MyContactHelper.nom,ch1);
        v.put(MyContactHelper.prenom,ch2);
        v.put(MyContactHelper.numero,ch3);
        mabase.insert(MyContactHelper.table_contact,null,v);

    }
    public void insererUtilisateur(String ch1, String ch2, String ch3){
        ContentValues v=new ContentValues();
        v.put(MyContactHelper.nomUtilisateur,ch1);
        v.put(MyContactHelper.email,ch2);
        v.put(MyContactHelper.mot_de_passe,ch3);
        mabase.insert(MyContactHelper.table_user,null,v);

    }
    ArrayList<Contact> selectionnertout(){
        ArrayList<Contact> data=new ArrayList<Contact>();
        Cursor cr=mabase.query(MyContactHelper.table_contact,new String[]{MyContactHelper.id,MyContactHelper.nom,MyContactHelper.prenom,MyContactHelper.numero},null,null,null,null,null);
    cr.moveToFirst();
    while(!cr.isAfterLast()){
        int i1=cr.getInt(0);
        String i2=cr.getString(1);
        String i3=cr.getString(2);
        String i4=cr.getString(3);
        data.add(new Contact(i2,i3,i4));
        cr.moveToNext();
    }
    return data;
    }
    long modifier(Contact c,String nom,String prenom,String numero){
      int a=-1;
      a= (int) supprimer(c.num);
      insererContact(nom,prenom,numero);
//        ContentValues v= new ContentValues();
//        //v.put(MyContactHelper.id,id);
//        v.put(MyContactHelper.nom,nom);
//        v.put(MyContactHelper.prenom,prenom);
//        v.put(MyContactHelper.numero,numero);
//        a=mabase.update(MyContactHelper.table_contact,v,MyContactHelper.nom+"="+c.prenom +" and "+ MyContactHelper.prenom+"="+c.nom +" and "+ MyContactHelper.numero+"="+c.num,null);
        return a;
    }
    long supprimer(String phone){
        int a=-1;
        a=mabase.delete(MyContactHelper.table_contact,MyContactHelper.numero+"="+phone,null);
        return a;
    }

    ArrayList<Contact> rechercherContact(String ch){
        ArrayList<Contact> data=new ArrayList<Contact>();
        Cursor cr=mabase.query(MyContactHelper.table_contact,new String[]{MyContactHelper.id,MyContactHelper.nom,MyContactHelper.prenom,MyContactHelper.numero}, String.valueOf(ch== MyContactHelper.nom || ch==MyContactHelper.prenom || ch==MyContactHelper.numero),null,null,null,null);
        cr.moveToFirst();
        while(!cr.isAfterLast()){
            int i1=cr.getInt(0);
            String i2=cr.getString(1);
            String i3=cr.getString(2);
            String i4=cr.getString(3);
            data.add(new Contact(i2,i3,i4));
            cr.moveToNext();
        }
        return data;
    }
    boolean utilisateurNew(String nom, String email){

        Cursor cr=mabase.rawQuery("select * from table_user where nomUtilisateur=? or email=?",new String[]{nom,email});
     if(cr.getCount()>0){
         return false;
     }else{
         return true;
     }
    }

    boolean authentifier(String mail,String mdp){

        Cursor cr=mabase.rawQuery("select * from table_user where email=? or mot_de_passe=?",new String[]{mail,mdp});
        if(cr.getCount()>0){
            return true;
        }else{
            return false;
        }
    }


}
