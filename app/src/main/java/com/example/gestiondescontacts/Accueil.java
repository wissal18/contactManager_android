package com.example.gestiondescontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    //private TextView tvusername;
    private Button btn_ajout,btn_aff,btn_notifs;
    public static ArrayList<Contact> data=new ArrayList<Contact>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        //tvusername=findViewById(R.id.title_acc);
        btn_ajout=findViewById(R.id.btn_ajout);
        btn_aff=findViewById(R.id.btn_aff);

        btn_notifs=findViewById(R.id.btn_notif_activity);
        //Intent x=this.getIntent();
        //Bundle b=x.getExtras();
        //String u=b.getString("USER");
        //tvusername.setText("Accueil de Mr/Mme "+u);
        ContactManager manager=new ContactManager(Accueil.this);
        manager.ouvrir();
        Accueil.data=manager.selectionnertout();

        btn_ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Accueil.this,Ajout.class);

                startActivity(i);
            }


        });
        btn_aff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Accueil.this,Affichage.class);

                startActivity(i);
            }


        });
        btn_notifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Accueil.this,Notifications.class);

                startActivity(i);
            }
        });
    }
}