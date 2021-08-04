package com.example.truthanddare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class GameBoard extends AppCompatActivity {
    private ImageView board;
    private ImageView bottle;
    private final Random random = new Random();

    private float startAngle = 0.0f;
    private float pivoitX;
    private float pivoitY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        board = findViewById(R.id.board);
        bottle = findViewById(R.id.bottle);

        int numPlayer = Integer.parseInt(getIntent().getStringExtra("playerNum"));
        switch (numPlayer) {
            case 2:
                board.setImageResource(R.drawable.two);
                break;
            case 3:
                board.setImageResource(R.drawable.three);
                break;
            case 4:
                board.setImageResource(R.drawable.four);
                break;
            case 5:
                board.setImageResource(R.drawable.five);
                break;
            case 6:
                board.setImageResource(R.drawable.six);
                break;


        }

        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spin(view);
            }
        });


    }

    public void spin(View view) {
        pivoitX = (float) bottle.getWidth() / 2;
        pivoitY = (float) bottle.getHeight() / 2;

        int randomNum = random.nextInt(361);
        float endingAngle = (float) randomNum;

        Animation spin = new RotateAnimation(startAngle, endingAngle + 1440, pivoitX, pivoitY);
        spin.setDuration(2000);
        spin.setFillAfter(true);
        spin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                bottle.setOnClickListener(null);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bottle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        spin(view);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Log.d("test", "startAngle" + startAngle);
        Log.d("test", "endingAngle" + endingAngle);
        bottle.startAnimation(spin);
        startAngle = endingAngle;

    }
}