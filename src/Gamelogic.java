import processing.core.PApplet;

import java.util.LinkedList;

public class Gamelogic {

    public void gameUpdate(LinkedList<Catcus> catcuses,float speed, float groundSpeed,Player[]players){
        catcusesUpdate(catcuses,speed,groundSpeed);
        playerUpdate(players,catcuses);
    }

    public float showObjects(Player []players,LinkedList<Catcus> catcuses,float groundHeight,PApplet window){

        showCatcus(catcuses,window,groundHeight);
        return showPlayers(players,window,groundHeight);
    }

    private void showCatcus(LinkedList<Catcus> catcuses, PApplet window, float groundHeight){
        for(Catcus catcus : catcuses){
            window.fill(0);
            window.rectMode(window.CENTER);
            window.rect(catcus.getPosition().x, window.height - groundHeight - catcus.getCatusSize().y / 2 - catcus.getPosition().y, catcus.getCatusSize().x, catcus.getCatusSize().y);
        }
    }

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

    private void playerUpdate(Player[]players,LinkedList<Catcus> catcuses){
        for(Player player : players){
            if(player.playerDeath()) {
                continue;
            }

            playerLogic(player,catcuses);
        }
    }

    private void playerLogic(Player player,LinkedList<Catcus> catcuses) {

        player.think(catcuses);
        player.dinoJump();
        player.checkAndUpdate(catcuses);
    }


}
