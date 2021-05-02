package uni.fmi.bachalors.speaktome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class SpeakActivity extends AppCompatActivity {

    EditText textToSpeakET;
    Button speakB;
    TextToSpeech tst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        textToSpeakET = findViewById(R.id.textToSpeakEditText);
        speakB = findViewById(R.id.speakNowButton);
        tst = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    tst.setLanguage(Locale.ENGLISH);
                }
            }
        });


        speakB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSpeak = textToSpeakET.getText().toString();
                textToSpeakET.setText("");

                tst.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, "babaQga");
            }
        });
    }
}