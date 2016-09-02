/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alexandre.letteridentification.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Alexandre
 */

@Entity
public class Weights implements Serializable
{
    @Id
    private String id;
    
    private Character letter;
    
    private Double[] weigths;

    public Weights(String id, Character letter, Double[] weigths) {
        this.id = id;
        this.letter = letter;
        this.weigths = weigths;
    }

    public Weights() {
    }

    public String getId() {
        return id;
    }

    public Character getLetter() {
        return letter;
    }

    public Double[] getWeigths() {
        return weigths;
    }

    public void setWeigths(Double[] weigths) {
        this.weigths = weigths;
    }
}
