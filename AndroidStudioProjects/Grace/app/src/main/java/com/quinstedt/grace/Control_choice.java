package com.quinstedt.grace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Control_choice extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_choice);

        ConstraintLayout layout = findViewById(R.id.control_choice);
        AnimationDrawable animationBackground = (AnimationDrawable) layout.getBackground();
        animationBackground.setEnterFadeDuration(2500);
        animationBackground.setExitFadeDuration(5000);
        animationBackground.start();

        Button buttonJoystick = findViewById(R.id.button_joystick);
        buttonJoystick.setOnClickListener(view -> openJoystick());

        Button controlButton = findViewById(R.id.button_control);
        controlButton.setOnClickListener(view -> openButtonControl());
    }

    public void openButtonControl() {
        Intent buttonControlIntent = new Intent(this, ControlPad.class);
        startActivity(buttonControlIntent);
    }

    public void openJoystick() {
        Intent joystickIntent = new Intent(this, Analog_joystick.class);
        startActivity(joystickIntent);
    }
}