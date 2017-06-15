package com.sportstracker.magicstudios.sportstracker;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class GameActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private final String INNING_TOP = "top";
    private final String INNING_BOTTOM ="bot";

    private int homeScore;
    private int visitorScore;
    private int inning = 1;

    //Views
    TextView topBotTV;

    // RadioButton Arrays;
    RadioButton[] walksRadioGroup = new RadioButton[3];
    RadioButton[] strikesRadioGroup = new RadioButton[2];
    RadioButton[] outsRadioGroup = new RadioButton[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getIntent().getExtras() != null && !getIntent().getStringExtra("gameName").equals("")) {
            toolbar.setTitle(getIntent().getStringExtra("gameName"));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpCountGroups();

     //   RadioGroup outRG = (RadioGroup) findViewById(R.id.outsRG);
     //   outRG.setOnCheckedChangeListener(this);
        Button clearCountBtn = (Button) findViewById(R.id.clearCountBtn);
        clearCountBtn.setOnClickListener(this);

        topBotTV = (TextView) findViewById(R.id.topBot);
        topBotTV.setText(INNING_TOP);
        TextView inningTV = (TextView) findViewById(R.id.inningTV);
        inningTV.setText(String.valueOf(inning));

        // score buttons
        Button advanceHomeScoreBtn = (Button) findViewById(R.id.advanceHomeScore);
        Button decreaseHomeScoreBtn = (Button) findViewById(R.id.decreaseHomeScore);
        Button advanceVisitorScoreBtn = (Button) findViewById(R.id.advanceVisitorScore);
        Button decreaseVisitorBtn = (Button) findViewById(R.id.decreaseVisitorScore);
        advanceHomeScoreBtn.setOnClickListener(this);
        decreaseHomeScoreBtn.setOnClickListener(this);
        advanceVisitorScoreBtn.setOnClickListener(this);
        decreaseVisitorBtn.setOnClickListener(this);
        Button upInning = (Button) findViewById(R.id.advanceInning);
        Button downInning = (Button) findViewById(R.id.decreaseInning);
        upInning.setOnClickListener(this);
        downInning.setOnClickListener(this);

        Button walkBtn = (Button) findViewById(R.id.walkBtn);
        walkBtn.setOnClickListener(this);
        Button hitBtn = (Button) findViewById(R.id.hitBtn);
        hitBtn.setOnClickListener(this);
    }

    private void setUpCountGroups() {
        walksRadioGroup[0] = (RadioButton) findViewById(R.id.ball1);
        walksRadioGroup[1] = (RadioButton) findViewById(R.id.ball2);
        walksRadioGroup[2] = (RadioButton) findViewById(R.id.ball3);

        strikesRadioGroup[0] = (RadioButton) findViewById(R.id.strike1);
        strikesRadioGroup[1] = (RadioButton) findViewById(R.id.strike2);

        outsRadioGroup[0] = (RadioButton) findViewById(R.id.out1);
        outsRadioGroup[1] = (RadioButton) findViewById(R.id.out2);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
    /*
        if (group.getId() == R.id.outsRG) {
            if (checkedId != -1) {
                RadioGroup ballsRG = (RadioGroup) findViewById(R.id.ballsRG);
                ballsRG.check(-1);
                RadioGroup strikesRG = (RadioGroup) findViewById(R.id.strikesRG);
                strikesRG.check(-1);
            }
        }
       */
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        TextView homeScoreTV = (TextView) findViewById(R.id.homeScoreTV);
        TextView visitorScoreTV = (TextView) findViewById(R.id.visitorScoreTV);

        switch (id) {
            case R.id.clearCountBtn:
                clearCount();
                break;
            case R.id.advanceHomeScore:
                homeScore += 1;
                homeScoreTV.setText(String.valueOf(homeScore));
                break;
            case R.id.decreaseHomeScore:
                //don't allow negative scores
                if (homeScore != 0) {
                    homeScore -= 1;
                    homeScoreTV.setText(String.valueOf(homeScore));
                }
                break;
            case R.id.advanceVisitorScore:
                visitorScore += 1;
                visitorScoreTV.setText(String.valueOf(visitorScore));
                break;
            case R.id.decreaseVisitorScore:
                //don't allow negative scores
                if (visitorScore != 0) {
                    visitorScore -= 1;
                    visitorScoreTV.setText(String.valueOf(visitorScore));
                }
                break;
            case R.id.advanceInning:
            case R.id.decreaseInning:
                setInning(id);
                break;
            case R.id.walkBtn:
            case R.id.hitBtn:
                clearCount();
                break;

        }
    }

    // check for top or bottom of the inning and set accordingly
    // (@param) id of Button clicked to know if the inning was incremented or decremented
    private void setInning(int viewID) {
        TextView inningTV = (TextView) findViewById(R.id.inningTV);

        /*if bottom of inning and incrementing, we need to increment inning and change to top
         * if bottom of inning and decrementing, we need to just change to top
        * if top of the inning and incrementing, just change the topBot variable to bottom
        * if top of the inning and decrementing, decrement inning and change to bottom
       */
        switch (viewID) {
            case R.id.advanceInning:
                if (topBotTV.getText().toString().equals(INNING_TOP)) {
                    topBotTV.setText(INNING_BOTTOM);
                } else {
                    inning++;
                    topBotTV.setText(INNING_TOP);
                    inningTV.setText(String.valueOf(inning));
                }
                break;
            case R.id.decreaseInning:
                if (topBotTV.getText().toString().equals(INNING_TOP)) {
                    // don't allow negative inning values
                    if (inning != 0) {
                        inning--;
                    }
                    topBotTV.setText(INNING_BOTTOM);
                    inningTV.setText(String.valueOf(inning));
                } else {
                    topBotTV.setText(INNING_TOP);
                }
                break;
        }
    }

    private void clearCount() {
        for (RadioButton button : walksRadioGroup) {
            button.setChecked(false);
        }
        for (RadioButton button : strikesRadioGroup) {
            button.setChecked(false);
        }
    }

    private void clearOuts() {
        for (RadioButton button : outsRadioGroup) {
            button.setChecked(false);
        }
        clearCount();
    }
}
