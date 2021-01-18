public class GameEngine {

    private char currentPlayer;
    private char [][] chess = new char[3][3];
    private int drawCount;


    public void startGame(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                chess[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
        drawCount = 0;
    }
    public void PlayerSetChess(int positionX, int positionY){
        chess[positionX][positionY] = currentPlayer;
        drawCount ++;

        // the player places the market on the board each time
        // the draw count increments one. This means when draw count reaches 9 the game is over

    }
    public void computerSetChess(int positionX, int positionY){
        chess[positionX][positionY] = currentPlayer;
        drawCount ++;

    }


        /*
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

        if(drawCount == 9){
            return 'E';
        }
        /*
        checking the conditions for the cheese board in a two dimesional array by row
         */
        if(chess[0][0] == chess[0][1] && chess[0][1] == chess[0][2]){
            return chess[0][0];
        }else if(chess[1][0] == chess[1][1] && chess[1][1] == chess[1][2]){
            return chess[1][0];
        }else if(chess[2][0] == chess[2][1] && chess[2][1] == chess[2][2]){
            return chess[2][0];
        }

         /*

        checking the conditions for the cheese board in a two dimensional array by column
         */
        if(chess[0][0] == chess[1][0] && chess[1][0] == chess[2][0]){
            return chess[0][0];
        }else if(chess[0][1] == chess[1][1] && chess[1][1] == chess[2][1]){
            return chess[0][1];
        }else if(chess[0][2] == chess[1][2] && chess[1][2] == chess[2][2]){
            return chess[0][2];
        }

        if(chess[0][0] == chess[1][1] && chess[1][1] == chess[2][2]){
            return chess[0][0];
        }else if(chess[0][2] == chess[1][1] && chess[1][1] == chess[2][0]){
            return chess[0][2];
        }


        return 'C';
        // this means there is neither winner or draw exceeding the limit
        // the game continues

    }

    public void printBoard(){

        for(int i = 0; i < 3; i++){
            System.out.println("---------------");
            for(int j = 0; j < 3; j++){

                System.out.print('|' + chess[i][j]+'|');
            }
            System.out.println("---------------");
        }

    }





}
