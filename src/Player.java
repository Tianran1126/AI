import processing.core.PVector;

import java.util.LinkedList;

public class Player {

    private boolean playerDie = false;
    private float gameScore = 0;
    private Network network;
    private float y= 0; //reference to the groundprivate float velY = 0f; //v y
    private float gravity = -0.75f;
    private double velocity;


    public Player(){
        network = new Network();
        network.GenerateWeight();
    }



    public boolean playerDeath() {
        return playerDie;
    }

    public float getGameScore() {
        return gameScore;
    }



    public void think(LinkedList<Catcus> catcuses){
        network.process(catcuses, this);
        if(network.performAction()) {
            jumpForce();
        }
    }

    public void checkAndUpdate(LinkedList<Catcus> catcuses){
        if(playerDie){
            System.out.println("the game is over");
        }

        for(Catcus catcus : catcuses){
            if(catcus.checkCollision(this)){
                playerDie = true;
                return;
            }
        }
        gameScore += 0.01;
    }

    @Override
    public Player clone(){
        Player p = new Player();
        p.network = network.clone();
        return p;
    }

    public void mutation(double rate){//chance that any vector in directions gets changed
        network.mutation(rate);
    }





    private boolean jump(){
        return (y == 0 && velocity > 0)||y>0;
    }

    public void dinoJump(){

        if(jump()){
            y += velocity;
            velocity += gravity;
        }
        if(y < 0){
            y = 0;
            velocity = 0;
        }

    }


    public void jumpForce(){
        if( onGround()){
            velocity = 11.2f;
        }
    }

    private boolean onGround(){
        return y == 0 && velocity == 0;
    }

    public PVector dinoPosition() {
        return new PVector(100, y);
    }

    public PVector dinoSize(){
        return new PVector(40, 40);

    }


}
