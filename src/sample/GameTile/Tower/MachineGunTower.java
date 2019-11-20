package sample.GameTile.Tower;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class MachineGunTower extends Tower {

    public MachineGunTower(Pane layer,double x,double y) {
        super(300,layer, new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_178/1.png"), x, y, 300, 100, 20, Math.PI / 2, new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_138/3.png"),new Image("file:src/sample/AssetsKit_1/sprites/DefineSprite_172/1.png"),42,39,40,50,"file:src/sample/AssetsKit_1/sprites/DefineSprite_178/");
        this.spritesImageAddress =    "file:src/sample/AssetsKit_1/sprites/DefineSprite_178/";
    }
}
