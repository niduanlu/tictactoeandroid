package com.comp1601.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * the TicTacToe class is the model of the entire TicTac App
 * Personal statement: the main work was done in the model  -- the brain/business logic
 * and not single UI involved in the model
 * and the Controller involves only UI interaction with View
 *
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int[][] cardsIds = {{R.id.button_00, R.id.button_01, R.id.button_02
    }, {R.id.button_10,R.id.button_11, R.id.button_12
    },{R.id.button_20, R.id.button_21, R.id.button_22}
    };
    // another way is to retrieve the button's id ! .getId() method.
    // I deliberately divide the button id to a two dimensional array for easy manipulation

    private Button [][] cards = new Button[3][3];
    // 3 * 3 cardboard / chessboard for the game

    private TextView mTextViewX;
    private TextView mTextViewO;
    // player score view

    private int playerScore;
    private int computerScore;
    // player score it was too tedious to instantiate an totalScore for each player on the model, rather just keep
    // them on the controller


    private int [] winPosition;

    public final Handler mHandler = new Handler();
    // this is to extend the time for the delay

    public static final String GAME_ENGINE_STATE = "game_engine_state";
    public static final String PLAYER_SCORE_STATE = "player_score_state";
    public static final String COMPUTER_SCORE_STATE = "computer_score_state";



    TicTacToe gameEngine = new TicTacToe();
    // model must start right off the bat


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerScore = 0;
        computerScore = 0;
        // all the scores must be 0 when the app is launched

        gameEngine.startGame();
        gameEngine.play();
        // model : initialized the board of the game

        mTextViewX = findViewById(R.id.tex_view_X);
        mTextViewO = findViewById(R.id.tex_view_O);
        // initalized the text of the game


        for(int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                //String coordinate = "cards_" + i + j;
                //int resId = getResources().getIdentifier(coordinate, "id", getPackageName());
                cards[i][j] = findViewById(cardsIds[i][j]);
                cards[i][j].setOnClickListener(this);
                mTextViewX.setText("X: "+playerScore);

                mTextViewO.setText("O: "+computerScore);

            }
        }

            // here is the thing to ponder over for me
            // how do we create a onClick method to run over a set of methods that has been generate here
            // to set the event listener by implementing the android view.onClickListener interface
         if(savedInstanceState != null){
             playerScore = savedInstanceState.getInt(PLAYER_SCORE_STATE);
             computerScore = savedInstanceState.getInt(COMPUTER_SCORE_STATE);

             char []oneDimensionalChesss = savedInstanceState.getCharArray(GAME_ENGINE_STATE);
             char [][] twoDimensionalChess = new char[3][3];

             for(int i = 0; i < 3; i++){
                 for(int j = 0; j < 3; j++){
                     twoDimensionalChess[i][j] = oneDimensionalChesss[j * 3 + i];
                     cards[i][j].setText(""+twoDimensionalChess[i][j]);
                 }
             }
         }
    }

    /*
     * this method utilizes the coordination of the chess and button array to connect business log with UI elements
     * updateBoard was invoked only with the latest visual representation of a card being placed
     * switch player was to swap the player's turn back characters
     */

     @Override
    public void onClick(View v){
        String idString = getResources().getResourceEntryName(v.getId());
        char result;

        System.out.println("this button has been clicked:"+idString);
        int positionX = Integer.parseInt(String.valueOf(idString.charAt(7)));
        int positionY = Integer.parseInt(String.valueOf(idString.charAt(8)));
        // get the 7th and 8th of the id which is hte position we set for the coordinate of matrix
         // sophisticatedly named id of each button with coordination and combination
         // i.e. button_00, so they can be applied to precisely locate the position on board
         // there may be other ways to do it smartly, open end question tho
         char currentPlayer = gameEngine.getCurrentPlayer();
         gameEngine.playerSetChess(currentPlayer, positionX, positionY);
         result = gameEngine.checkResult();

         System.out.println("the positionX="+positionX);
         System.out.println("the positionY="+positionY);
         // to know where the X or O took place in

         switch (result){
             case 'E':
                 Toast.makeText(this,"End Draw", Toast.LENGTH_SHORT).show();
                 gameEngine.startGame();
                 System.out.println("End Draw, no win no lose");
                 updateBoard();
                 break;
             case 'X':
                 winPosition = gameEngine.getWinPosition();
                 for(int i = 0; i < winPosition.length; i+=2){
                     //cards[winPosition[i]][winPosition[i+1]].setBackgroundResource(android.R.drawable.alert_light_frame);
                     cards[winPosition[i]][winPosition[i+1]].setBackgroundColor(getResources().getColor(R.color.orangeColor));
                 }
                 mHandler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         displayResultandRestartGame("X Wins", 'X');
                     }
                 },1000);


                 break;

         }

         updateBoard();
         // gameEngine.switchPlayer();
         gameEngine.play();
         gameEngine.computerSetChess();


         // after placing a chess, the result must be check instantly




         gameEngine.play();
         updateBoard();

         result = gameEngine.checkResult();

         System.out.println("the positionX="+positionX);
         System.out.println("the positionY="+positionY);
         // to know where the X or O took place in

         switch (result){
             case 'E':
                 Toast.makeText(this,"End Draw", Toast.LENGTH_SHORT).show();
                 gameEngine.startGame();
                 System.out.println("End Draw, no win no lose");
                 updateBoard();
                 break;
             case 'X':
                 winPosition = gameEngine.getWinPosition();
                 for(int i = 0; i < winPosition.length; i+=2){
                     //cards[winPosition[i]][winPosition[i+1]].setBackgroundResource(android.R.drawable.alert_light_frame);
                     cards[winPosition[i]][winPosition[i+1]].setBackgroundColor(getResources().getColor(R.color.orangeColor));
                 }
                 mHandler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         displayResultandRestartGame("X Wins", 'X');
                     }
                 },1000);


                 break;
             case 'O':
                 winPosition = gameEngine.getWinPosition();
                 for(int i = 0; i < winPosition.length; i+=2){
                     cards[winPosition[i]][winPosition[i+1]].setBackgroundColor(getResources().getColor(R.color.orangeColor));
                 }

                 mHandler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         displayResultandRestartGame("O Wins!", 'O');
                     }
                 },1000);

                 break;

         }
         // in the swtich statemetn, it gets the result from our model
         // X stands for the winning of playerX , O for computer otherwise
         // E is the end of draw
     }


    /*
     * simply just board the board layout with X and O after they being placed on the map
     * and setText of the marker(X/O)
     *
     */

    public void updateBoard(){
          char [][] chessOnBoard = gameEngine.getChess();
          //get chess from UI

          for(int i = 0; i < 3; i++){
              for(int j = 0; j < 3; j++){

                  cards[i][j].setText(""+chessOnBoard[i][j]);
                  cards[i][j].setBackgroundResource(android.R.drawable.btn_default);

              }
          }
    }



     /*
      *
      * add the player total score aka wins , update UI on player scores
      * make a toast to congratulate or just a congratulatory message on the model
      * restart the game , update the board
      */

     public void displayResultandRestartGame(String message, char winner){
         if(winner == 'X'){
             playerScore ++;
             mTextViewX.setText(winner+": "+playerScore);
             System.out.println("Player Won");
         }else{
             computerScore++;
             mTextViewO.setText(winner+": "+computerScore);
             System.out.println("Computer Won");
         }

         Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
         gameEngine.startGame();

         updateBoard();
     }


    /*@Override
    protected void onSaveInstanceState(Bundle savedInStanceState){
        super.onSaveInstanceState(savedInStanceState);
        char [] oneDimensionChess = new char[9];
        char [][] gameChess = gameEngine.getChess();
        int counter = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                oneDimensionChess[counter] = gameChess[i][j];
                counter++;
            }
        }
        savedInStanceState.putCharArray(GAME_ENGINE_STATE,oneDimensionChess);
        savedInStanceState.putInt(PLAYER_SCORE_STATE, playerScore);
        savedInStanceState.putInt(COMPUTER_SCORE_STATE, computerScore);
    }*/


}
