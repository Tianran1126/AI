public class OutputNeuron {

    private InputNeuron[] inputNeurons;
    private double[] linkweights;

    /**
     * create weight for each node
     * @param node node
     */
    public void setInput(InputNeuron[] node){
        inputNeurons = node;
        linkweights = new double[inputNeurons.length];
    }

    /**
     * Calculate the output of the neural network
     * @return Output of the neural network
     */
    public double calculateOutput(){
        double sum = 0;
        for(int j = 0; j < inputNeurons.length; j++){
            sum += inputNeurons[j].calculateOutput() * linkweights[j];
        }
        if(MainLoop.activation==1){
            return Tools.sigmoid(sum);
        }
          return  Math.tanh(sum);
    }

    public double[] getLinkweights() {
        return linkweights;
    }

    public void setLinkweights(double[] linkweights) {
        this.linkweights = linkweights;
    }
}
