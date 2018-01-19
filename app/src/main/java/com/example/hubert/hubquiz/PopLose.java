package com.example.hubert.hubquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class PopLose extends AppCompatActivity {
    private Button btn;
    private TextView text;
    public int pkt;
    public TextView lose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pop_lose);

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("intVariableName", 0);
        String strget = mIntent.getStringExtra("StringVariablename");


        btn = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.pktkoniec);
        lose = (TextView) findViewById(R.id.lose);
        text.setText("" + intValue);
        lose.setText("" + strget);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                System.exit(0);
            }
        });
    }
}
