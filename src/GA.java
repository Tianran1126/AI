import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class GA {
    private final float groundHeight = 60f;
    private final float groundSpeed = 9;
    private int generations = 1;
    private int population = 0;
    float max = 0;
    private Gamelogic gamelogic;
    private Selection selection;
    private PApplet window = MainLoop.window;
    public Player[] players;
    private LinkedList<Catcus> catcuses = new LinkedList<>();
    private ArrayList<Float> storemax=new ArrayList<>();
    private float  average=0;
    public static float speed = 0f;
    private int type = 0;
    private int elitism=0;

    public float getGroundHeight() {
        return groundHeight;
    }

    public GA(int population, int type,int elitism) {
        this.population = population;
        this.type = type;
        this.elitism=elitism;
        gamelogic = new Gamelogic();
        selection = new Selection();
        initialPopulation(population);
    }


    public void initialPopulation(int population) {
        this.population = population;
        players = new Player[population];
        for (int i = 0; i < population; i++) {
            players[i] = new Player();
        }
    }

    public void showObjects() {
        max = gamelogic.showObjects(players, catcuses, groundHeight, window);
        window.fill(0);
        window.textSize(20);
        window.text("Score: " + max, MainLoop.window.width - 200, 30);
        window.text("Generations: " + generations, MainLoop.window.width - 200, 60);
    }

    public void update() {


        speed += 0.002;

        gamelogic.gameUpdate(catcuses, speed, groundSpeed, players);
    }


    public boolean GameOver() {
        for (Player player : players) {
            if (!player.playerDeath()) {
                return false;
            }

        }
        return true;
    }

    private float findMax() {
        float max = 0;
        for (Player player : players) {
            if (player.getGameScore() > max) {
                max = player.getGameScore();
            }
        }
        return max;
    }

    private int elitism(Player[] newDino) {
        int number = 0;
        for (Player player : players) {
            if (player.getGameScore() == max) {
                newDino[number++] = player.clone();
            }
        }
        return number;
    }

    private float calculateFitnessSum(){
        float fitnessSum=0;
        for (Player player : players) {
          fitnessSum+=player.getGameScore();
        }
        return fitnessSum;
    }

    public void selection() {
        float fitnessSum=calculateFitnessSum();
        Player[] newDino = new Player[players.length];//next gen
        max = findMax();
        storemax.add(max);
        int number =0;

        if(elitism==1) {
             number = elitism(newDino);
        }

        while (number < population) {
            if (type == 1) {
                newDino[number++] = selection.rws(fitnessSum, window, players).clone();
            } else if (type == 2) {
                newDino[number++] = selection.tournament(players).clone();
            } else  if(type==3){
                newDino[number++] = selection.trunctuation(players,0.9f).clone();
            }
            else{
                newDino[number++] = selection.TRWS(fitnessSum,window,players).clone();
            }
        }
        players = newDino;



        System.out.println(fitnessSum/population);

     if (generations==40){
         Collections.sort(storemax);
         System.out.println("Best");
         for(float m:storemax){
             System.out.println(m);
         }
         System.out.println("finish");

        }
    }


    public void mutation(double rate) {
        for (Player player : players) {
            player.mutation(rate);
        }
        generations++;
        catcuses.clear();
        speed = 0;
        max = 0;
    }
}
