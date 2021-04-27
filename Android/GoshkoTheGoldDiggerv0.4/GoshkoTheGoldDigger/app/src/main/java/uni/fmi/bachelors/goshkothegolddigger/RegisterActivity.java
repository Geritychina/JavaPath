package uni.fmi.bachelors.goshkothegolddigger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    ImageView avatarIV;
    EditText usernameET;
    EditText passwordET;
    EditText repeatPassword;
    RadioGroup genderRG;
    Button saveB;
    Button cancelB;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        avatarIV = findViewById(R.id.avatarImageView);
        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        repeatPassword = findViewById(R.id.repeatPasswordEditText);
        genderRG = findViewById(R.id.genderRadioGroup);
        saveB = findViewById(R.id.saveButton);
        cancelB = findViewById(R.id.cancelButton);

        saveB.setOnClickListener(onClickListener);
        cancelB.setOnClickListener(onClickListener);

        dbHelper = new DBHelper(this);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.saveButton){

                if(    usernameET.getText().length() == 0 ||
                        passwordET.getText().length() == 0 ||
                        !passwordET.getText().toString().equals(repeatPassword.getText().toString())){
                    return;
                }

                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                int checkedRB = genderRG.getCheckedRadioButtonId();

                RadioButton rb = findViewById(checkedRB);

                String gender = rb.getText().toString();

                User user = new User(username, password, gender);

                if(!dbHelper.registerUser(user))
                {
                    Toast.makeText(RegisterActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    };
}