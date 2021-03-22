package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 - X
    //1 - O
    boolean game_active = true;
    int activeplayer = 0;
    int x_wins = 0;
    int o_wins = 0;
    int set_flag = 0;
    int[] gamestates = {2,2,2,2,2,2,2,2,2};
    //States:
    //0 - X
    //1 - O
    //2 - NULL

    int[][] winning_states = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int count = 0;

    public void tap(View view){
            ImageView img = (ImageView) view;
            int imagetag = Integer.parseInt(img.getTag().toString());
            if(gamestates[imagetag] == 2 && game_active){
                gamestates[imagetag] = activeplayer;
                img.setTranslationY(-1000f);
                if(activeplayer == 0){
                    count++;
                    img.setImageResource(R.drawable.x);
                    TextView status = findViewById(R.id.status);
                    if(count!=9) {
                        status.setText("Player-O Turn");
                        activeplayer = 1;
                    }
                    else{
                        status.setText("Match draw\nTap on Grid to Restart");
                        resetgame(view);
                        game_active = false;
                    }
                }
                else{
                    count++;
                    img.setImageResource(R.drawable.o);
                    TextView status = findViewById(R.id.status);
                    if(count!=9){
                        activeplayer = 0;
                        status.setText("Player-X Turn");
                    }
                    else{
                        status.setText("Match draw\nTap on Grid to Restart");
                        resetgame(view);
                        game_active = false;
                    }
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }

            else if(game_active == false){
                game_active = true;
                TextView status = findViewById(R.id.status);
                status.setText("Tap to Play");
            }
            // check if any player won
            for(int[] winposition : winning_states){
                if(gamestates[winposition[0]] == gamestates[winposition[1]]
                    && gamestates[winposition[1]] == gamestates[winposition[2]] &&
                    gamestates[winposition[0]] != 2){
                    if(gamestates[winposition[0]] == 0){
                        TextView status = findViewById(R.id.status);
                        TextView wins = findViewById(R.id.wins);
                        game_active = false;
                        status.setText("Player-X Wins!!!\nTap on Grid to Restart");
                        x_wins++;
                        wins.setText("Player-X Wins: "+x_wins+"   "+"Player-O Wins: "+o_wins);
                        resetgame(view);
                    }
                    else if (gamestates[winposition[0]] == 1){
                        TextView status = findViewById(R.id.status);
                        TextView wins = findViewById(R.id.wins);
                        o_wins++;
                        wins.setText("Player-X Wins: "+x_wins+"   "+"Player-O Wins: "+o_wins);
                        game_active = false;

                        status.setText("Player-O Wins!!!\nTap on Grid to Restart");
                        resetgame(view);


                    }
                    }
            }

    }

    public void resetgame(View view){

        for(int i = 0 ; i< 9 ;i++){
            gamestates[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        count = 0;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}