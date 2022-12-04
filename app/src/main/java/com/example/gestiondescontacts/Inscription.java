package com.example.gestiondescontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {

    private Button btn_inscription;
    private EditText nomUtilisateur,email,mdp,mdpConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        nomUtilisateur=findViewById(R.id.id_username_insc);
        email=findViewById(R.id.id_email_inscription);
        mdp=findViewById(R.id.id_mdp_inscription);
        mdpConf=findViewById(R.id.id_mdpConf_inscription);
        btn_inscription=findViewById(R.id.id_btn_inscr);
         btn_inscription.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String userName=nomUtilisateur.getText().toString();
                 String userMail=email.getText().toString();
                 String userPwd=mdp.getText().toString();
                 String userPwdC=mdpConf.getText().toString();
                 if(userPwd.equals(userPwdC)){
                     ContactManager manager=new ContactManager(Inscription.this);
                     manager.ouvrir();
                     if(manager.utilisateurNew(userName,userMail)){
                         Toast.makeText(getApplicationContext(), "Nom d'utilisateur ou email utilisé", Toast.LENGTH_SHORT).show();
                     }
                     else{
                         try {
                             manager.insererUtilisateur(userName,userMail,userPwd);

                             Intent i2=new Intent(Inscription.this,Affichage.class);

                             startActivity(i2);
                         }catch (Exception e){
                             System.out.println(e);
                         }
                     }
                 }
                 else
                 {
                     Toast.makeText(getApplicationContext(), "Verifier le champs de confirmation du mot de passe", Toast.LENGTH_SHORT).show();
                 }

             }
         });
    }
}