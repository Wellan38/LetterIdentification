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
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author alexa
 */
public class Test {
    public static void main(String[] args) throws IOException
    {
        Service serv = new Service();
        
        BufferedImage im = ImageIO.read(new File("C:\\Users\\alexa\\Desktop\\Training\\G\\6.png"));
        
        Double[] input = serv.getCenteredImage(im);
        
        for (int i = 0; i < Math.sqrt(NeuralNetwork.NB_INPUT); i++)
        {
            for (int j = 0; j < Math.sqrt(NeuralNetwork.NB_INPUT); j++)
            {
                System.out.print((int)(double)input[i * (int)Math.sqrt(NeuralNetwork.NB_INPUT) + j]);
            }
            System.out.println();
        }
    }
}
