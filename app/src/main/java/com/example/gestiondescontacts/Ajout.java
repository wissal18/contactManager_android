package com.example.gestiondescontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ajout extends AppCompatActivity {
    private Button btn_ajout,btn_aff;
    private EditText nom,prenom,num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        btn_ajout=findViewById(R.id.btn_ajout2);
        btn_aff=findViewById(R.id.id_aff2);
       nom=findViewById(R.id.id_nom);
        prenom=findViewById(R.id.id_prenom);
        num=findViewById(R.id.id_num);

        btn_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nom_contact=nom.getText().toString();
               String prenom_contact=prenom.getText().toString();
               String num_contact=num.getText().toString();
               ContactManager manager=new ContactManager(Ajout.this);
               manager.ouvrir();
               manager.insererContact(nom_contact,prenom_contact,num_contact);

                Intent i=new Intent(Ajout.this,Affichage.class);

                startActivity(i); }
        });
        btn_aff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Ajout.this,Affichage.class);

                startActivity(i);
            }


        });

    }
}