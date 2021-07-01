package com.aakash_score;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

//This is a simple score keeping app where a user has to keep track of score.
// The App can keep points system of 1 point, 2 points, 3 points
// There is a reset button to clear all scores.
public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    SwitchCompat teamSelectSwitch;
    TextView teamATextView, teamBTextView, pointSelectorText;
    int teamAScore, teamBScore;
    Button addButton, subtractButton, resetButton;
    RadioGroup numPointsGroup;
    private int numPoints;

    //This is the onCreate method when the App starts this method is called first and is one time only
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing values of numberof points, team score here
        numPoints = 1;
        teamAScore = 0;
        teamBScore = 0;

        //calling a init function where all ids are defined
        initIds();

        //setting up on click listners
        teamSelectSwitch.setOnClickListener(this);
        addButton.setOnClickListener(this);
        subtractButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        //Radio group on click listner
        try {
            numPointsGroup.setOnCheckedChangeListener(this);
        } catch (Exception e) {
            Log.e("RadioGroupError", Log.getStackTraceString(e));
        }

    }

    // init function with all ids
    public void initIds() {
        teamSelectSwitch = findViewById(R.id.teamSelectSwitch);

        addButton = findViewById(R.id.buttonAdd);
        subtractButton = findViewById(R.id.buttonSubtract);

        teamATextView = findViewById(R.id.teamOneTV);
        teamBTextView = findViewById(R.id.teamTwoTV);

        numPointsGroup = findViewById(R.id.radioGroup);

        pointSelectorText = findViewById(R.id.pointSelectioinTV);

        resetButton = findViewById(R.id.resetButton);
    }

    //onclicking the buttons function defined here
    //Based on button id we can do actions
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.teamSelectSwitch:
                if (teamSelectSwitch.isChecked()) {
                    teamBTextView.setBackgroundResource(R.color.purple_500);
                    teamATextView.setBackgroundColor(Color.WHITE);
                } else {
                    teamATextView.setBackgroundResource(R.color.purple_500);
                    teamBTextView.setBackgroundColor(Color.WHITE);

                }
                break;
            case R.id.buttonAdd:
                //we will check if switch is on team A or Team B
                //Based on that we will increase score as this button is add button
                if (!teamSelectSwitch.isChecked()) {
                    if (teamAScore >= 0) {
                        teamAScore = numPoints + teamAScore;
                    } else {
                        teamAScore = 0;
                    }
                } else {
                    if (teamBScore >= 0) {
                        teamBScore = numPoints + teamBScore;
                    } else {
                        teamBScore = 0;
                    }
                }
                break;
            //we will decrease score as this button is subtract button
            case R.id.buttonSubtract:
                if (!teamSelectSwitch.isChecked()) {
                    if (teamAScore <= 0) {

                        teamAScore = 0;
                    } else {
                        teamAScore = teamAScore - numPoints;
                    }
                } else {
                    if (teamBScore <= 0) {

                        teamBScore = 0;
                    } else {
                        teamBScore = teamBScore - numPoints;
                    }
                }
                break;

            //reset button onclick will call the reset function
            case R.id.resetButton:
                resetScreen();
                break;
        }

        //setting the textview based on the team score
        teamATextView.setText(String.valueOf(teamAScore));
        teamBTextView.setText(String.valueOf(teamBScore));

    }

    // defining the on check change of radio buttons
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //we will check the radio button id and then increase the score type to 1,2,3
        switch (checkedId) {
            case R.id.onePointRadio:
                numPoints = 1;
                pointSelectorText.setText(String.valueOf(numPoints));
                break;
            case R.id.twoPointRadio:
                numPoints = 2;
                pointSelectorText.setText(String.valueOf(numPoints));
                break;
            case R.id.threePointRadio:
                numPoints = 3;
                pointSelectorText.setText(String.valueOf(numPoints));
                break;
        }
    }

    //reset screen score to 0 function
    public void resetScreen() {
        numPoints = 1;
        teamAScore = 0;
        teamBScore = 0;
        numPointsGroup.clearCheck();
        numPointsGroup.check(R.id.onePointRadio);
    }
}