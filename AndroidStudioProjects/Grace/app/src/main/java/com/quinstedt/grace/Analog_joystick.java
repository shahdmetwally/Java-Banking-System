package com.quinstedt.grace;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joystickjhr.JoystickJhr;

public class Analog_joystick extends MovementConnection {

  /*  int lastDirection = 0;
    private static final int FORWARD = 1;
    private static final int UP_RIGHT= 2;
    private static final int RIGHT = 3;
    private static final int DOWN_RIGHT = 4;
    private static final int REVERSE = 5;
    private static final int DOWN_LEFT = 6;
    private static final int LEFT = 7;
    private static final int UP_LEFT = 8;
    private static final int STOP = 9;
    private Button stop;
    private ImageView mCameraView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCameraView = findViewById(R.id.camera);
        setContentView(R.layout.activity_analog_joystick);

        stop = findViewById(R.id.stop);
        stop.setOnClickListener(view -> stopCar());


        final JoystickJhr joystick = findViewById(R.id.joystick);

        joystick.setOnTouchListener((view, motionEvent) -> {

            joystick.move(motionEvent);
            joystick.joyX();
            joystick.joyY();
            joystick.angle();
            joystick.distancia();

            int direction = joystick.getDireccion();
            if (lastDirection != direction) {
                lastDirection = direction;

                if (direction == JoystickJhr.STICK_UP) {
                    drive(FORWARD, "Moving forward");
                } else if (direction == JoystickJhr.STICK_UPRIGHT) {
                    drive(UP_RIGHT, "Moving forward to diagonal right");
                } else if (direction == JoystickJhr.STICK_RIGHT) {
                    drive(RIGHT, "Moving right");
                } else if (direction == JoystickJhr.STICK_DOWNRIGHT) {
                    drive(DOWN_RIGHT, "Moving reverse to diagonal right");
                } else if (direction == JoystickJhr.STICK_DOWN) {
                    drive(REVERSE, "Moving in reverse");
                } else if (direction == JoystickJhr.STICK_DOWNLEFT) {
                    drive(DOWN_LEFT, "Moving reverse to diagonal left");
                } else if (direction == JoystickJhr.STICK_LEFT) {
                    drive(LEFT, "Moving to the left");
                } else if (direction == JoystickJhr.STICK_UPLEFT) {
                    drive(UP_LEFT, "Moving diagonal forward left");
                }

            }

            return true;
        });

    }

    private void stopCar() {
        drive(STOP,"Stopping");
    }

   */

}