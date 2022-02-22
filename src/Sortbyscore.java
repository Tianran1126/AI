import java.util.Comparator;

public class Sortbyscore implements Comparator<Player> {
    @Override
    public int compare(Player a, Player b) {
        return  ((int)a.getGameScore()-(int)b.getGameScore());
    }
}
