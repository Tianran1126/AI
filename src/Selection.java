import processing.core.PApplet;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Selection {


    /**
     *  Implementation of RWS.
     * @param fitnessSum Sum of all agent's score
     * @param window The window of the game
     * @param players List of players
     * @return The selected agent
     */
    public Player rws(float fitnessSum, PApplet window,Player[]players){
        int index=1;
        float weights[]=new float[players.length];
        for(int i=0;i< players.length;i++){
            weights[i]=players[i].getGameScore()/fitnessSum;
        }
        float p= (float) Math.random();//return a number betwenn 0 and 1
        float  output[]=cumSum(weights);

        for(int i=0;i<output.length;i++) {
            if (output[i] > p) {
                   index= i;
                   break;
            }
        }
        return players[index];
    }
    private float[] cumSum(float[] input) {
        float[] output = new float[input.length];
        float total = 0;
        for (int i = 0; i < input.length; i++) {
            total += input[i];
            output[i] = total;
        }
        return output;
    }


    /**
     * Implementation of tournament selection
     * @param players list of players
     * @return The selected agent
     */
    public Player tournament(Player[]players){
        Random random=new Random();
        int a=random.nextInt(players.length);
        int b=random.nextInt(players.length);
        while (a==b){
            a=random.nextInt(players.length);
        }

        if(players[a].getGameScore()>players[b].getGameScore()){
            return players[a];
        }
            return players[b];
    }

    /**
     *  Implementation of TRWS.
     * @param fitnessSum Sum of all agent's score
     * @param window The window of the game
     * @param players List of players
     * @return The selected agent
     */
    public Player TRWS(float fitnessSum, PApplet window, Player[]players){
        Random random=new Random();
        if(random.nextBoolean()){
          return  tournament(players);
        }
        return rws(fitnessSum,window,players);
    }


    public Player trunctuation(Player[]players,float value){
        Arrays.sort(players,new Sortbyscore());
        int min=(int)(players.length*value);
        int randomNum = ThreadLocalRandom.current().nextInt(min-1, players.length);
        return players[randomNum];
    }

}
