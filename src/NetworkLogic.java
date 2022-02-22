import java.util.LinkedList;

public class NetworkLogic {

    public void GenerateWeight(OutputNeuron outputLayer ){

        for(int i = 0; i < outputLayer.getLinkweights().length; i++) {
            outputLayer.getLinkweights()[i] = Tools.GenerateRandom();
        }
    }

    public void startNetwork(InputNeuron[] inputLayer, OutputNeuron outputLayer){
        for(int i = 0; i < inputLayer.length; i++){
            inputLayer[i] = new InputNeuron();
        }

        outputLayer.setInput(inputLayer);
    }

    public void process(LinkedList<Catcus> catcuses, Player dinosaur, double bias, InputNeuron[] inputLayer){
            inputLayer[0].setInput(catcuses.get(0).getPosition().x);
            inputLayer[1].setInput(catcuses.get(0).getCatusSize().x );
            inputLayer[2].setInput(catcuses.get(0).getCatusSize().y);
            inputLayer[3].setInput(GA.speed);
           inputLayer[4].setInput(dinosaur.dinoPosition().y);
           inputLayer[5].setInput(bias);
    }

    public Network cloneNetwork(OutputNeuron outputLayer, double bias){
        Network network = new Network();


        for(int j = 0; j < network.getOutputLayer().getLinkweights().length; j++){
            network.getOutputLayer().getLinkweights()[j] = outputLayer.getLinkweights()[j];
        }
        network.setBias(bias);
        return network;
    }

    public void mutation(OutputNeuron outputLayer, double mutationRate){
        for (int i = 0; i < outputLayer.getLinkweights().length; i++) {
            if (MainLoop.window.random(1) < mutationRate) {
                outputLayer.getLinkweights()[i] = Tools.GenerateRandom();
            }
        }




    }


}
