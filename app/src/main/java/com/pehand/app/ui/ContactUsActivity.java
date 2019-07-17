package com.pehand.app.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.pehand.app.R;
import com.pehand.app.backend.services.PostDataRepository;
import com.pehand.app.backend.services.PostDataRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class ContactUsActivity extends AppCompatActivity {

    private EditText nameET;
    private EditText mailET;
    private EditText phoneET;
    private EditText messageET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        nameET = findViewById(R.id.username);
        mailET = findViewById(R.id.email);
        phoneET = findViewById(R.id.phone);
        messageET = findViewById(R.id.message);
        Button send = findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String mail = mailET.getText().toString();
                String phone = phoneET.getText().toString();
                String message = messageET.getText().toString();
                if (validateData(name, mail, phone, message)) {
                    Map<String, String> data = new HashMap<>();
                    data.put("Name", name);
                    data.put("Email", mail);
                    data.put("Mobile", phone);
                    data.put("Msg", message);
                    PostDataRepository repository = new PostDataRepositoryImpl();
                    repository.contactUs(data, new PostDataRepository.PostRequestCallback() {
                        @Override
                        public void onSuccess() {
                            Snackbar.make(findViewById(android.R.id.content),
                                            getString(R.string.msg_sent_successfully),
                                            Snackbar.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(String msg) {
                            Snackbar.make(findViewById(android.R.id.content),
                                    getString(R.string.send_error),
                                    Snackbar.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }

    private boolean validateData(String name, String mail, String phone, String message) {
        boolean validate = true;
        if (name == null || name.isEmpty()) {
            nameET.setError(getString(R.string.required_field));
            validate = false;
        }
        if (mail == null || mail.isEmpty()) {
            mailET.setError(getString(R.string.required_field));
            validate = false;
        } if (phone == null || phone.isEmpty()) {
            phoneET.setError(getString(R.string.required_field));
            validate = false;
        }
        if (message == null || message.isEmpty()) {
            messageET.setError(getString(R.string.required_field));
            validate = false;
        }
        return validate;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
