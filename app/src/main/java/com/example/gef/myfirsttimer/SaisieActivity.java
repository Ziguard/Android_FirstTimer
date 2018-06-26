package com.example.gef.myfirsttimer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SaisieActivity extends AppCompatActivity {

    static final String DECOMPTE = "DECOMPTE";

    private EditText editTextDecompte = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie);

        editTextDecompte = findViewById(R.id.saisie_decompte);
    }

    public void onClickBoutonValider(View view){
        try{
            int decompte =  Integer.parseInt(editTextDecompte.getText().toString());

            Intent intent = new Intent();
            intent.putExtra(DECOMPTE,decompte );
            setResult(Activity.RESULT_OK, intent);
            finish();

        } catch (Exception e) {
            Toast.makeText(this,R.string.saisie_message_erreur,Toast.LENGTH_LONG).show();
        }
    }
}
