package com.example.gef.myfirsttimer;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int ID_RETOUR_SAISIE = 123;
    private int decompte;

    private TextView mTextField = null;
    private Button buttonEnvoyer = null;

    // Timer :
    private CountDownTimer countDownTimer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextField = findViewById(R.id.main_saisie);
        buttonEnvoyer = findViewById(R.id.bouton_envoyer);
    }

    public void clickBouton(View view) {

        if(view == mTextField)
        {
            Intent intent = new Intent(this , SaisieActivity.class);
            startActivityForResult(intent , ID_RETOUR_SAISIE);
        }
        else if (view == buttonEnvoyer) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.main_message_decompte, decompte));
            intent.setType("text/plain");

            if(intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ID_RETOUR_SAISIE && resultCode == Activity.RESULT_OK)
        {
            decompte = data.getIntExtra(SaisieActivity.DECOMPTE,0 );
        }

        countDownTimer = new CountDownTimer (decompte * 1000 , 100)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                String libelleDecompte = ((int) Math.floor(millisUntilFinished / 1000) + 1 ) + " s";
                mTextField.setText(libelleDecompte);
            }

            @Override
            public void onFinish(){
                mTextField.setText("done !");
            }
        };
        countDownTimer.start();

        // affichage du bouton envoyer :
        buttonEnvoyer.setVisibility(View.VISIBLE);
    }


}
