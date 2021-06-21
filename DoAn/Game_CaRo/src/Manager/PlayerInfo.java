/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.awt.Point;

/**
 *
 * @author Black Dung
 */
public class PlayerInfo {

    private Point point;
    private int curentPlayer;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getCurentPlayer() {
        return curentPlayer;
    }

    public void setCurentPlayer(int curentPlayer) {
        this.curentPlayer = curentPlayer;
    }

    public PlayerInfo(Point point, int curentPlayer) {
        this.point = point;
        this.curentPlayer = curentPlayer;
    }

}
