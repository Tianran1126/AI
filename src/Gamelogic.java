import processing.core.PApplet;

import java.util.LinkedList;

public class Gamelogic {

    /***
     *It updates movement of all game objects
     * @param catcuses list of obstacles
     * @param speed The speed of the obstacle
     * @param groundSpeed The ground speed
     * @param players list of players
     */
    public void gameUpdate(LinkedList<Catcus> catcuses,float speed, float groundSpeed,Player[]players){
        catcusesUpdate(catcuses,speed,groundSpeed);
        playerUpdate(players,catcuses);
    }


    /**
     * It renders all the objects in the game
     * @param players cactus
     * @param catcuses list of obstacles
     * @param groundHeight The ground speed
     * @param window window of the screen
     * @return
     */
    public float showObjects(Player []players,LinkedList<Catcus> catcuses,float groundHeight,PApplet window){

        showCatcus(catcuses,window,groundHeight);
        return showPlayers(players,window,groundHeight);
    }

    /**
     * It renders obstacles
     * @param catcuses list of obstacles
     * @param window window of the screen
     * @param groundHeight The ground speed
     */

    private void showCatcus(LinkedList<Catcus> catcuses, PApplet window, float groundHeight){
        for(Catcus catcus : catcuses){
            window.fill(0);
            window.rectMode(window.CENTER);
            window.rect(catcus.getPosition().x, window.height - groundHeight - catcus.getCatusSize().y / 2 - catcus.getPosition().y, catcus.getCatusSize().x, catcus.getCatusSize().y);
        }
    }

    /**
     * It renders players
     * @param players list of players
     * @param window window of the game
     * @param groundHeight The ground speed
     * @return the max score of the player
     */
    private float showPlayers(Player []players,PApplet window,float groundHeight){
        float max=0;
        for(Player player : players){
            if(player.playerDeath()) {
                continue;
            }
            if(player.getGameScore() > max) {
                max = player.getGameScore();
            }
            window.fill(window.color(255,0,0));
            window.rectMode(window.CENTER);
            window.rect(player.dinoPosition().x, window.height - groundHeight - player.dinoSize().y / 2 - player.dinoPosition().y, player.dinoSize().x,player.dinoSize().y);
        }

        return max;
    }

    /**
     * It updates movement of obstacles
     * @param catcuses list of obstacles
     * @param speed The speed of obstacles
     * @param groundSpeed The ground speed
     */
    private void catcusesUpdate(LinkedList<Catcus> catcuses,float speed, float groundSpeed){
        LinkedList<Catcus> discardCatcus = new LinkedList<>();
        for(Catcus catcus : catcuses){
            catcus.move((groundSpeed+ speed));
            if(catcus.getPosition().x<=0) {
                discardCatcus.add(catcus);
            }
        }
        catcuses.removeAll(discardCatcus);

        Catcus catcus = catcuses.peekLast();

        if(catcus == null){
            catcuses.add(new Catcus());
        }
    }

    /**
     * It updates movement of players
     * @param players list of players
     * @param catcuses list of obstacles
     */
    private void playerUpdate(Player[]players,LinkedList<Catcus> catcuses){
        for(Player player : players){
            if(player.playerDeath()) {
                continue;
            }

            playerLogic(player,catcuses);
        }
    }

    /***
     * Each player has a neural network , neural network decides the actions of the player.
     * @param player  The agent
     * @param catcuses list of obstacles
     */
    private void playerLogic(Player player,LinkedList<Catcus> catcuses) {

        player.think(catcuses);
        player.dinoJump();
        player.checkAndUpdate(catcuses);
    }


}
