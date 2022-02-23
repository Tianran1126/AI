import processing.core.PVector;

import java.util.LinkedList;

public class Player {

    private boolean playerDie = false;
    private float gameScore = 0;
    private Network network;
    private float y= 0;
    private float gravity = -0.75f;
    private double velocity;


    public Player(){
        network = new Network();
        network.GenerateWeight();
    }


    /**
     * The status of the player
     * @return true when the player is dead.otherwise false
     */
    public boolean playerDeath() {
        return playerDie;
    }

    /**
     *  Game score of the player
     * @return  return game score of the player
     */
    public float getGameScore() {
        return gameScore;
    }


    /**
     * make the player jumps if the output of the neural network is bigger than 0.8.
     * @param catcuses list of obstacles
     */
    public void think(LinkedList<Catcus> catcuses){
        network.process(catcuses, this);
        if(network.performAction()) {
            jumpForce();
        }
    }

    /**
     * Check the collision between the player and obstacles.
     * @param catcuses list of obstacles.
     */
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


    /**
     * mutation function of the genetic algorithm
     * @param rate rate of mutation
     */
    public void mutation(double rate){//chance that any vector in directions gets changed
        network.mutation(rate);
    }


    /**
     * Check when should the player jumps
     * @return true when player can jump, otherwise false
     */
    private boolean jump(){
        return (y == 0 && velocity > 0)||y>0;
    }

    /**
     * The jumping of the agent.
     */
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


    /**
     * Apply jump force when agent is on the ground.
     */
    public void jumpForce(){
        if( onGround()){
            velocity = 11.2f;
        }
    }

    /**
     *
     * @return true when agent is on the ground
     */
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
