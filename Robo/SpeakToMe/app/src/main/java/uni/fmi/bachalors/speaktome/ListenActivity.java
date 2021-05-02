package uni.fmi.bachalors.speaktome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class ListenActivity extends AppCompatActivity {

    TextView spokenTV;
    Button listenB;

    private final int LISTEN_RESULT_CODE = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        spokenTV = findViewById(R.id.spokenTextView);
        listenB = findViewById(R.id.listenNowButton);

        listenB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak NOW!");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                try{
                    startActivityForResult(intent, LISTEN_RESULT_CODE);
                }catch (ActivityNotFoundException e){
                    Toast.makeText(getApplicationContext(),
                            "You`r device doesn't support that function! :(",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == LISTEN_RESULT_CODE){
            if(resultCode == RESULT_OK){
                ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                spokenTV.setText(result.get(0).toString());
            }
        }
    }
}