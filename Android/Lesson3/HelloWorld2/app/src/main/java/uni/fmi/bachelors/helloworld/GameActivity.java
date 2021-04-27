package uni.fmi.bachelors.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView hiddenNumberTV;
    TextView hintTV;
    TextView numbersTV;
    EditText numberET;
    Button guessB;
    Button resetB;


    Random random = new Random();

    int number;
    int tries = 5;

    private View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(numberET.getText().toString().isEmpty())
                return;

            int enteredNumber = Integer.parseInt(numberET.getText().toString());


            if(enteredNumber < number){
                hintTV.setText("▲");
            }else if(enteredNumber > number){
                hintTV.setText("▼");
            }else{
                hintTV.setText("You Won!");
                guessB.setEnabled(false);
                return;
            }

            --tries;

            if(tries == 0){
                hintTV.setText("HA HA HA LOSER!!!");
                guessB.setEnabled(false);
            }

            numbersTV.append(enteredNumber + ",");//setText(numbersTV.getText().toString() + enteredNumber);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        hiddenNumberTV = findViewById(R.id.hiddenNumberTextView);
        hintTV = findViewById(R.id.hintTextView);
        numbersTV = findViewById(R.id.numbersTextView);
        numberET = findViewById(R.id.numberEditText);
        guessB = findViewById(R.id.guessButton);
        resetB = findViewById(R.id.resetButton);

        guessB.setOnClickListener(onButtonClick);

        resetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == resetB.getId())
                {
                    restartGame();
                }
            }
        });

        restartGame();
    }

    private void restartGame(){
        number = random.nextInt(101);

        numbersTV.setText("");
        guessB.setEnabled(true);
        numberET.setText("");
        hintTV.setText("Guess number b/o 0 and 100");
        tries = 5;

        Log.wtf("Testing", number + "");
        //WTF - What a Terrible Failure
    }
}