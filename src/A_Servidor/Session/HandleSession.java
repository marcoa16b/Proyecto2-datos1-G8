package A_Servidor.Session;

import A_Servidor.enumConst.DataChecks;
import A_Servidor.operators.ManagePlayers;

import java.net.Socket;

public class HandleSession implements Runnable {

    private ManagePlayers player1;
    private ManagePlayers player2;

    private boolean isOver;

    private boolean continueToPlay = true;

    public HandleSession(Socket p1, Socket p2){
        player1 = new ManagePlayers(DataChecks.PLAYER_ONE.getValue(), p1);
        player2 = new ManagePlayers(DataChecks.PLAYER_TWO.getValue(), p2);
    }

    @Override
    public void run() {

        try{
            //player1.sendData(1);

            while (continueToPlay){

                //  wait for player 1's action
                int from = player1.receiveData(); // Id of player (1 or 2)
                int to = player1.receiveData();   // action
                int data = player1.receiveData(); // Data of action
                checkStatus(from, to);
                updateGameModel(from, to, data);

                // Send Data to 2nd player
                if (isOver)
                    player2.sendData(DataChecks.YOU_LOSE.getValue());
                int fromStatus = player2.sendData(from);
                int toStatus = player2.sendData(to);
                checkStatus(fromStatus,toStatus);

                // If game is over, breakdata
                if(isOver){
                    player1.sendData(DataChecks.YOU_WIN.getValue());
                    continueToPlay=false;
                    break;
                }

                System.out.println("after break");

                //wait for player 2's Action
                from = player2.receiveData();
                to = player2.receiveData();
                data = player2.receiveData();
                checkStatus(from, to);
                updateGameModel(from, to, data);

                //Send Data back to 1st Player
                if(isOver){
                    player1.sendData(DataChecks.YOU_LOSE.getValue());		//Game Over notification
                }
                fromStatus = player1.sendData(from);
                toStatus = player1.sendData(to);
                checkStatus(fromStatus,toStatus);

                //IF game is over, break
                if(isOver){
                    player2.sendData(DataChecks.YOU_WIN.getValue());
                    continueToPlay=false;
                    break;
                }

                System.out.println("second break");

            }
        } catch (Exception ex) {
            System.out.println("Connection is being closed");

            if(player1.isOnline())
                player1.closeConnection();

            if(player2.isOnline())
                player2.closeConnection();

            return;
        }

    }

    private void checkStatus(int status, int status2) throws Exception{
        if(status==99 || status2==99){
            throw new Exception("Connection is lost");
        }
    }

    private void updateGameModel(int from, int to, int data) {
        // update the game window
        if (from == DataChecks.PLAYER_ONE.getValue()){ // If the action is from the player 1
            if (to == DataChecks.MOVE_RIGHT.getValue()){
                player2.sendData(DataChecks.PLAYER_ONE.getValue());
                player2.sendData(DataChecks.MOVE_RIGHT.getValue());
                player2.sendData(data);
            } else if (to == DataChecks.MOVE_LEFT.getValue()){
                player2.sendData(DataChecks.PLAYER_ONE.getValue());
                player2.sendData(DataChecks.MOVE_LEFT.getValue());
                player2.sendData(data);
            } else if (to == DataChecks.SHOT.getValue()){
                // Receive and send the position in X
            }

        } else if (from == DataChecks.PLAYER_TWO.getValue()) { // If the action is from the player 2
            if (to == DataChecks.MOVE_RIGHT.getValue()){
                player1.sendData(DataChecks.PLAYER_ONE.getValue());
                player1.sendData(DataChecks.MOVE_RIGHT.getValue());
                player1.sendData(data);
            } else if (to == DataChecks.MOVE_LEFT.getValue()){
                player1.sendData(DataChecks.PLAYER_ONE.getValue());
                player1.sendData(DataChecks.MOVE_LEFT.getValue());
                player1.sendData(data);
            } else if (to == DataChecks.SHOT.getValue()){
                // Receive the position in X
            }
        }
    }

}
