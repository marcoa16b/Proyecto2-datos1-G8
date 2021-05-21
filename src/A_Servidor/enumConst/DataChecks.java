package A_Servidor.enumConst;

public enum DataChecks {

    PLAYER_ONE(1),
    PLAYER_TWO(2),
    YOU_WIN(90),
    YOU_LOSE(91),
    MOVE_RIGHT(5),
    MOVE_LEFT(6),
    SHOT(7),
    PLAYER_ACTIVE(3);

    private int value;

    private DataChecks(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

}
