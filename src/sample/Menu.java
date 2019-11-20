package sample;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import sample.GameTile.Tower.*;

import java.util.ArrayList;
import java.util.List;


public class Menu {
    Pane layer = new Pane();
    List<TowerBtn> towerBtnArrayList = new ArrayList<>();

    ImageView imageViewBackground = new ImageView("file:src/sample/AssetsKit_1/shapes/209.png");
    Button pauseBtn = new Button("PAUSE");
    Text cashes = new Text("CASHES :$");
    BloodBar healthBar = new BloodBar(this.layer,100,15);
    StackPane animationLayer = new StackPane();


    Menu(Pane layer) {
        layer.getChildren().add(this.layer);
        this.layer.setLayoutY(0);
        this.layer.setLayoutX(660);
        imageViewBackground.setFitWidth(240);
        imageViewBackground.setFitHeight(660);
        this.layer.getChildren().add(imageViewBackground);
        towerBtnArrayList.add( new TowerBtn(this.layer,new Image("file:src/sample/AssetsKit_1/shapes/74.png"),new Image("file:src/sample/AssetsKit_1/shapes/73.png"),new Point2D(40,280),0));
        towerBtnArrayList.add( new TowerBtn(this.layer,new Image("file:src/sample/AssetsKit_1/shapes/64.png"),new Image("file:src/sample/AssetsKit_1/shapes/63.png"),new Point2D(140,280),1));
        towerBtnArrayList.add( new TowerBtn(this.layer,new Image("file:src/sample/AssetsKit_1/shapes/69.png"),new Image("file:src/sample/AssetsKit_1/shapes/68.png"),new Point2D(40,370),2));
        towerBtnArrayList.add( new TowerBtn(this.layer,new Image("file:src/sample/AssetsKit_1/shapes/79.png"),new Image("file:src/sample/AssetsKit_1/shapes/78.png"), new Point2D(140,370),3));
        pauseBtn.setPrefWidth(100);
        pauseBtn.setPrefHeight(50);
        pauseBtn.setLayoutX(75);
        pauseBtn.setLayoutY(500);
        healthBar.update(20,40);

        cashes.setLayoutX(10);
        cashes.setLayoutY(160);
        cashes.setStyle("-fx-font-size: 20px;");
        this.layer.getChildren().addAll(pauseBtn,cashes);
        healthBar= new BloodBar(layer,100,15);
        healthBar.update(720,50);
        Text normalPrice = new Texts().NormalPrice(layer);
        Text tankPrice = new Texts().TankPrice(layer);
        Text sniperPrice = new Texts().SniperPrice(layer);
        Text gunPrice = new Texts().GunPrice(layer);

    }



    public Tower addNewTower(int i, Point2D pos, Pane layer) {
        switch (i) {
            case 0:

                animationLayer = new StackPane(new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/73.png")),new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/74.png")));
                return new NormalTower(layer,pos.getX(),pos.getY());
            case 1:
                animationLayer = new StackPane(new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/63.png")),new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/64.png")));
                return new SniperTower(layer,pos.getX(),pos.getY());
            case 2:
                animationLayer= new StackPane(new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/68.png")),new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/69.png")));
                return new MachineGunTower(layer,pos.getX(),pos.getY());
            case 3:
                animationLayer= new StackPane(new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/78.png")),new ImageView(new Image("file:src/sample/AssetsKit_1/shapes/79.png")));
                return new Tank(layer,pos.getX(),pos.getY());
            default:
                return null;

        }

    }


}
