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

    public void move(float vel){
        positionX -= vel;
    }

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

    private boolean collision(float playerX,float playerY,float width,float height,float posY){
       return  playerX> positionX + catusSize.x|| playerX+width< positionX || playerY>posY+ catusSize.y||playerY+height<posY;
    }



}
