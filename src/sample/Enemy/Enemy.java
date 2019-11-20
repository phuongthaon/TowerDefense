package sample.Enemy;

import javafx.geometry.Point2D;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sample.*;
import sample.GameTile.Path.CellRoad;
import sample.GameTile.Path.Path;


public class Enemy extends GameEntity {
    public Path path = new Path();
    public CellRoad nextCell = new CellRoad(0,0);
    public  CellRoad destination = new CellRoad(660,550);
    public   int nthCell = 0; // cell hien tai trong path enemy di chuyen den
    public  double speed;
    public double armor;
    public double blood;
    public  double maxBlood;
    public BloodBar bloodBar ;
    public String imageAddress;
    int counter = 0;
    public Enemy(Pane layer,double width, double height, double rad0,double speed,double armor, double maxBlood,int Map[][],String imageAddress){
        super(layer,new Image(imageAddress +"0.png"),5 * 110 + 55 - 20, 55-20,-Math.PI/2,width,height);
        this.movable = true;
        this.canRotate = true;
        path = new Path(layer);
        this.speed = speed;
        this.armor = armor;
        this.maxBlood = maxBlood;
        this.blood  = this.maxBlood;
        path.setPath(Map);
        this.rad0 = rad0;
        this.rad = rad0;
        nextCell = path.map.get(1);
        bloodBar= new BloodBar(layer,36,8);
        bloodBar.update(this.pos.getX(),this.pos.getY());
        this.imageAddress = imageAddress;
    }

    public Enemy(){}

    public void getDamagedBy(Bullet bullet) {

        if( this.armor >= bullet.damage){
            this.armor -= bullet.damage;
        } else if(this.armor > 0 && this.armor < bullet.damage){
            this.armor -= bullet.damage;
            this.blood += this.armor;
            this.armor = 0;
        } else {
            this.blood -= bullet.damage;
        }
    }

    public void move() {
        if (this.isAtDestination()) {
            removable = true;
            return;
        }

        run();

        double angle = path.map.get(nthCell).radDirection;
        this.rad = angle + this.rad0;
        if (this.getCenter().distance(nextCell.getCenter()) >= this.speed) {
            this.pos = new Point2D(this.pos.getX() + this.speed * Math.cos(angle), this.pos.getY() + this.speed * Math.sin(angle));

        } else if (this.getCenter().distance(nextCell.getCenter()) < speed) {
            double d = speed - this.getCenter().distance(nextCell.getCenter());
            this.pos = nextCell.getCenter().subtract(20, 20);
            nthCell++;
            if(nthCell + 1 < path.map.size())
                nextCell = path.map.get(nthCell + 1);
            angle = path.map.get(nthCell).radDirection;
            this.pos = new Point2D(this.pos.getX() + d * Math.cos(angle), this.pos.getY() + d * Math.sin(angle));
        }

        if (blood > 0) {
            bloodBar.setScale(blood, maxBlood);
            bloodBar.setBloodBarWidth();
            bloodBar.update(this.pos.getX(), this.pos.getY());
        } else {
            blood = 0;
            this.removable = true;
        }
    }
    public boolean isAtDestination(){
        if(this.nthCell >= this.path.map.size() - 1)
            return true;
        return false;

    }

    public void update() {
        if (Math.abs(Math.cos(this.rad)) == 1)
        this.imageView.setScaleX(Math.cos(this.rad));
        else this.imageView.setScaleX(1);
        System.out.println(this.rad / Math.PI);
        this.imageView.relocate(this.pos.getX(),this.pos.getY());
    }

    public void run() {
        this.image = new Image(this.imageAddress + String.valueOf(counter % 5)+".png");
        counter = (counter + 1) % 10;
        this.imageView.setImage(image);
    }

    public void removeImageView() {
        super.removeImageView();
        this.bloodBar.removeImageView();
    }

}
