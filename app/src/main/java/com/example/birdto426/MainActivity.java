package com.example.birdto426;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText birdField, zipField, personField;
    Button submit, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birdField = findViewById(R.id.birdField);
        zipField = findViewById(R.id.zipField);
        personField = findViewById(R.id.personField);
        submit = findViewById(R.id.submit);
        search = findViewById(R.id.search);

        submit.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.searchMenu) {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        }
        else if (item.getItemId() == R.id.reportMenu) {
            Toast.makeText(this, "You are already on the Report Page", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == submit) {
            String birdName = birdField.getText().toString();
            String personName = personField.getText().toString();
            int zipCode = Integer.parseInt(zipField.getText().toString());
            Bird b = new Bird(birdName, personName, zipCode);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("birds");

            myRef.push().setValue(b);
        }
        else if (view == search) {
            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);
        }
    }
}
