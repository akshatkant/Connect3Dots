package com.example.connect3dots;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //yellow=0 , red=1 , empty=2;

    int gameState[]={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        Log.i("Info",counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        gameState[tappedCounter]=activePlayer;



        if(activePlayer==0) {
            counter.setImageResource(R.drawable.yellow);
            counter.animate().translationYBy(1500).setDuration(300);
            activePlayer=1;
        }
        else
        {
            counter.setImageResource(R.drawable.red);
            counter.animate().translationYBy(1500).setDuration(300);
            activePlayer=0;
        }

        for(int[] winningPosition: winningPositions)
        {
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2)
            {
                //someone has won
                gameActive = false;
                String winner = "";

                
                if(activePlayer==1)
                {
                    winner="Yellow";
                }
                else
                {
                    winner = "Red";
                }

               // Toast.makeText(this, winner +" has won", Toast.LENGTH_SHORT).show();
                TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
                Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
                winnerTextView.setAlpha(1);
                winnerTextView.setText(winner+ " has won !");
                playAgainButton.animate().translationY(0).alpha(1).setDuration(300);
                Toast.makeText(this, "Play Again?", Toast.LENGTH_SHORT).show();









            }


        }



    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        activePlayer = 0;

        gameActive = true;

        winnerTextView.setAlpha(0);

        winnerTextView.setVisibility(View.VISIBLE);
        //winnerTextView.setText(winner+ " has won !");
        playAgainButton.setVisibility(View.VISIBLE);





    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Let's Start The Game", Toast.LENGTH_SHORT).show();


    }
}