package sample.GameTile.Tower;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import sample.Bullet;
import sample.Calculate;
import sample.Enemy.Enemy;
import sample.GameEntity;
import sample.GameTile.GameTile;


import java.util.ArrayList;
import java.util.List;

public class Tower extends GameTile {
    public double shootingRange;
    protected double rateFire;
    protected double damage;
    protected Bullet bullet;
    protected double counter = 0.0;
    protected double rad0;
    protected Image bulletImage;
    public double price;
    public List<Bullet> bullets = new ArrayList<>();
    protected ImageView imageViewBottom;
    public StackPane animationLayer = new StackPane();
    protected String spritesImageAddress;

    public Tower(Pane layer,Image image,double x,double y, double rad0){
        super(layer, image, x, y,rad0,50,50);
      //  this.price = price;
    }

    public Tower(){}

    public Tower(Point2D pos) {
        this.pos = pos;
        this.width = 50;
        this.height = 50;
    }


    public Tower(double price,Pane layer, Image image, double x, double y, double shootingRange, double rateFire, double damage,double rad0,Image bulletImage , Image imageBottom,double widthB,double heightB,double width,double height,String spritesImageAddress) {
        this.price = price;
        this.layer = layer;
        this.layer.getChildren().add(animationLayer);
        this.spritesImageAddress =spritesImageAddress;
        this.image = image;
        this.imageView = new ImageView(image);
        this.width =width;
        this.height = height;
        this.pos = new Point2D(x, y);
        this.imageView.setFitWidth(width);
        this.imageView.setFitHeight(height);
        this.shootingRange = shootingRange;
        this.rateFire = rateFire;
        this.damage = damage;
        this.canRotate = true;
        this.rad0 = rad0;
        this.bulletImage = bulletImage;
        this.imageViewBottom = new ImageView(imageBottom);
        this.imageViewBottom.setFitWidth(widthB);
        this.imageViewBottom.setFitHeight(heightB);
        addImageView();
        update();
        animationLayer.setPrefWidth(50);
        animationLayer.setPrefHeight(50);
        animationLayer.setLayoutX(this.pos.getX());
        animationLayer.setLayoutY(this.pos.getY());
        animationLayer.getChildren().addAll(this.imageViewBottom,this.imageView);
    }


    public void addBulletAndShoot(List<Bullet> bullets, Enemy e) {
        this.rad = (new Calculate()).angle(this.getCenter(),e.getCenter()) + rad0;
        image = new Image(spritesImageAddress+ "1.png");
        imageView.setImage(image);
        if(e.getCenter().distance(this.getCenter()) <= shootingRange ) {
            if (this.counter  == 0) {
                bullet = new Bullet(layer, bulletImage, this.getCenterX() , this.getCenterY() , this.rad, this.damage, shootingRange, 10, e);
                bullets.add(bullet);
            }
            image = new Image(spritesImageAddress +String.valueOf((int)(this.counter % 6 + 1)) +".png");
            imageView.setImage(image);
            this.counter = (this.counter + 1) % rateFire;

        }
    }


    public void update() {
        imageViewBottom.relocate(this.pos.getX(),this.pos.getY());
        imageView.relocate(this.pos.getX(),this.pos.getY());
        imageView.setRotate(rad / Math.PI * 180);
    }

}
