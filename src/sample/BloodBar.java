package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BloodBar {
    double scale = 1;
    private double width;
    private double height;
    public Pane layer = new Pane();
    private Image maxBloodBar = new Image("file:src/sample/AssetsKit_1/shapes/241.png");
    private Image bloodBar = new Image("file:src/sample/AssetsKit_1/shapes/243.png");
    protected ImageView imageViewMaxBloodBar = new ImageView(maxBloodBar);
    protected ImageView imageViewBloodBar = new ImageView(bloodBar);
    public BloodBar(Pane layer,double width,double height) {
        this.width = width;
        this.height =height;
        imageViewMaxBloodBar.setFitWidth(width);
        imageViewMaxBloodBar.setFitHeight(height);
        imageViewBloodBar.setFitWidth(width);
        imageViewBloodBar.setFitHeight(height);
        this.layer.getChildren().addAll(imageViewMaxBloodBar,imageViewBloodBar);
        layer.getChildren().add(this.layer);
    }
    public void setBloodBarWidth() {
        imageViewBloodBar.setFitWidth(width * scale);
        imageViewBloodBar.setFitHeight(this.height);
    }

    public void setScale(double blood, double maxBlood ) {
        scale = blood/ maxBlood;
    }
    public  void update(double x,double y) {
        this.imageViewMaxBloodBar.relocate(x,y);
        this.imageViewBloodBar.relocate(x,y);

    }
    public void removeImageView() {
        this.layer.getChildren().removeAll(imageViewMaxBloodBar,imageViewBloodBar);
    }

}
