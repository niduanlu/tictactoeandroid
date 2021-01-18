package com.comp1601.tictactoe;

import java.util.Random;
import android.os.Handler;

/**
 * the TicTacToe class is the model of the entire TicTac App
 * note: this game can even be launched without any controllers or view, all it needs it is a substitution with
 * main function
 *
 */

public class TicTacToe {

    private char currentPlayer;
    // defines the character X , O to alternate the player
    private char [][] chess = new char[3][3];
    // two dimensional array that lays out the position for each card/chess/tick/circle
    private int drawCount;
    // totally 9 draws for the game
    private int[] winPosition = new int[6];

    public final Handler mHandler = new Handler();
    // this is to extend the time for the delay


    public TicTacToe(){
        startGame();
    }

    /*
     * this method initializes the elements of entire board to an empty character
     * the rational behind this is we only apply the characters as markers X, O
     *  so that there is no need to put images and I only have to alternate characters
     */

    public void startGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                chess[i][j] = ' ';
            }
        }
        // constructor initializes the game image to ' '
        currentPlayer = 'X';
        // the gamePlayer always starts with player one
        drawCount = 0;
        // no card was shuffled when the game starts for sure
    }

    /*
     * @param   takes in the playerName, x Coordinate and y Coordinate of on the chessboard,
     * @return N/A
     * this method allows the player places the marker on the board each time
     * the draw count increments one. This means when draw count reaches 9 the game is over
     * i did not play with one dimensional array because it is less intutive to represent the specifics of this game layout
     *
     */

    public void playerSetChess(char player, int positionX, int positionY){
        chess[positionX][positionY] = currentPlayer;
        drawCount ++;
        // the player places the marker on the board each time
        // the draw count increments one. This means when draw count reaches 9 the game is over
    }

    /*
     to do AI part
     */
    public void computerSetChess(){
        Random rand = new Random();

        outerloop:
            for(int i = 0; i < 3; i++ ){
                for(int j = 0; j < 3; j++){
                    if(chess[i][j] == ' '){
                        chess[i][j] = 'O';
                        drawCount++;
                        break outerloop;
                    }
                }
        }
    }

    /*
     * @return everything in chess [x][x]
     * getter to get the entire dimension of chess -- all the elements
     */

    public char [][] getChess(){
        return chess;
    }
    public char getCurrentPlayer(){
        return currentPlayer;
    }

    /*
    * @return N/A
    * getter to get the entire dimension of chess -- all the elements
    ternary operator to set the player marker
    if(currentPlayer is equal to X),
    currentPlayer = X, for the next round
    we switch to O,  X other wise
    this could have been simplied with if(current == X) current = 'O';
    but preferred shorthand
    */

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';

    }

    public char checkResult(){
        /*
        first and foremost, check if there is any other draw left.
        no remaining draw means the game is over without winners
         */

        if(drawCount >= 9){
            return 'E';
        }
        /*
        checking the conditions for the cheese board in a two dimensional array by row
         */
        if(chess[0][0] == chess[0][1] && chess[0][1] == chess[0][2]){
            winPosition[0] = 0;
            winPosition[1] = 0;
            winPosition[2] = 0;
            winPosition[3] = 1;
            winPosition[4] = 0;
            winPosition[5] = 2;

            return chess[0][0];
        }else if(chess[1][0] == chess[1][1] && chess[1][1] == chess[1][2]){
            winPosition[0] = 1;
            winPosition[1] = 0;
            winPosition[2] = 1;
            winPosition[3] = 1;
            winPosition[4] = 1;
            winPosition[5] = 2;
            return chess[1][0];
        }else if(chess[2][0] == chess[2][1] && chess[2][1] == chess[2][2]){
            winPosition[0] = 2;
            winPosition[1] = 0;
            winPosition[2] = 2;
            winPosition[3] = 1;
            winPosition[4] = 2;
            winPosition[5] = 2;
            return chess[2][0];
        }

         /*
        checking the conditions for the cheese board in a two dimensional array by column
         */
        if(chess[0][0] == chess[1][0] && chess[1][0] == chess[2][0]){
            winPosition[0] = 0;
            winPosition[1] = 0;
            winPosition[2] = 1;
            winPosition[3] = 0;
            winPosition[4] = 2;
            winPosition[5] = 0;
            return chess[0][0];
        }else if(chess[0][1] == chess[1][1] && chess[1][1] == chess[2][1]){
            winPosition[0] = 0;
            winPosition[1] = 1;
            winPosition[2] = 1;
            winPosition[3] = 1;
            winPosition[4] = 2;
            winPosition[5] = 1;
            return chess[0][1];
        }else if(chess[0][2] == chess[1][2] && chess[1][2] == chess[2][2]){
            winPosition[0] = 0;
            winPosition[1] = 2;
            winPosition[2] = 1;
            winPosition[3] = 2;
            winPosition[4] = 2;
            winPosition[5] = 2;
            return chess[0][2];
        }

        if(chess[0][0] == chess[1][1] && chess[1][1] == chess[2][2]){
            winPosition[0] = 0;
            winPosition[1] = 0;
            winPosition[2] = 1;
            winPosition[3] = 1;
            winPosition[4] = 2;
            winPosition[5] = 2;
            return chess[0][0];
        }else if(chess[0][2] == chess[1][1] && chess[1][1] == chess[2][0]){
            winPosition[0] = 0;
            winPosition[1] = 2;
            winPosition[2] = 1;
            winPosition[3] = 1;
            winPosition[4] = 2;
            winPosition[5] = 0;
            return chess[0][2];
        }
        return 'C';
        // this means there is neither winner or draw exceeding the limit
        // the game continues

    }


    /*
     * as mentioned previous, this model could run itself without any visual effects -- namely UI
     * printBoard is designed to be the most simplistic view that we can test our logic in model!!
     * print out the entire board on logcat in order to provide the tracebility of the game
     * which gives a nicely laid out diagram of our chess board when you search System.out in the logcat
     * todo:use log.something????
     * :---------------
        |    |   X|    |
        ---------------
        |   O|   X|   O|
         ---------------
        |   X|   O|    |
        ---------------
     * personally, i prefer to call it printBoard()
     */

    public void play(){

       System.out.println("the board is below");

        System.out.println("---------------");
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){

                System.out.print("|   " + chess[i][j]);
            }
            System.out.print("|");
            System.out.println("");
            System.out.println("---------------");


        }

    }
    public int [] getWinPosition(){
        return winPosition;
    }
}
