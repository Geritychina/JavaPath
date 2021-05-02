package uni.fmi.bachalors.speaktome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button speakB;
    Button listenB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speakB = findViewById(R.id.speakButton);
        listenB = findViewById(R.id.listenButton);

        speakB.setOnClickListener(onClickListener);
        listenB.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = null;

            switch (v.getId()){
                case R.id.speakButton:
                    intent = new Intent(MainActivity.this, SpeakActivity.class);
                    break;

                case R.id.listenButton:
                    intent = new Intent(MainActivity.this, ListenActivity.class);
                    break;
            }

            startActivity(intent);
        }
    };
}