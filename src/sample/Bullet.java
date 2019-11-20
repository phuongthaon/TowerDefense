package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sample.Enemy.Enemy;

public class Bullet extends GameEntity  {
    public double damage ;
    public double range;
    public double speed;
    protected double s;
    protected double angle;


    public Bullet(Pane layer, Image image, double x, double y, double rad, double damage, double range, double speed, Enemy e) {
        super(layer,image,x,y,rad,10,10);
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.movable = true;
        setDirection(e);
        this.width = 10;
        this.height = 10;
        this.imageView.setFitWidth(10);
        this.imageView.setFitHeight(10);
        this.s = 0;
    }

    public void setDirection(Enemy e) {

        Calculate cal = new Calculate();
        this.angle = cal.angle(this.getCenter(),e.getCenter());
    }

    public void move() {
        if(this.range <= this.s || this.pos.getX() -10 < 0 ||this.pos.getX()  > 660 || this.pos.getY() -10 < 0|| this.pos.getY() >660) {
            removable = true;
            return;
        }
        this.pos = new Point2D(this.pos.getX() + speed * Math.cos(angle) , this.pos.getY() + speed * Math.sin(angle));
        this.s += this.speed;

    }

}
