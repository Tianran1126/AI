import java.util.Random;

public class Tools {

    private static Random random=new Random();

    /**
     * Implementation of the sigmoid activation function
     * @param number input to the activation function
     * @return return the output
     */
    public static  double sigmoid(double number){
        //using serial expansion
        return (1 / (1 + Math.exp(-number)));
    }

    /**
     * Generate random weight and bias
     * @return  return random weight and bias
     */
    public static double GenerateRandom(){

        if(random.nextBoolean())
            return random.nextDouble()*random.nextInt(100);
        else
            return -random.nextDouble()*random.nextInt(100);
    }

}
