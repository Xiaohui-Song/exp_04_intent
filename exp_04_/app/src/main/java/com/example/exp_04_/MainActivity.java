package com.example.exp_04_;

import androidx.appcompat.app.AppCompatActivity;
import android.net.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.url);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String url = editText.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("key",url);
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }

        });

    }
}
