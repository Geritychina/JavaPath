package uni.fmi.bachelors.goshkothegolddigger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    ImageView logoIV;
    EditText usernameET;
    EditText passwordET;
    Button loginB;
    TextView registerTV;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logoIV = findViewById(R.id.logoImageView);
        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);
        loginB = findViewById(R.id.loginButton);
        registerTV = findViewById(R.id.registerTextView);

        loginB.setOnClickListener(onClickListener);
        registerTV.setOnClickListener(onClickListener);

        dbHelper = new DBHelper(this);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;

            if(v.getId() == R.id.registerTextView){
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                return;
            }

            if(usernameET.getText().length() > 0 && passwordET.getText().length() > 0){
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();

                User user = dbHelper.getUser(username, password);

               // if(dbHelper.isLoginSuccessful(username, password)){
                if(user != null){
                    intent = new Intent(LoginActivity.this, MainActivity.class);

                    intent.putExtra("user", user);

                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Wrong credentials!", Toast.LENGTH_LONG).show();
                }
            }
        }
    };

}