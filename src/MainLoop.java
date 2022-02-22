import processing.core.PApplet;

import java.util.Scanner;

public class MainLoop extends PApplet {
    private final int width = 600;
    private final int height = 300;
    private final float FPS = 60;
    private GA GA;
    private double mutation;
    public  static int  activation=0;

    public static PApplet window = new MainLoop();

    @Override
    public void settings() {
        size(width, height);
    }


    @Override
    public void setup(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter number of population");
        int population=scanner.nextInt();
        System.out.println("Enter 1 for Elitism");
        int elitism=scanner.nextInt();
        System.out.println("Enter 1 for rws , 2 for tournament , 3 for  truncation  , 4 for a combination of RWS  and tournament");
        int type=scanner.nextInt();
        System.out.println("Enter 1 for sigmoid or  2 for tanh activation function");
        activation=scanner.nextInt();
        System.out.println("Enter mutation percentage");
        mutation=scanner.nextDouble();
        System.out.println(mutation);
        frameRate(FPS);
        GA = new GA(population,type,elitism);
    }

    @Override
    public void draw(){
        background(255);
        fill(0);
        line(0,height- GA.getGroundHeight() , width, height- GA.getGroundHeight());
        if(GA.GameOver()){
            GA.selection();
            GA.mutation(mutation);
        }else {
            GA.update();
            GA.showObjects();
        }
    }


    public static void run(){
        String[] name = {"AI"};
        PApplet.runSketch(name, window);
    }

    public static void main(String[] args) {
    run();
    }
}
