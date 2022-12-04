package com.example.gestiondescontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Authentification extends AppCompatActivity {

    private EditText email,mdpA;
    private Button btn_connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authetification);

        btn_connect=findViewById(R.id.id_btn_inscr);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=email.getText().toString();
                String mdp=mdpA.getText().toString();
                ContactManager manager=new ContactManager(Authentification.this);
                manager.ouvrir();
                if(manager.authentifier(mail,mdp))
                {System.out.println("2222");
                    Intent i=new Intent(Authentification.this,Accueil.class);
                    i.putExtra("USER",mail);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Authentification.this,"valeur non validée",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}