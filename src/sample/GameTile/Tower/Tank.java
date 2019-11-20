package sample.GameTile.Tower;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Tank extends Tower {

    public Tank(Pane layer,double x,double y) {
        super(400,layer, new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_191/1.png"), x, y, 400, 80, 20, Math.PI / 2, new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_138/6.png"),new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_185/1.png"),42,42,33,51,"file:src/sample/AssetsKit_1/sprites/DefineSprite_163/");
        this.spritesImageAddress = "file:src/sample/AssetsKit_1/sprites/DefineSprite_191/";
    }
}
