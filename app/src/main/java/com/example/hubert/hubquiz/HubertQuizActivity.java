package com.example.hubert.hubquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HubertQuizActivity extends AppCompatActivity {

    private QandA qanda = new QandA();
    private TextView pktview;
    private TextView chnview;
    private Button buttonodp1;
    private Button buttonodp2;
    private Button buttonodp3;
    private Button buttonodp4;
    private Button byehqa;
    private Button flag;

    private  String dOdp;
    private int pkt;
    public int pktsend;
    private int chn = 3;
    private int correct = 0;
    private String info;

    List<String> list = new ArrayList<>(4);
    Random generator = new Random();
    PopLose popLose = new PopLose();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hubquiz);
        pktview = (TextView) findViewById(R.id.pkt);
        chnview = (TextView) findViewById(R.id.chn);
        buttonodp1 = (Button) findViewById(R.id.wyb1);
        buttonodp2 = (Button) findViewById(R.id.wyb2);
        buttonodp3 = (Button) findViewById(R.id.wyb3);
        buttonodp4 = (Button) findViewById(R.id.wyb4);
        byehqa = (Button) findViewById(R.id.quit);
        flag = (Button) findViewById(R.id.flaga);

        updateQuestion();



        // buttons listner
        buttonodp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                checkAnswer(buttonodp1);
            }
        });
        buttonodp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(buttonodp2);
            }
        });
        buttonodp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(buttonodp3);
            }
        });
        buttonodp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(buttonodp4);
            }
        });
        byehqa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //popLose.lose.setText("");
                info = "";
                openActivity(PopLose.class);
                System.exit(0);
            }
        });

    }
    private void updateQuestion(){
        int qnum = 0;
        int count = 0;
        String temp;
        list.clear();
        correct = generator.nextInt(qanda.getLength()); // set  number correct answer from QandA
        dOdp = qanda.getName(correct); // set correct String from QandA
        while(true) { // generate fake fake answer
            qnum = generator.nextInt(qanda.getLength());
            temp = qanda.getName(qnum);
            if (!list.contains(temp) && qnum!=correct) {
                list.add(temp);
                count++;
            }
            if(count==3){
                list.add(dOdp);
                flag.setBackgroundResource(qanda.getImg(correct));
                count=0;
                break;
            }
        }
        suffleArray(list);
        buttonodp1.setText(list.get(0));
        buttonodp2.setText(list.get(1));
        buttonodp3.setText(list.get(2));
        buttonodp4.setText(list.get(3));

    }
    //update points on activity
    private void updateScore(int pkt){
        pktview.setText("" + pkt);
        popLose.pkt = pkt;
    }
    //update chance on activity
    private  void updateChance (int chn) {
        chnview.setText("" + chn);
    }
    //shuffle answer on buttons
    private void suffleArray(List<String> list){
        for (int i = 3; i > 0; i--)
        {
            int index = generator.nextInt(i + 1);
            // Simple swap
            String a = list.get(index);
            list.set(index, list.get(i));
            list.set(i, a);
        }
    }
        public void checkAnswer(final Button answer){
        final long changeTime = 1000L;
        final Animation alpha1 = AnimationUtils.loadAnimation(this, R.anim.buttoncorrectanswer);
        answer.startAnimation(alpha1);

                if (answer.getText() == dOdp) {
                    answer.setBackgroundResource(R.drawable.custombtngreen);
                    answer.postDelayed(new Runnable() {
                        @Override
                        public void run(){
                            answer.setBackgroundResource(R.drawable.customchoicebtn);
                            pkt = pkt + 1;
                            updateScore(pkt);
                            updateQuestion();
                        }

                    }, changeTime);
                }else{
                    answer.setBackgroundResource(R.drawable.custombtnred);
                    answer.postDelayed(new Runnable() {
                        @Override
                        public void run(){
                           answer.setBackgroundResource(R.drawable.customchoicebtn);
                            chn = chn - 1;
                            updateChance(chn);
                            if (chn == 0){
                                info = "Przegrałeś";
                                openActivity(PopLose.class);
                               System.exit(0);
                            }
                            updateQuestion();
                        }
                    }, changeTime);
                }
            }
    public void openActivity(Class class1){
        pktsend=pkt;
        Intent intent = new Intent(this, class1);
        intent.putExtra("intVariableName", pktsend);
        intent.putExtra("StringVariablename", info);
        startActivity(intent);

    }
    }

