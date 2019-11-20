package sample.GameTile;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sample.GameEntity;


public class GameTile  extends GameEntity {
        public GameTile(Pane layer, Image image,double x,double y,double rad0,double width,double height){
            super(layer,image,x,y,rad0,width,height);
            this.movable = false;
        }
        public GameTile(){}
}
