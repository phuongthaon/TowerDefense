package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.scene.image.Image;

public class GameEntity {
    public Image image ;
    public ImageView imageView = new ImageView();
    public Pane layer = new Pane();

    public Point2D pos;
    public double rad = 0;
    public double rad0;

    public double width;
    public double height;

    public boolean removable;
    public boolean movable;
    public boolean canRotate;

    public GameEntity(Pane layer, Image  image, double x, double y, double rad0,double width,double height) {
        this.layer = layer;
        this.image = image;
        this.pos = new Point2D(x,y);
        this.rad0 = rad0;
        this.rad = rad0;
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(40);
        this.imageView.setFitHeight(40);
        this.update();
        this.addImageView();
        this.width = width;
        this.height = height;
    }

    public GameEntity(){}

    public void move(){}

    public void addImageView() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeImageView() {
        this.layer.getChildren().remove(this.imageView);
    }

    public double getCenterX() {
        return this.pos.getX() + this.width * 0.5;
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public double getCenterY() {
        return this.pos.getY() + this.height * 0.5;
    }

    public Point2D getCenter() {
        return new Point2D(this.getCenterX(),this.getCenterY());
    }

    public boolean isRemovable() {
        return removable;
    }


    public void remove() {
        this.removable = true;
    }


    public void update() {
        imageView.relocate(pos.getX(), pos.getY());
        imageView.setRotate(rad / Math.PI * 180);
    }

    public boolean hasCollision(GameEntity other) {
        if(other.pos.getX() + other.width >= this.pos.getX() && other.pos.getX() <= this.pos.getX() + this.width && other.pos.getY() + other.height >= this.pos.getY() && other.pos.getY() <= this.pos.getY() + this.height) {
            return true;
        }
        return false;
    }



}
