package edu.sharif.courseworkapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.sharif.courseworkapp.R;
import edu.sharif.courseworkapp.controller.LoginHandler;
import edu.sharif.courseworkapp.model.user.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String username = "";
        String password = "";

        Intent intent = getIntent();
        if (intent.hasExtra("username")) {
            username = intent.getStringExtra("username");
        }
        if (intent.hasExtra("password")) {
            password = intent.getStringExtra("password");
        }

        TextView usernameTextView = findViewById(R.id.username);
        TextView passwordTextView = findViewById(R.id.password);

        Button loginButton = findViewById(R.id.login_button);
        Button registerButton = findViewById(R.id.register_button);

        usernameTextView.setText(username);
        passwordTextView.setText(password);

        loginButton.setOnClickListener(view -> {
            LoginHandler loginHandler = new LoginHandler(
                    usernameTextView.getText().toString(),
                    passwordTextView.getText().toString()
            );
            User user = loginHandler.login();
            if (user == null) {
                String error_message = "Username doesn't exist or password is wrong";
                Toast.makeText(LoginActivity.this, error_message, Toast.LENGTH_SHORT).show();
            }
            String success_message = "User logged in";
            Toast.makeText(LoginActivity.this, success_message, Toast.LENGTH_SHORT).show();
            login(user);
        });

        registerButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            myIntent.putExtra("username", usernameTextView.getText().toString());
            myIntent.putExtra("password", passwordTextView.getText().toString());
            LoginActivity.this.startActivity(myIntent);
        });
    }

    private void login(User user) {
        //pass
    }
}