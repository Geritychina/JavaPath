package uni.fmi.bachelors.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET;
    EditText passwordET;
    Button loginB;
    TextView registerTV;

    String username = "Ivancho";
    String password = "124";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        loginB = findViewById(R.id.loginButton);
        registerTV = findViewById(R.id.registerTextView);

        loginB.setOnClickListener(onClick);
        registerTV.setOnLongClickListener(onLongClick);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("mySecretData", MODE_PRIVATE);
        username = pref.getString(RegisterAcitivity.USERNAME_TAG, "ivancho");
        password = pref.getString(RegisterAcitivity.PASSWORD_TAG, "124");

    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String loginUsername = usernameET.getText().toString();
            String loginPassword = passwordET.getText().toString();

            if(loginUsername.equalsIgnoreCase(username) && loginPassword.equals(password)){
                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(LoginActivity.this, "Hacker!!! Get out get out!!", Toast.LENGTH_LONG).show();
            }

        }
    };

    View.OnLongClickListener onLongClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            Intent intent = new Intent(LoginActivity.this, RegisterAcitivity.class);
            startActivity(intent);

            return true;
        }
    };

}