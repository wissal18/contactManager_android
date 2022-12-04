package com.example.gestiondescontacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Notifications extends AppCompatActivity {
   Button btn_toast,btn_alert,btn_notify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        btn_alert=findViewById(R.id.id_Alert);
        btn_notify=findViewById(R.id.id_notif);
        btn_toast=findViewById(R.id.id_Toast);
        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Notifications.this,"I'm a delicious toast",Toast.LENGTH_SHORT).show();


            }
        });
        btn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// Creation d'une instance builder d'une alert dialog
                AlertDialog.Builder alert=new AlertDialog.Builder(Notifications.this );
                alert.setTitle("warning!!"); // titre
                alert.setMessage("you want to delete this file?"); // message
                /* Ajout d'un bouton avec action utilisant une classe anonyme */
                alert.setPositiveButton("Delete", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "bouton confirmation", Toast.LENGTH_SHORT).show();
                            }
                        });
                /* Ajout d'un deuxieme bouton avec action */
                alert.setNegativeButton("Cancel", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "bouton annulation", Toast.LENGTH_SHORT).show();
                            }
                        });
                /* Ajout d'un autre bouton sans action */
                alert.setNeutralButton("Quit",null); // bouton sans action

                // creation d'une instance de l'alert dialogue
                AlertDialog dialog=alert.create();
                // affichage du boite de dialog
                dialog.show();
                // pour fermer la boite de dialog explicitement, on peut
//utiliser dialog.dismiss();
            }
        });
        btn_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mynotif =new NotificationCompat.Builder(getApplicationContext(), "myapplication_channel");
                mynotif.setContentTitle("Notification");
                mynotif.setContentText("You get it!");
                mynotif.setSmallIcon(android.R.drawable.ic_dialog_map);
                mynotif.setAutoCancel(true);
                Uri son= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                mynotif.setSound(son);
            }
        });
    }
}