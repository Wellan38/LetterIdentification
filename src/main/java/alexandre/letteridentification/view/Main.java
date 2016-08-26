/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.letteridentification.view;

import alexandre.letteridentification.service.Service;
import alexandre.letteridentification.util.NeuralNetwork;

/**
 *
 * @author Alexandre
 */
public class Main {
    public static void main(String[] args) throws Throwable
    {
        Service serv = new Service();
        
        Double[] w = {1.,2.,3.,4.,5.,6.,7.,8.,9.};
        
        serv.updateWeights('a', w);
        
        NeuralNetwork net = serv.createNetwork('a');
        
        System.out.println(net.getHiddenLayer().get(1).getAllInConnections().get(1).getWeight());
    }
}
