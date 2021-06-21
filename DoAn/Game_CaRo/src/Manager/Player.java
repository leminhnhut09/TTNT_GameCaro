/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 *
 * @author Black Dung
 */
public class Player {
    private String name;
    private ImageIcon markImage;
    private ImageIcon markImageavt;
    
    public Player() {
    }

    public Player(String name, ImageIcon markImage, ImageIcon markImageavt) {
        this.name = name;
        this.markImage = markImage;
        this.markImageavt = markImageavt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon getMarkImage() {
        return markImage;
    }

    public void setMarkImage(ImageIcon markImage) {
        this.markImage = markImage;
    }

    public ImageIcon getMarkImageavt() {
        return markImageavt;
    }

    public void setMarkImageavt(ImageIcon markImageavt) {
        this.markImageavt = markImageavt;
    }

    
    
}
