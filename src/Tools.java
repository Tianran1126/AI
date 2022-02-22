import java.util.Random;

public class Tools {

    private static Random random=new Random();
    public static  double sigmoid(double number){
        //using serial expansion
        return (1 / (1 + Math.exp(-number)));
    }

    public static double GenerateRandom(){

        if(random.nextBoolean())
            return random.nextDouble()*random.nextInt(100);
        else
            return -random.nextDouble()*random.nextInt(100);
    }

}
