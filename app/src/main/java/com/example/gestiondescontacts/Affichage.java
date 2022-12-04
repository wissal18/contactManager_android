package com.example.gestiondescontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Affichage extends AppCompatActivity {

    public static ArrayList<Contact> data=new ArrayList<Contact>();
    ListView lv;
    private EditText pln_recherche;
    MyListViewAdapter l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage);
        pln_recherche=findViewById(R.id.id_recherche);

        lv=findViewById(R.id.lv_rech);
        String rech=pln_recherche.getText().toString();
        ContactManager manager=new ContactManager(Affichage.this);
        manager.ouvrir();
        //ArrayList<Contact> d =Accueil.data ;
        MyListViewAdapter ad = new MyListViewAdapter(Affichage.this, manager.selectionnertout());
        lv.setAdapter(ad);
       pln_recherche.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               ArrayList<Contact> d = manager.selectionnertout();
               MyListViewAdapter ad = new MyListViewAdapter(Affichage.this, d);
               // ad = new ArrayAdapter(Afficher_tout.this, android.R.layout.simple_list_item_1, d);
               l1 = new MyListViewAdapter(Affichage.this, d);
               //ad = new RecyclerAdapter(d,Afficher_tout.this);
               //lv.setAdapter(ad);
               data.clear();
               for (int c = 0; c < d.size(); c++) {
                   if (d.get(c).prenom.startsWith(charSequence.toString()) ||
                           d.get(c).nom.startsWith(charSequence.toString()) ||
                           d.get(c).num.startsWith(charSequence.toString())) {
                       data.add(d.get(c));
                   }
               }

               // ad = new ArrayAdapter(Afficher_tout.this, android.R.layout.simple_list_item_1, search_data);
               //lv.setAdapter(ad);

               //  l1 = new Mylistview(search_data, Afficher_tout.this);
               // lv.setAdapter(l1);
               ad = new MyListViewAdapter(Affichage.this, data);

               lv.setAdapter(ad);

           }

           @Override
           public void afterTextChanged(Editable editable) {

           }


       });

    }
}

