import java.util.LinkedList;

public class Network {
    private OutputNeuron outputLayer = new OutputNeuron();
    private InputNeuron[] inputLayer = new InputNeuron[6];
    private NetworkLogic networkLogic=new NetworkLogic();

    public Network(){
     networkLogic.startNetwork(inputLayer,outputLayer);
    }
    public Network(double[]linkweights,double bias){
        networkLogic.startNetwork(inputLayer,outputLayer);
      setLinkweights(linkweights);
      setBias(bias);
    }
    private double bias;
    public double getBias() {
        return bias;
    }

    public OutputNeuron getOutputLayer() {
        return outputLayer;
    }

    public double[] getLinkweights(){
       return  outputLayer.getLinkweights();
    }

    public void setLinkweights(double[] linkweights) {
        outputLayer.setLinkweights(linkweights);
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    /**
     * Generate weight bias when a player is created
     */
    public void GenerateWeight(){
        networkLogic.GenerateWeight(outputLayer);
        bias = Tools.GenerateRandom();
    }

    /**
     * Input layer process the information
     * @param catcuses list of obstacles
     * @param dinosaur the player
     */
    public void process(LinkedList<Catcus> catcuses, Player dinosaur){
       networkLogic.process(catcuses,dinosaur,bias,inputLayer);
    }

    public boolean performAction(){
        return outputLayer.calculateOutput() >= 0.8;
    }

    public Network clone(){
   return networkLogic.cloneNetwork(outputLayer,bias);
    }

    /**
     * Implementation of mutation
     * @param mutationRate mutation rate
     */
    public void mutation(double mutationRate){
        networkLogic.mutation(outputLayer,mutationRate);
        if (MainLoop.window.random(1) < mutationRate) {
            bias = Tools.GenerateRandom();
        }

    }

}
