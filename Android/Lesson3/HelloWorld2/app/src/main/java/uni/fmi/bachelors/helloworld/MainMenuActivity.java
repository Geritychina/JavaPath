package uni.fmi.bachelors.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    TextView greetingsTV;
    Button helloB;
    Button gameB;
    Button calculatorB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        greetingsTV = findViewById(R.id.greetingsTextView);
        helloB = findViewById(R.id.helloButton);
        gameB = findViewById(R.id.gameButton);
        calculatorB = findViewById(R.id.calculatorButton);

        helloB.setOnClickListener(onClick);
        gameB.setOnClickListener(onClick);
        calculatorB.setOnClickListener(onClick);

    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent;

            switch (v.getId()){
                case R.id.helloButton:
                    intent = new Intent(MainMenuActivity.this, MainActivity.class);
                    break;
                case R.id.gameButton:
                    intent = new Intent(MainMenuActivity.this, GameActivity.class);
                    break;
                case R.id.calculatorButton:
                    intent = new Intent(MainMenuActivity.this, CalculatorActivity.class);
                    break;

                default:
                    intent = new Intent();
            }

            startActivity(intent);
        }
    };
}