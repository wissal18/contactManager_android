package com.example.gestiondescontacts;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;

public class MyListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Contact> data;

    public MyListViewAdapter(Context context, ArrayList<Contact> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ContactManager manager=new ContactManager(context);
        manager.ouvrir();
        //creation d'un view:
        LayoutInflater lf=LayoutInflater.from(context);
        View v=lf.inflate(R.layout.view_contact,null);
        //recuperer les holders:

        TextView tv_nom=v.findViewById(R.id.tv_nom);
        TextView tv_prenom=v.findViewById(R.id.tv_prenom);
        TextView tv_num=v.findViewById(R.id.tv_num);
        ImageView id_call=v.findViewById(R.id.id_call);
        ImageView id_edit=v.findViewById(R.id.id_edit);
        ImageView id_delete=v.findViewById(R.id.id_delete);

        Contact c=data.get(i);

        tv_nom.setText(c.nom);
        tv_prenom.setText(c.prenom);
        tv_num.setText(c.num);

        id_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+c.num));
                context.startActivity(i);
            }
        });
        id_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.remove(i);
                manager.supprimer(c.num);
                notifyDataSetChanged();
            }
        });
        id_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View viewEdit = LayoutInflater.from(context).inflate(R.layout.edit_dialog, null);
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Your Title Here");

                alertDialog.setCancelable(false);
                alertDialog.setMessage("Your Message Here");
                final EditText etprenom = (EditText) viewEdit.findViewById(R.id.etprenom);
                etprenom.setText(c.prenom);
                final EditText etnom = (EditText) viewEdit.findViewById(R.id.etnom);
                etnom.setText(c.nom);
                final EditText etnum = (EditText) viewEdit.findViewById(R.id.etnum);
                etnum.setText(c.num);

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Enregistrer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newNom=etnom.getText().toString();
                        String newPrenom=etprenom.getText().toString();
                        String newNum=etnum.getText().toString();
                        ContactManager manager=new ContactManager(context);
                        manager.ouvrir();
                        manager.modifier(c,newNom,newPrenom,newNum);
                        Intent intent=new Intent(context,Affichage.class);
                        context.startActivity(intent);
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialog.setView(viewEdit);
                alertDialog.show();
            }
        });
        return v;
    }
}
