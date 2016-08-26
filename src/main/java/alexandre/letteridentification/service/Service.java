/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.letteridentification.service;

import alexandre.letteridentification.dao.JpaUtil;
import alexandre.letteridentification.dao.WeightsDao;
import alexandre.letteridentification.model.Weights;
import alexandre.letteridentification.util.NeuralNetwork;
import alexandre.letteridentification.util.Neuron;
import alexandre.letteridentification.util.Synapse;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author Alexandre
 */
public class Service
{
    public NeuralNetwork createNetwork(Character letter) throws Throwable
    {
        NeuralNetwork net = new NeuralNetwork();
        
        Weights w = findWeightsByLetter(letter);
        
        if (w != null)
        {
            List<Neuron> hiddenNeurons = net.getHiddenLayer();
            
            for (int i = 0; i < NeuralNetwork.NB_HIDDEN; i++)
            {
                List<Synapse> in = hiddenNeurons.get(i).getAllInConnections();
                
                for (int j = 0; j < in.size(); j++)
                {
                    in.get(j).setWeight(w.getWeigths()[i * in.size() + j]);
                }
            }
            
            List<Neuron> outputNeurons = net.getOutputLayer();
            
            for (int i = 0; i < NeuralNetwork.NB_OUTPUT; i++)
            {
                List<Synapse> in = hiddenNeurons.get(i).getAllInConnections();
                
                for (int j = 0; j < in.size(); j++)
                {
                    in.get(j).setWeight(w.getWeigths()[NeuralNetwork.NB_INPUT * NeuralNetwork.NB_HIDDEN + i * in.size() + j]);
                }
            }
        }
        
        return net;
    }
    
    public Weights findWeightsByLetter(Character letter) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        WeightsDao dao = new WeightsDao();
        
        List<Weights> weights = dao.findAll();
        
        for (Weights w : weights)
        {
            if (w.getLetter().equals(letter))
            {
                JpaUtil.fermerEntityManager();
                
                return w;
            }
        }
        
        JpaUtil.fermerEntityManager();
        
        return null;
    }
    
    public Double[] testNetwork(Character letter, Double[] input) throws Throwable
    {
        NeuralNetwork net = createNetwork(letter);
        net.test(input);
        return net.getOutput();
    }
    
    public Double[] createInputFromImage(BufferedImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        
        Double[] res = new Double[width * height];
        
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                res[i * height + j] = Double.valueOf(image.getRGB(i, j));
            }
        }
        
        return res;
    }
    
    public Weights createWeights(String id, Character letter, Double[] weights) throws Throwable
    {
        Weights w = new Weights(id, letter, weights);
        
        JpaUtil.creerEntityManager();
        
        WeightsDao dao = new WeightsDao();
        
        JpaUtil.ouvrirTransaction();
        
        dao.create(w);
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return w;
    }
    
    public Weights updateWeights(Character letter, Double[] newWeights) throws Throwable
    {
        JpaUtil.creerEntityManager();
        
        WeightsDao dao = new WeightsDao();
        
        Weights w = findWeightsByLetter(letter);
        
        JpaUtil.ouvrirTransaction();
        
        w = dao.update(w);
        
        JpaUtil.validerTransaction();
        
        JpaUtil.fermerEntityManager();
        
        return w;
    }
    
    public void trainNetwork(Character letter, Double[] input) throws Throwable
    {
        if (findWeightsByLetter(letter) != null)
        {
            
        }
    }
}
