import processing.core.PVector;

import java.util.Random;

public class Catcus {
    private PVector catusSize;
    private float positionX;


    private  PVector[] catcues = new PVector[]{
            new PVector(40,40),
            new PVector(100,40),
            new PVector(40,80),
    };

    /**
     * Generate a  random obstacle
     */
    public Catcus(){
        Random random=new Random();
        int number = random.nextInt(3);
        catusSize = catcues[number];
        positionX = MainLoop.window.width + catusSize.x;
    }

    public PVector getCatusSize() {
        return catusSize;
    }

    public PVector getPosition(){
        return new PVector(positionX, 0);
    }

    /**
     * move the obstacle to the left to attack player
     * @param vel velocity of obstacle
     */
    public void move(float vel){
        positionX -= vel;
    }

    /**
     * check the collision between the player and obstacle
     * @param player the player
     * @return return true when there is  collision , otherwise false
     */
    public boolean checkCollision(Player player){
        float playerX = player.dinoPosition().x;
        float playerY = player.dinoPosition().y;
        float width=player.dinoSize().x;
        float height=player.dinoSize().y;
        if(collision(playerX,playerY,width,height,0)){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Algorithm of the collision
     * @param playerX x pos of the player
     * @param playerY y pos of the player
     * @param width width of the player
     * @param height height of the player
     * @param posY the height of the ground
     * @return return true when there is no collision , otherwise false
     */
    private boolean collision(float playerX,float playerY,float width,float height,float posY){
       return  playerX> positionX + catusSize.x|| playerX+width< positionX || playerY>posY+ catusSize.y||playerY+height<posY;
    }



}
