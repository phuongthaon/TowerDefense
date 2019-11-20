package sample;

import javafx.geometry.Point2D;

public class Calculate {
    public double angle(Point2D A,Point2D B) {
        double dX = A.subtract(B).getX();
        double dY = A.subtract(B).getY();
        if(dX == 0 ) {
            if(dY == 0) return  0;
            else if(dY < 0) return Math.PI /2;
            return - Math.PI / 2;
        } else if( dX < 0) {
            return Math.atan( dY / dX);
        } else {
            return dY<0 ? Math.atan(dY / dX) + Math.PI : Math.atan(dY /dX) - Math.PI;
        }
    }
}
