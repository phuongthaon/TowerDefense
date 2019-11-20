package sample;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class TowerBtn {
    StackPane layer = new StackPane();
    Image imageBottom;
    Image imageRotate;
    ImageView imageViewRotate;
    ImageView imageViewBottom ;
    Point2D pos;
    int index ;
    Button btn = new Button();

    public TowerBtn(){}

    public TowerBtn(Pane layer,Image imageBottom,Image imageRotate,Point2D pos,int index) {

        this.pos = pos;
        this.layer.setLayoutX(this.pos.getX());
        this.layer.setLayoutY(this.pos.getY());
        this.layer.setPrefHeight(50);
        this.layer.setPrefWidth(50);
        this.imageRotate = imageRotate;
        this.imageViewRotate = new ImageView(imageRotate);
        this.imageBottom = imageBottom;
        imageViewBottom = new ImageView(imageBottom);
        this.layer.getChildren().addAll(imageViewRotate,imageViewBottom);
        this.index = index;
        this.btn.setGraphic(this.layer);
        this.btn.setPrefSize(80,50);
        layer.getChildren().add(this.btn);
        this.btn.setLayoutX(this.pos.getX());
        this.btn.setLayoutY(this.pos.getY());

    }
}
