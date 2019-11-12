package com.example.birdto426;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    EditText zipSearch;
    TextView birdResult, personResult;
    Button searchPress, report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        zipSearch = findViewById(R.id.zipSearch);
        birdResult = findViewById(R.id.birdResult);
        personResult = findViewById(R.id.personResult);
        searchPress = findViewById(R.id.searchPress);
        report = findViewById(R.id.report);

        searchPress.setOnClickListener(this);
        report.setOnClickListener(this);
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
            Toast.makeText(this, "You are already on the Report Page", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.reportMenu) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view == searchPress) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("birds");
            String zip = zipSearch.getText().toString();
            int zipCode = Integer.parseInt(zip);

            myRef.orderByChild("zipCode").equalTo(zipCode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Bird b = dataSnapshot.getValue(Bird.class);
                    String birdName = b.birdName;
                    String personName = b.personName;
                    personResult.setText(personName);
                    birdResult.setText(birdName);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



           /* myRef.orderByChild("zipCode").equalTo(zipCode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Toast.makeText(SearchActivity.this, "Found bird", Toast.LENGTH_SHORT).show();
                    //String key = dataSnapshot.getKey();
                    Bird b = dataSnapshot.getValue(Bird.class);
                    String birdName = b.birdName;
                    String personName = b.personName;
                    personResult.setText(personName);
                    birdResult.setText(birdName);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/
        }
        else if (view == report) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        }
    }
}
