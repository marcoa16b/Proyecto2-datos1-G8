package B_Cliente.game_handler;

import B_Cliente.main.Game;
import B_Cliente.objects.ObjectId;
import B_Cliente.objects.Player2;

public class SecPlayerController {

    private static boolean isSet = false;
    private static Player2 player;

    public static void setPlayer2(){
        if (isSet == false){
            Handler handler = Game.getHandler();
            player = new Player2(50, 50, ObjectId.SECOND_PLAYER);
            handler.addObject(player);
            isSet = true;
        }
    }

    public static void setposXPlayer(int x){
        if (isSet == true){
            player.setX(x);
        } else {
            System.out.println("Movimiento invalido");
        }
    }

    public static void setposYPlayer(int y){
        if (isSet == true){
            player.setY(y);
        } else {
            System.out.println("Movimiento invalido");
        }
    }
}
