/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.letteridentification.view;

import alexandre.letteridentification.service.Service;
import alexandre.letteridentification.util.NeuralNetwork;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Alexandre
 */
public class Main {
    
    public static String path = "C:\\Users\\alexa\\OneDrive\\Documents\\NetBeansProjects\\LetterIdentificationServer\\src\\main\\webapp\\Training";
    
    public static void main(String[] args) throws Throwable
    {
        Service serv = new Service();
        
        for (int l = 65; l <= 90; l++)
        {
            Character letter = (char)l;
            
            System.out.println(letter);
            
            NeuralNetwork net = serv.createNetwork(letter);

            for (int i = 0; i < 1000; i++)
            {            
                double n = Math.random();

                if (n >= 0.5)
                {
                    int nb_files = new File(path + "\\" + letter).list().length;
                    
                    int index = (int)(nb_files * Math.random() + 1);

                    BufferedImage im = ImageIO.read(new File(path + "\\" + letter + "\\" + index + ".png"));

                    Double[] in = serv.getCenteredImage(im);

                    Double[] target = {1.};

                    net.train(in, target);
                }
                else
                {
                    boolean ok = false;
                    Character let = null;
                    
                    while (!ok)
                    {
                        let = (char)((int)(65 + 26 * Math.random()));
                        
                        if (!let.equals(letter))
                        {
                            ok = true;
                        }
                    }
                    
                    int nb_files = new File(path + "\\" + let).list().length;

                    int index = (int)(nb_files * Math.random() + 1);

                    BufferedImage im = ImageIO.read(new File(path + "\\" + let + "\\" + index + ".png"));

                    Double[] in = serv.getCenteredImage(im);

                    Double[] target = {0.};

                    net.train(in, target);
                }
            }
            
            serv.saveWeights(net, letter);
        }
        
        /*
        NeuralNetwork net = serv.createNetwork('A');
        
        BufferedImage imA = ImageIO.read(new File("C:\\Users\\alexa\\OneDrive\\Documents\\NetBeansProjects\\LetterIdentificationServer\\src\\main\\webapp\\Training\\Z\\5.png"));
        
        Double[] inA = serv.getCenteredImage(imA);
        
        for (int i = 0; i < Math.sqrt(NeuralNetwork.NB_INPUT); i++)
        {
            for (int j = 0; j < Math.sqrt(NeuralNetwork.NB_INPUT); j++)
            {
                if (inA[(int)(i * Math.sqrt(NeuralNetwork.NB_INPUT) + j)].equals(1.))
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        
        System.out.println(serv.testNetwork('A', inA));
        */
    }
}

