package A_Servidor.Session;

import A_Servidor.enumConst.DataChecks;
import A_Servidor.operators.ManagePlayers;

import java.net.Socket;

public class HandleSession implements Runnable {

    private ManagePlayers player1;
    private ManagePlayers player2;

    public boolean isOver;

    private boolean continueToPlay = true;

    public HandleSession(Socket p1, Socket p2){
        System.out.println("Se creo el HandleSession");
        player1 = new ManagePlayers(DataChecks.PLAYER_ONE.getValue(), p1);
        player2 = new ManagePlayers(DataChecks.PLAYER_TWO.getValue(), p2);
        isOver = false;
    }

    @Override
    public void run() {

        //int numOfPlayers

        try{

            // Notify that players are online
            //player1.sendData(DataChecks.PLAYER_TWO.getValue());
            //player1.sendData(DataChecks.PLAYER_ACTIVE.getValue());
            //player1.sendData(0);
            sendingMessages1(DataChecks.PLAYER_TWO.getValue(), DataChecks.PLAYER_ACTIVE.getValue(), 0);

            //player2.sendData(DataChecks.PLAYER_ONE.getValue());
            //player2.sendData(DataChecks.PLAYER_ACTIVE.getValue());
            //player2.sendData(0);
            sendingMessages2(DataChecks.PLAYER_ONE.getValue(), DataChecks.PLAYER_ACTIVE.getValue(), 0);


            while (continueToPlay){

                if (!isOver) {

                    //  Receive the info of player 1
                    int from = player1.receiveData(); // Id of player (1 or 2)
                    int to = player1.receiveData();   // action
                    int data = player1.receiveData(); // Data of action
                    checkerState(from, to);
                    if (checkStatus(from, to) == false) {
                        updateGameModel(from, to, data);
                    } else {
                        //player2.closeConnection();
                        break;
                    }

                    // Receive the info of player 2
                    from = player2.receiveData();
                    to = player2.receiveData();
                    data = player2.receiveData();
                    checkerState(from, to);
                    if (checkStatus(from, to) == false) {
                        updateGameModel(from, to, data);
                    } else {
                        //player1.closeConnection();
                        break;
                    }

                } else {
                    continueToPlay = false;
                    break;
                }

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

    private void sendingMessages1(int sms1, int sms2, int sms3){
        player1.sendData(sms1);
        player1.sendData(sms2);
        player1.sendData(sms3);
    }
    private void sendingMessages2(int sms1, int sms2, int sms3){
        player2.sendData(sms1);
        player2.sendData(sms2);
        player2.sendData(sms3);
    }

    private void checkerState(int state1, int state2) throws Exception{
        if (state1 == 99 || state2 == 99){
            throw new Exception("Conection is lost");
        }
    }

    private boolean checkStatus(int from, int to) { // throws Exception
        if((from==1 && to==99) || (from==2 && to==99)){
            return true;
            //throw new Exception("Connection is lost");

        }else{
            return false;
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
