package uni.fmi.bachelors.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView helloTV;
    EditText nameET;
    Button helloB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTV = findViewById(R.id.helloTextView);
        nameET = findViewById(R.id.nameEditText);
        helloB = findViewById(R.id.helloButton);

        helloB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                helloTV.setText("Hello " + name);
                nameET.setText("");
            }
        });
    }
}