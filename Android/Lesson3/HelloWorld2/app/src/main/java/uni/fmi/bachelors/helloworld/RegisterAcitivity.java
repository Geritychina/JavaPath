package uni.fmi.bachelors.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegisterAcitivity extends AppCompatActivity {


    public static final String USERNAME_TAG = "username";
    public static final String PASSWORD_TAG = "password";
    public static final String GENDER_TAG = "gender";

    ImageView avatarIV;
    EditText usernameET;
    EditText passwordET;
    EditText repeatPassword;
    RadioGroup genderRG;
    Button saveB;
    Button cancelB;

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_acitivity);


        avatarIV = findViewById(R.id.avatarImageView);
        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        repeatPassword = findViewById(R.id.repeatPasswordEditText);
        genderRG = findViewById(R.id.genderRadioGroup);
        saveB = findViewById(R.id.saveButton);
        cancelB = findViewById(R.id.cancelButton);

        saveB.setOnClickListener(onClickListener);
        cancelB.setOnClickListener(onClickListener);

        pref = getApplication().getSharedPreferences("mySecretData", MODE_PRIVATE);
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

                SharedPreferences.Editor editor = pref.edit();
                editor.putString(USERNAME_TAG, username);
                editor.putString(PASSWORD_TAG, password);
                editor.putString(GENDER_TAG, gender);

                editor.commit();
            }

            Intent intent = new Intent(RegisterAcitivity.this, LoginActivity.class);
            startActivity(intent);

        }
    };
}