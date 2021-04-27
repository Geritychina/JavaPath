package uni.fmi.bachelors.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CalculatorActivity extends AppCompatActivity {

    TextView resultTV;
    EditText firstNumberET;
    EditText secondNumberET;
    Button plusB;
    Button minusB;
    Button multiplyB;
    Button divideB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultTV = findViewById(R.id.resultTextView);
        firstNumberET = findViewById(R.id.firstNumberEditText);
        secondNumberET = findViewById(R.id.secondNumberEditText);

        plusB = findViewById(R.id.plusButton);
        minusB = findViewById(R.id.minusButton);
        multiplyB = findViewById(R.id.multiplyButton);
        divideB = findViewById(R.id.divideButton);

        plusB.setOnClickListener(onButtonClick);
        minusB.setOnClickListener(onButtonClick);
        multiplyB.setOnClickListener(onButtonClick);
        divideB.setOnClickListener(onButtonClick);

    }


    View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(firstNumberET.getText().toString().isEmpty() || secondNumberET.getText().toString().isEmpty())
                return;

            double a = Double.parseDouble(firstNumberET.getText().toString());
            double b = Double.parseDouble(secondNumberET.getText().toString());
            double result = 0;

            switch (v.getId()){
                case R.id.plusButton:
                    result = a + b;
                    break;

                case R.id.minusButton:
                    result = a - b;
                    break;

                case R.id.divideButton:
                    if(b == 0){
                        resultTV.setText("Cannot divide by zero!!!");
                        return;
                    }
                    else
                        result = a / b;
                    break;

                case R.id.multiplyButton:
                    result = a * b;
                    break;
            }

            resultTV.setText("Result:" + result);
        }
    };

}