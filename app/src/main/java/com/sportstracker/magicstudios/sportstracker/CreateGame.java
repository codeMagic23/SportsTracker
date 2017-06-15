package com.sportstracker.magicstudios.sportstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static com.sportstracker.magicstudios.sportstracker.R.id.fab;

public class CreateGame extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String[] gameTypes = new String[1];
        gameTypes[0] = "Baseball";
        ArrayAdapter<String> gameTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, gameTypes);
        Spinner typeSpinner = (Spinner) findViewById(R.id.gameType);
        typeSpinner.setAdapter(gameTypeAdapter);

        Button createGameBtn = (Button) findViewById(R.id.createGame);
        createGameBtn.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        EditText gameNameET = (EditText) findViewById(R.id.gameName);
        Intent i = new Intent(v.getContext(), GameActivity.class);
        i.putExtra("gameName", gameNameET.getText().toString());
        startActivity(i);
    }

}
