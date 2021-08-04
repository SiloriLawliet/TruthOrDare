package com.example.truthanddare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button enterButton;
    EditText playerInput;
    String numberOfPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_TruthAndDare);
        setContentView(R.layout.activity_main);


        enterButton = findViewById(R.id.enterButton);
        playerInput = findViewById(R.id.playerInput);
        enterButton.setEnabled(false);

        playerInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                enterButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOfPlayer = playerInput.getText().toString();
                if (TextUtils.isEmpty(numberOfPlayer)) {
                    playerInput.setError("Enter Number Of Player");
                    return;
                }

                if (Integer.parseInt(numberOfPlayer) < 2 || 6 < Integer.parseInt(numberOfPlayer)) {
                    playerInput.setError("Number of Player should be Between 2 and 6");
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), GameBoard.class);
                intent.putExtra("playerNum", numberOfPlayer);
                startActivity(intent);

            }
        });


//        Intent intent = new Intent(this, GameBoard.class);
//            intent.putExtra("playerNum", playerNum);
//
//        playerNum = playerInput.getText().toString();


    }
}