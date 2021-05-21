package B_Cliente.game_handler;

import A_Servidor.enumConst.DataChecks;
import B_Cliente.enums.DataCheckers;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Controller implements Runnable{

    private boolean continueToPlay;
    private boolean waitinfForAction;
    private boolean isOver;
    private boolean myTurn = false;

    // Netwrok
    private static DataInputStream fromServer;
    private static DataOutputStream toServer;

    public Controller(DataInputStream input, DataOutputStream output){
        this.fromServer = input;
        this.toServer = output;
    }

    public static DataOutputStream getOutput(){
        return toServer;
    }
    public static DataInputStream getInput(){
        return fromServer;
    }

    @Override
    public void run() {

        continueToPlay = true;
        waitinfForAction = true;
        isOver = false;

        try{
            while (continueToPlay && !isOver){



                if (DataCheckers.ID.getValue() == DataChecks.PLAYER_ONE.getValue()){
                    waitForPlayerAction();
                    if(!isOver)
                        receiveInfoFromServer();
                } else if (DataCheckers.ID.getValue() == DataChecks.PLAYER_TWO.getValue()) {
                    receiveInfoFromServer();
                    if(!isOver)
                        waitForPlayerAction();
                }
            }

            if (isOver){
                JOptionPane.showMessageDialog(null, "game is over", "information", JOptionPane.INFORMATION_MESSAGE, null);
                System.exit(0);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Connection lost",
                    "Error", JOptionPane.ERROR_MESSAGE, null);
            System.exit(0);
        }
    }

    private void receiveInfoFromServer() throws IOException{

        this.myTurn = false;
        int from = fromServer.readInt();
        if (from==DataChecks.YOU_LOSE.getValue()){
            from = fromServer.readInt();
            int to = fromServer.readInt();
            updateReceivedInfo(from, to);
            isOver = true;
        } else if (from == DataChecks.YOU_WIN.getValue()){
            isOver = true;
            continueToPlay = false;
        } else {
            int to = fromServer.readInt();
            updateReceivedInfo(from, to);
        }

    }

    private void  waitForPlayerAction(){

    }

    private void updateReceivedInfo(int from, int to) {
    }
}
