package sample.GameTile.Path;

import javafx.geometry.Point2D;
import sample.GameTile.GameTile;
import sample.Setting;

public class CellRoad extends GameTile {
    public double radDirection;

    public CellRoad(double x, double y) {
        this.pos = new Point2D(x,y);
        this.width = Setting.CELLROAD_HALFWIDTH * 2;
        this.height = Setting.CELLROAD_HALFHEIGHT * 2;
    }

    public Point2D getPos() {
        return pos;
    }

}
